package model.Family;

import model.GenealogicalTree.GenealogicalTree;

import java.io.Serializable;

public class FileManager {
    private static final String FILE_PATH = "src/model/GenealogicalTree/tree.txt";
    private static final FileOperations fileHandler = new FileHandler(FILE_PATH);

    public static void saveAndLoadTest() {
        GenealogicalTree tree = new GenealogicalTree();
        save(tree);
        System.out.println("Дерево сохранено: " + tree);

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
