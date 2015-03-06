/**
 * Copyright (c) 2014 RATANSFOT.All rights reserved.
 * @filename AccountRecordViewDao.java
 * @package cn.ttsales.work.persistence.acc
 * @author dandyzheng
 * @date 2012-7-27
 */
package cn.ttsales.work.persistence.acc;

import java.util.List;

import cn.ttsales.work.domain.AccountRecordView;



/**
 * AccountRecordView DAO
 * @author dandyzheng
 *
 */
public interface AccountRecordViewDao {
	/**
	 * 保存 AccountRecordView
	 * @param accountRecordView AccountRecordView
	 * @return AccountRecordView 
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public AccountRecordView saveAccountRecordView(AccountRecordView accountRecordView);
	
	/**
	 * 批量保存AccountRecordView
	 * @param accountRecordViews AccountRecordViews
	 * @return List<AccountRecordView>
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public List<AccountRecordView> saveAccountRecordViews(List<AccountRecordView> accountRecordViews);
	
	/**
	 * 修改AccountRecordView
	 * @param accountRecordView AccountRecordView
	 * @return AccountRecordView
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public AccountRecordView editAccountRecordView(AccountRecordView accountRecordView);
	
	/**
	 * 批量修改AccountRecordView
	 * @param accountRecordViews AccountRecordViews
	 * @return List<AccountRecordView>
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public List<AccountRecordView> editAccountRecordViews(List<AccountRecordView> accountRecordViews);
	
	/**
	 * 删除AccountRecordView
	 * @param AccountRecordView AccountRecordView
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public void removeAccountRecordView(AccountRecordView accountRecordView);
	
	/**
	 * 批量删除AccountRecordView
	 * @param AccountRecordViews AccountRecordViews
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public void removeAccountRecordViews(List<AccountRecordView> accountRecordViews);
	
	/**
	 * 根据AccountRecordView' id，删除AccountRecordView
	 * @param accountRecordViewId AccountRecordView's id
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public void removeAccountRecordView(String accountRecordViewId);
	
	/**
	 * 根据AccountRecordView' id，获取AccountRecordView
	 * @param accountRecordViewId AccountRecordView's id
	 * @return AccountRecordView
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public AccountRecordView getAccountRecordView(String accountRecordViewId); 
	
	/**
	 * 获取所有AccountRecordView
	 * @return List<AccountRecordView>
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public List<AccountRecordView> getAllAccountRecordViews();
	
		
}
