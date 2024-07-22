package Family;// Family.FamilyManager.java
import GenealogicalTree.GenealogicalTree;
import Human.Gender;
import Human.Person;

import java.io.IOException;
import java.util.List;

public class FamilyManager {

    private final GenealogicalTree tree;
    private final FileOperations fileHandler;

    public FamilyManager(GenealogicalTree tree, FileOperations fileHandler) {
        this.tree = tree;
        this.fileHandler = fileHandler;
    }

    public void createPeople() {

        Person peter = new Person("Пётр", "Иванович", "Смирнов", "1970-01-01", Gender.Мужской);
        Person evgenia = new Person("Евгения", "Сергеевна", "Смирнова", "1975-02-02", Gender.Женский);
        Person anna = new Person("Анна", "Петровна", "Смирнова", "2000-03-03", Gender.Женский);
        Person nikolay = new Person("Николай", "Иванович", "Смирнов", "2005-04-04", Gender.Мужской);
        Person alice = new Person("Алиса", "Евгеньевна", "Смирнова", "2007-05-05", Gender.Женский);
        Person ivan = new Person("Иван", "Андреевич", "Смирнов", "2010-06-06", Gender.Мужской);
        Person alexey = new Person("Алексей", "Николаевич", "Смирнов", "2012-07-07", Gender.Мужской);
        Person maria = new Person("Мария", "Алексеевна", "Смирнова", "1978-09-09", Gender.Женский);
        Person andrey = new Person("Андрей", "Владимирович", "Смирнов", "1973-11-11", Gender.Мужской);

        tree.addPerson(peter);
        tree.addPerson(evgenia);
        tree.addPerson(anna);
        tree.addPerson(nikolay);
        tree.addPerson(alice);
        tree.addPerson(ivan);
        tree.addPerson(alexey);
        tree.addPerson(maria);
        tree.addPerson(andrey);
    }

    public void establishRelationships() {

        tree.addRelationship("Смирнов Пётр Иванович", "Смирнова Анна Петровна");
        tree.addRelationship("Смирнова Евгения Сергеевна", "Смирнова Анна Петровна");
        tree.addRelationship("Смирнова Евгения Сергеевна", "Смирнова Алиса Евгеньевна");
        tree.addRelationship("Смирнова Анна Петровна", "Смирнов Иван Андреевич");
        tree.addRelationship("Смирнов Николай Иванович", "Смирнов Алексей Николаевич");
    }

    public void establishMarriages() {

        tree.addMarriage("Смирнов Пётр Иванович", "Смирнова Евгения Сергеевна");
        tree.addMarriage("Смирнова Анна Петровна", "Смирнов Андрей Владимирович");
        tree.addMarriage("Смирнов Николай Иванович", "Смирнова Мария Алексеевна");
    }

    public void addNewFamilies() {

        Person sergey = new Person("Сергей", "Дмитриевич", "Петров", "1965-08-08", Gender.Мужской);
        Person olga = new Person("Ольга", "Васильевна", "Петрова", "1970-07-07", Gender.Женский);
        Person tatiana = new Person("Татьяна", "Сергеевна", "Петрова", "1995-06-06", Gender.Женский);
        Person dmitry = new Person("Дмитрий", "Сергеевич", "Петров", "2000-05-05", Gender.Мужской);

        tree.addPerson(sergey);
        tree.addPerson(olga);
        tree.addPerson(tatiana);
        tree.addPerson(dmitry);

        tree.addRelationship("Петров Сергей Дмитриевич", "Петрова Татьяна Сергеевна");
        tree.addRelationship("Петрова Ольга Васильевна", "Петрова Татьяна Сергеевна");
        tree.addRelationship("Петров Сергей Дмитриевич", "Петров Дмитрий Сергеевич");
        tree.addRelationship("Петрова Ольга Васильевна", "Петров Дмитрий Сергеевич");

        tree.addMarriage("Петров Сергей Дмитриевич", "Петрова Ольга Васильевна");

        Person ivanS = new Person("Иван", "Александрович", "Сидоров", "1980-03-15", Gender.Мужской);
        Person natalia = new Person("Наталья", "Игоревна", "Сидорова", "1983-11-12", Gender.Женский);
        Person viktor = new Person("Виктор", "Иванович", "Сидоров", "2005-01-01", Gender.Мужской);
        Person elena = new Person("Елена", "Ивановна", "Сидорова", "2007-02-02", Gender.Женский);

        tree.addPerson(ivanS);
        tree.addPerson(natalia);
        tree.addPerson(viktor);
        tree.addPerson(elena);

        tree.addRelationship("Сидоров Иван Александрович", "Сидоров Виктор Иванович");
        tree.addRelationship("Сидорова Наталья Игоревна", "Сидоров Виктор Иванович");
        tree.addRelationship("Сидоров Иван Александрович", "Сидорова Елена Ивановна");
        tree.addRelationship("Сидорова Наталья Игоревна", "Сидорова Елена Ивановна");

        tree.addMarriage("Сидоров Иван Александрович", "Сидорова Наталья Игоревна");
    }

    public void writeFamiliesToFile(List<String> people, String filename) {
        StringBuilder sb = new StringBuilder();


        for (String personFullName : people) {
            String familyInfo = getFamilyInfo(personFullName);
            if (!familyInfo.isEmpty()) {
                sb.append(familyInfo).append("\n");
            }
        }


        try {
            fileHandler.appendToFile(filename, sb.toString());
            System.out.println("Data appended to file " + filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getFamilyInfo(String parentFullName) {
        Person parent = tree.getPerson(parentFullName);
        if (parent == null) return "";

        Person spouse = parent.getSpouse();
        List<Person> children = parent.getChildren();


        StringBuilder sb = new StringBuilder();
        sb.append(parentFullName);
        if (spouse != null) {
            sb.append(" и ").append(getSpouseTitle(spouse));
        }
        sb.append(" Дети: ");


        String childrenNames = children.stream()
                .map(child -> String.format("%s (Возраст: %d, Пол: %s)",
                        child.getFullName(), child.getAge(), child.getGender().name()))
                .reduce((a, b) -> a + ", " + b)
                .orElse("");

        sb.append(childrenNames);
        return sb.toString();
    }

    private String getSpouseTitle(Person spouse) {
        if (spouse.getGender() == Gender.Мужской) {
            return "Супруг " + spouse.getFullName();
        } else {
            return "Супруга " + spouse.getFullName();
        }
    }

    public void readFamiliesFromFile(String filename) {
        try {
            String data = fileHandler.readFromFile(filename);
            String[] lines = data.split("\n");

            for (String line : lines) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
