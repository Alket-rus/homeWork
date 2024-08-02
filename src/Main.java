import GenealogicalTree.GenealogicalTree;
import Family.FileHandler;
import Family.FileManager;
import Family.FamilyManager;
import GenealogicalTree.TreeElement;

public class Main {
    public static void main(String[] args) {
        GenealogicalTree tree = FileManager.load();
        if (tree == null) {
            tree = new GenealogicalTree();
        }
        FileHandler fileHandler = new FileHandler("src/GenealogicalTree/tree.txt");

        FamilyManager familyManager = new FamilyManager(tree, fileHandler);

        familyManager.createPeople();
        familyManager.establishRelationships();
        familyManager.establishMarriages();
        familyManager.addNewFamilies();
        familyManager.printFamilyInfo();
        FileManager.saveAndLoad();
        System.out.println("Люди, отсортированные по возрасту:");
        for (Object o : tree.getPeopleSortedByAge()) {
            if (o instanceof TreeElement) {
                TreeElement p = (TreeElement) o;
                System.out.println(p.getFullName() + " - " + p.getAge());
            }
        }

        System.out.println("Люди, отсортированные по имени:");
        for (Object o : tree.getPeopleSortedByName()) {
            if (o instanceof TreeElement) {
                TreeElement p = (TreeElement) o;
                System.out.println(p.getFullName());
            }
        }

    }
}
