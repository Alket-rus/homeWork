package GenealogicalTree;

import java.util.Iterator;
import java.util.List;

public class GenealogicalTreeIterator<T extends TreeElement<T>> implements Iterator<T> {
    private final Iterator<T> iterator;

    public GenealogicalTreeIterator(List<T> entities) {
        this.iterator = entities.iterator();
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

