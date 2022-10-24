package com.util.value;

public class IntegerProperty extends NumberProperty<Integer> implements ObservableIntegerValue {
    public IntegerProperty(String name, Object bean, int value) {
        super(name, bean, value);
    }

    public IntegerProperty(int value) {
        super("", null, value);
    }

    public IntegerProperty() {
        this(0);
    }

    @Override
    public int get() {
        return getValue();
    }
}
