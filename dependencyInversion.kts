//region Compile Time Dependency Example
class A {
    fun printB() {
        //Source code A depends on module/class B
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
interface Print {
    fun print()
}

class DependencyInversion(private val print: Print) {
    fun printImplementation() {
        //The source code (The class) "DependencyInversion" does not care implementation of the interface "Print"
        print.print()
    }
}

//PrintBImpl implements the interface PrintB
class PrintBImpl: Print {
    override fun print() {
        println("Dependency Inversion: PrintBImpl implements Print: B")
    }
}

//PrintAImpl implements the interface PrintB
class PrintAImpl: Print {
    override fun print() {
        println("Dependency Inversion: PrintAImpl implements Print: A")
    }
}

//We decide which implementation to use at the calling (usage) side
DependencyInversion(PrintBImpl()).printImplementation()
DependencyInversion(PrintAImpl()).printImplementation()
//endregion
