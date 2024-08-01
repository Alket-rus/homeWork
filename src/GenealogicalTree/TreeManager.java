package GenealogicalTree;

import Family.Gender;
import Human.Person;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

public interface TreeManager<T> extends Serializable {

    String getFullName();

    LocalDate getBirthDate();

    Gender getGender();

    T getSpouse();

    void setSpouse(T spouse);

    List<T> getChildren();

    void addChild(T child);

    void addParent(Person parent);

    List<Person> getParents();

    int getAge();
}
