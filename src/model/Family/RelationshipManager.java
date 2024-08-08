package model.Family;

import model.GenealogicalTree.GenealogicalTree;
import model.Human.Person;

public class RelationshipManager implements RelationshipManagerInterface {
    private final GenealogicalTree<Person> tree;

    public RelationshipManager(GenealogicalTree<Person> tree) {
        this.tree = tree;
    }

    @Override
    public void establishRelationships() {
        tree.addRelationship("Смирнов Пётр Иванович", "Смирнова Анна Петровна");
        tree.addRelationship("Смирнова Евгения Сергеевна", "Смирнова Анна Петровна");
        tree.addRelationship("Смирнова Евгения Сергеевна", "Смирнова Алиса Евгеньевна");
        tree.addRelationship("Смирнова Анна Петровна", "Смирнов Иван Андреевич");
        tree.addRelationship("Смирнов Николай Иванович", "Смирнов Алексей Николаевич");
    }

    @Override
    public void establishMarriages() {
        tree.addMarriage("Смирнов Пётр Иванович", "Смирнова Евгения Сергеевна");
        tree.addMarriage("Смирнова Анна Петровна", "Смирнов Андрей Владимирович");
        tree.addMarriage("Смирнов Николай Иванович", "Смирнова Мария Алексеевна");
    }
}
