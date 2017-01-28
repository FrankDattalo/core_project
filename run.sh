rm -rf ./bin
mkdir bin
javac -d bin -sourcepath src src/core/main/Main.java
java -cp bin core.main.Main $1