/**
 * Copyright (c) 2014 RATANSFOT.All rights reserved.
 * @filename WxAppUserDao.java
 * @package cn.ttsales.work.persistence.rbs
 * @author dandyzheng
 * @date 2012-7-27
 */
package cn.ttsales.work.persistence.sys;

import java.util.List;

import cn.ttsales.work.domain.WxAppUser;



/**
 * WxAppUser DAO
 * @author dandyzheng
 *
 */
public interface WxAppUserDao {
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
	 * 
	 * @param appId
	 * @param openId
	 * @return
	 */
	public WxAppUser queryWxAppUserByAppIdAndWCOpenId(String appId,
			String openId);
	
	/**
	 * 
	 * @param appId
	 * @param userId
	 * @return
	 */
	public WxAppUser queryWxAppUserByAppIdAndUserId(String appId, String userId);
	
	/**
	 * 
	 * @param openId
	 * @return
	 */
	public WxAppUser getWxAppUserByWcOpenId(String openId);
	
		
}
