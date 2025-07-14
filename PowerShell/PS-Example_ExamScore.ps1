[float]$grade = Read-Host "Grade: "

switch($grade) {
    {$ErrorActionPreference} {"SilentlyContinue"} # Avoid error rise and continues.
    {$grade -lt 5 -and $grade -ge 0} {"Failed";break} 
    {$grade -ge 5 -and $grade -lt 6} {"Passed D";break}
    {$grade -ge 6 -and $grade -lt 7} {"Passed C";break}
    {$grade -ge 7 -and $grade -lt 9} {"Passed B";break}
    {$grade -ge 9 -and $grade -lt 10} {"Passed A";break}
    {$grade -eq 10} {"Passed A+";break}
    default
        {"[ERROR] Value not accepted"}
}