package view;

import model.PersonManager;
import presenter.Presenter;
import model.Human.Person;

import java.util.List;
import java.util.Scanner;

public class ConsoleUl implements View {
    private final Scanner scanner;
    private Presenter presenter;
    private boolean work;
    private final MainMenu menu;

    public ConsoleUl(Presenter presenter) {
        this.presenter = presenter;
        this.scanner = new Scanner(System.in);
        this.work = true;
        this.menu = new MainMenu(this);
    }

    @Override
    public void start() {
        while (work) {
            System.out.println(menu.menu());
            int choice = Integer.parseInt(scanner.nextLine());
            menu.execute(choice);
        }
    }

    public void setPresenter(Presenter presenter) {
        this.presenter = presenter;
    }

    public void finish() {
        work = false;
    }

    public void showTree() {
        if (presenter != null) {
            presenter.showTree();
        } else {
            printAnswer("Презентатор не инициализирован.");
        }
    }

    public void sortByAge() {
        if (presenter != null) {
            presenter.sortByAge();
        } else {
            printAnswer("Презентатор не инициализирован.");
        }
    }

    public void sortByName() {
        if (presenter != null) {
            presenter.sortByName();
        } else {
            printAnswer("Презентатор не инициализирован.");
        }
    }

    public void addPerson() {
        if (presenter != null) {
            PersonManager personManager = presenter.getPersonManager();  // Предполагаем, что getPersonManager доступен
            PersonInputHandler handler = new PersonInputHandler(personManager, new UserInputHandler());
            handler.handleAddPerson();
        } else {
            printAnswer("Презентатор не инициализирован.");
        }
    }

    @Override
    public void printAnswer(String answer) {
        System.out.println(answer);
    }

    @Override
    public void displayPeople(List<Person> people) {
        String formattedList = formatPeopleList(people);
        printAnswer(formattedList);
    }

    private String formatPeopleList(List<Person> people) {
        StringBuilder sb = new StringBuilder();
        for (Person person : people) {
            sb.append(person.toString()).append("\n");
        }
        return sb.toString();
    }
}
