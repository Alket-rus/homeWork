package view;

import model.PersonManager;
import presenter.Presenter;

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
            printAnswer("Генеалогическое дерево:");
            presenter.showTree();
        } else {
            printAnswer("Презентатор не инициализирован.");
        }
    }

    public void sortByAge() {
        if (presenter != null) {
            printAnswer("Люди, отсортированные по возрасту:");
            presenter.sortByAge();
        } else {
            printAnswer("Презентатор не инициализирован.");
        }
    }

    public void sortByName() {
        if (presenter != null) {
            printAnswer("Люди, отсортированные по имени:");
            presenter.sortByName();
        } else {
            printAnswer("Презентатор не инициализирован.");
        }
    }

    public void addPerson() {
        if (presenter != null) {
            PersonManager personManager = presenter.getPersonManager();
            PersonInputHandler handler = new PersonInputHandler(personManager, new UserInputHandler());
            handler.handleAddPerson();
        } else {
            printAnswer("Презентатор не инициализирован.");
        }
    }

    public void saveTree() {
        if (presenter != null) {
            printAnswer("Генеалогическое дерево сохранено.");
            presenter.saveTree();
        } else {
            printAnswer("Ошибка: Сервис семейного дерева не найден.");
        }
    }

    public void loadTree() {
        if (presenter != null) {
            printAnswer("Генеалогическое дерево загружено.");
            presenter.loadTree();
        } else {
            printAnswer("Ошибка: Сервис семейного дерева не найден.");
        }
    }

    @Override
    public void printAnswer(String answer) {
        System.out.println(answer);
    }

    @Override
    public void displayPeople(String peopleInfo) {
        printAnswer(peopleInfo);
    }
}
