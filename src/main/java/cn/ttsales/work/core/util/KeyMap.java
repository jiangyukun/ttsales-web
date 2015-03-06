package cn.ttsales.work.core.util;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public abstract class KeyMap<K,V> extends DeferMap<K,V>{
	private Collection<V> initValues;
	public KeyMap(){
	}
	public KeyMap(Collection<V> initValues){
		this.initValues = initValues;
	}
	
	public V put(V value) {
		return put(key(value),value);
	}
	
	public void putAll(Collection<V> values) {
		for(V v:values) put(v);
	}

	public abstract K key(V value);

	@Override
	protected Map<K, V> doFetch() {
		Map<K,V> ret = new HashMap<K,V>();
		if(initValues != null){
			putAll(initValues);
			initValues = null;
		}
		return ret;
	}

}
