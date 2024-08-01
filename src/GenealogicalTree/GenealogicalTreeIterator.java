package GenealogicalTree;

import java.util.Iterator;
import java.util.Map;

public class GenealogicalTreeIterator<T> implements Iterator<T> {
    private final Iterator<T> iterator;

    public GenealogicalTreeIterator(Map<String, T> entities) {
        this.iterator = entities.values().iterator();
    }

    @Override
    public boolean hasNext() {
        return iterator.hasNext();
    }

    @Override
    public T next() {
        return iterator.next();
    }
}
