package model;

import model.Family.FileOperations;
import model.GenealogicalTree.GenealogicalTree;
import model.Human.Person;
import model.Family.Gender;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class FamilyService implements PersonManager {
    private final GenealogicalTree<Person> tree;
    private final FileOperations fileHandler;

    public FamilyService(GenealogicalTree<Person> tree, FileOperations fileHandler) {
        this.tree = tree;
        this.fileHandler = fileHandler;
    }

    @Override
    public void addPerson(String firstName, String middleName, String lastName, LocalDate birthDate, Gender gender,
                          String spouseFullName, String parent1FullName, String parent2FullName) {
        Person person = new Person(firstName, middleName, lastName, birthDate, gender);
        tree.addPerson(person);

        if (spouseFullName != null && !spouseFullName.isEmpty()) {
            Person spouse = tree.getPerson(spouseFullName).orElse(null);
            if (spouse != null) {
                tree.addMarriage(person.getFullName(), spouse.getFullName());
            }
        }

        if (parent1FullName != null && !parent1FullName.isEmpty()) {
            Person parent1 = tree.getPerson(parent1FullName).orElse(null);
            if (parent1 != null) {
                tree.addRelationship(parent1.getFullName(), person.getFullName());
            }
        }

        if (parent2FullName != null && !parent2FullName.isEmpty()) {
            Person parent2 = tree.getPerson(parent2FullName).orElse(null);
            if (parent2 != null) {
                tree.addRelationship(parent2.getFullName(), person.getFullName());
            }
        }
    }

    @Override
    public List<Person> getAllPeople() {
        return tree.getAllPeople();
    }

    @Override
    public List<Person> sortByName() {
        return tree.getAllPeople()
                .stream()
                .sorted((p1, p2) -> p1.getLastName().compareToIgnoreCase(p2.getLastName()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Person> sortByAge() {
        return tree.getAllPeople()
                .stream()
                .sorted((p1, p2) -> p2.getBirthDate().compareTo(p1.getBirthDate()))  // Сортировка по возрасту (старший - первый)
                .collect(Collectors.toList());
    }

    public void saveTree() {
        fileHandler.save(tree);
    }

    public GenealogicalTree<Person> loadTree() {
        return (GenealogicalTree<Person>) fileHandler.read();
    }
}
