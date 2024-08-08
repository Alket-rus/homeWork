package config;

import model.Family.FamilyManager;
import model.FamilyService;
import model.GenealogicalTree.GenealogicalTree;
import model.Human.Person;
import model.Family.FileHandler;
import presenter.Presenter;
import view.ConsoleUl;

public class AppInitializer {
    private Presenter presenter;
    private ConsoleUl consoleUl;
    private FamilyManager familyManager;
    private GenealogicalTree<Person> tree;
    private FileHandler fileHandler;

    public void initialize() {
        String filePath = "src/model/GenealogicalTree/tree.txt";
        fileHandler = new FileHandler(filePath);

        tree = (GenealogicalTree<Person>) fileHandler.read();
        if (tree == null) {
            tree = new GenealogicalTree<>();
        }

        familyManager = new FamilyManager(tree, fileHandler);
        FamilyService familyService = new FamilyService(tree, fileHandler);

        if (tree.getAllPeople().isEmpty()) {
            familyManager.createPeople();
            familyManager.establishRelationships();
            familyManager.establishMarriages();
            familyManager.addNewFamilies();
        }

        consoleUl = new ConsoleUl(null);
        presenter = new Presenter(consoleUl, familyService);
        consoleUl.setPresenter(presenter);

        Runtime.getRuntime().addShutdownHook(new Thread(this::saveState));

        familyManager.printFamilyInfo();
    }

    private void saveState() {
        if (tree != null) {
            fileHandler.save(tree);
            System.out.println("Состояние, сохраненное перед выходом программы.");
        }
    }

    public ConsoleUl getConsoleUl() {
        return consoleUl;
    }
}
