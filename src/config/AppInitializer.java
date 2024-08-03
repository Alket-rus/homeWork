package config;

import model.Family.FamilyManager;
import model.GenealogicalTree.GenealogicalTree;
import model.Human.Person;
import model.Family.FileHandler;
import model.Family.FileOperations;
import presenter.Presenter;
import view.ConsoleUl;

import static model.Family.FileManager.saveAndLoad;

public class AppInitializer {
    private Presenter presenter;
    private ConsoleUl consoleUl;
    private FamilyManager familyManager;
    private GenealogicalTree<Person> tree;
    private FileOperations fileOperations;

    public void initialize() {
        fileOperations = new FileHandler("family_tree.dat");
        tree = new GenealogicalTree<>();
        familyManager = new FamilyManager(tree, fileOperations);

        familyManager.createPeople();
        familyManager.establishRelationships();
        familyManager.establishMarriages();
        familyManager.addNewFamilies();
        saveAndLoad();
        consoleUl = new ConsoleUl(null);
        presenter = new Presenter(consoleUl, tree);
        consoleUl.setPresenter(presenter);

        if (tree.getAllPeople().isEmpty()) {
            tree.saveTree(fileOperations);
        } else {
            familyManager.printFamilyInfo();
        }

        Runtime.getRuntime().addShutdownHook(new Thread(this::saveState));
    }

    private void saveState() {
        if (tree != null && fileOperations != null) {
            tree.saveTree(fileOperations);
            System.out.println("State saved before exit.");
        }
    }

    public ConsoleUl getConsoleUl() {
        return consoleUl;
    }
}
