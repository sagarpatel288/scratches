interface Auth {
    fun authenticate(email: String, password: String)
}

class QuickBlox : Auth {
    override fun authenticate(email: String, password: String) {
        println("from AuthService: email: $email password: $password")
    }
}

class Twilio : Auth {
    override fun authenticate(email: String, password: String) {
        println("from FirebaseAuthService: email: $email password: $password")
    }
}

interface BaseException {
    fun printLog(error: String?)
}

class QuickBloxException : BaseException {
    override fun printLog(error: String?) {
        println("ExceptionHandler: error: $error")
    }
}

class TwilioException : BaseException {
    override fun printLog(error: String?) {
        println("CustomExceptionHandler: $error")
    }
}

class SingleResponsibility(
    private val authService: Auth,
    private val exceptionHandler: BaseException
) {
    fun authenticate(email: String, password: String) {
        try {
            authService.authenticate(email, password)
        } catch (exception: Exception) {
            exceptionHandler.printLog(exception.message)
        }
    }
}

class Usage {
    val singleResponsibility = SingleResponsibility(Twilio(), TwilioException())
}

/**
 * Dec/10/22
 * A feature that copies the character from the keyboard and writes it to the printer
 * June/11/23
 * The feature can read either from the keyboard or from the tape reader!
 * Dec/11/23
 * The feature can write through either a printer or a paper tape punch! 
 */
class ReadWrite {
    // Mark it true to read from the tape reader instead of a keyboard  
    var readFromTape = false
    // Mark it true to write to the paper tape punch instead of a printer
    var writeToPaperTapePunch = false
    
    fun copyPaste() {
        var char = if (readFromTape) readFromTapeReader() else readKeyboard()
        while(char != 0) {
            writeToPrinter(char)
        }
    }

    private fun writeToPrinter(char: Int) {
        println(char)
    }

    private fun readKeyboard(): Int {
        return (0..10).random()
    }
    
    private fun readFromTapeReader(): Int {
        return (0..20).random()
    }
    
    private fun writeThroughPaperTapePunch(char: Int) {
        println("From a paper tape punch: $char")
    }
}

/**
 * Dec/10/22
 * A feature that copies the character from the keyboard.
 * June/11/23
 * The feature can read either from the keyboard or from the tape reader!
 */
interface Reader {
    fun getChar(): String
}

/**
 * Dec/10/22
 * A feature that writes characters to the printer
 * Dec/11/23
 * The feature can write through either a printer or a paper tape punch!
 */
interface Writer {
    fun putChar()
}

/**
 * Dec/10/22
 * A feature that reads and writes characters
 * June/11/23
 * A feature that reads and writes characters
 * Dec/11/23
 * A feature that reads and writes characters
 */
class ImprovedReadWrite(private val reader: Reader, private val writer: Writer) {

    fun copyPaste() {
        var char = reader.getChar()
        while (!char.isNullOrEmpty()) {
            writer.putChar()
        }
    }
}



class Keyboard: Reader {
    override fun getChar(): String {
        return "From Keyboard"
    }
}

class PaperTapeReader: Reader {
    override fun getChar(): String {
        return "From A Paper Tape Reader"
    }
}

class Printer: Writer {
    override fun putChar() {
        println("To A Printer")
    }
}

class PaperTapePunch: Writer {
    override fun putChar() {
        println("To A Paper Tape Punch")
    }
}



