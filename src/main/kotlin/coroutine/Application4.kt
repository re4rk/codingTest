package coroutine

import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

data class UserInfo(val name: String, val lastName: String, val id: Int)

lateinit var user: UserInfo
lateinit var job: Deferred<Unit>
fun main() {
    runBlocking {
        asyncGetUserInfo(1)
        delay(1000)
        job.await()
        println("User ${user.id} is ${user.name} ${user.lastName}")
    }
}

suspend fun asyncGetUserInfo(id: Int) {
    job = GlobalScope.async {
        delay(1100)
        user = UserInfo("Jason", "Park", id)
    }
}