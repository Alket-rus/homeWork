package model;

import model.Family.Gender;
import model.Human.Person;

import java.time.LocalDate;
import java.util.List;

public interface PersonManager {
    void addPerson(String firstName, String middleName, String lastName, LocalDate birthDate, Gender gender,
                   String spouseFullName, String parent1FullName, String parent2FullName);

    List<Person> getAllPeople();

    List<Person> sortByName();

    List<Person> sortByAge();
}
