data class Person(var firstName: String = "Sagar", var lastName: String = "Patel")

var person: Person? = Person()

//region `apply` returns the object itself
var apply = person?.apply {
    firstName = "Android"
    lastName = "Developer"
}
//endregion

println(apply)





