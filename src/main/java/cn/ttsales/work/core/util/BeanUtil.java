package cn.ttsales.work.core.util;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.List;
import java.util.Map;

import org.springframework.beans.FatalBeanException;

public abstract class BeanUtil extends org.springframework.beans.BeanUtils{
	
	
	public static <S,T> T beanCopy(S source,T target,String... ignoreProperties){
		if(source != null && target != null)
			copyProperties(source, target,ignoreProperties);
		return target;
	}
	
	public static <S,T> T beanCopy(S source,Class<T> targetClass,String... ignoreProperties){
		if(source == null) return null;
		return beanCopy(source,instantiateClass(targetClass),ignoreProperties);
	}
	
	public static <S,T> List<T> beanCopy(List<S> sources,final Class<T> targetClass,final String... ignoreProperties){
		return (new MapperList<S,T>(sources){
			@Override
			protected T map(S source) {
				return beanCopy(source,targetClass,ignoreProperties);
			}
		}).fetch();
	}
	
	/*public static<B,P> P beanGetProperty(B bean,String property){
		return beanGetProperty(bean,property,null);
	}*/
	
	@SuppressWarnings("unchecked")
	public static<B,P> P beanGetProperty(B bean,String property,P defaultValue){
		if(bean == null) return defaultValue;
		PropertyDescriptor pd = getPropertyDescriptor(bean.getClass(),property);
		if (pd != null && pd.getReadMethod() != null) {
			try {
				Method readMethod = pd.getReadMethod();
				if (!Modifier.isPublic(readMethod.getDeclaringClass().getModifiers())) {
					readMethod.setAccessible(true);
				}
				return (P)readMethod.invoke(bean);
			}
			catch (Throwable ex) {
				throw new FatalBeanException("Could not get property from bean", ex);
			}
		}	
		return defaultValue;
	}
	
	public static<B,P> void beanSetProperty(B bean,String property,P value){
		if(bean == null) return;
		PropertyDescriptor pd = getPropertyDescriptor(bean.getClass(),property);
		if (pd != null && pd.getWriteMethod() != null) {
			try {
				Method writeMethod = pd.getWriteMethod();
				if (!Modifier.isPublic(writeMethod.getDeclaringClass().getModifiers())) {
					writeMethod.setAccessible(true);
				}
				writeMethod.invoke(bean,value);
			}
			catch (Throwable ex) {
				throw new FatalBeanException("Could not set property to bean", ex);
			}
		}	
	}


	public static<B,P> List<P> beanGetPropertyList(List<B> sources,String property){
		return beanGetPropertyList(sources,property,(P)null);
	}

	public static<B,P> List<P> beanGetPropertyList(List<B> sources,final String property,final P defaultValue){
		if(sources == null) return null;
		return (new MapperList<B,P>(sources){
			@Override
			protected P map(B source) {
				return beanGetProperty(source,property,defaultValue);
			}
		}).fetch();
	}
	
	public static<K,B> Map<K,B> beanMakePropertyMap(List<B> values,String property){
		return beanMakePropertyMap(values,property,(K)null);
	}
	
	public static<K,B> Map<K,B> beanMakePropertyMap(List<B> values,final String property,final K defaultKey){
		return new KeyMap<K,B>(values){
			@Override
			public K key(B value) {
				return beanGetProperty(value,property,defaultKey);
			}};
	}
}
