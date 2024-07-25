package GenealogicalTree;

import Human.Person;
import java.util.Iterator;
import java.util.Map;

public class GenealogicalTreeIterator implements Iterator<Person> {
    private Iterator<Person> iterator;

    public GenealogicalTreeIterator(Map<String, Person> people) {
        this.iterator = people.values().iterator();
    }

    @Override
    public boolean hasNext() {
        return iterator.hasNext();
    }

    @Override
    public Person next() {
        return iterator.next();
    }
}
