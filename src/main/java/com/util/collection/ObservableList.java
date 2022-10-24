package com.util.collection;

import java.util.List;

import com.util.beans.Observable;

public interface ObservableList<E> extends List<E>, Observable {
    void addListener(ListChangeListener<? super E> listener);

    void removeListener(ListChangeListener<? super E> listener);
}
