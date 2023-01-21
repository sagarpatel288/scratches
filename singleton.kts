main()

object SingletonExample {
    fun printHashCode() {
        println("Singleton ${hashCode()}")
        println()
    }
}

class UseSingletonOnce {
    init {
        SingletonExample.printHashCode()
        println("Class one has code: ${hashCode()}")
        println()
    }
}

class UsingSingletonTwice {
    init {
        SingletonExample.printHashCode()
        println("Class two has code: ${hashCode()}")
        println()
    }
}

fun main() {
    UseSingletonOnce()
    UsingSingletonTwice()
    UseSingletonOnce()
    UsingSingletonTwice()
    SingletonExample.printHashCode()
}









