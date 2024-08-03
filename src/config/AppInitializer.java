package config;

import model.Family.FamilyManager;
import model.GenealogicalTree.GenealogicalTree;
import model.Human.Person;
import model.Family.FileManager;
import presenter.Presenter;
import view.ConsoleUl;

import static model.Family.FileManager.saveAndLoadTest;

public class AppInitializer {
    private Presenter presenter;
    private ConsoleUl consoleUl;
    private FamilyManager familyManager;
    private GenealogicalTree<Person> tree;

    public void initialize() {
        tree = FileManager.load();
        if (tree == null) {
            tree = new GenealogicalTree<>();
        }

        familyManager = new FamilyManager(tree, null);

        if (tree.getAllPeople().isEmpty()) {
            familyManager.createPeople();
            familyManager.establishRelationships();
            familyManager.establishMarriages();
            familyManager.addNewFamilies();
        }
        consoleUl = new ConsoleUl(null);
        presenter = new Presenter(consoleUl, tree);
        consoleUl.setPresenter(presenter);


        Runtime.getRuntime().addShutdownHook(new Thread(this::saveState));

        familyManager.printFamilyInfo();
    }

    private void saveState() {
        if (tree != null) {
            FileManager.save(tree);
            System.out.println("Состояние, сохраненное перед выходом программы.");
        }
    }

    public ConsoleUl getConsoleUl() {
        return consoleUl;
    }
}
