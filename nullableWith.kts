var name: String? = "Sagar Patel"

//region Without With
println(name?.length)
println(name?.get(0))
println(name?.lowercase())
//endregion

//region Using With Scope Function
with(name) {
    println(this?.length)
    println(this?.get(0))
    println(this?.lowercase())
}
//endregion

val nullable: String? = null

// No null safety check
with(nullable) {
    println(this?.length ?: this!!)
}




