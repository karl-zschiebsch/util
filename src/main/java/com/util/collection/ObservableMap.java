package com.util.collection;

import java.util.Map;

import com.util.beans.Observable;

public interface ObservableMap<K, V> extends Map<K, V>, Observable {
	void addListener(MapChangeListener<? super K, ? super V> listener);

	void removeListener(MapChangeListener<? super K, ? super V> listener);
}
