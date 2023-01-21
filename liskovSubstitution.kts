//region Violation of LSP
open class Rectangle() {
    open var height: Double = 0.0
    var width: Double = 0.0
    fun area() = height * width
    fun printDetails() = println("width: $width height: $height area: ${area()}")
}

class ShapeUsages() {
    fun printShape(shape: Rectangle, width: Double, height: Double) {
        shape.width = width
        shape.height = height
        shape.printDetails()
    }
}

ShapeUsages().printShape(Rectangle(), 4.0, 3.0)

//region Violation of LSP
class Square(): Rectangle() {
    override var height: Double = 0.0
        set(value) {
            field = value
            width = value
        }
}
//endregion

ShapeUsages().printShape(Square(), 4.0, 3.0)
//endregion



class TypeExample(private val mutableListOfRectangle: MutableList<Rectangle>) {
    fun addListOfRectangle(listOfRectangle: Array<Rectangle>) {
        mutableListOfRectangle.addAll(listOfRectangle)
    }
    fun addShape(square: Square) {
        mutableListOfRectangle.add(square)
    }
}

TypeExample(mutableListOf()).addListOfRectangle(arrayOf(Square()))
TypeExample(mutableListOf()).addListOfRectangle(arrayOf(Rectangle()))

