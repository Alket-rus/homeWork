package model;

import model.Human.Person;
import java.util.List;

public class PersonFormatter {
    public String formatPeopleList(List<Person> people) {
        StringBuilder sb = new StringBuilder();
        for (Person person : people) {
            sb.append(person.toString()).append("\n");
        }
        return sb.toString();
    }
}
