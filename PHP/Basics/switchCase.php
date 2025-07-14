<?php
    // Switch case is a special statement used to avoid nested statements.
    // Reserved word: DEFAULT --- If any of the cases above match, this will be what's going to be displayed.

    $a = 4;

    switch($a){
        case 1:
            echo "$a is equal to 1";
            break;
        case 2:
            echo "$a is equal to 2";
            break;
        case 3:
            echo "$a is equal to 3";
            break;
        default:
            echo "$a is not equal to 1, 2 or 3.";
            break;
    }

    // SYNTAX:
    // switch(<variable>) {
    //      case <result>:
    //          <code>;
    //      default:
    //          <code>;
    // }             
?>