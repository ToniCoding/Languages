# PowerShell includes several data types to work with numbers, string and dates. Here are the most common DTs.
# We can get the data type by getType().NAME method.

# int32: A number without decimal numbers.
$integer = 1234
Write-Host $integer.getType().NAME

# Single: A number with one decimal number.
[float]$float = 1.2
Write-Host $float.getType().NAME

# Double: A number with a lot of decimal numbers.
$double = 1.233245235345345
Write-Host $double.getType().NAME

# int64: A number with a lot of numbers.
[int64]$long = 5453453453453455453
Write-Host $long.GetType().NAME

# Datetime: Used for dates. Format: mm/dd/yyyy.
[datetime]$date = "09/22/2022"
Write-Host $date.GetType().NAME