find ./src/ -type f -name "*.java" > ./out/sources.txt
javac -d out @./out/sources.txt
java -cp out Puddle
