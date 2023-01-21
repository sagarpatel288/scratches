class Engine {
    fun start() {
        println("Engine started")
    }
    
    fun burnTheFuel() {
        println("Burning the fuel")
    }
}

class Window {
    fun wipe() {
        println("Wiped the window")
    }
}

class Tyre {
    fun isPunctured() : Boolean {
        println("The tyre is not punctured")
        return false
    }
    
    fun rollAndRun() {
        println("The tyre is rolling and running")
    }
}

class Battery {
    fun isCharged(): Boolean {
        println("The battery is fully charged!")
        return true
    }
}

class Car {
    var engine: Engine = Engine()
    var window: Window = Window()
    var tyre: Tyre = Tyre()
    var battery: Battery = Battery()
    
    fun checkComponentStatus() {
        println("engine: ${engine.start()} window: ${window.wipe()} tyre: ${tyre.isPunctured()}")
    }
}