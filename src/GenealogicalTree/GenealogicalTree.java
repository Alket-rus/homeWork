package GenealogicalTree;
import Human.Person;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class GenealogicalTree<T extends TreeElement<T>> implements Iterable<T>, Serializable {
    private List<T> people;

    public GenealogicalTree() {
        this.people = new ArrayList<>();
    }

    public void addPerson(T person) {
        if (people.stream().anyMatch(p -> p.getFullName().equals(person.getFullName()))) {
            throw new IllegalArgumentException("Человек с именем " + person.getFullName() + " уже существует.");
        }
        people.add(person);
    }

    public void addRelationship(String parentFullName, String childFullName) {
        T parent = people.stream().filter(p -> p.getFullName().equals(parentFullName)).findFirst().orElse(null);
        T child = people.stream().filter(p -> p.getFullName().equals(childFullName)).findFirst().orElse(null);

        if (parent == null || child == null) {
            throw new IllegalArgumentException("Родитель или ребенок не найдены в дереве.");
        }

        parent.addChild(child);
    }

    public void addMarriage(String person1FullName, String person2FullName) {
        T person1 = people.stream().filter(p -> p.getFullName().equals(person1FullName)).findFirst().orElse(null);
        T person2 = people.stream().filter(p -> p.getFullName().equals(person2FullName)).findFirst().orElse(null);

        if (person1 == null || person2 == null) {
            throw new IllegalArgumentException("Один или оба человека не найдены в дереве.");
        }

        person1.setSpouse(person2);
        person2.setSpouse(person1);
    }

    public T getPerson(String personFullName) {
        return people.stream().filter(p -> p.getFullName().equals(personFullName)).findFirst().orElse(null);
    }

    public List<T> getAllPeople() {
        return new ArrayList<>(people);
    }

    public List<T> getPeopleSortedByAge() {
        List<T> sortedList = getAllPeople();
        sortedList.sort(Comparator.comparingInt(T::getAge));
        return sortedList;
    }

    public List<T> getPeopleSortedByName() {
        List<T> sortedList = getAllPeople();
        sortedList.sort(Comparator.comparing(T::getFullName));
        return sortedList;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Генеалогическое Дерево:\n");

        for (T person : people) {
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
        return people.iterator();
    }
}
