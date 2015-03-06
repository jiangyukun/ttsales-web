/**
 * Copyright (c) 2014 RATANSFOT.All rights reserved.
 * @filename ProProducTypeDao.java
 * @package cn.ttsales.work.persistence.pro
 * @author dandyzheng
 * @date 2012-7-27
 */
package cn.ttsales.work.persistence.pro;

import java.util.List;

import cn.ttsales.work.domain.ProProducType;



/**
 * ProProducType DAO
 * @author dandyzheng
 *
 */
public interface ProProducTypeDao {
	/**
	 * 保存 ProProducType
	 * @param proProducType ProProducType
	 * @return ProProducType 
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public ProProducType saveProProducType(ProProducType proProducType);
	
	/**
	 * 批量保存ProProducType
	 * @param proProducTypes ProProducTypes
	 * @return List<ProProducType>
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public List<ProProducType> saveProProducTypes(List<ProProducType> proProducTypes);
	
	/**
	 * 修改ProProducType
	 * @param proProducType ProProducType
	 * @return ProProducType
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public ProProducType editProProducType(ProProducType proProducType);
	
	/**
	 * 批量修改ProProducType
	 * @param proProducTypes ProProducTypes
	 * @return List<ProProducType>
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public List<ProProducType> editProProducTypes(List<ProProducType> proProducTypes);
	
	/**
	 * 删除ProProducType
	 * @param ProProducType ProProducType
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public void removeProProducType(ProProducType proProducType);
	
	/**
	 * 批量删除ProProducType
	 * @param ProProducTypes ProProducTypes
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public void removeProProducTypes(List<ProProducType> proProducTypes);
	
	/**
	 * 根据ProProducType' id，删除ProProducType
	 * @param proProducTypeId ProProducType's id
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public void removeProProducType(String proProducTypeId);
	
	/**
	 * 根据ProProducType' id，获取ProProducType
	 * @param proProducTypeId ProProducType's id
	 * @return ProProducType
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public ProProducType getProProducType(String proProducTypeId); 
	
	/**
	 * 获取所有ProProducType
	 * @return List<ProProducType>
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public List<ProProducType> getAllProProducTypes();
	
		
}
