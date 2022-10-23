package com.util.collection;

import java.util.HashMap;

public class Collections {
	private Collections() {

	}

	public static <K, V> ObservableMap<K, V> newObservableHashMap() {
		return new ObservableMapWrapper<>(new HashMap<>());
	}
}
