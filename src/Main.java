import config.AppInitializer;
import view.ConsoleUl;

public class Main {
    public static void main(String[] args) {
        AppInitializer appInitializer = new AppInitializer();
        appInitializer.initialize();

        ConsoleUl consoleUl = appInitializer.getConsoleUl();
        consoleUl.start();
    }
}