package model.GenealogicalTree;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

public class GenealogicalTree<T extends TreeElement<T>> implements Iterable<T>, Serializable {
    private static final long serialVersionUID = 1L;

    private List<T> people;

    public GenealogicalTree() {
        this.people = new ArrayList<>();
    }

    public void addPerson(T person) {
        if (getPerson(person.getFullName()).isPresent()) {
            throw new IllegalArgumentException("Человек с именем " + person.getFullName() + " уже существует.");
        }
        people.add(person);
    }

    public void addRelationship(String parentFullName, String childFullName) {
        T parent = findPerson(parentFullName).orElseThrow(() -> new IllegalArgumentException("Родитель не найден."));
        T child = findPerson(childFullName).orElseThrow(() -> new IllegalArgumentException("Ребенок не найден."));
        parent.addChild(child);
    }

    public void addMarriage(String person1FullName, String person2FullName) {
        T person1 = findPerson(person1FullName).orElseThrow(() -> new IllegalArgumentException("Один из людей не найден."));
        T person2 = findPerson(person2FullName).orElseThrow(() -> new IllegalArgumentException("Один из людей не найден."));
        person1.setSpouse(person2);
        person2.setSpouse(person1);
    }

    public Optional<T> getPerson(String personFullName) {
        return findPerson(personFullName);
    }

    public List<T> getAllPeople() {
        return new ArrayList<>(people);
    }

    private Optional<T> findPerson(String fullName) {
        return people.stream().filter(p -> p.getFullName().equals(fullName)).findFirst();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Генеалогическое Дерево:\n");

        for (T person : people) {
            sb.append(person.toString());
            T spouse = person.getSpouse();
            if (spouse != null) {
                sb.append(" и Супруг/Супруга ").append(spouse.getFullName());
            }
            List<T> children = person.getChildren();
            if (!children.isEmpty()) {
                sb.append(" Дети: ");
                for (T child : children) {
                    sb.append(child.toString()).append(", ");
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
