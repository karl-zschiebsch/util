package com.util.value;

public interface ValueChangeListener<T> {
    void onChanged(Change<? extends T> change);

    interface Change<T> {
        T getOldValue();

        T getNewValue();

        ObservableValue<T> getValue();
    }
}
