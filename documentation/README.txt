Documentation
************************************************************************************************************************************************

Refer to API.txt in order to view the documentation for this project.

Testing
************************************************************************************************************************************************

This project was tested with the various test cases within the testCase folder.
The test cases have semantic naming conventions, and thus the names describe the tests that the test cases are testing against.
The test cases included in this project contain negative tests as well as positive tests.
Furthermore, the test cases for this project contain test of the Tokenizer, Parser, and Executor.

Compiling
************************************************************************************************************************************************

$ cd <this directory>
$ mkdir bin
$ javac -d bin -sourcepath src src/core/main/Main.java
$ java -cp bin core.main.Main <path to .core file>