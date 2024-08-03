package view.commands;

import view.ConsoleUl;

public class Finish extends Command {

    public Finish(ConsoleUl consoleUl) {
        super("Выход:", consoleUl);
    }

    @Override
    public void execute() {
        getConsoleUl().finish();
    }
}