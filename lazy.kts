class LazyExample {

    private val lazyVal: String by lazy { "lazy initialization" }

    private fun useLazyVal() {
        println(lazyVal)
    }

    init {
        useLazyVal()
    }
}

fun main() {
    LazyExample()
}

main()

