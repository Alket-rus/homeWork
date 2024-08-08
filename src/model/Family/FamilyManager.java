package model.Family;

import model.GenealogicalTree.GenealogicalTree;
import model.Human.Person;

public class FamilyManager {
    private final GenealogicalTree<Person> tree;
    private final FileHandler fileHandler;
    private final PersonCreatorInterface personCreator;
    private final RelationshipManagerInterface relationshipManager;

    public FamilyManager(GenealogicalTree<Person> tree, FileHandler fileHandler, PersonCreatorInterface personCreator, RelationshipManagerInterface relationshipManager) {
        this.tree = tree;
        this.fileHandler = fileHandler;
        this.personCreator = personCreator;
        this.relationshipManager = relationshipManager;
    }

    public PersonCreatorInterface getPersonCreator() {
        return personCreator;
    }

    public RelationshipManagerInterface getRelationshipManager() {
        return relationshipManager;
    }

    public void initializeFamilyData() {
        personCreator.createPeople();
        relationshipManager.establishRelationships();
        relationshipManager.establishMarriages();
        personCreator.addNewFamilies();
    }

}
