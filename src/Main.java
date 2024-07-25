import Family.FamilyManager;
import Family.FileManager;
import Family.FileHandler;
import Family.FileOperations;
import GenealogicalTree.GenealogicalTree;
import Human.Person;


public class Main {
    public static void main(String[] args) {
        GenealogicalTree tree = new GenealogicalTree();
        FileOperations fileHandler = new FileHandler();
        FamilyManager familyManager = new FamilyManager(tree, fileHandler);

        familyManager.createPeople();
        familyManager.establishRelationships();
        familyManager.establishMarriages();
        familyManager.addNewFamilies();

        //FileManager.save(tree);
        //FileManager.load();
        //System.out.print(tree);
        // Вывод информации о семье
        //familyManager.printFamilyInfo();

        // Пример использования итерабала
        for (Person person : tree) {
            System.out.println(person.getFullName() + " (Возраст: " + person.getAge() + ")");
            }

        /*    System.out.println("Сортировка по возрасту:");
        for (Person person : tree.getPeopleSortedByAge()) {
            System.out.println(person.getFullName() + " (Возраст: " + person.getAge() + ")");
        }

        System.out.println("Сортировка по имени:");
        for (Person person : tree.getPeopleSortedByName()) {
            System.out.println(person.getFullName());
        */
    }
}


