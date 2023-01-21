val name = "Sagar Patel"

//region Without With
println(name.length)
println(name[0])
println(name.lowercase())
//endregion

//region Using With Scope Function
with(name) {
    println(length)
    println(get(0))
    println(lowercase())
}
//endregion

val nullable: String? = null

// No null safety check
with(nullable) {
    println(this?.length ?: this!!)
}




