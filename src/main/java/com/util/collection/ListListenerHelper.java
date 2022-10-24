package com.util.collection;

import java.util.HashSet;
import java.util.Set;

import com.util.beans.InvalidationListener;
import com.util.collection.ListChangeListener.Change;

public class ListListenerHelper<E> {
    Set<InvalidationListener> invalidationListeners = new HashSet<>();
    Set<ListChangeListener<? super E>> listChangeListeners = new HashSet<>();

    protected void fireValueChangedEvent(Change<? extends E> change) {
        for (InvalidationListener listener : invalidationListeners) {
            listener.invalidated(change.getList());
        }

        for (ListChangeListener<? super E> listener : listChangeListeners) {
            listener.onChanged(change);
        }
    }

    public void addListener(InvalidationListener listener) {
        invalidationListeners.add(listener);
    }

    public void removeListener(InvalidationListener listener) {
        invalidationListeners.remove(listener);
    }

    public void addListener(ListChangeListener<? super E> listener) {
        listChangeListeners.add(listener);
    }

    public void removeListener(ListChangeListener<? super E> listener) {
        listChangeListeners.remove(listener);
    }
}
