# Statements help us control the flow of our program depending on certain circunstances.
# Duct tape stock stored in a variable.
$ductTape = 30

# Flow control.
if($ductTape -ge 15 -or $ductTape -gt 15) {
    Write-Host "We still have stock on duct tape."
} elseif($ductTape -eq 0) {
    Write-Host "We ran out of stock on duct tape."
} elseif($ductTape -le 15) {
    Write-Host "We are running out on duct tape."
} else {
    Write-Host "Duct tape stock is unknown."
}

# Flow control with strings.
$testString = "Ducks are cool"
Write-Host "`nTest string: $testString`n"

if($testString -like "*Ducks*") {
    Write-Host "[FOUND] Keyword: 'Ducks'"
}

if($testString -notlike "*not*") {
    Write-Host "[NOT FOUND] Keyword: 'not'"
}

# LEGEND:
# -ge = Greater (or) Equal.
# -gt = Greater Than.
# -eq = Equal (to).
# -le = Less (or) Equal.
# -lt = Less Than.
# -like = Like (used for strings).
# -notlike = Not like (used for strings).