package cn.ttsales.work.core.util;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

public abstract class DeferMap<K, V> extends
		java.util.AbstractMap<K, V> {
	private Map<K, V> fetched;

	public final boolean isFetched() {
		return fetched != null && !isExpired();
	}

	protected boolean isExpired() {
		return false;
	}

	public Map<K, V> fetch() {
		if (!isFetched())
			fetched = doFetch();
		return fetched;
	}

	protected abstract Map<K, V> doFetch();

	@Override
	public int size() {

		return fetch().size();
	}

	@Override
	public boolean isEmpty() {

		return fetch().isEmpty();
	}

	@Override
	public V get(Object key) {

		return fetch().get(key);
	}

	@Override
	public boolean containsKey(Object key) {

		return fetch().containsKey(key);
	}

	@Override
	public V put(K key, V value) {

		return fetch().put(key, value);
	}

	@Override
	public void putAll(Map<? extends K, ? extends V> m) {

		fetch().putAll(m);
	}

	@Override
	public V remove(Object key) {

		return fetch().remove(key);
	}

	@Override
	public void clear() {

		fetch().clear();
	}

	@Override
	public boolean containsValue(Object value) {

		return fetch().containsValue(value);
	}

	@Override
	public Set<K> keySet() {

		return fetch().keySet();
	}

	@Override
	public Collection<V> values() {

		return fetch().values();
	}

	@Override
	public Set<java.util.Map.Entry<K, V>> entrySet() {

		return fetch().entrySet();
	}
}
