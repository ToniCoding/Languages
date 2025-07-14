# We can test if a path is already existing or not with a simple cmdlet.
$newDirName = Read-Host "Name of the new directory: "
$newDir = "$home\Documents\$newDireName"

# Checks if the path already exists.
if(Test-Path $newDirName) { # IF TRUE
    Write-Host "Directory already exists!" # DO THIS
} else { # IF FALSE
    Write-Host "Creating directory..." # DO THIS
    New-Item $newDirName -itemType Directory # THEN DO THIS
}