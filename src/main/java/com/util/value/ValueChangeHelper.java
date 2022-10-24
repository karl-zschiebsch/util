package com.util.value;

import java.util.HashSet;
import java.util.Set;

import com.util.beans.InvalidationListener;
import com.util.value.ValueChangeListener.Change;

public class ValueChangeHelper<T> {
    Set<InvalidationListener> invalidationListeners = new HashSet<>();
    Set<ValueChangeListener<? super T>> listChangeListeners = new HashSet<>();

    protected void fireValueChangedEvent(Change<? extends T> change) {
        for (InvalidationListener listener : invalidationListeners) {
            listener.invalidated(change.getValue());
        }

        for (ValueChangeListener<? super T> listener : listChangeListeners) {
            listener.onChanged(change);
        }
    }

    public void addListener(InvalidationListener listener) {
        invalidationListeners.add(listener);
    }

    public void removeListener(InvalidationListener listener) {
        invalidationListeners.remove(listener);
    }

    public void addListener(ValueChangeListener<? super T> listener) {
        listChangeListeners.add(listener);
    }

    public void removeListener(ValueChangeListener<? super T> listener) {
        listChangeListeners.remove(listener);
    }
}
