// Defining a function
val funName = { a: Int, b: Int -> a + b }

// Accessing the function
funName.invoke(1, 2)

// A fun that has another fun as a parameter
fun higherOrderFun(a: Int, b: Int, ft: (Int, Int) -> Int): String {
    return "result: ${ft.invoke(a, b)}"
}

/**
 * Passing [funName] as an argument
 */
higherOrderFun(1, 2, funName)

// Passing a lambda expression as an argument
higherOrderFun(1, 2) { a, b ->
    a * b
}

// Passing a lambda expression as an argument
higherOrderFun(1, 2) { c, d ->
    d - c
}


