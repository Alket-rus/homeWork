package model.Family;

import model.GenealogicalTree.GenealogicalTree;
import model.Human.Person;

import java.time.LocalDate;
import java.util.List;

public class FamilyManager {
    private final GenealogicalTree<Person> tree;
    private final FileOperations fileHandler;

    public FamilyManager(GenealogicalTree<Person> tree, FileOperations fileHandler) {
        this.tree = tree;
        this.fileHandler = fileHandler;
    }

    public void createPeople() {
        Person peter = new Person("Пётр", "Иванович", "Смирнов", LocalDate.of(1970, 1, 1), Gender.Мужской);
        Person evgenia = new Person("Евгения", "Сергеевна", "Смирнова", LocalDate.of(1975, 2, 2), Gender.Женский);
        Person anna = new Person("Анна", "Петровна", "Смирнова", LocalDate.of(2000, 3, 3), Gender.Женский);
        Person nikolay = new Person("Николай", "Иванович", "Смирнов", LocalDate.of(2005, 4, 4), Gender.Мужской);
        Person alice = new Person("Алиса", "Евгеньевна", "Смирнова", LocalDate.of(2007, 5, 5), Gender.Женский);
        Person ivan = new Person("Иван", "Андреевич", "Смирнов", LocalDate.of(2010, 6, 6), Gender.Мужской);
        Person alexey = new Person("Алексей", "Николаевич", "Смирнов", LocalDate.of(2012, 7, 7), Gender.Мужской);
        Person maria = new Person("Мария", "Алексеевна", "Смирнова", LocalDate.of(1978, 9, 9), Gender.Женский);
        Person andrey = new Person("Андрей", "Владимирович", "Смирнов", LocalDate.of(1973, 11, 11), Gender.Мужской);

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
        Person sergey = new Person("Сергей", "Дмитриевич", "Петров", LocalDate.of(1965, 8, 8), Gender.Мужской);
        Person olga = new Person("Ольга", "Васильевна", "Петрова", LocalDate.of(1970, 7, 7), Gender.Женский);
        Person tatiana = new Person("Татьяна", "Сергеевна", "Петрова", LocalDate.of(1995, 6, 6), Gender.Женский);
        Person dmitry = new Person("Дмитрий", "Сергеевич", "Петров", LocalDate.of(2000, 5, 5), Gender.Мужской);

        tree.addPerson(sergey);
        tree.addPerson(olga);
        tree.addPerson(tatiana);
        tree.addPerson(dmitry);

        tree.addRelationship("Петров Сергей Дмитриевич", "Петрова Татьяна Сергеевна");
        tree.addRelationship("Петрова Ольга Васильевна", "Петрова Татьяна Сергеевна");
        tree.addRelationship("Петров Сергей Дмитриевич", "Петров Дмитрий Сергеевич");
        tree.addRelationship("Петрова Ольга Васильевна", "Петров Дмитрий Сергеевич");

        tree.addMarriage("Петров Сергей Дмитриевич", "Петрова Ольга Васильевна");

        Person ivanS = new Person("Иван", "Александрович", "Сидоров", LocalDate.of(1980, 3, 15), Gender.Мужской);
        Person natalia = new Person("Наталья", "Игоревна", "Сидорова", LocalDate.of(1983, 11, 12), Gender.Женский);
        Person viktor = new Person("Виктор", "Иванович", "Сидоров", LocalDate.of(2005, 1, 1), Gender.Мужской);
        Person elena = new Person("Елена", "Ивановна", "Сидорова", LocalDate.of(2007, 2, 2), Gender.Женский);

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

    public void printFamilyInfo() {
        List<String> people = List.of(
                "Смирнов Пётр Иванович",
                "Смирнова Анна Петровна",
                "Смирнов Николай Иванович",
                "Петров Сергей Дмитриевич",
                "Сидоров Иван Александрович"
        );

        for (String personFullName : people) {
            System.out.println(getFamilyInfo(personFullName));
        }
    }

    private String getFamilyInfo(String parentFullName) {
        Person parent = (Person) tree.getPerson(parentFullName);
        if (parent == null) return "";

        Person spouse = (Person) parent.getSpouse();
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
}
