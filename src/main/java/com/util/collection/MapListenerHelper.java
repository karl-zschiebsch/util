package com.util.collection;

import java.util.HashSet;
import java.util.Set;

import com.util.beans.InvalidationListener;
import com.util.collection.MapChangeListener.Change;

public class MapListenerHelper<K, V> {
	Set<InvalidationListener> invalidationListeners = new HashSet<>();
	Set<MapChangeListener<? super K, ? super V>> mapChangeListeners = new HashSet<>();

	protected void fireValueChangedEvent(Change<? extends K, ? extends V> change) {
		for (InvalidationListener listener : invalidationListeners) {
			listener.invalidated(change.getMap());
		}

		for (MapChangeListener<? super K, ? super V> listener : mapChangeListeners) {
			listener.onChanged(change);
		}
	}

	public void addListener(InvalidationListener listener) {
		invalidationListeners.add(listener);
	}

	public void removeListener(InvalidationListener listener) {
		invalidationListeners.remove(listener);
	}

	public void addListener(MapChangeListener<? super K, ? super V> listener) {
		mapChangeListeners.add(listener);
	}

	public void removeListener(MapChangeListener<? super K, ? super V> listener) {
		mapChangeListeners.remove(listener);
	}
}
