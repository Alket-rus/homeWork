// FileHandler.java
import java.io.*;
import java.nio.file.*;

public class FileHandler implements FileOperations {

    @Override
    public void appendToFile(String filename, String data) throws IOException {
        Files.write(Paths.get(filename), data.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
    }

    @Override
    public String readFromFile(String filename) throws IOException {
        return new String(Files.readAllBytes(Paths.get(filename)));
    }
}
