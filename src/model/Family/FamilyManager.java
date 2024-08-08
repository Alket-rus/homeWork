package model.Family;

import model.GenealogicalTree.GenealogicalTree;
import model.Human.Person;
import java.util.List;
import java.util.Optional;

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

    private String getFamilyInfo(String parentFullName) {
        Optional<Person> optionalParent = tree.getPerson(parentFullName);
        if (optionalParent.isEmpty()) return "";

        Person parent = optionalParent.get();
        Person spouse = parent.getSpouse();
        List<Person> children = parent.getChildren();

        StringBuilder sb = new StringBuilder();
        sb.append(parentFullName);
        if (spouse != null) {
            sb.append(" и ").append(getSpouseTitle(spouse));
        }
        sb.append(" Дети: ");

        String childrenNames = children.stream()
                .map(child -> String.format("%s (Возраст: %d, Пол: %s)",
                        child.getFullName(), child.getAge(), child.getGender().name()))
                .reduce((a, b) -> a + ", " + b)
                .orElse("");

        sb.append(childrenNames);
        return sb.toString();
    }

    private String getSpouseTitle(Person spouse) {
        if (spouse.getGender() == Gender.Мужской) {
            return "Супруг " + spouse.getFullName();
        } else {
            return "Супруга " + spouse.getFullName();
        }
    }
}
