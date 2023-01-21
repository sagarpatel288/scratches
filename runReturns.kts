data class Person(var firstName: String = "Sagar", var lastName: String = "Patel")

var person: Person? = Person()

//region `run` returns Unit without return statement
var runWithoutReturn = person?.run {
    firstName = "Android"
    lastName = "Developer"
}
println(runWithoutReturn)
//endregion

//region `run` returns the lambda expression with return statement
var runWithReturn = person?.run {
    firstName = "Software"
    lastName = "Engineer"
    return@run "return of run"
}
println(runWithReturn)
//endregion


