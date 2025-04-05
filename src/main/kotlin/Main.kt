fun main() {
    println("Welcome to error handling HQ :-)")
    // example 1
//    basicDivider(2, 0)

    // example 2
//    dividerWithStandardException(2, 0)


    try {dividerWithCustomException(2, 0)}
    catch (e: DivisionByZeroException) {
        e.printStackTrace()
        println(e.message)
    }

}

// catch generic exception within the method
// ✅ catches all types of exceptions
// ✅ immediate feedback
// ⛔️ catching generic exceptions can make debugging harder and is considered bad practise
fun divider(a: Int, b: Int): Int {
    try {
        a / b
    } catch (e: Exception) {
        println("basic message: you cannot divide by zero")
        e.printStackTrace() // prints the stack trace to the terminal
    }
    return a / b
}

fun dividerIfElse(a: Int, b: Int): Int {
    return if (b != 0 ) {
        a/b
    } else {
        0 // not sure this is okay
    }
}


//  catch generic exception and throw custom exception within the method
fun dividerWithStandardException(a: Int, b: Int): Int {
    try {
        a / b
    } catch (e: Exception) {
        throw DivisionByZeroException("custom message needed as part of decl")
    }
    return a / b
}

// throw custom exception within the method (catch later, further up the stack)
//  ✅ separation of concerns - it is up to the caller to handle the error
//  ✅ exception thrown before attempting division which prevents a redundant operation
//  ✅ the caller can handle the error where they have more context for handling it appropriately
fun dividerWithCustomException(a: Int, b: Int): Int {
    if (b == 0 || a == 0) {
        throw DivisionByZeroException("this message can be here or you can leave it out")
    }
    return a/b
}



// Example stack trace
//Exception type and message will appear at the top
//2. first stack frame (top most method call)  - where the error occured - this references the line and the method
//3. call chain - everything underneath tells you how you gor to this point - a reference of all the method calls prior to the exception

//java.lang.ArithmeticException: / by zero
//at MainKt.divider(Main.kt:9)
//at MainKt.main(Main.kt:4)
//at MainKt.main(Main.kt)

class DivisionByZeroException(message: String) : ArithmeticException(message)

//"This is the DivisionByZeroException custom message: You cannot divide by zero"
