/**
 * Copyright (c) 2014 RATANSOFT.All rights reserved.
 * @filename WxAppUserService.java
 * @package cn.ttsales.work.service.rbs
 * @author dandyzheng
 * @date 2012-7-27
 */
package cn.ttsales.work.service.sys;

import java.util.List;

import cn.ttsales.work.domain.WxAppUser;


/**
 * WxAppUser Service
 * @author dandyzheng
 *
 */
public interface WxAppUserService {
	/**
	 * 保存 WxAppUser
	 * @param wxAppUser WxAppUser
	 * @return WxAppUser 
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public WxAppUser saveWxAppUser(WxAppUser wxAppUser);
	
	/**
	 * 批量保存WxAppUser
	 * @param wxAppUsers WxAppUsers
	 * @return List<WxAppUser>
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public List<WxAppUser> saveWxAppUsers(List<WxAppUser> wxAppUsers);
	
	/**
	 * 修改WxAppUser
	 * @param wxAppUser WxAppUser
	 * @return WxAppUser
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public WxAppUser editWxAppUser(WxAppUser wxAppUser);
	
	/**
	 * 批量修改WxAppUser
	 * @param wxAppUsers WxAppUsers
	 * @return List<WxAppUser>
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public List<WxAppUser> editWxAppUsers(List<WxAppUser> wxAppUsers);
	
	/**
	 * 删除WxAppUser
	 * @param WxAppUser WxAppUser
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public void removeWxAppUser(WxAppUser wxAppUser);
	
	/**
	 * 批量删除WxAppUser
	 * @param WxAppUsers WxAppUsers
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public void removeWxAppUsers(List<WxAppUser> wxAppUsers);
	
	/**
	 * 根据WxAppUser' id，删除WxAppUser
	 * @param wxAppUserId WxAppUser's id
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public void removeWxAppUser(String wxAppUserId);
	
	/**
	 * 根据WxAppUser' id，获取WxAppUser
	 * @param wxAppUserId WxAppUser's id
	 * @return WxAppUser
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public WxAppUser getWxAppUser(String wxAppUserId); 
	
	/**
	 * 获取所有WxAppUser
	 * @return List<WxAppUser>
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public List<WxAppUser> getAllWxAppUsers();
	
	/**
	 * 根据openId 和 appId 查找WxAppUser
	 * @param string
	 * @param openId
	 * @return
	 */
	public WxAppUser queryWxAppUserByAppIdAndWCOpenId(String appId,
			String openId);
	
	/**
	 * 根据userId 和 appId 查找WxAppUser
	 * @param appId
	 * @param userId
	 * @return
	 */
	public WxAppUser queryWxAppUserByAppIdAndUserId(String appId, String userId);
	
	/**
	 * 根据wcOpenId查找WxAppUser
	 * @param openId
	 * @return
	 */
	public WxAppUser getWxAppUserByWcOpenId(String openId);
	
	/**
	 * 根据appId和wcOpenId userId更新WxAppUser
	 * @param appId
	 * @param wcOpenId
	 */
	public WxAppUser editWxAppUserByAppIdAndUserId(String appId, String userId, String wcOpenId);	
}
