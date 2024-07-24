package Sorting;

import Human.Person;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Sort {


    public static void sortByAge(List<Person> people) {
        Collections.sort(people, new Comparator<Person>() {
            @Override
            public int compare(Person p1, Person p2) {
                return Integer.compare(p1.getAge(), p2.getAge());
            }
        });
    }


    public static void sortByName(List<Person> people) {
        Collections.sort(people, new Comparator<Person>() {
            @Override
            public int compare(Person p1, Person p2) {
                return p1.getFullName().compareTo(p2.getFullName());
            }
        });
    }
}
