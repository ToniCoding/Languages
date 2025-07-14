# Loops are a set of instructions/lines of code that will execute X times depending on a condition.

# LEGEND:
# - vInit = Variable initialization.
# - vCond = Variable condition.
# - vInc = Variable incremental.
# - i = Incremental value.

# For loop.
# Example syntax: for(vInit; vCond; vInc){<code>}
function forFunc{
    for($i = 0; $i -lt 5; $i++){
        Write-Host "Incremental value: $i";
    }
}
# While loop.
# Example synxtax: while(condition){<code>}
$j = 0
function whileFunc{
    while($j -lt 5){
        Write-Host "Incremental value: $j";
        $j++
    }
}

# You can break loops anytime by using the keyword "break".
# This while loop will break when x reaches 5.
$x = 0
function whileBreaking{
    while($x -lt 10){
        Write-Host "Incremental value: $x"
        $x++
        if($x -eq 5){
            Write-Host "X reached 5, breaking loop..."
            break
        }
    }
}

# [IGNORE] Main program loop
$isRunning = $true
while($isRunning){
    $choice = Read-Host "Which loop do you want to test? (For, while, bWhile or exit) "
    switch($choice){
        "for" {forFunc}
        "while" {forFunc}
        "bWhile" {whileBreaking}
        "exit" {"Exiting"; $isRunning = $false; break}
        Default {
            "Use one of the options available."
            Clear-Host
        }
    }
    
}