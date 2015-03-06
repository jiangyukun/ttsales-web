package cn.ttsales.work.core.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class MapperList<TSource,TTarget> extends DeferList<TTarget>{
	private Collection<TSource> sourceList;
	public MapperList(Collection<TSource> sourceList){
		this.sourceList = sourceList;
	}
	@Override
	protected List<TTarget> doFetch() {
		List<TTarget> ret = new ArrayList<TTarget>(sourceList.size());
		for(TSource source : sourceList)
			try{
				ret.add(map(source));
			}catch(SkipException e){}
		return ret;
	}
	protected final TTarget skip(){
		throw new SkipException();
	}
	protected abstract TTarget map(TSource source);
	@SuppressWarnings("serial")
	private static class SkipException extends RuntimeException{}
}
