/**
    We can print to the console in order to visualize data. Printing can be formatted to use variables inside of strings and/or function results.
    Syntax: println(<text>)
*/

// We define a variable to print it later.
def testVar = "Stored in a variable"

// We define a function to print the result later.
def sayHi() {
    return "Hi"
}

// Basic print.
println("Hello world!")

// Variable print.
println(testVar)

// Formatted print.
println("The value of the variable testVar is ${testVar}!")

// Returned data function print.
println(sayHi())