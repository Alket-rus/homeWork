package view.commands;

import view.ConsoleUl;

public abstract class Command {
    private String description;
    private ConsoleUl consoleUl;

    public Command(String description, ConsoleUl consoleUl) {
        this.description = description;
        this.consoleUl = consoleUl;  // Инициализируем поле consoleUl
    }

    public String getDescription() {
        return description;
    }

    protected ConsoleUl getConsoleUl() {  // Изменяем модификатор доступа на protected
        return consoleUl;
    }

    public abstract void execute();
}
