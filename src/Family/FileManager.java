package Family;

import GenealogicalTree.GenealogicalTree;

import java.io.Serializable;

public class FileManager {
    final static String filePath = "src/GenealogicalTree/tree.txt";
    private static FileOperations fileHandler = new FileHandler(filePath);

    public static void saveAndLoad() {
        GenealogicalTree tree = new GenealogicalTree(); // Создаем новый объект для теста
        save(tree);
        System.out.println("Дерево сохранено: " + tree);

        // Попробуем загрузить объект
        GenealogicalTree loadedTree = load();
        if (loadedTree != null) {
            System.out.println("Дерево загружено: " + loadedTree);
        } else {
            System.out.println("Не удалось загрузить дерево.");
        }
    }

    public static GenealogicalTree load() {
        return (GenealogicalTree) fileHandler.read();
    }

    public static void save(GenealogicalTree genealogicalTree) {
        fileHandler.save((Serializable) genealogicalTree);
    }
}
