package view.commands;

import view.ConsoleUl;

public class SaveTree extends Command {

    public SaveTree(ConsoleUl consoleUl) {
        super("Сохранить дерево.", consoleUl);
    }

    @Override
    public void execute() {
        getConsoleUl().saveTree();
    }
}

