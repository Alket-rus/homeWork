package config;

import model.Family.FamilyManager;
import model.Family.PersonCreator;
import model.Family.PersonCreatorInterface;
import model.Family.RelationshipManager;
import model.Family.RelationshipManagerInterface;
import model.GenealogicalTree.GenealogicalTree;
import model.Human.Person;
import model.Family.FileHandler;
import presenter.Presenter;
import view.ConsoleUl;
import model.FamilyService;

public class AppInitializer {
    private Presenter presenter;
    private ConsoleUl consoleUl;
    private FamilyManager familyManager;
    private GenealogicalTree<Person> tree;
    private FileHandler fileHandler;
    private PersonCreatorInterface personCreator;
    private RelationshipManagerInterface relationshipManager;
    private FamilyService familyService;

    public void initialize() {
        String filePath = "src/model/GenealogicalTree/tree.txt";
        fileHandler = new FileHandler(filePath);

        tree = (GenealogicalTree<Person>) fileHandler.read();
        if (tree == null) {
            tree = new GenealogicalTree<>();
        }

        personCreator = new PersonCreator(tree);
        relationshipManager = new RelationshipManager(tree);
        familyManager = new FamilyManager(tree, fileHandler, personCreator, relationshipManager);

        familyService = new FamilyService(tree, fileHandler);

        if (tree.getAllPeople().isEmpty()) {
            familyManager.initializeFamilyData();
        }

        consoleUl = new ConsoleUl(null);
        presenter = new Presenter(consoleUl, familyService);
        consoleUl.setPresenter(presenter);

        Runtime.getRuntime().addShutdownHook(new Thread(this::saveState));

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
