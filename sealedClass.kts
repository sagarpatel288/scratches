sealed class SealedClassExample {

    data class Success(val result: String) : SealedClassExample()
    data class Failure(val exception: Exception) : SealedClassExample()
    class Person(val id: Int, var name: String) : SealedClassExample()
    object Loading : SealedClassExample()
}

fun main(sealedClassExample: SealedClassExample) = when (sealedClassExample) {
    is SealedClassExample.Success -> {
        println("The success result is: ${sealedClassExample.result}")
    }
    is SealedClassExample.Failure -> {
        println("The exception is: ${sealedClassExample.exception.message}")
    }
    is SealedClassExample.Person -> {
        println("The name of the person is: ${sealedClassExample.name}")
    }
    is SealedClassExample.Loading -> {
        println("Loading...")
    }
    else -> {
        println("Unknown state!")
    }
}

main(SealedClassExample.Success("Happiness!"))












