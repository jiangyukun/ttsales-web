/**
 * Copyright (c) 2014 RATANSFOT.All rights reserved.
 * @filename AccountSchemeViewDao.java
 * @package cn.ttsales.work.persistence.acc
 * @author dandyzheng
 * @date 2012-7-27
 */
package cn.ttsales.work.persistence.acc;

import java.util.List;

import cn.ttsales.work.core.jpa.PageModel;
import cn.ttsales.work.core.jpa.PageParam;
import cn.ttsales.work.domain.AccountSchemeView;
import cn.ttsales.work.dto.MyRankDTO;
import cn.ttsales.work.dto.RankingListUserDTO;



/**
 * AccountSchemeView DAO
 * @author dandyzheng
 *
 */
public interface AccountSchemeViewDao {
	/**
	 * 保存 AccountSchemeView
	 * @param accountSchemeView AccountSchemeView
	 * @return AccountSchemeView 
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public AccountSchemeView saveAccountSchemeView(AccountSchemeView accountSchemeView);
	
	/**
	 * 批量保存AccountSchemeView
	 * @param accountSchemeViews AccountSchemeViews
	 * @return List<AccountSchemeView>
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public List<AccountSchemeView> saveAccountSchemeViews(List<AccountSchemeView> accountSchemeViews);
	
	/**
	 * 修改AccountSchemeView
	 * @param accountSchemeView AccountSchemeView
	 * @return AccountSchemeView
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public AccountSchemeView editAccountSchemeView(AccountSchemeView accountSchemeView);
	
	/**
	 * 批量修改AccountSchemeView
	 * @param accountSchemeViews AccountSchemeViews
	 * @return List<AccountSchemeView>
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public List<AccountSchemeView> editAccountSchemeViews(List<AccountSchemeView> accountSchemeViews);
	
	/**
	 * 删除AccountSchemeView
	 * @param AccountSchemeViewDTO AccountSchemeView
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public void removeAccountSchemeView(AccountSchemeView accountSchemeView);
	
	/**
	 * 批量删除AccountSchemeView
	 * @param AccountSchemeViews AccountSchemeViews
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public void removeAccountSchemeViews(List<AccountSchemeView> accountSchemeViews);
	
	/**
	 * 根据AccountSchemeView' id，删除AccountSchemeView
	 * @param accountSchemeViewId AccountSchemeView's id
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public void removeAccountSchemeView(String accountSchemeViewId);
	
	/**
	 * 根据AccountSchemeView' id，获取AccountSchemeView
	 * @param accountSchemeViewId AccountSchemeView's id
	 * @return AccountSchemeView
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public AccountSchemeView getAccountSchemeView(String accountSchemeViewId); 
	
	/**
	 * 获取所有AccountSchemeView
	 * @return List<AccountSchemeView>
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public List<AccountSchemeView> getAllAccountSchemeViews();
	/**
	 * 根据文案Id获取AccountSchemeViews
	 * @param schemeId
	 * @return
	 */
	public PageModel<RankingListUserDTO> queryAccountSchemeViewDTOsBySchemeId(PageParam pageParam,
			String schemeId,String userType);
	
	/**
	 * 根据memberId获取我的排名
	 * @param memberId
	 * @param schemeId
	 * @return
	 */
	public MyRankDTO getMyRankDTOByMemberId(String memberId, String schemeId);
	
		
}
