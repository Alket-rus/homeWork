package view;

import presenter.Presenter;
import model.Family.PersonInputHandler;
import java.util.Scanner;

public class ConsoleUl implements View {
    private Scanner scanner;
    private Presenter presenter;
    private boolean work;
    private MainMenu menu;

    public ConsoleUl(Presenter presenter) {
        this.scanner = new Scanner(System.in);
        this.presenter = presenter;
        this.work = true;
        this.menu = new MainMenu(this);
    }

    // Метод для установки Presenter после создания объекта ConsoleUl
    public void setPresenter(Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void start() {
        while (work) {
            System.out.println(menu.menu());
            String choice = scanner.nextLine();
            int choiceInt = Integer.parseInt(choice);
            menu.execute(choiceInt);
        }
    }

    public void finish() {
        work = false;
    }

    public void showTree() {
        presenter.showTree();
    }

    public void sortByAge() {
        presenter.sortByAge();
    }

    public void sortByName() {
        presenter.sortByName();
    }

    public void addPerson() {
        PersonInputHandler handler = new PersonInputHandler(presenter);
        handler.handleAddPerson();
    }

    @Override
    public void printAnswer(String answer) {
        System.out.print(answer);
    }
}
