package view;

import model.Family.Gender;
import model.Human.Person;
import model.PersonManager;

import java.time.LocalDate;
import java.util.List;

public class PersonInputHandler {
    private final PersonManager personManager;
    private final UserInputHandler userInputHandler;

    public PersonInputHandler(PersonManager personManager, UserInputHandler userInputHandler) {
        this.personManager = personManager;
        this.userInputHandler = userInputHandler;
    }

    public void handleAddPerson() {
        String firstName = userInputHandler.getInput("Введите имя:");
        String patronymic = userInputHandler.getInput("Введите отчество:");
        String lastName = userInputHandler.getInput("Введите фамилию:");
        LocalDate birthDate = userInputHandler.getDateInput("Введите дату рождения (гггг-мм-дд):");
        Gender gender = userInputHandler.getGenderInput("Введите пол (Мужской/Женский):");

        List<Person> people = personManager.getAllPeople();
        String spouseFullName = selectPersonFromList(people, "Выберите супруга из следующего списка (введите номер или 0 если нет):");
        String parent1FullName = selectPersonFromList(people, "Выберите первого родителя из следующего списка (введите номер или 0 если нет):");
        String parent2FullName = selectPersonFromList(people, "Выберите второго родителя из следующего списка (введите номер или 0 если нет):");

        personManager.addPerson(firstName, patronymic, lastName, birthDate, gender, spouseFullName, parent1FullName, parent2FullName);
    }

    private String selectPersonFromList(List<Person> people, String prompt) {
        if (people.isEmpty()) {
            return null;
        }
        System.out.println(prompt);
        for (int i = 0; i < people.size(); i++) {
            System.out.printf("%d: %s\n", i + 1, people.get(i).getFullName());
        }
        int personIndex = Integer.parseInt(userInputHandler.getInput("").trim());
        if (personIndex > 0 && personIndex <= people.size()) {
            return people.get(personIndex - 1).getFullName();
        }
        return null;
    }
}
