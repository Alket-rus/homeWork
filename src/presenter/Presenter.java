package presenter;

import model.Family.Gender;
import model.Human.Person;
import model.FamilyService;
import view.View;

import java.time.LocalDate;
import java.util.List;

public class Presenter {
    private final View view;
    private final FamilyService service;

    public Presenter(View view, FamilyService service) {
        this.view = view;
        this.service = service;
    }

    public void addPerson(String firstName, String middleName, String lastName, LocalDate birthDate, Gender gender,
                          String spouseFullName, String parent1FullName, String parent2FullName) {
        service.addPerson(firstName, middleName, lastName, birthDate, gender, spouseFullName, parent1FullName, parent2FullName);
        view.printAnswer("Добавлен новый человек: " + firstName + " " + middleName + " " + lastName + "\n");
        showTree();
    }

    public List<Person> getAllPeople() {
        return service.getAllPeople();
    }

    public void sortByName() {
        List<Person> sortedPeople = service.getAllPeople();
        sortedPeople.sort((p1, p2) -> p1.getFullName().compareTo(p2.getFullName()));

        StringBuilder sb = new StringBuilder("Отсортировано по имени:\n");
        sortedPeople.forEach(person -> sb.append(person.getFullName()).append("\n"));

        view.printAnswer(sb.toString());
    }

    public void sortByAge() {
        List<Person> sortedPeople = service.getAllPeople();
        sortedPeople.sort((p1, p2) -> Integer.compare(p1.getAge(), p2.getAge()));

        StringBuilder sb = new StringBuilder("Отсортировано по возрасту:\n");
        sortedPeople.forEach(person ->
                sb.append(String.format("%s (Возраст: %d, Пол: %s)%n", person.getFullName(), person.getAge(), person.getGender().name()))
        );

        view.printAnswer(sb.toString());
    }

    public void showTree() {
        view.printAnswer(service.loadTree().toString());
    }
}
