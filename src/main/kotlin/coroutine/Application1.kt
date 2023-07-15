package coroutine

import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

suspend fun main() {
    println("before test()")
    var b = 1
    runBlocking {
        val a = measureTimeMillis {
            val vv = async {
                test()
                b = 2
                1
            }
            async { test() }.await()
            delay(10)
            println("after async() : $vv")
        }
        println("after async() : ${a}ms")
    }
    println("after test()")
    println(b)
}

suspend fun test() {
    delay(1000)
    println("Hello World!")
}
