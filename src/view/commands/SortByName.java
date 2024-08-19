package view.commands;

import view.ConsoleUl;

public class SortByName extends Command {

    public SortByName(ConsoleUl consoleUl) {
        super("Отсортировать по имени", consoleUl);
    }

    @Override
    public void execute() {
        getConsoleUl().sortByName();
    }
}
