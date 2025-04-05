fun main() {
    println("Welcome to error handling HQ :-)")
    // EXAMPLE 1
//    divider(2, 0)

    // EXAMPLE 2
//    dividerIfElse(2, 0)

    // EXAMPLE 3
//    try {
//        dividerThatThrowsException(2, 0)
//    } catch (e: Exception) {
//        e.printStackTrace()
//    }

    //EXAMPLE 4
    try {
        dividerThatThrowsCustomException(2, 0)
    } catch (e: DivisionExceptionWithMessage) {
        e.printStackTrace()
    }

}

// 1
// catch generic exception within the method
// ✅ catches all types of exceptions
// ✅ immediate feedback
// ⛔️ catching generic exceptions can make debugging harder and is considered bad practise
fun divider(a: Int, b: Int): Int {
    return try {
        a / b
    } catch (e: Exception) {
        println("you cannot divide by zero")
        e.printStackTrace() // prints the stack trace to the terminal
        0 // return a safe, default value
    }
}

// 2
// no try catch
// ⛔️ we don't catch an exception here, we just avoid it occurring - this could make spotting issues we want to be aware of hard.
fun dividerIfElse(a: Int, b: Int): Int {
    return if (b != 0) {
        a / b
    } else {
        0
    }
}

// 3
//  catch generic exception and throw custom exception within the method
fun dividerThatThrowsException(a: Int, b: Int): Int {
    if (b == 0 || a == 0) {
        throw Exception()
    }
    return a / b
}

// 4
// throw custom exception within the method (catch later, further up the stack)
//  ✅ separation of concerns - it is up to the caller to handle the error
//  ✅ exception thrown before attempting division which prevents a redundant operation
//  ✅ the caller can handle the error where they have more context for handling it appropriately
fun dividerThatThrowsCustomException(a: Int, b: Int): Int {
    if (b == 0 || a == 0) {
        throw DivisionExceptionWithMessage("you cannot divide by zero")
    }
    return a / b
}

class DivisionExceptionWithMessage(message: String) : ArithmeticException(message)


// Example stack trace
//Exception type and message will appear at the top
//2. first stack frame (top most method call)  - where the error occured - this references the line and the method
//3. call chain - everything underneath tells you how you gor to this point - a reference of all the method calls prior to the exception

//java.lang.ArithmeticException: / by zero
//at MainKt.divider(Main.kt:9)
//at MainKt.main(Main.kt:4)
//at MainKt.main(Main.kt)

//"This is the DivisionByZeroException custom message: You cannot divide by zero"
