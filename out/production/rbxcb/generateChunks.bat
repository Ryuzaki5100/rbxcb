@echo off
setlocal enabledelayedexpansion

REM Get the directory of the batch script
set script_dir=%~dp0

REM Define the input file containing Java file paths relative to the script's directory
set input_file=%script_dir%java_files.txt

REM Define the Java class and method to call
set java_class=com.cube.demo.SimpleJavaParser
set java_method=parseJavaCodeToJson

REM Define the Java executable
set java_executable=java

REM Define the output file to store JSON results relative to the script's directory
set output_file=%script_dir%chunks.json

REM Define the directory containing the compiled Java class relative to the script's directory
set java_class_path=%script_dir%demo\src\main\java\com\cube\demo

REM Initialize the output file with an empty JSON object
echo { > %output_file%

REM Read each path from the input file
set "first_file=true"
for /f "delims=" %%f in (%input_file%) do (
    REM Read the content of the Java file
    set "file_content="
    for /f "delims=" %%l in (%%f) do (
        set "file_content=!file_content!%%l"
    )

    REM Call the Java method with the file content as a parameter and capture the output
    set "json_output="
    for /f "delims=" %%o in ('%java_executable% -cp "%java_class_path%" %java_class% %java_method% "!file_content!"') do (
        set "json_output=!json_output!%%o"
    )

    REM Escape double quotes and backslashes in the file path
    set "escaped_path=!file_content!"
    set "escaped_path=!escaped_path:"=\"!"
    set "escaped_path=!escaped_path:\=\\!"

    REM Append the file path and JSON output to the output file
    if "!first_file!"=="true" (
        echo     "!escaped_path!": !json_output! >> %output_file%
        set "first_file=false"
    ) else (
        echo , >> %output_file%
        echo     "!escaped_path!": !json_output! >> %output_file%
    )
)

REM Close the JSON object
echo } >> %output_file%

echo Processing complete.
