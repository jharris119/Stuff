package info.jayharris.stuff;

import com.google.common.collect.ForwardingDeque;
import com.google.common.collect.ForwardingIterator;
import com.google.common.collect.Sets;

import java.util.*;

/**
 * A deque that only contains at most one instance of each of its elements.
 *
 * @param <E>
 */
public class UniqueEntryDeque<E> extends ForwardingDeque<E> {

    final Deque<E> delegate;
    final Set<E> delegateSet;

    public UniqueEntryDeque() {
        this.delegate = new ArrayDeque<>();
        this.delegateSet = Sets.newHashSet();
    }

    @Override
    protected Deque<E> delegate() {
        return delegate;
    }

    @Override
    public boolean add(E e) {
        if (contains(e)) {
            return false;
        }
        delegate().add(e);
        return delegateSet.add(e);
    }

    @Override
    public void addFirst(E e) {
        if (contains(e)) {
            return;
        }
        super.addFirst(e);
        delegateSet.add(e);
    }

    @Override
    public void addLast(E e) {
        add(e);
    }

    @Override
    public void push(E e) {
        addFirst(e);
    }

    @Override
    public boolean addAll(Collection<? extends E> collection) {
        return standardAddAll(collection);
    }

    @Override
    public boolean offer(E o) {
        return standardOffer(o);
    }

    @Override
    public boolean offerFirst(E e) {
        if (contains(e)) {
            return false;
        }
        super.offerFirst(e);
        return delegateSet.add(e);
    }

    @Override
    public boolean offerLast(E e) {
        return offer(e);
    }

    @Override
    public boolean contains(Object object) {
        return delegateSet.contains(object);
    }

    @Override
    public boolean containsAll(Collection<?> collection) {
        return standardContainsAll(collection);
    }

    @Override
    public E remove() {
        E x = super.remove();
        delegateSet.remove(x);
        return x;
    }

    @Override
    public E removeFirst() {
        return remove();
    }

    @Override
    public E removeLast() {
        E x = super.removeLast();
        delegateSet.remove(x);
        return x;
    }

    @Override
    public E pop() {
        return remove();
    }

    @Override
    public boolean remove(Object object) {
        return standardRemove(object);      // delegates to iterator().remove(), which calls delegateSet.remove()
    }

    @Override
    public boolean removeFirstOccurrence(Object o) {
        return remove(o);
    }

    @Override
    public boolean removeLastOccurrence(Object o) {
        return remove(o);
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        return standardRemoveAll(collection);
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        return standardRetainAll(collection);
    }

    @Override
    public void clear() {
        super.clear();
        delegateSet.clear();
    }

    @Override
    public E poll() {
        return standardPoll();
    }

    @Override
    public E pollFirst() {
        return poll();
    }

    @Override
    public E pollLast() {
        try {
            return removeLast();
        }
        catch (NoSuchElementException e) {
            return null;
        }
    }

    @Override
    public Iterator<E> iterator() {
        final Iterator<E> delegate = super.iterator();
        final UniqueEntryDeque<E> owner = UniqueEntryDeque.this;

        return new ForwardingIterator<E>() {
            E prev;

            @Override
            protected Iterator<E> delegate() {
                return delegate;
            }

            @Override
            public E next() {
                return prev = super.next();
            }

            @Override
            public void remove() {
                if (prev == null) {
                    throw new IllegalStateException();
                }
                owner.delegate.remove(prev);
                owner.delegateSet.remove(prev);
                prev = null;
            }
        };
    }

    @Override
    public Iterator<E> descendingIterator() {
        // TODO: implement this
        return null;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    /**
     * Compares the specified object with this deque for equality. Returns true
     * if and only if the specified object is also a deque, both deques have the
     * same size, and all corresponding pairs of elements in the two deque are
     * equals. In other words, two deques are defined to be equal if they
     * contain the same elements in the same order.
     *
     * This implementation first checks if the specified object is this deque.
     * If so, it returns true; if not, it checks if the specified object is a
     * deque. If not, it returns false; if so, it iterates over both deques,
     * comparing corresponding pairs of elements. If any comparison returns
     * false, this method returns false. If either iterator runs out of
     * elements before the other it returns false (as the lists are of unequal
     * length); otherwise it returns true when the iterations complete.
     *
     * @param o the object to be compared for equality with this deque
     * @return <code>true</code>  if the specified object is equal to this deque
     */
    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Deque)) {
            return false;
        }

        Iterator<E> e1 = iterator();
        Iterator e2 = ((Deque) o).iterator();
        while (e1.hasNext() && e2.hasNext()) {
            E o1 = e1.next();
            Object o2 = e2.next();
            if (!(o1 == null ? o2 == null : o1.equals(o2)))
                return false;
        }
        return !(e1.hasNext() || e2.hasNext());
    }

    @Override
    public int hashCode() {
        int hashCode = 1;
        for (E e : this) {
            hashCode = 31 * hashCode + (e == null ? 0 : e.hashCode());
        }
        return hashCode;
    }
}
