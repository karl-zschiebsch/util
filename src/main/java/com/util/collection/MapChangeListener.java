package com.util.collection;

public interface MapChangeListener<K, V> {
	void onChanged(Change<? extends K, ? extends V> change);

	interface Change<K, V> {
		K getKey();

		V getOldValue();

		V getNewValue();

		boolean wasAdded();

		boolean wasRemoved();

		ObservableMap<K, V> getMap();
	}
}
