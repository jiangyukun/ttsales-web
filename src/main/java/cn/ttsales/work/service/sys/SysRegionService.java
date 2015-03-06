/**
 * Copyright (c) 2014 RATANSOFT.All rights reserved.
 * @filename SysRegionService.java
 * @package cn.ttsales.work.service.sys
 * @author dandyzheng
 * @date 2012-7-27
 */
package cn.ttsales.work.service.sys;

import java.util.List;

import cn.ttsales.work.domain.SysRegion;


/**
 * SysRegion Service
 * @author dandyzheng
 *
 */
public interface SysRegionService {
	/**
	 * 保存 SysRegion
	 * @param sysRegion SysRegion
	 * @return SysRegion 
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public SysRegion saveSysRegion(SysRegion sysRegion);
	
	/**
	 * 批量保存SysRegion
	 * @param sysRegions SysRegions
	 * @return List<SysRegion>
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public List<SysRegion> saveSysRegions(List<SysRegion> sysRegions);
	
	/**
	 * 修改SysRegion
	 * @param sysRegion SysRegion
	 * @return SysRegion
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public SysRegion editSysRegion(SysRegion sysRegion);
	
	/**
	 * 批量修改SysRegion
	 * @param sysRegions SysRegions
	 * @return List<SysRegion>
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public List<SysRegion> editSysRegions(List<SysRegion> sysRegions);
	
	/**
	 * 删除SysRegion
	 * @param SysRegion SysRegion
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public void removeSysRegion(SysRegion sysRegion);
	
	/**
	 * 批量删除SysRegion
	 * @param SysRegions SysRegions
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public void removeSysRegions(List<SysRegion> sysRegions);
	
	/**
	 * 根据SysRegion' id，删除SysRegion
	 * @param sysRegionId SysRegion's id
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public void removeSysRegion(String sysRegionId);
	
	/**
	 * 根据SysRegion' id，获取SysRegion
	 * @param sysRegionId SysRegion's id
	 * @return SysRegion
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public SysRegion getSysRegion(String sysRegionId); 
	
	/**
	 * 获取所有SysRegion
	 * @return List<SysRegion>
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public List<SysRegion> getAllSysRegions();	
	
	
	/**
	 * 根据文案ID，获取参加该文案的所有4S店所在城市，城市按受字母顺序排列
	 * 
	 * @param schemeId 文案ID
	 * @return 城市列表
	 * @author dandyzheng
	 * @date 2014年10月22日
	 * @see
	 */
	public List<SysRegion> querySysRegionsBySchemeId(String schemeId);
}
