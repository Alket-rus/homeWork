package GenealogicalTree;

import Human.Person;
import Sorting.Sort;

import java.io.Serializable;
import java.util.*;

public class GenealogicalTree implements Serializable, Iterable<Person> {
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

    public Person getPerson(String personFullName) {
        Person person = people.get(personFullName);
        if (person == null) {
            throw new IllegalArgumentException("Человек с именем " + person.getFullName() + " не найден.");
        }

        return person;
    }

    public List<Person> getAllPeople() {
        return new ArrayList<>(people.values());
    }

    public List<Person> getPeopleSortedByAge() {
        List<Person> sortedList = getAllPeople();
        Sort.sortByAge(sortedList);
        return sortedList;
    }

    public List<Person> getPeopleSortedByName() {
        List<Person> sortedList = getAllPeople();
        Sort.sortByName(sortedList);
        return sortedList;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Генеалогическое Дерево:\n");

        for (Person person : people.values()) {
            sb.append(person.getFullName()).append(" (")
                    .append(person.getGender()).append(" ")
                    .append(person.getBirthdate()).append(")");


            Person spouse = person.getSpouse();
            if (spouse != null) {
                sb.append(" и Супруг/Супруга ")
                        .append(spouse.getFullName());
            }


            List<Person> children = person.getChildren();
            if (!children.isEmpty()) {
                sb.append(" Дети: ");
                for (Person child : children) {
                    sb.append(child.getFullName())
                            .append(" (Возраст: ").append(child.getAge())
                            .append(", Пол: ").append(child.getGender())
                            .append("), ");
                }
                sb.setLength(sb.length() - 2);  // Убираем последнюю запятую и пробел
            }

            sb.append("\n");
        }

        return sb.toString();
    }

    @Override
    public Iterator<Person> iterator() {
        return new GenealogicalTreeIterator(people);
    }
}
