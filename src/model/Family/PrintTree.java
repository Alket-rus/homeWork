package model.Family;

import model.GenealogicalTree.GenealogicalTree;
import model.Human.Person;

public class PrintTree {
    private GenealogicalTree<Person> tree;

    public PrintTree(GenealogicalTree<Person> tree) {
        this.tree = tree;
    }

    public String getFormattedTree() {
        StringBuilder sb = new StringBuilder();
        for (Person person : tree) {
            appendPerson(sb, person, 0);
        }
        return sb.toString();
    }

    private void appendPerson(StringBuilder sb, Person person, int level) {
        indent(sb, level);
        sb.append(person.getFullName()).append(" (").append(person.getGender()).append(" ").append(person.getBirthDate()).append(")");
        if (person.getSpouse() != null) {
            sb.append(" и Супруг/Супруга ").append(person.getSpouse().getFullName());
        }
        sb.append("\n");

        for (Person child : person.getChildren()) {
            appendPerson(sb, child, level + 1);
        }
    }

    private void indent(StringBuilder sb, int level) {
        for (int i = 0; i < level; i++) {
            sb.append("  ");
        }
    }
}
