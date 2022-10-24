package com.util.value;

public interface ObservableNumberValue<T extends Number> extends ObservableValue<T> {
    int intValue();

    long longValue();

    float floatValue();

    double doubleValue();
}
