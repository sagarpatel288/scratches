//region Self-made low confidence example
open class ExceptionHandler {

    /**
     * 22/02/22 23:05
     * <p>
     * If we ever require changes in the implementation, instead of changing
     * the origin, we should extend it.
     * </p>
     * The origin should be closed for the modification and
     * should be open for the extension.
     * </p>
     *
     * @author srdpatel
     * @since 1.0.0.
     */
    open fun printLog(error: String?) {
        println("error: $error")
    }
}

class DifferentExceptionHandler : ExceptionHandler() {

    override fun printLog(error: String?) {
        println("A different way of handling the error: $error")
    }
}
//endregion

//region Violation of SRP and Open-Closed Principle
enum class Type {
    DINNER, BREAKFAST, CAR_RENTAL
}

open class Expense(val type: Type, open val amount: Int)

class PrintReport {
    //region Violation of SRP and Open-Closed Principle.
    // This function is open for the modification (!) and closed for the extension (!).
    fun printReport(expenses: List<Expense>) {
        var total = 0 //Includes car rental expense also
        var mealExpenses = 0 //Includes only meal expense

        //region A business logic to print a header.
        println("Header: Expenses")
        //endregion

        //region A business logic to print each expense.
        expenses.forEach {

            //region A business logic to filter and calculate meal expenses.
            if (it.type == Type.BREAKFAST || it.type == Type.DINNER) {
                mealExpenses += it.amount
            }
            //endregion

            //region A business logic to create an expense message title.
            val expenseName = when (it.type) {
                Type.BREAKFAST -> "Breakfast"
                Type.DINNER -> "Dinner"
                Type.CAR_RENTAL -> "Car Rental"
            }
            //endregion

            //region A business logic to prefix "X" for the overage expenses.
            val expenseApproval =
                if ((it.type == Type.DINNER && it.amount > 5000) || (it.type == Type.BREAKFAST && it.amount > 1000)) "X" else " "
            //endregion

            //region A business logic to format and print a message for the individual expense item.
            println(String.format("%s: %s: %d\n", expenseApproval, expenseName, it.amount))
            //endregion

            //region A business logic to calculate the total expense.
            total += it.amount
            //endregion
        }
        //endregion

        //region A business logic to print the meal expenses.
        println(String.format("\nMeal Expenses: %d", mealExpenses))
        //endregion

        //region A business logic to print the total expenses.
        println(String.format("\nTotal Expenses: %d", total))
        //endregion
    }
    //endregion
}

class Report {
    init {
        PrintReport().printReport(
            listOf(
                Expense(Type.BREAKFAST, 500), Expense(Type.DINNER, 5001), Expense(
                    Type.CAR_RENTAL, 100)))
    }
}

//Report()
//endregion

//region A better design
//region Business interface for Business logic
interface ExpenseName {
    fun getExpenseName(expense: BaseExpenseInterface): String
}

class ExpenseNameImpl: ExpenseName {
    override fun getExpenseName(expense: BaseExpenseInterface): String {
        return when (expense) {
            is Breakfast -> "Breakfast"
            is Dinner -> "Dinner"
            is CarRental -> "CarRental"
            is Lunch -> "Lunch"
            else -> "Tilt"
        }
    }
}
//endregion

//region Note that it is not a base class. It is a base interface for the common behavior!
interface BaseExpenseInterface {
    val amount: Int
    fun isMeal(): Boolean
    fun isOverage(): Boolean
}
//endregion

//region Notice how it removed "type" enum!
data class Breakfast(override val amount: Int): BaseExpenseInterface {
    override fun isMeal() = true
    override fun isOverage() = amount > 1000
}

data class Dinner(override val amount: Int): BaseExpenseInterface {
    override fun isMeal() = true
    override fun isOverage() = amount > 5000
}

data class CarRental(override val amount: Int): BaseExpenseInterface {
    override fun isMeal() = false
    override fun isOverage() = amount > 500
}
//endregion

//region Expense inventory/database! Read/write expense data.
interface ExpenseRepo {
    var expenses: MutableList<BaseExpenseInterface>
    fun addExpense(expense: BaseExpenseInterface)
    fun addExpenses(expenses: List<BaseExpenseInterface>)
    fun totalExpenses(): Int
    fun mealExpenses(): Int
}

/**
 * In an actual case, the repo can use abstract communication for a database schema and a network API service—
 * the abstract and separate communication channel for each, using a construction dependency injection.
 * Also, it will not use the value objects directly.
 * So, for example, we might have similar but separate remote objects, value objects, and UI objects.
 */
class ExpenseRepoImpl(): ExpenseRepo {
    //region Consider this repo gets the expenses either from network calls or from the local database.
    override var expenses: MutableList<BaseExpenseInterface>
    = mutableListOf(CarRental(100), Breakfast(500), Lunch(3001), Dinner(900))
    //endregion

    override fun addExpense(expense: BaseExpenseInterface) {
        expenses.add(expense)
    }

    override fun addExpenses(expenses: List<BaseExpenseInterface>) {
        this.expenses.addAll(expenses)
    }

    override fun totalExpenses(): Int {
        var totalExpense = 0
        expenses.forEach {
            totalExpense += it.amount
        }
        return totalExpense
    }

    override fun mealExpenses(): Int {
        var mealExpense = 0
        expenses.filter { it.isMeal() }.forEach {
            mealExpense += it.amount
        }
        return mealExpense
    }
}
//endregion

//region Print expense
interface PrintExpenseReport {
    fun printReport()
    fun printHeader()
    fun printEachExpense(expenses: List<BaseExpenseInterface>)
    fun printIndividualExpense(expense: BaseExpenseInterface)
    fun printTotalByCategory(mealExpenses: Int, totalExpenses: Int)
}

class PrintExpenseReportImpl(
        private val expenseRepo: ExpenseRepo,
        private val expenseName: ExpenseName
                            ): PrintExpenseReport {
    override fun printReport() {
        printHeader()
        printEachExpense(expenseRepo.expenses)
        printTotalByCategory(expenseRepo.mealExpenses(), expenseRepo.totalExpenses())
    }

    override fun printHeader() {
        println("Header: Expenses")
    }

    override fun printEachExpense(expenses: List<BaseExpenseInterface>) {
        expenses.forEach {
            printIndividualExpense(it)
        }
    }

    override fun printIndividualExpense(expense: BaseExpenseInterface) {
        println(
            String.format(
                "%s: %s: %d\n", if (expense.isOverage()) "X" else " ", expenseName.getExpenseName(expense), expense
                    .amount))
    }

    override fun printTotalByCategory(mealExpenses: Int, totalExpenses: Int) {
        println(String.format("\nMeal Expenses: %d", mealExpenses))
        println(String.format("\nTotal Expenses: %d", totalExpenses))
    }
}
//endregion

//region Use expense data.
interface ExpenseFacade {
    fun printExpenseReport()
}

class ExpenseFacadeImpl(
        private val printExpense: PrintExpenseReport): ExpenseFacade {
    override fun printExpenseReport() {
        printExpense.printReport()
    }
}

ExpenseFacadeImpl(PrintExpenseReportImpl(ExpenseRepoImpl(), ExpenseNameImpl())).printExpenseReport()
//endregion

data class Lunch(override var amount: Int): BaseExpenseInterface {
    override fun isMeal() = true
    override fun isOverage() = amount > 3000
}
//endregion


