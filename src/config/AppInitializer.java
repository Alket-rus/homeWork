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

        familyManager = new FamilyManager(tree, null); // FileOperations не требуется

        // Создание и обновление данных, если данные не загружены
        if (tree.getAllPeople().isEmpty()) {
            familyManager.createPeople();
            familyManager.establishRelationships();
            familyManager.establishMarriages();
            familyManager.addNewFamilies();
        }
        consoleUl = new ConsoleUl(null); // Объект ConsoleUl создается без Presenter
        presenter = new Presenter(consoleUl, tree); // Передаем консольный интерфейс в Presenter
        consoleUl.setPresenter(presenter); // Устанавливаем Presenter в ConsoleUl

        // Добавляем обработчик завершения работы программы
        Runtime.getRuntime().addShutdownHook(new Thread(this::saveState));

        familyManager.printFamilyInfo();
    }

    private void saveState() {
        // Метод для сохранения состояния перед завершением работы программы
        if (tree != null) {
            FileManager.save(tree);
            System.out.println("State saved before exit.");
        }
    }

    public ConsoleUl getConsoleUl() {
        return consoleUl;
    }
}
