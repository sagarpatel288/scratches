open class Person(var name: String = "Human Being") {
    open fun feels() {
        println("$name feels")
    }
}

// The base (child) class initializes the super (parent) class
class Employer(employerName: String = "Someone", companyName: String): Person(employerName) {

    // employerName and companyName are parameters and not properties
    private val ceo = employerName.uppercase()

    //region The init block respects the order of member declaration
    init {
        println("$ceo owns $companyName")
    }
    //endregion

    private val provides = "Employment"

    //region We can have multiple init blocks
    init {
        println("$companyName provides $provides")
    }
    //endregion

    override fun feels() {
        println("$ceo feels good.")
    }
}

// employeeName and companyName are properties
class Employee(var employeeName: String, var companyName: String) : Person(employeeName) {

    fun worksAt() {
        println("$employeeName Work/s at $companyName")
    }
}

fun main() {
    val person = Person("Abc")
    Employer(companyName = "Something")
    val employee = Employee("Abc", "XYZ")
    person.feels()
    employee.worksAt()
}

main()



