class LateInitExample {

    var eager = 1
    lateinit var later: String //cannot be a nullable

    fun someEvent(shouldInitialize: Boolean) {
        if (shouldInitialize) {
            later = "lateInitVar"
            println(" from someEvent fun: ${later.length}")
        }
    }

    init {
        if (::later.isInitialized) {
            println(" from init: ${later.length}")
        }
    }
}

fun main() {
    LateInitExample().someEvent(true)
}

main()

