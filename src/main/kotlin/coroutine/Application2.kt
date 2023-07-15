package coroutine

import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import java.lang.Thread.sleep

fun main() {
    runBlocking {
        // test1()
        val a = async {
            test1()
            "a"
        } // 1000 + 10ms
        val b = async {
            test1()
            "a"
        } // 1000 + 10ms
        println("A") // 10 ms
        println("${a.await()}") // 10ms
    } // 1000 + 20ms
    println("B")
}

suspend fun test1() {
    delay(1000) // 1000ms
    println("Hello World!") // 10ms
}

fun main2() {
    test2()
    println("A")
    println("B")
}

fun test2() {
    sleep(1000)
    println("Hello World!")
}
