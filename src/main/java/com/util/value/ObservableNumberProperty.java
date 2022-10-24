package com.util.value;

import com.util.beans.InvalidationListener;

public class ObservableNumberProperty<T extends Number> implements ObservableNumberValue<T> {
    private final ValueChangeHelper<T> helper = new ValueChangeHelper<>();

    protected T value;

    private class Change implements ValueChangeListener.Change<T> {
        private final T old;
        private final T added;

        public Change(T old, T added) {
            this.old = old;
            this.added = added;
        }

        @Override
        public T getOldValue() {
            return old;
        }

        @Override
        public T getNewValue() {
            return added;
        }

        @Override
        public ObservableValue<T> getValue() {
            return ObservableNumberProperty.this;
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
    public void addListener(ValueChangeListener<? super T> listener) {
        helper.addListener(listener);
    }

    @Override
    public void removeListener(ValueChangeListener<? super T> listener) {
        helper.removeListener(listener);
    }

    @Override
    public int intValue() {
        return (int) value;
    }

    @Override
    public long longValue() {
        return (long) value;
    }

    @Override
    public float floatValue() {
        return (float) value;
    }

    @Override
    public double doubleValue() {
        return (double) value;
    }

    @Override
    public T setValue(T value) {
        if (this.value == value) {
            return value;
        }
        T ret = value;
        this.value = value;
        helper.fireValueChangedEvent(new Change(ret, value));
        return ret;
    }

    @Override
    public T getValue() {
        return value;
    }

}
