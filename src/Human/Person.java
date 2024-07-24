package Human;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Person implements Serializable {
    private String firstName;
    private String lastName;
    private String patronymic;
    private String birthdate;
    private Gender gender;
    private Person spouse;
    private List<Person> children;
    private List<Person> parents;

    public Person(String firstName, String patronymic, String lastName, String birthdate, Gender gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.birthdate = birthdate;
        this.gender = gender;
        this.children = new ArrayList<>();
        this.parents = new ArrayList<>();
    }

    public String getFullName() {
        return lastName + " " + firstName + " " + patronymic;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public Gender getGender() {
        return gender;
    }

    public void setSpouse(Person spouse) {
        this.spouse = spouse;
    }

    public Person getSpouse() {
        return spouse;
    }

    public void addChild(Person child) {
        this.children.add(child);
        child.addParent(this); // Автоматически добавляем родителя
    }

    public List<Person> getChildren() {
        return children;
    }

    public void addParent(Person parent) {
        this.parents.add(parent);
    }

    public List<Person> getParents() {
        return parents;
    }

    public int getAge() {
        // Предполагается, что дата рождения в формате "yyyy-MM-dd"
        String[] birthdateParts = birthdate.split("-");
        int birthYear = Integer.parseInt(birthdateParts[0]);
        int currentYear = java.time.LocalDate.now().getYear();
        return currentYear - birthYear;
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
