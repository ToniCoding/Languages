<?php
// We can use loops to execute the same set of code several times depending on a condition. There are two types of loops: while and for.
// $i will act as an incremental.

for($i = 0; $i <= 5; $i++) {
    echo "$i" . "<br/>";
}; 
// SYNTAX: for(<variableInit>;<condition>;<incremental>){<code>};

// Separator.
echo "----------------" . "<br/>";

// Restore to default variable.
$i = 0;

while($i <= 5){
    echo "$i" . "<br/>";
    $i++;
};
// SYNTAX: for(<condition>){<code>;<incremental>};

// Loops output: 0 1 2 3 4 5
?>