package presenter;

import model.Family.Gender;
import model.FamilyService;
import model.Human.Person;
import model.PersonManager;
import view.View;

import java.time.LocalDate;
import java.util.List;

public class Presenter {
    private final View view;
    private final PersonManager personManager;

    public Presenter(View view, FamilyService familyService) {
        this.view = view;
        this.personManager = familyService;
    }

    public void addPerson(String firstName, String middleName, String lastName, LocalDate birthDate,
                          Gender gender, String spouseFullName, String parent1FullName, String parent2FullName) {
        personManager.addPerson(firstName, middleName, lastName, birthDate, gender, spouseFullName, parent1FullName, parent2FullName);
        view.printAnswer("Добавлен новый человек: " + firstName + " " + middleName + " " + lastName + "\n");
        showTree();
    }

    public void sortByName() {
        List<Person> sortedPeople = personManager.sortByName();
        view.displayPeople(sortedPeople);
    }

    public void sortByAge() {
        List<Person> sortedPeople = personManager.sortByAge();
        view.displayPeople(sortedPeople);
    }

    public void showTree() {
        view.printAnswer(((FamilyService) personManager).loadTree().toString());
    }

    public PersonManager getPersonManager() {
        return personManager;
    }
}
