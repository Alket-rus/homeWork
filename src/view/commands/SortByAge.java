package view.commands;

import view.ConsoleUl;

public class SortByAge extends Command {

    public SortByAge(ConsoleUl consoleUl) {
        super("Люди, отсортированные по возрасту:", consoleUl);
    }

    @Override
    public void execute() {
        getConsoleUl().sortByAge();
    }
}