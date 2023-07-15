package coroutine

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.coroutines.coroutineContext

fun main() = runBlocking {
    val qrCode = QRCode()
    val customTimer = CustomTimer()

    customTimer.timerListener = object : CustomTimer.TimerListener {
        override fun onTick() {
            println("onTick")
        }

        override fun onFinish() {
            qrCode.generateQRCode()
            launch {
                customTimer.start(5)
            }
        }
    }

    qrCode.generateQRCode()
    customTimer.start(5)
}

class CustomTimer(
    private val interval: Long = DEFAULT_INTERVAL,
) {
    private lateinit var job: Job

    var timerListener: TimerListener = object : TimerListener {
        override fun onTick() {}
        override fun onFinish() {}
    }

    interface TimerListener {
        fun onTick()
        fun onFinish()
    }

    suspend fun start(seconds: Int = 10) {
        if (::job.isInitialized) job.cancel()

        job = CoroutineScope(coroutineContext).launch {
            val start = System.currentTimeMillis()
            repeat(seconds) {
                val adjustment = System.currentTimeMillis() - start - interval * it
                delay(interval - adjustment)
                timerListener.onTick()
            }
            timerListener.onFinish()
        }
    }

    fun stop() {
        if (::job.isInitialized) job.cancel()
    }

    companion object {
        private const val DEFAULT_INTERVAL = 1000L
    }
}

class QRCode {
    fun generateQRCode() {
        println("QRCode generated")
    }
}
