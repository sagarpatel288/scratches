var firstName: String? = null
var lastName = "last name scope" //Type inference

firstName?.let {
    println(it.length)
}
println(lastName.length)




