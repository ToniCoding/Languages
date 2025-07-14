/**
    Functions are blocks of code that serve different purposes:
        - Code reuse: Allows using the same logic in different parts of the code without need of repeating the same code several times.
        - Organization and modularity: Helps splitting big programs in smaller and more manageable parts.
        - Abstraction: Hides implementation details allowing developers to focus on higher level logic.
        - Maintenace: If the code needs maintance or updating, functions combined with modulatiry helps reducing the amount of code that is going to be needed to fix a bug, implement or updating a feature.
        - Testing and debugging: It can help testing and debugging in case of errors.

        Syntax: def <functionName>(<functionArguments>) {
            // Function logic...
        }
*/

// Declaration of a basic functions.
def printHello() {
    println("Hello!")
}

// To call a function we write its name followed by parenthesis.
printHello()

// We can declare functions with arguments. Arguments are data that will be passed to a function when it is called.
def addTwoNumbers(a, b) {
    println(a + b)
}

addTwoNumbers(1, 2) // Output: 3

// We can declare functions that return data. The following function will return a boolean value (true).
def returningFunction() {
    return true
}

println(returningFunction())

// We can combine all of the above in order to create more advanced logic.
def processAge(personAge, minimumAge) {
    if(personAge > minimumAge) {
        return false
    } else {
        return true
    }
}

// Here we evaluate the data returned by the function when we call it. At the same time, we pass two intergers as two parameters.
// NOTE: At function declaration the data passed to the function is called "arguments" and when calling the function the data passed is called "parameters".
if(processAge(21, 20)) {
    println("The person is under the minimum age.")
} else {
    println("The person is over or have the same age as the minimum age.")
}