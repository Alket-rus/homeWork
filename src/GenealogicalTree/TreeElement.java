package GenealogicalTree;

import Family.Gender;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

public interface TreeElement<T extends TreeElement<T>> extends Serializable {
    String getFullName();
    LocalDate getBirthDate();
    Gender getGender();
    T getSpouse();
    List<T> getChildren();
    void addParent(T parent);
    void setSpouse(T spouse);
    List<T> getParents();
    int getAge();
    void addChild(T child);
}
