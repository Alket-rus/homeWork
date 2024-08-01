package Family;

import java.io.*;

public class FileOperationsImpl implements FileOperations {
    private String filePath;

    @Override
    public boolean save(Serializable serializable) {
        if (filePath == null || filePath.isEmpty()) {
            throw new IllegalArgumentException("File path is not set.");
        }
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(serializable);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Object read() {
        if (filePath == null || filePath.isEmpty()) {
            throw new IllegalArgumentException("File path is not set.");
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            return ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void setPath(String filePath) {
        this.filePath = filePath;
    }
}
