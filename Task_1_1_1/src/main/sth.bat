javac -d .\out .\java\ru\nsu\primakova\Main.java .\java\ru\nsu\primakova\Heapsort.java
javadoc -d .\javadoc .\java\ru\nsu\primakova\Main.java .\java\ru\nsu\primakova\Heapsort.java
cd .\out
java -classpath . ru.nsu.primakova.Main
pause