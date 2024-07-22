package Family;// Family.FileOperations.java
import java.io.IOException;

public interface FileOperations {
    void appendToFile(String filename, String data) throws IOException;
    String readFromFile(String filename) throws IOException;
}
