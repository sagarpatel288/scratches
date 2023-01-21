data class Person(var firstName: String = "Sagar", var lastName: String = "Patel")

var person: Person? = Person()

//region `also` returns the object itself.
var alsoWithoutReturn = person?.also {
    it.firstName = "Android"
    it.lastName = "Developer"
}
println(alsoWithoutReturn)
//endregion



