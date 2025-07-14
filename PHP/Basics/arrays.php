<?php
    // An array is a set of data that can be accessed through the use of indexes.
    $array = array("firstValue", "secondValue", "thirdValue");

    // You can print arrays with print_r() like this.
    print_r($array);

    // Arrays are indexed starting from 0.
    print_r("<br/>" . $array[0] . "<br/>"); // Prints the first value.
    print_r($array[1] . "<br/>"); // Prints the second value.
    print_r($array[2]); // Prints the third value.
?>