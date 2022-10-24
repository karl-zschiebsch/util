package com.util.collection;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import com.util.beans.InvalidationListener;

public class ObservableMapWrapper<K, V> implements ObservableMap<K, V> {
	private final MapListenerHelper<K, V> helper = new MapListenerHelper<>();

	private final Map<K, V> backing;

	public ObservableMapWrapper(Map<K, V> map) {
		backing = map;
	}

	private class Change implements MapChangeListener.Change<K, V> {
		private final K key;
		private final V old;
		private final V added;
		private final boolean wasAdded;
		private final boolean wasRemoved;

		public Change(K key, V old, V added, boolean wasAdded, boolean wasRemoved) {
			assert (wasAdded || wasRemoved);
			this.key = key;
			this.old = old;
			this.added = added;
			this.wasAdded = wasAdded;
			this.wasRemoved = wasRemoved;
		}

		@Override
		public boolean wasAdded() {
			return wasAdded;
		}

		@Override
		public boolean wasRemoved() {
			return wasRemoved;
		}

		@Override
		public K getKey() {
			return key;
		}

		@Override
		public V getNewValue() {
			return added;
		}

		@Override
		public V getOldValue() {
			return old;
		}

		@Override
		public ObservableMap<K, V> getMap() {
			return ObservableMapWrapper.this;
		}
	}

	@Override
	public void addListener(InvalidationListener listener) {
		helper.addListener(listener);
	}

	@Override
	public void removeListener(InvalidationListener listener) {
		helper.removeListener(listener);
	}

	@Override
	public void addListener(MapChangeListener<? super K, ? super V> listener) {
		helper.addListener(listener);
	}

	@Override
	public void removeListener(MapChangeListener<? super K, ? super V> listener) {
		helper.removeListener(listener);
	}

	@Override
	public int size() {
		return backing.size();
	}

	@Override
	public boolean isEmpty() {
		return backing.isEmpty();
	}

	@Override
	public boolean containsKey(Object key) {
		return backing.containsKey(key);
	}

	@Override
	public boolean containsValue(Object value) {
		return backing.containsValue(value);
	}

	@Override
	public V get(Object key) {
		return backing.get(key);
	}

	@Override
	public V put(K key, V value) {
		V ret;
		if (backing.containsKey(key)) {
			ret = backing.put(key, value);
			if (ret == null && value != null || ret != null && !ret.equals(value)) {
				helper.fireValueChangedEvent(new Change(key, ret, value, true, true));
			}
		} else {
			ret = backing.put(key, value);
			helper.fireValueChangedEvent(new Change(key, ret, value, true, false));
		}
		return ret;
	}

	@Override
	public V remove(Object key) {
		if (!backing.containsKey(key)) {
			return null;
		}
		V ret = backing.remove(key);
		@SuppressWarnings("unchecked")
		K mapping = (K) key;
		helper.fireValueChangedEvent(new Change(mapping, ret, null, false, true));
		return ret;
	}

	@Override
	public void putAll(Map<? extends K, ? extends V> m) {
		for (Entry<? extends K, ? extends V> entry : m.entrySet()) {
			put(entry.getKey(), entry.getValue());
		}
	}

	@Override
	public void clear() {
		for (K key : backing.keySet()) {
			remove(key);
		}
	}

	@Override
	public Set<K> keySet() {
		return backing.keySet();
	}

	@Override
	public Collection<V> values() {
		return backing.values();
	}

	@Override
	public Set<Entry<K, V>> entrySet() {
		return backing.entrySet();
	}
}
