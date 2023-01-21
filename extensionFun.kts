data class Person(var firstName: String = "Sagar", var lastName: String = "Patel")

var person: Person? = Person()

fun Person.printPerson() {
    println("First name is: ${this.firstName}; And Last name is: ${this.lastName}.")
}

person?.printPerson()



