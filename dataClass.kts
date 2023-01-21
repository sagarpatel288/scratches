data class Person(var firstName: String = "Sagar", var lastName: String = "Patel")

var person = Person() // Default constructor

with(person) {

    println(firstName) //getter - We did not have to define it explicitly

    firstName = "MUJ" //setter - We did not have to define it explicitly

    println(firstName)

    person // The last statement is a return for the "with"

}.let {

    println(it) //toString

    println(it.hashCode()) //hashCode

    val person = Person()

    println(it == person)

}




