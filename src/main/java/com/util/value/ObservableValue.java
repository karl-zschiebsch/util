package com.util.value;

import com.util.beans.Observable;

public interface ObservableValue<T> extends Observable {
    void addListener(ValueChangeListener<? super T> listener);

    void removeListener(ValueChangeListener<? super T> listener);

    T setValue(T value);

    T getValue();
}
