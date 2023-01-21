var name: String? = "Sagar Patel"

//region Using Null-Safety Operator
println(name?.length)
println(name?.get(0))
println(name?.lowercase())
//endregion

//region Using Let Scope Function
name?.let {
    name = "changed from let scope"
    println(name)
    println(it)
    println(it.length)
    println(it[0])
    println(it.lowercase())
}
println(name)
//endregion

val nullable: String? = null

// Null Safety Check Using Let
nullable?.let {
    println(it.length)
}




