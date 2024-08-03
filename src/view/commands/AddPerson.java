package view.commands;

import view.ConsoleUl;

public class AddPerson extends Command {

    public AddPerson(ConsoleUl consoleUl) {
        super("Добавить нового человека", consoleUl);
    }

    @Override
    public void execute() {
        getConsoleUl().addPerson();
    }
}