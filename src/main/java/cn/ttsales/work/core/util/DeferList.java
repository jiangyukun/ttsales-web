package cn.ttsales.work.core.util;

import java.util.AbstractList;
import java.util.List;


public abstract class DeferList<T> extends AbstractList<T> {
	private List<T> fetched;
	
	public final boolean isFetched(){
		return fetched != null && !isExpired();
	}
	
	protected boolean isExpired() {
		return false;
	}

	public List<T> fetch(){
		if(!isFetched()) 
			fetched = doFetch();
		return fetched;
	}
	
	protected abstract List<T> doFetch();
	

	@Override
	public T set(int index, T element) {
		return fetch().set(index, element);
	}

	@Override
	public void add(int index, T element) {
		fetch().add(index, element);
	}

	@Override
	public T remove(int index) {
		return fetch().remove(index);
	}

	@Override
	public T get(int index) {
		return fetch().get(index);
	}

	@Override
	public int size() {
		return fetch().size();
	}
	
	@Override
	public List<T> subList(final int fromIndex, final int toIndex) {
		return new DeferList<T>(){
			@Override
			protected List<T> doFetch() {
				return DeferList.this.fetch().subList(fromIndex, toIndex);
			}
			@Override
			protected boolean isExpired() {
				return DeferList.this.isExpired();
			}
		};
	}
}