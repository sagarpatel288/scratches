class AuthService {
    fun authenticate(email: String, password: String) {
        println("email: $email password: $password")
    }
}

class ExceptionHandler {
    fun printLog(error: String?) {
        println("error: $error")
    }
}

class SingleResponsibility(
    var authService: AuthService,
    var exceptionHandler: ExceptionHandler
) {

    fun authenticate(email: String, password: String) {
        try {
            authService.authenticate(email, password)
        } catch (exception: Exception) {
            exceptionHandler.printLog(exception.message)
        }
    }
}



