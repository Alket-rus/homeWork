import java.util.List;

public class Main {
    public static void main(String[] args) {
        GenealogicalTree tree = new GenealogicalTree();

        // Создание людей с фамилиями и отчествами
        Person peter = new Person("Пётр", "Иванович", "Смирнов", "1970-01-01", "мужской пол");
        Person evgenia = new Person("Евгения", "Сергеевна", "Смирнова", "1975-02-02", "женский пол");
        Person anna = new Person("Анна", "Петровна", "Смирнова", "2000-03-03", "женский пол");
        Person nikolay = new Person("Николай", "Иванович", "Смирнов", "2005-04-04", "мужской пол");
        Person alice = new Person("Алиса", "Евгеньевна", "Смирнова", "2007-05-05", "женский пол");
        Person ivan = new Person("Иван", "Андреевич", "Смирнов", "2010-06-06", "мужской пол");
        Person alexey = new Person("Алексей", "Николаевич", "Смирнов", "2012-07-07", "мужской пол");
        Person maria = new Person("Мария", "Алексеевна", "Смирнова", "1978-09-09", "женский пол"); // Супруга Николая
        Person andrey = new Person("Андрей", "Владимирович", "Смирнов", "1973-11-11", "мужской пол"); // Супруг Анны

        // Добавление людей в дерево
        tree.addPerson(peter);
        tree.addPerson(evgenia);
        tree.addPerson(anna);
        tree.addPerson(nikolay);
        tree.addPerson(alice);
        tree.addPerson(ivan);
        tree.addPerson(alexey);
        tree.addPerson(maria);
        tree.addPerson(andrey);

        // Установление родственных связей
        tree.addRelationship("Смирнов Пётр Иванович", "Смирнова Анна Петровна");
        tree.addRelationship("Смирнова Евгения Сергеевна", "Смирнова Анна Петровна");
        tree.addRelationship("Смирнова Евгения Сергеевна", "Смирнова Алиса Евгеньевна");
        tree.addRelationship("Смирнова Анна Петровна", "Смирнов Иван Андреевич");
        tree.addRelationship("Смирнов Николай Иванович", "Смирнов Алексей Николаевич");

        // Установление супружеских пар
        tree.addMarriage("Смирнов Пётр Иванович", "Смирнова Евгения Сергеевна");
        tree.addMarriage("Смирнова Анна Петровна", "Смирнов Андрей Владимирович");
        tree.addMarriage("Смирнов Николай Иванович", "Смирнова Мария Алексеевна");

        // Добавление новых семей
        Person sergey = new Person("Сергей", "Дмитриевич", "Петров", "1965-08-08", "мужской пол");
        Person olga = new Person("Ольга", "Васильевна", "Петрова", "1970-07-07", "женский пол");
        Person tatiana = new Person("Татьяна", "Сергеевна", "Петрова", "1995-06-06", "женский пол");
        Person dmitry = new Person("Дмитрий", "Сергеевич", "Петров", "2000-05-05", "мужской пол");

        tree.addPerson(sergey);
        tree.addPerson(olga);
        tree.addPerson(tatiana);
        tree.addPerson(dmitry);

        tree.addRelationship("Петров Сергей Дмитриевич", "Петрова Татьяна Сергеевна");
        tree.addRelationship("Петрова Ольга Васильевна", "Петрова Татьяна Сергеевна");
        tree.addRelationship("Петров Сергей Дмитриевич", "Петров Дмитрий Сергеевич");
        tree.addRelationship("Петрова Ольга Васильевна", "Петров Дмитрий Сергеевич");

        tree.addMarriage("Петров Сергей Дмитриевич", "Петрова Ольга Васильевна");

        Person ivanS = new Person("Иван", "Александрович", "Сидоров", "1980-03-15", "мужской пол");
        Person natalia = new Person("Наталья", "Игоревна", "Сидорова", "1983-11-12", "женский пол");
        Person viktor = new Person("Виктор", "Иванович", "Сидоров", "2005-01-01", "мужской пол");
        Person elena = new Person("Елена", "Ивановна", "Сидорова", "2007-02-02", "женский пол");

        tree.addPerson(ivanS);
        tree.addPerson(natalia);
        tree.addPerson(viktor);
        tree.addPerson(elena);

        tree.addRelationship("Сидоров Иван Александрович", "Сидоров Виктор Иванович");
        tree.addRelationship("Сидорова Наталья Игоревна", "Сидоров Виктор Иванович");
        tree.addRelationship("Сидоров Иван Александрович", "Сидорова Елена Ивановна");
        tree.addRelationship("Сидорова Наталья Игоревна", "Сидорова Елена Ивановна");

        tree.addMarriage("Сидоров Иван Александрович", "Сидорова Наталья Игоревна");

        // Проведение исследования
        printFamily(tree, "Смирнов Пётр Иванович");
        printFamily(tree, "Смирнова Анна Петровна");
        printFamily(tree, "Смирнов Николай Иванович");
        printFamily(tree, "Петров Сергей Дмитриевич");
        printFamily(tree, "Сидоров Иван Александрович");
    }

    private static void printFamily(GenealogicalTree tree, String parentFullName) {
        Person parent = tree.getPerson(parentFullName);
        Person spouse = parent.getSpouse();
        List<Person> children = tree.getChildren(parentFullName);

        System.out.print(parentFullName);
        if (spouse != null) {
            System.out.print(" и " + getSpouseTitle(spouse));
        }
        System.out.print(" Дети: ");
        for (int i = 0; i < children.size(); i++) {
            System.out.print(children.get(i));
            if (i < children.size() - 1) {
                System.out.print(", ");
            }
        }
        System.out.println();
    }

    private static String getSpouseTitle(Person spouse) {
        if (spouse.getGender().equals("мужской пол")) {
            return "Супруг " + spouse.getFullName();
        } else {
            return "Супруга " + spouse.getFullName();
        }
    }
}
