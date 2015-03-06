/**
 * Copyright (c) 2014 RATANSFOT.All rights reserved.
 * @filename SysPopulStatDao.java
 * @package cn.ttsales.work.persistence.sys
 * @author dandyzheng
 * @date 2012-7-27
 */
package cn.ttsales.work.persistence.sys;

import java.util.List;

import cn.ttsales.work.wxapi.corp.pojo.User;


 
public interface SyncContactDao {
	
	/**
	 * 根据状态获得需要同步的用户
	 * @param state
	 * @return
	 * @author zhaoxiaobin
	 * @date 2014-10-11
	 * @see
	 */
	public List<User> getSyncUsersByState(String state);
	
	/**
	 * 根据用户id更新用户状态
	 * @param userid
	 * @author zhaoxiaobin
	 * @date 2014-10-11
	 * @see
	 */
	public void updateUserState(String userid,String state);
	
	/**
	 * 根据状态和用户id获取同步用户
	 * @param state
	 * @param memberId
	 * @return
	 * @author zhaoxiaobin
	 * @date 2014-11-4
	 * @see
	 */
	public User getSyncUserByStateAndMemberId(String state,
			String memberId);
	
	/**
	 * 根据用户id获取同步用户
	 * @param state
	 * @param memberId
	 * @return
	 * @author zhaoxiaobin
	 * @date 2014-11-4
	 * @see
	 */
	public User getSyncUserByMemberId(String memberId);
		
}
