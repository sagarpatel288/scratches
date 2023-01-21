enum class EnumExampleKotlin(val id: Int) {
    TEACHER(1),
    FIRE_PERSON(2),
    CHEF(3);
}

fun main(enumExampleKotlin: EnumExampleKotlin) {
    when (enumExampleKotlin) {
        EnumExampleKotlin.TEACHER -> {
            println("A Teacher teaches!")
        }
        EnumExampleKotlin.FIRE_PERSON -> {
            println("A fire-person extinguishes fire!")
        }
        EnumExampleKotlin.CHEF -> {
            println("A chef cooks!")
        }
    }
}

main(EnumExampleKotlin.TEACHER)







