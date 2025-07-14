/**
    We can evaluate a condition and execute actions if one or multiple conditions are met. For this purpose, we use comparison and logical operators.

    Comparison operators:
    - == -> Is equal to.
    - === -> Is equal to and it's the same data type as.
    - != -> Not equal to.

    Logical operators:
    - && -> This represents AND operator.
    - || -> This represents OR operator.
    - ! -> This represents NOT opeator.

    Syntax: if(condition) {<actions>} else if(condition) {<actions>} else {<actions>}
*/

// Initialize and assign two variables and we will work with them from now on.
def personGenre = "M";
def personAge = 19;

// Here, we evaluate the condition of the variable "personGenre" being assigned as "M" string.
if(personGenre == "M") {
    println("The person is a man.")
} else {
    println("The person is a woman.")
}

// Here, we evaluate multiple conditions.
if(personAge <= 17 && personAge >= 15) {
    println("The person's age is between 15 and 17.")
} else if(personAge >= 18) {
    println("The person is an adult.")
} else {
    println("The person is underage.")
}