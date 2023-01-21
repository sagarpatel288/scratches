// A class with default property
class Things(private var name: String = "Human Being") {

    // A function with default parameter
    fun feels(canHelp: Boolean = true, canBreath: Boolean = true) {
        println("$name Can help? $canHelp. Can breath? $canBreath")
    }
}

fun main() {

    /**
     * Calling with default values. Things() will use "Human Being" and feels() will use
     * hasEmotions = true, and canBreath = true
     */
    Things().feels()

    /**
     * Passing named arguments.
     * "Kids" will override the default value "Human Being."
     */
    Things("Kids").feels()

    /**
     * Passing named arguments.
     * "Smart Phone" will override the default value "Human Being."
     * The "feels" function call will override the second argument.
     */
    Things("Smart Phone").feels(canBreath = false)
}

main()




