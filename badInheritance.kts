open class LivingCreature {
    open fun reproduce() {

    }
}

open class Bird : LivingCreature() {
    open fun see() {
        
    }

    open fun fly() {

    }
}

class Human : LivingCreature() {
    var bird: Bird = Bird()

    fun see() {
        bird.see()
    }

    fun learnAndGrow() {

    }
}