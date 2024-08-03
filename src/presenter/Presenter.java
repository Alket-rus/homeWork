package presenter;

import model.Family.FileHandler;
import model.GenealogicalTree.GenealogicalTree;
import model.Human.Person;
import model.Family.Gender;
import view.View;

import java.time.LocalDate;
import java.util.List;

public class Presenter {
    private View view;
    private GenealogicalTree<Person> tree;

    public Presenter(View view, GenealogicalTree<Person> tree) {
        this.view = view;
        this.tree = tree;
    }

    public List<Person> getAllPeople() {
        return tree.getAllPeople();
    }

    public void addPerson(String firstName, String middleName, String lastName, LocalDate birthDate, Gender gender,
                          String spouseFullName, String parent1FullName, String parent2FullName) {
        Person person = new Person(firstName, middleName, lastName, birthDate, gender);
        tree.addPerson(person);

        if (spouseFullName != null && !spouseFullName.isEmpty()) {
            Person spouse = tree.getPerson(spouseFullName);
            if (spouse != null) {
                tree.addMarriage(person.getFullName(), spouse.getFullName());
            }
        }

        if (parent1FullName != null && !parent1FullName.isEmpty()) {
            Person parent1 = tree.getPerson(parent1FullName);
            if (parent1 != null) {
                tree.addRelationship(parent1.getFullName(), person.getFullName());
            }
        }

        if (parent2FullName != null && !parent2FullName.isEmpty()) {
            Person parent2 = tree.getPerson(parent2FullName);
            if (parent2 != null) {
                tree.addRelationship(parent2.getFullName(), person.getFullName());
            }
        }

        view.printAnswer("Добавлен новый человек: " + person.getFullName());
        showTree();
    }

    public void sortByName() {
        List<Person> sortedPeople = tree.getPeopleSortedByName();
        StringBuilder sb = new StringBuilder("Отсортировано по имени:\n");
        for (Person person : sortedPeople) {
            sb.append(person.getFullName())
                    .append(" (")
                    .append(person.getGender().name())
                    .append(" ")
                    .append(person.getBirthDate())
                    .append(")\n");
        }
        view.printAnswer(sb.toString());
    }

    public void sortByAge() {
        List<Person> sortedPeople = tree.getPeopleSortedByAge();
        StringBuilder sb = new StringBuilder("Отсортировано по возрасту:\n");
        for (Person person : sortedPeople) {
            sb.append(person.getFullName())
                    .append(" (")
                    .append(person.getGender().name())
                    .append(" ")
                    .append(person.getBirthDate())
                    .append(")\n");
        }
        view.printAnswer(sb.toString());
    }

    public void showTree() {
        view.printAnswer(tree.toString());
    }
}
