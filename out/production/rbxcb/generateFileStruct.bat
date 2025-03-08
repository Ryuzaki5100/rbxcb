@echo off
setlocal enabledelayedexpansion

REM Define the output file
set output_file=java_files.txt

REM Clear the output file if it already exists
type nul > %output_file%

REM Get the directory of the batch file
set "batch_dir=%~dp0"

REM Find all .java files and store their paths relative to the batch file directory
for /r "%batch_dir%" %%f in (*.java) do (
    REM Get the relative path
    set "relative_path=%%f"
    REM Convert the path to be relative to the batch file directory
    set "relative_path=!relative_path:%batch_dir%=!"
    REM Append the relative path to the output file
    echo !relative_path! >> %output_file%
)

echo Paths of all .java files have been stored in %output_file%
