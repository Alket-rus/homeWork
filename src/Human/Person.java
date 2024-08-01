package Human;

import Family.Gender;
import GenealogicalTree.TreeManager;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class Person implements TreeManager<Person> {
    private String firstName;
    private String lastName;
    private String patronymic;
    private LocalDate birthdate;
    private Gender gender;
    private Person spouse;
    private List<Person> children;
    private List<Person> parents;

    public Person(String firstName, String patronymic, String lastName, LocalDate birthdate, Gender gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.birthdate = birthdate;
        this.gender = gender;
        this.children = new ArrayList<>();
        this.parents = new ArrayList<>();
    }

    @Override
    public String getFullName() {
        return lastName + " " + firstName + " " + patronymic;
    }

    @Override
    public LocalDate getBirthDate() {
        return birthdate;
    }

    @Override
    public Gender getGender() {
        return gender;
    }

    @Override
    public void setSpouse(Person spouse) {
        this.spouse = spouse;
    }

    @Override
    public Person getSpouse() {
        return spouse;
    }

    @Override
    public void addChild(Person child) {
        this.children.add(child);
        child.addParent(this);
    }

    @Override
    public List<Person> getChildren() {
        return children;
    }

    @Override
    public void addParent(Person parent) {
        this.parents.add(parent);
    }

    @Override
    public List<Person> getParents() {
        return parents;
    }

    @Override
    public int getAge() {
        return (int) ChronoUnit.YEARS.between(birthdate, LocalDate.now());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getFullName())
                .append(" (")
                .append(gender.name())
                .append(" ")
                .append(birthdate)
                .append(")");
        return sb.toString();
    }
}
