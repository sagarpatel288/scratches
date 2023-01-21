var name: String? = "Sagar Patel"

//region Using Null-Safety Operator
println(name?.length)
println(name?.get(0))
println(name?.lowercase())
//endregion

//region Using `Run` Scope Function
name?.run {
    println(length) //We can opt-out the context reference keyword "this"
    println(get(0))
    println(lowercase())
}
//endregion

val nullable: String? = null

// Null Safety Check Using `Run`
nullable?.run {
    println(length)
}




