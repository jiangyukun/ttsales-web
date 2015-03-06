/**
 * Copyright (c) 2014 RATANSOFT.All rights reserved.
 * @filename WxAppService.java
 * @package cn.ttsales.work.service.rbs
 * @author dandyzheng
 * @date 2012-7-27
 */
package cn.ttsales.work.service.sys;

import java.util.List;

import cn.ttsales.work.domain.WxApp;


/**
 * WxApp Service
 * @author dandyzheng
 *
 */
public interface WxAppService {
	/**
	 * 保存 WxApp
	 * @param wxApp WxApp
	 * @return WxApp 
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public WxApp saveWxApp(WxApp wxApp);
	
	/**
	 * 批量保存WxApp
	 * @param wxApps WxApps
	 * @return List<WxApp>
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public List<WxApp> saveWxApps(List<WxApp> wxApps);
	
	/**
	 * 修改WxApp
	 * @param wxApp WxApp
	 * @return WxApp
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public WxApp editWxApp(WxApp wxApp);
	
	/**
	 * 批量修改WxApp
	 * @param wxApps WxApps
	 * @return List<WxApp>
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public List<WxApp> editWxApps(List<WxApp> wxApps);
	
	/**
	 * 删除WxApp
	 * @param WxApp WxApp
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public void removeWxApp(WxApp wxApp);
	
	/**
	 * 批量删除WxApp
	 * @param WxApps WxApps
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public void removeWxApps(List<WxApp> wxApps);
	
	/**
	 * 根据WxApp' id，删除WxApp
	 * @param wxAppId WxApp's id
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public void removeWxApp(String wxAppId);
	
	/**
	 * 根据WxApp' id，获取WxApp
	 * @param wxAppId WxApp's id
	 * @return WxApp
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public WxApp getWxApp(String wxAppId); 
	
	/**
	 * 获取所有WxApp
	 * @return List<WxApp>
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public List<WxApp> getAllWxApps();	
}
