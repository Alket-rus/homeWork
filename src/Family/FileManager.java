package Family;

import GenealogicalTree.GenealogicalTree;

import java.io.Serializable;

public class FileManager {
    final static String filePath = "src/GenealogicalTree/tree.txt";
    GenealogicalTree tree = load();


    public static void saveAndLoad() {
        GenealogicalTree tree = load();
        save(tree);
        System.out.println(tree);
    }


    public static GenealogicalTree load() {
        FileOperations fileHandler = new FileHandler();
        fileHandler.setPath(filePath);
        return (GenealogicalTree) fileHandler.read();
    }

    public static void save(GenealogicalTree genealogicalTree) {
        FileOperations fileHandler = new FileHandler();
        fileHandler.setPath(filePath);
        fileHandler.save((Serializable) genealogicalTree);

    }


}

