package com.util.value;

public class ObservableDoubleProperty extends ObservableNumberProperty<Double> implements ObservableDoubleValue {
    @Override
    public double get() {
        return getValue();
    }
}
