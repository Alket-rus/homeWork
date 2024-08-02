package Family;

import java.io.*;

public class FileHandler implements FileOperations {
    private String filePath;

    public FileHandler(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public boolean save(Serializable obj) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(obj);
            return true;
        } catch (IOException e) {
            System.err.println("Ошибка при сохранении объекта: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Object read() {
        File file = new File(filePath);
        if (!file.exists() || file.length() == 0) {
            System.err.println("Файл не существует или пуст.");
            return null;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            return ois.readObject();
        } catch (EOFException e) {
            System.err.println("Достигнут неожиданный конец файла. Возможно, файл пуст или поврежден.");
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            System.err.println("Ошибка при чтении объекта: " + e.getMessage());
            e.printStackTrace();
            return null;
        } catch (ClassNotFoundException e) {
            System.err.println("Класс не найден при десериализации.");
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void setPath(String filePath) {
        this.filePath = filePath;
    }
}
