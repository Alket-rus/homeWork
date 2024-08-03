package model.Family;

import presenter.Presenter;
import model.Human.Person;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class PersonInputHandler {
    private Presenter presenter;
    private Scanner scanner;

    public PersonInputHandler(Presenter presenter) {
        this.presenter = presenter;
        this.scanner = new Scanner(System.in);
    }

    public void handleAddPerson() {
        System.out.println("Введите имя:");
        String firstName = scanner.nextLine();

        System.out.println("Введите отчество:");
        String patronymic = scanner.nextLine();

        System.out.println("Введите фамилию:");
        String lastName = scanner.nextLine();

        System.out.println("Введите дату рождения (гггг-мм-дд):");
        LocalDate birthDate = LocalDate.parse(scanner.nextLine());

        System.out.println("Введите пол (Мужской/Женский):");
        String genderInput = scanner.nextLine().trim();

        Gender gender;
        try {
            gender = parseGender(genderInput);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Неизвестный пол: " + genderInput);
        }

        // Выбор супруга
        List<Person> people = presenter.getAllPeople();
        String spouseFullName = null;
        if (!people.isEmpty()) {
            System.out.println("Выберите супруга из следующего списка (введите номер или 0 если нет):");
            printPeopleList(people);
            int spouseIndex = Integer.parseInt(scanner.nextLine().trim());
            if (spouseIndex > 0 && spouseIndex <= people.size()) {
                spouseFullName = people.get(spouseIndex - 1).getFullName();
            }
        }

        // Выбор родителей
        String parent1FullName = null;
        System.out.println("Выберите первого родителя из следующего списка (введите номер или 0 если нет):");
        if (!people.isEmpty()) {
            printPeopleList(people);
            int parent1Index = Integer.parseInt(scanner.nextLine().trim());
            if (parent1Index > 0 && parent1Index <= people.size()) {
                parent1FullName = people.get(parent1Index - 1).getFullName();
            }
        }

        String parent2FullName = null;
        System.out.println("Выберите второго родителя из следующего списка (введите номер или 0 если нет):");
        if (!people.isEmpty()) {
            printPeopleList(people);
            int parent2Index = Integer.parseInt(scanner.nextLine().trim());
            if (parent2Index > 0 && parent2Index <= people.size()) {
                parent2FullName = people.get(parent2Index - 1).getFullName();
            }
        }

        presenter.addPerson(firstName, patronymic, lastName, birthDate, gender, spouseFullName, parent1FullName, parent2FullName);
    }

    private void printPeopleList(List<Person> people) {
        for (int i = 0; i < people.size(); i++) {
            Person person = people.get(i);
            System.out.println((i + 1) + ". " + person.getFullName());
        }
    }

    private Gender parseGender(String genderInput) {
        switch (genderInput.toLowerCase()) {
            case "мужской":
                return Gender.Мужской;
            case "женский":
                return Gender.Женский;
            default:
                throw new IllegalArgumentException("Неизвестный пол: " + genderInput);
        }
    }
}
