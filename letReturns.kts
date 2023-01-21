data class Person(var firstName: String = "Sagar", var lastName: String = "Patel")

var person: Person? = Person()

//region `let` returns Unit without return statement
var letWithoutReturn = person?.let {
    it.firstName = "Android"
    it.lastName = "Developer"
}
println(letWithoutReturn)
//endregion

//region `let` returns the lambda expression with return statement
var letWithReturn = person?.let {
    it.firstName = "Software"
    it.lastName = "Engineer"
    return@let "return of let"
}
println(letWithReturn)
//endregion





