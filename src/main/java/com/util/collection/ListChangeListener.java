package com.util.collection;

public interface ListChangeListener<E> {
    void onChanged(Change<? extends E> change);

    interface Change<E> {
        int getIndex();
        
        E getAdded();

        E getRemoved();

        boolean wasAdded();

        boolean wasRemoved();

        ObservableList<E> getList();
    }
}
