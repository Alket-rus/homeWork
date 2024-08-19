package view.commands;

import view.ConsoleUl;

public class LoadTree extends Command {

    public LoadTree(ConsoleUl consoleUl) {
        super("Загрузить дерево:", consoleUl);
    }

    @Override
    public void execute() {
        getConsoleUl().loadTree();
    }
}

