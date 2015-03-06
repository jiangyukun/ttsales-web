/**
 * Copyright (c) 2014 RATANSFOT.All rights reserved.
 * @filename RepOpenUserDao.java
 * @package cn.ttsales.work.persistence.rep
 * @author dandyzheng
 * @date 2012-7-27
 */
package cn.ttsales.work.persistence.rep;

import java.util.List;

import cn.ttsales.work.domain.RepOpenUser;



/**
 * RepOpenUser DAO
 * @author dandyzheng
 *
 */
public interface RepOpenUserDao {
	/**
	 * 保存 RepOpenUser
	 * @param repOpenUser RepOpenUser
	 * @return RepOpenUser 
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public RepOpenUser saveRepOpenUser(RepOpenUser repOpenUser);
	
	/**
	 * 批量保存RepOpenUser
	 * @param repOpenUsers RepOpenUsers
	 * @return List<RepOpenUser>
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public List<RepOpenUser> saveRepOpenUsers(List<RepOpenUser> repOpenUsers);
	
	/**
	 * 修改RepOpenUser
	 * @param repOpenUser RepOpenUser
	 * @return RepOpenUser
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public RepOpenUser editRepOpenUser(RepOpenUser repOpenUser);
	
	/**
	 * 批量修改RepOpenUser
	 * @param repOpenUsers RepOpenUsers
	 * @return List<RepOpenUser>
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public List<RepOpenUser> editRepOpenUsers(List<RepOpenUser> repOpenUsers);
	
	/**
	 * 删除RepOpenUser
	 * @param RepOpenUser RepOpenUser
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public void removeRepOpenUser(RepOpenUser repOpenUser);
	
	/**
	 * 批量删除RepOpenUser
	 * @param RepOpenUsers RepOpenUsers
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public void removeRepOpenUsers(List<RepOpenUser> repOpenUsers);
	
	/**
	 * 根据RepOpenUser' id，删除RepOpenUser
	 * @param repOpenUserId RepOpenUser's id
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public void removeRepOpenUser(String repOpenUserId);
	
	/**
	 * 根据RepOpenUser' id，获取RepOpenUser
	 * @param repOpenUserId RepOpenUser's id
	 * @return RepOpenUser
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public RepOpenUser getRepOpenUser(String repOpenUserId); 
	
	/**
	 * 获取所有RepOpenUser
	 * @return List<RepOpenUser>
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public List<RepOpenUser> getAllRepOpenUsers();

	/**
	 * 根据关注状态获取RepOpenUser
	 * @param openSubStateSub
	 * @return
	 * @author zhaoxiaobin
	 * @date 2014-12-22
	 * @see
	 */
	public List<RepOpenUser> getRepOpenUsersBySubscribeState(
			String subState);

}
