# Directory structure
SRC_DIR="src"
OUT_DIR="out"
LIB_DIR="lib"
MAIN_FILE="MainApp.java"
MYSQL_JAR="mysql-connector-java-8.3.0.jar"

# Compile frontEnd package
javac -d "$OUT_DIR" -cp "$OUT_DIR:$LIB_DIR/$MYSQL_JAR" $(find "$SRC_DIR/Frontend" -name "*.java")

# Compile backEnd package
javac -d "$OUT_DIR" -cp "$OUT_DIR:$LIB_DIR/$MYSQL_JAR" $(find "$SRC_DIR/Backend" -name "*.java")

# Compile main class
javac -d "$OUT_DIR" -cp "$OUT_DIR:$LIB_DIR/$MYSQL_JAR" "$SRC_DIR/$MAIN_FILE"

# Check for compilation errors
if [ $? -eq 0 ]; then
    echo "Build successful"
else
    echo "Build failed"
    exit 1
fi
