package Human;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Person implements Serializable {
    private static final long serialVersionUID = 1L;
    private String firstName;
    private String middleName;
    private String lastName;
    private String birthDate; // Формат: yyyy-MM-dd
    private List<Person> children;
    private List<Person> parents;
    private Gender gender;
    private Person spouse;


    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public Person(String firstName, String middleName, String lastName, String birthDate, Gender gender) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.gender = gender;
        this.children = new ArrayList<>();
        this.parents = new ArrayList<>();
        this.spouse = null;
    }

    // Метод для добавления ребенка
    public void addChild(Person child) {
        if (child == null) {
            throw new IllegalArgumentException("Ребенок не может быть null.");
        }
        children.add(child);
        if (!child.getParents().contains(this)) {
            child.getParents().add(this);
        }
    }

    public int getAge() {
        LocalDate birthDate = LocalDate.parse(this.birthDate, DATE_FORMATTER);
        LocalDate today = LocalDate.now();
        return Period.between(birthDate, today).getYears();
    }


    public List<Person> getChildren() {
        return children;
    }

    public List<Person> getParents() {
        return parents;
    }

    public String getFullName() {
        return lastName + " " + firstName + " " + middleName;
    }

    public Gender getGender() {
        return gender;
    }

    public Person getSpouse() {
        return spouse;
    }

    public void setSpouse(Person spouse) {
        this.spouse = spouse;
    }

    @Override
    public String toString() {
        String childrenNames = children.stream()
                .map(child -> String.format("%s (Возраст: %d, Пол: %s)",
                        child.getFullName(), child.getAge(), child.getGender().name()))
                .reduce((a, b) -> a + ", " + b)
                .orElse("");

        String parentsNames = parents.stream()
                .map(Person::getFullName)
                .reduce((a, b) -> a + ", " + b)
                .orElse("");

        String spouseTitle = "";
        if (spouse != null) {
            spouseTitle = (gender == Gender.Мужской ? "Супруга " : "Супруг ") + spouse.getFullName();
        }

        return String.format("%s %s %s ( %s Дети: [%s] Родители: [%s] %s %s)",
                lastName, firstName, middleName,
                spouseTitle,
                childrenNames,
                parentsNames,
                gender.name(),
                birthDate);
    }
}
