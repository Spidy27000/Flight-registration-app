OUT_DIR="out"
LIB_DIR="lib"
MAIN_FILE="MainApp"

MYSQL_JAR="mysql-connector-java-8.3.0.jar"

./build.sh

cd $OUT_DIR

java -cp "../$OUT_DIR:../$LIB_DIR/$MYSQL_JAR" "$MAIN_FILE"

cd ../
