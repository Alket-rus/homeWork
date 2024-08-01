import GenealogicalTree.GenealogicalTree;
import Family.FamilyManager;
import Family.FileOperations;
import Family.FileOperationsImpl;
import Human.Person;

public class Main {
    public static void main(String[] args) {
        GenealogicalTree<Person> personTree = new GenealogicalTree<>();
        FileOperations fileOperations = new FileOperationsImpl();
        FamilyManager personManager = new FamilyManager(personTree, fileOperations);

        // Создайте людей и установите отношения
        personManager.createPeople();
        personManager.establishRelationships();
        personManager.establishMarriages();

        // Добавьте новые семьи
        personManager.addNewFamilies();

        // Распечатайте информацию о семьях
        personManager.printFamilyInfo();

        // Печать людей, отсортированных по возрасту
        System.out.println("Люди, отсортированные по возрасту:");
        personTree.getPeopleSortedByAge().forEach(person ->
                System.out.println(person.getFullName() + " (Возраст: " + person.getAge() + ")")
        );

        // Печать людей, отсортированных по имени
        System.out.println("Люди, отсортированные по имени:");
        personTree.getPeopleSortedByName().forEach(person ->
                System.out.println(person.getFullName())
        );
    }
}
