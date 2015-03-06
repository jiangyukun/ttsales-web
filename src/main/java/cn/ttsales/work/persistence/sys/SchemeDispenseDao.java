/**
 * Copyright (c) 2014 RATANSFOT.All rights reserved.
 * @filename SysRegionDao.java
 * @package cn.ttsales.work.persistence.sys
 * @author dandyzheng
 * @date 2012-7-27
 */
package cn.ttsales.work.persistence.sys;

import java.util.Set;



/**
 * SysRegion DAO
 * @author dandyzheng
 *
 */
public interface SchemeDispenseDao {
	/**
	 * 根据部分id获取所有子部门id
	 * @param deptId
	 * @return
	 * @author zhaoxiaobin
	 * @date 2014-9-13
	 * @see
	 */
	public Set<String> getChildDeptIds(String deptId);
	 
}
