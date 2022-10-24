package com.util.collection;

import java.util.ArrayList;
import java.util.HashMap;

public class Collections {
	private Collections() {

	}

	public static <E> ObservableList<E> newObservableArrayList() {
	    return new ObservableListWrapper<>(new ArrayList<>());
	}
	
	public static <K, V> ObservableMap<K, V> newObservableHashMap() {
		return new ObservableMapWrapper<>(new HashMap<>());
	}
}
