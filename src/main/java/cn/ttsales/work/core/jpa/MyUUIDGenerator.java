package cn.ttsales.work.core.jpa;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Properties;
import java.util.UUID;

import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.dialect.Dialect;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.id.Configurable;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.type.Type;

import cn.ttsales.work.core.util.MD5Util;

/**
 * 自定义的uuid生成策略，用于生成带-的标准的uuid，与ormlite生成的uuid相同
 * 
 * @author zhaoxiaobin
 * 
 */
public class MyUUIDGenerator implements IdentifierGenerator, Configurable {

	public void configure(Type type, Properties params, Dialect d)
			throws MappingException {

	}
	/**
	 * 判断实体主键是否已经有值，有值直接返回，无值自动生成
	 */
	public Serializable generate(SessionImplementor session, Object object)
			throws HibernateException {
		String id =null;
	 	for (Field field : object.getClass().getDeclaredFields()) {
	 		if(field.getAnnotation(javax.persistence.Id.class)!=null){
	 			try {
	 				field.setAccessible(true);
	 				id =(String) field.get(object);
					//id =(String) object.getClass().getMethod("getUserId").invoke(object);
				} catch (Exception e) {
					 e.printStackTrace();
				}
	 			break;
	 		}
		}
	 	if(id==null){
	 		UUID uuid = UUID.randomUUID();
	 		id = Long.toHexString(uuid.getMostSignificantBits())
					+ Long.toHexString(uuid.getLeastSignificantBits());
	 	}
	 	return id;
	}
	
	public static String createUUID(){
		UUID uuid = UUID.randomUUID();
		String id = Long.toHexString(uuid.getMostSignificantBits())+ Long.toHexString(uuid.getLeastSignificantBits());
		return id;
		
	}
	
	public static String initUUID(String str){
		return MD5Util.EncoderByMd52(str);
	}

//	public Serializable generate(SessionImplementor session, Object object)
//			throws HibernateException {
//		String id = UUID.randomUUID().toString().toLowerCase();
//		return id;
//	}
	
	public static void main(String[] args){
		for (int i = 0; i < 200; i++) {
			System.out.println(createUUID());
		}
 	}
}
