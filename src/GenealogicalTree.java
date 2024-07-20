import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GenealogicalTree {
    private Map<String, Person> people;

    public GenealogicalTree() {
        people = new HashMap<>();
    }

    public void addPerson(Person person) {
        if (people.containsKey(person.getFullName())) {
            throw new IllegalArgumentException("Человек с именем " + person.getFullName() + " уже существует.");
        }
        people.put(person.getFullName(), person);
    }

    public void addRelationship(String parentFullName, String childFullName) {
        Person parent = people.get(parentFullName);
        Person child = people.get(childFullName);

        if (parent == null || child == null) {
            throw new IllegalArgumentException("Родитель или ребенок не найдены в дереве.");
        }

        parent.addChild(child);
    }

    public void addMarriage(String person1FullName, String person2FullName) {
        Person person1 = people.get(person1FullName);
        Person person2 = people.get(person2FullName);

        if (person1 == null || person2 == null) {
            throw new IllegalArgumentException("Один или оба человека не найдены в дереве.");
        }

        person1.setSpouse(person2);
        person2.setSpouse(person1);
    }

    public List<Person> getChildren(String personFullName) {
        Person person = people.get(personFullName);
        if (person == null) {
            throw new IllegalArgumentException("Человек с именем " + personFullName + " не найден.");
        }

        return person.getChildren();
    }

    public Person getPerson(String personFullName) {
        Person person = people.get(personFullName);
        if (person == null) {
            throw new IllegalArgumentException("Человек с именем " + personFullName + " не найден.");
        }

        return person;
    }
}
