package presenter;

import model.Family.Gender;
import model.FamilyService;
import model.Human.Person;
import model.PersonFormatter;
import model.PersonManager;
import view.View;

import java.time.LocalDate;
import java.util.List;

public class Presenter {
    private final View view;
    private final PersonManager personManager;
    private final PersonFormatter personFormatter;

    public Presenter(View view, FamilyService familyService) {
        this.view = view;
        this.personManager = familyService;
        this.personFormatter = new PersonFormatter();
    }

    public void addPerson(String firstName, String middleName, String lastName, LocalDate birthDate,
                          Gender gender, String spouseFullName, String parent1FullName, String parent2FullName) {
        personManager.addPerson(firstName, middleName, lastName, birthDate, gender, spouseFullName, parent1FullName, parent2FullName);
        view.printAnswer("Добавлен новый человек: " + firstName + " " + middleName + " " + lastName + "\n");
        showTree();
    }

    public void sortByName() {
        List<Person> sortedPeople = personManager.sortByName();
        String formattedPeople = personFormatter.formatPeopleList(sortedPeople);
        view.displayPeople(formattedPeople);
    }

    public void sortByAge() {
        List<Person> sortedPeople = personManager.sortByAge();
        String formattedPeople = personFormatter.formatPeopleList(sortedPeople);
        view.displayPeople(formattedPeople);
    }

    public void showTree() {
        view.printAnswer(((FamilyService) personManager).loadTree().toString());
    }

    public void saveTree() {
        if (personManager instanceof FamilyService) {
            ((FamilyService) personManager).saveTree();
        }
    }

    public void loadTree() {
        if (personManager instanceof FamilyService) {
            ((FamilyService) personManager).loadTree();
            showTree();
        }
    }

    public PersonManager getPersonManager() {
        return personManager;
    }
}
