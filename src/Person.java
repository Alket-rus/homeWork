import java.util.ArrayList;
import java.util.List;

public class Person {
    private String firstName;
    private String middleName;
    private String lastName;
    private String birthDate;
    private String gender;
    private List<Person> children;
    private Person spouse;

    public Person(String firstName, String middleName, String lastName, String birthDate, String gender) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.gender = gender;
        this.children = new ArrayList<>();
        this.spouse = null;
    }

    public void addChild(Person child) {
        children.add(child);
    }

    public List<Person> getChildren() {
        return children;
    }

    public String getFullName() {
        return lastName + " " + firstName + " " + middleName;
    }

    public String getGender() {
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
        return getFullName() + " (" + gender + ", " + getBirthVerb() + " " + birthDate + ")";
    }

    private String getBirthVerb() {
        return gender.equals("мужской пол") ? "родился" : "родилась";
    }
}
