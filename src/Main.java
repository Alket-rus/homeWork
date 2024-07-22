// Main.java
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        GenealogicalTree tree = new GenealogicalTree();
        FileOperations fileHandler = new FileHandler();
        FamilyManager familyManager = new FamilyManager(tree, fileHandler);


        familyManager.createPeople();


        familyManager.establishRelationships();


        familyManager.establishMarriages();


        familyManager.addNewFamilies();


        List<String> people = Arrays.asList(
                "Смирнов Пётр Иванович",
                "Смирнова Анна Петровна",
                "Смирнов Николай Иванович",
                "Петров Сергей Дмитриевич",
                "Сидоров Иван Александрович"
        );


        familyManager.writeFamiliesToFile(people, "families.txt");


        familyManager.readFamiliesFromFile("families.txt");
    }
}
