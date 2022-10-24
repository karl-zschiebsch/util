package com.util.collection;

import java.util.BitSet;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import com.util.beans.InvalidationListener;

public class ObservableListWrapper<E> implements ObservableList<E> {
    private final ListListenerHelper<E> helper = new ListListenerHelper<>();

    private final List<E> backing;

    public ObservableListWrapper(List<E> list) {
        backing = list;
    }

    private class Change implements ListChangeListener.Change<E> {
        private final int index;
        private final E removed;
        private final E added;
        private final boolean wasAdded;
        private final boolean wasRemoved;

        public Change(int index, E removed, E added, boolean wasAdded, boolean wasRemoved) {
            assert (wasAdded | wasRemoved);
            this.index = index;
            this.removed = removed;
            this.added = added;
            this.wasAdded = wasAdded;
            this.wasRemoved = wasRemoved;
        }

        @Override
        public E getAdded() {
            return added;
        }

        @Override
        public E getRemoved() {
            return removed;
        }

        @Override
        public boolean wasAdded() {
            return wasAdded;
        }

        @Override
        public boolean wasRemoved() {
            return wasRemoved;
        }

        @Override
        public ObservableList<E> getList() {
            return ObservableListWrapper.this;
        }

        @Override
        public int getIndex() {
            return index;
        }
    }

    @Override
    public void addListener(InvalidationListener listener) {
        helper.addListener(listener);
    }

    @Override
    public void removeListener(InvalidationListener listener) {
        helper.removeListener(listener);
    }

    @Override
    public void addListener(ListChangeListener<? super E> listener) {
        helper.addListener(listener);
    }

    @Override
    public void removeListener(ListChangeListener<? super E> listener) {
        helper.removeListener(listener);
    }

    @Override
    public int size() {
        return backing.size();
    }

    @Override
    public boolean isEmpty() {
        return backing.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return backing.contains(o);
    }

    @Override
    public Iterator<E> iterator() {
        return backing.iterator();
    }

    @Override
    public Object[] toArray() {
        return backing.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return backing.toArray(a);
    }

    @Override
    public void add(int index, E element) {
        backing.add(index, element);
        helper.fireValueChangedEvent(new Change(index, null, element, true, false));
    }

    @Override
    public E set(int index, E element) {
        E removed = backing.set(index, element);
        helper.fireValueChangedEvent(new Change(index, removed, element, true, false));
        return removed;
    }

    @Override
    public E remove(int index) {
        E removed = backing.remove(index);
        helper.fireValueChangedEvent(new Change(index, removed, null, true, false));
        return removed;
    }

    @Override
    public boolean add(E e) {
        add(backing.size(), e);
        return true;
    }

    @Override
    public boolean remove(Object o) {
        if (!backing.contains(o)) {
            return false;
        }
        remove(backing.indexOf(o));
        return true;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return backing.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        if (c.isEmpty()) {
            return false;
        }
        for (E e : c) {
            add(backing.size(), e);
        }
        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        if (c.isEmpty()) {
            return false;
        }
        for (E e : c) {
            add(index, e);
            index++;
        }
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        if (c.isEmpty()) {
            return false;
        }
        for (Object o : c) {
            remove(o);
        }
        return true;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        if (c.isEmpty()) {
            return false;
        }
        BitSet bs = new BitSet(c.size());
        for (int i = 0; i < backing.size(); ++i) {
            if (!c.contains(backing.get(i))) {
                bs.set(i);
            }
        }
        if (!bs.isEmpty()) {
            int cur = backing.size();
            while ((cur = bs.previousSetBit(cur - 1)) > -1) {
                remove(cur);
            }
            return true;
        }
        return false;
    }

    @Override
    public void clear() {
        if (!isEmpty()) {
            for (int i = backing.size(); i > -1; --i) {
                remove(i);
            }
        }
    }

    @Override
    public E get(int index) {
        return backing.get(index);
    }

    @Override
    public int indexOf(Object o) {
        return backing.indexOf(o);
    }

    @Override
    public int lastIndexOf(Object o) {
        return backing.lastIndexOf(o);
    }

    @Override
    public ListIterator<E> listIterator() {
        return backing.listIterator();
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return backing.listIterator(index);
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        return backing.subList(fromIndex, toIndex);
    }
}
