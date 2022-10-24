package com.util.value;

public class DoubleProperty extends NumberProperty<Double> implements ObservableDoubleValue {
    public DoubleProperty(String name, Object bean, double value) {
        super(name, bean, value);
    }

    public DoubleProperty(double value) {
        super("", null, value);
    }

    public DoubleProperty() {
        this(0);
    }

    @Override
    public double get() {
        return getValue();
    }
}
