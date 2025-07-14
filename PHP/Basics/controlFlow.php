<?php
    // Control flow is used to determinate a specific way to operate.
    // Given variable $x = 4. If it's equal to 4 return "equal to 4" >> If it's less than 4 return " less than 4" >> If it's greater than 4 return "greater than 4"

    $x = 4;

    if($x == 4) {
        echo "Equal to 4";
    } elseif($x > 4){
        echo "Greater than 4";
    } else {
        echo "Less than 4";
    }

    // Syntax:
    // if(condition) {code} elseif (condition) {code} else {code}

    // With "===" operator compares data type AND value of two variables. 
    $a = "String!";
    $b = "String!";

    if ($a === $b) {
        echo "<br/>A and B have equal values AND data type (both are strings).";
    }

    $numb1 = 1;
    $numb2 = 1;
    $numb3 = 2;
    $numb3 = 3;

    // If $numb1 is less than $numb3.
    if($numb1 < $numb3){
        echo "<br/>1 is less than 2";
    }

    // If $numb3 is greater than $numb1.
    if($numb3 > $numb1){
        echo "<br/>2 is greater than 1";
    }

    // If $numb1 is equal or greater than $numb2.
    if($numb1 <= $numb3){
        echo "<br/>1 is equal to or greater than 1";
    } // It goes the same way for equal or less than.

    // If $numb1 is not equal to $numb3.
    if($numb1 != $numb3){
        echo "<br/>1 is not the same as 2";
    }

    // Operators:
    // == Equal (value)
    // === Equal (value and data type)
    // >= Equal to or greater than
    // <= Equal to or less than
    // != Not equal to
?>