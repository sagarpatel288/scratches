//region Compile Time Dependency Example
class A {
    fun printB() {
        val b = B()
        b.printB()
    }
}

class B {
    fun printB() {
        println("B")
    }
}

A().printB()
//endregion

//region Dependency Inversion
interface PrintB {
    fun printB()
}

class DependencyInversion(private val printB: PrintB) {
    fun printB() {
        printB.printB()
    }
}

class PrintBImpl: PrintB {
    override fun printB() {
        println("Dependency Inversion: PrintBImpl implements PrintB")
    }
}
DependencyInversion(PrintBImpl()).printB()
//endregion
