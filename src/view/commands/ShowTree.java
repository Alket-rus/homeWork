package view.commands;

import view.ConsoleUl;

public class ShowTree extends Command {

    public ShowTree(ConsoleUl consoleUl) {
        super("Показать генеалогическое дерево", consoleUl);
    }

    @Override
    public void execute() {
        getConsoleUl().showTree();
    }
}