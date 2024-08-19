package view;

import view.commands.*;

import java.util.ArrayList;
import java.util.List;

public class MainMenu {
    private List<Command> commands;

    public MainMenu(ConsoleUl consoleUl) {
        commands = new ArrayList<>();
        commands.add(new AddPerson(consoleUl));
        commands.add(new SaveTree(consoleUl));
        commands.add(new LoadTree(consoleUl));
        commands.add(new SortByName(consoleUl));
        commands.add(new SortByAge(consoleUl));
        commands.add(new ShowTree(consoleUl));
        commands.add(new Finish(consoleUl));
    }

    public String menu() {
        StringBuilder menu = new StringBuilder();
        for (int i = 0; i < commands.size(); i++) {
            menu.append(i).append(". ").append(commands.get(i).getDescription()).append("\n");
        }
        return menu.toString();
    }

    public void execute(int choice) {
        if (choice >= 0 && choice < commands.size()) {
            commands.get(choice).execute();
        } else {
            System.out.println("Неправильный выбор, попробуйте снова.");
        }
    }
}
