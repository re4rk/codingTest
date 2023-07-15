package coroutine

import kotlinx.coroutines.Job
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

fun main() {
    runBlocking {
        println("${Thread.activeCount()} threads active at the start")

        val time = measureTimeMillis {
            createCoroutines(3)
        }
        println("${Thread.activeCount()} threads active at the start")
        println("Took $time ms")
    }
}

suspend fun createCoroutines(amount: Int) {
    coroutineScope {
        val jobs = mutableListOf<Job>()
        repeat(amount) {
            jobs += launch {
                println("Started $it in ${Thread.currentThread()}")
                delay(1000)
                println("Finished $it in ${Thread.currentThread().name}")
            }
        }
    }
}

// 1시 부터
// co1 : 2시간동안 쉰데
// co2 : 2시간동안 쉰데
// co1 : 3시부터
// co2 : 3시부터
// 슬립은 : 침대가 하나 있다.
// 1시부터 2시까지 수박
// 2시부터 3시까지 멜론

// delay : 작업
// sleep : 침대 1대
