package view;

import model.Human.Person;
import java.util.List;

public interface View {
    void start();
    void printAnswer(String answer);
    void displayPeople(String peopleInfo);
}
