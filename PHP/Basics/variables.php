<?php
    $stringVar = "Variable de string"; // Data: String
    $integerVar = 33; // Data: Number
    
    echo $stringVar . "<br/>";
    echo $integerVar;

    echo "<br/>--------------------<br/>Variable concat:<br/>" . $stringVar . "<br/>" . $integerVar; // Echo with concat operator

    // Basic arithmetic with variables and echoes
    $a = 4;
    $b = 4;

    // ADD
    echo "<br/>Result of 4 + 4 = " . $a + $b;

    // SUB
    echo "<br/>Result of 4 - 4 = " . $a - $b;

    // MUL
    echo "<br/>Result of 4 x 4 = " . $a * $b;

    // DIV
    echo "<br/>Result of 4 / 4 = " . $a % $b;

    // We can initialize a variable without assigning any value to it.
    $init;

    // And then later, give it a value.
    $init = "Testing";
?>