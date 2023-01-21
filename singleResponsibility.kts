class AuthService {
    fun authenticate(email: String, password: String) {
        System.out.println("email: $email password: $password")
    }
}

class ExceptionHandler {
    fun printLog(error: String?) {
        System.out.println("error: $error")
    }
}

class SingleResponsibility(var authService: AuthService, var exceptionHandler: ExceptionHandler) {

    fun authenticate(email: String, password: String) {
        try {
            authService.authenticate(email, password)
        } catch (exception: Exception) {
            exceptionHandler.printLog(exception.message)
        }
    }
}

class ApiService {

    /**
     * 22/02/22 21:18
     * This function [authenticate] has more than one reason to change.
     * It will have to change when we want different [AuthService].
     * It will also have to change when we want different [ExceptionHandler].
     * @author srdpatel
     * @since 1.0
     */
    fun authenticate(email: String, password: String) {
        try {
            AuthService().authenticate(email, password)
        } catch (exception: Exception) {
            ExceptionHandler().printLog(exception.message)
        }
    }
}

//region SRP Violation Example
/**
 * Single Responsibility: When creating a new class
 * Members of a class must belong to a single actor.
 * Only one actor should be able and responsible to request a change in the class.
 * Hence, a class must have only one reason to change - And the reason is: The actor.
 * The actor can do multiple tasks, but the nature (category) of each task must remain the same.
 * For example: An accountant can calculate pay, tax, deduction, and so on.
 * The nature of each task remains the same here. They all talk about salary.
 * We can replace the noun "salary" here with something that we can apply to each task.
 * For example: Cash, fund, account, etc.
 * Similarly, we can group all the members who belong to an identical actor in to a family.
 * For example: Calculate pay, tax, deduction - They all belong to an accountant.
 * Hence, each of these functions fall under the same umbrella as a family of an accountant.
 * To apply the SRP, we can ask the below questions to each member of the class.
 * To determine the actor:
 * Who is interested (responsible) in this member?
 * Who can request a change in this member?
 * To determine the category of a member:
 * What is the nature of the member's task?
 * What category (label) can we apply to the member's task/nature/purpose?
 * The answer of all the above questions must be the same to achieve SRP.
 * Categorize members and ensure that they all belong to a single actor to achieve SRP.
 */
class Employee {

    //region An accountant will need it. A part of payment family.
    // A person is playing a role of an accountant here.
    // If someone is playing a role, we call them, an actor.
    // The accountant family is responsible here to request any change in the logic.
    fun calculatePay() {

    }
    //endregion

    //region An accountant will need it. A part of payment family.
    // The actor here is: An accountant
    // The accountant family is responsible here to request any change in the logic.
    fun calculateTax() {

    }
    //endregion

    //region A database manager will need it. A part of database family.
    // The actor here is: A database manager
    // The database family is responsible here to request any change in the logic.
    fun saveEmployee() {

    }
    //endregion

    //region A database manager will need it. A part of database family.
    // The actor here is: A database manager
    // The database family is responsible here to request any change in the logic.
    fun findEmployeeById() {

    }
    //endregion

    //region A report manager will need it. A part of a reporting family.
    // The actor here is: A report manager
    // The reporting family is responsible here to request any change in the logic.
    fun describeEmployee() {

    }
    //endregion
}
//endregion

//region Simple DIP, improves SRP, but still needs improvement.
interface EmployeeInterface {
    fun calculatePay()
    fun calculateTax()
    fun saveEmployee()
    fun findEmployeeById()
    fun describeEmployee()
}

class EmployeeImplDip(): EmployeeInterface {
    override fun calculatePay() {}
    override fun calculateTax() {}
    override fun saveEmployee() {}
    override fun findEmployeeById() {}
    override fun describeEmployee() {}
}
//endregion

//region Simple Inheritance. Improves SRP but needs to avoid transitive dependencies.
open class BaseEmployeeDatabaseExtraction() {
    fun saveEmployee() {}
    fun findEmployeeById() {}
}

class EmployeeAccountExtraction(): BaseEmployeeDatabaseExtraction() {
    fun calculateBasePay() {}
    fun calculateFinalPay() {}
}

class EmployeeReportExtraction(): BaseEmployeeDatabaseExtraction() {
    fun describeEmployee(){}
    fun getPerformanceReport(){}
}
//endregion

//region Facade Design Pattern to achieve SRP. We need to remove wrapper functions.
interface EmployeeDatabase {
    fun save()
    fun findById()
}

/**
 * The only responsibility of this class is: Database
 * We can change the business logic of this class without interfering with any other class.
 * Changes in this class does not affect classes.
 */
class EmployeeDatabaseImpl(): EmployeeDatabase {
    override fun save() {}
    override fun findById() {}
}

interface EmployeePayment {
    fun basePay()
    fun finalPay()
}

/**
 * The only responsibility of this class is: Payment
 * We can change the business logic of this class without interfering with any other class.
 * Changes in this class does not affect classes.
 */
class EmployeePaymentImpl(): EmployeePayment {
    override fun basePay() {}
    override fun finalPay() {}
}

interface EmployeeReport {
    fun describe()
    fun performance()
}

/**
 * The only responsibility of this class is: Report
 * We can change the business logic of this class without interfering with any other class.
 * Changes in this class does not affect classes.
 */
class EmployeeReportImpl(): EmployeeReport {
    override fun describe() {}
    override fun performance() {}
}

//region Facade Design Pattern to achieve SRP. We need to remove wrapper functions.
/**
 * Note that all the actors are again in the same class!
 * However, they do not know implementation detail - which is a good thing here.
 * But, any change in the facade class has capability to affect other actors!
 */
class EmployeeFacadeSimple(
        private val database: EmployeeDatabase,
        private val payment: EmployeePayment,
        private val report: EmployeeReport) {

    fun save() {
        database.save()
    }

    fun findById() {
        database.findById()
    }

    fun basePay() {
        payment.basePay()
    }

    fun finalPay() {
        payment.finalPay()
    }

    fun describe() {
        report.describe()
    }
    fun performance() {
        report.performance()
    }
}
//endregion
//endregion

//region Facade + Delegation. Much better.
/**
 * Implementation detail is hidden.
 * On the one hand, all the actors are at one place but we have reduced the fragility.
 * Now, when we introduce a change in an actor's family, it is hard to affect other actors unexpectedly.
 */
class EmployeeFacadeImpl(
        private val database: EmployeeDatabase,
        private val payment: EmployeePayment,
        private val report: EmployeeReport
                        ):
    EmployeeDatabase by database,
    EmployeePayment by payment,
        EmployeeReport by report
{

}
//endregion

//region Extension using Open-Closed Principle
interface WorkTimeActivityTracker {
    fun totalTime()
    fun totalTasks()
}

class WorkTimeActivityTrackerImpl: WorkTimeActivityTracker {
    override fun totalTime() {}
    override fun totalTasks() {}
}

class EmployeeFacadeExtensionUsingOpenClosed(
        private val database: EmployeeDatabase, private val payment: EmployeePayment, private val report: EmployeeReport,
     private val workTimeActivityTracker: WorkTimeActivityTracker
     ) : EmployeeDatabase by database, EmployeePayment by payment, EmployeeReport by report, WorkTimeActivityTracker by workTimeActivityTracker {

}
//endregion



