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


        personManager.createPeople();
        personManager.establishRelationships();
        personManager.establishMarriages();


        personManager.addNewFamilies();


        personManager.printFamilyInfo();


        System.out.println("Люди, отсортированные по возрасту:");
        personTree.getPeopleSortedByAge().forEach(person ->
                System.out.println(person.getFullName() + " (Возраст: " + person.getAge() + ")")
        );


        System.out.println("Люди, отсортированные по имени:");
        personTree.getPeopleSortedByName().forEach(person ->
                System.out.println(person.getFullName())
        );
    }
}
