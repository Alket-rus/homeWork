package GenealogicalTree;
import java.util.*;

public class GenealogicalTree<T extends TreeManager<T>> implements Iterable<T> {
    private Map<String, T> people;

    public GenealogicalTree() {
        people = new HashMap<>();
    }

    public void addPerson(T person) {
        if (people.containsKey(person.getFullName())) {
            throw new IllegalArgumentException("Человек с именем " + person.getFullName() + " уже существует.");
        }
        people.put(person.getFullName(), person);
    }

    public void addRelationship(String parentFullName, String childFullName) {
        T parent = people.get(parentFullName);
        T child = people.get(childFullName);

        if (parent == null || child == null) {
            throw new IllegalArgumentException("Родитель или ребенок не найдены в дереве.");
        }

        parent.addChild(child);
    }

    public void addMarriage(String person1FullName, String person2FullName) {
        T person1 = people.get(person1FullName);
        T person2 = people.get(person2FullName);

        if (person1 == null || person2 == null) {
            throw new IllegalArgumentException("Один или оба человека не найдены в дереве.");
        }

        person1.setSpouse(person2);
        person2.setSpouse(person1);
    }

    public T getPerson(String personFullName) {
        T person = people.get(personFullName);
        if (person == null) {
            throw new IllegalArgumentException("Человек с именем " + personFullName + " не найден.");
        }

        return person;
    }

    public List<T> getAllPeople() {
        return new ArrayList<>(people.values());
    }

    public List<T> getPeopleSortedByAge() {
        List<T> sortedList = getAllPeople();
        sortedList.sort(Comparator.comparingInt(TreeManager::getAge));
        return sortedList;
    }

    public List<T> getPeopleSortedByName() {
        List<T> sortedList = getAllPeople();
        sortedList.sort(Comparator.comparing(TreeManager::getFullName));
        return sortedList;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Генеалогическое Дерево:\n");

        for (T person : people.values()) {
            sb.append(person.getFullName()).append(" (")
                    .append(person.getGender()).append(" ")
                    .append(person.getBirthDate()).append(")");

            T spouse = person.getSpouse();
            if (spouse != null) {
                sb.append(" и Супруг/Супруга ")
                        .append(spouse.getFullName());
            }

            List<T> children = person.getChildren();
            if (!children.isEmpty()) {
                sb.append(" Дети: ");
                for (T child : children) {
                    sb.append(child.getFullName())
                            .append(" (Возраст: ").append(child.getAge())
                            .append(", Пол: ").append(child.getGender())
                            .append("), ");
                }
                sb.setLength(sb.length() - 2);
            }

            sb.append("\n");
        }

        return sb.toString();
    }

    @Override
    public Iterator<T> iterator() {
        return new GenealogicalTreeIterator<>(people);
    }
}
