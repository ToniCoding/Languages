# In PowerShell you can declare variables by using $ followed by the name of the variable.
# For clearer print, a separator is declared in the following variable.
$separator = "================================="

# This variable stores someones age.
# Variable stores: Integer
Write-Host $
$age = 20

# This variable stores someones password.
# Variable stores: String
$passwd = "risingMoon_1234"

# This variable stores someones name and last name with spaces(NOT RECOMMENDED!).
# Variable stores: String
${Full Name} = "Robert Miles"

# This variable stores if it's someone is administrator or not.
# Variable stores: Boolean
$isAdmin = $false
$isAdminUser = $true

# To print the variables just use the Write-Host command followed by the $var.
Write-Host "Integer: $age"
Write-Host "String: $passwd"
Write-Host "Variable name with spaces: ${Full Name}"

# You can update existing variables.
Write-Host "================================="
Write-Host "U p d a t i n g  v a r i a b l e s"
Write-Host "Before update:"
$toUpdate = "Install software.`nInstall hardware.`n"
Write-Host $toUpdate

Write-Host "After update:"
$toUpdate = "Install software."
Write-Host $toUpdate

Write-Host "================================="