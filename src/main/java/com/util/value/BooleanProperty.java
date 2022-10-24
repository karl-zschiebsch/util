package com.util.value;

import com.util.beans.InvalidationListener;

public class BooleanProperty extends Property implements ObservableBooleanValue {
    private final ValueChangeHelper<Boolean> helper = new ValueChangeHelper<>();

    private boolean value;

    public BooleanProperty(String name, Object bean, boolean value) {
        super(name, bean);
        this.value = value;
    }

    public BooleanProperty(boolean value) {
        this("", null, value);
    }

    public BooleanProperty() {
        this(false);
    }

    private class Change implements ValueChangeListener.Change<Boolean> {
        private final boolean old;
        private final boolean added;

        public Change(boolean old, boolean added) {
            this.old = old;
            this.added = added;
        }

        @Override
        public Boolean getOldValue() {
            return old;
        }

        @Override
        public Boolean getNewValue() {
            return added;
        }

        @Override
        public BooleanProperty getValue() {
            return BooleanProperty.this;
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
    public void addListener(ValueChangeListener<? super Boolean> listener) {
        helper.addListener(listener);
    }

    @Override
    public void removeListener(ValueChangeListener<? super Boolean> listener) {
        helper.removeListener(listener);
    }

    @Override
    public Boolean setValue(Boolean value) {
        if (this.value == value) {
            return value;
        }
        boolean ret = value;
        this.value = value;
        helper.fireValueChangedEvent(new Change(ret, value));
        return ret;
    }

    @Override
    public Boolean getValue() {
        return value;
    }

    @Override
    public boolean get() {
        return value;
    }
}
