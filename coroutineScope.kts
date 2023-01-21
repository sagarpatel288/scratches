import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

fun main() {
    CoroutineScope(Dispatchers.IO).launch { 
        val firstApiCall = doSomethingOne()
        println("result of first API call is: $firstApiCall")
        val secondApiCall = doSomethingAgain()
        println("combined result is: ${firstApiCall + secondApiCall}")
    }
}

suspend fun doSomethingOne(): Int {
    delay(1000L)
    return 1
}

suspend fun doSomethingAgain(): Int {
    delay(1000L)
    return 2
}

main()




