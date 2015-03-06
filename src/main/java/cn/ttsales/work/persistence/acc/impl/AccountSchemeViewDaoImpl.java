/**
 * Copyright (c) 2013 RATANSOFT.All rights reserved.
 * @filename AccountSchemeViewDaoImpl.java
 * @package cn.ttsales.work.persistence.acc.impl
 * @author dandyzheng
 * @date 2012-7-27
 */
package cn.ttsales.work.persistence.acc.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import cn.ttsales.work.core.jpa.AbstractFacade;
import cn.ttsales.work.core.jpa.PageModel;
import cn.ttsales.work.core.jpa.PageParam;
import cn.ttsales.work.core.util.ListUtil;
import cn.ttsales.work.core.util.StringUtil;
import cn.ttsales.work.domain.AccountSchemeView;
import cn.ttsales.work.dto.MyRankDTO;
import cn.ttsales.work.dto.RankingListUserDTO;
import cn.ttsales.work.persistence.acc.AccountSchemeViewDao;


/**
 * AccountSchemeView Dao Impl
 * @author dandyzheng
 *
 */
@Repository("accountSchemeViewDao")
public class AccountSchemeViewDaoImpl extends AbstractFacade implements AccountSchemeViewDao {

	@PersistenceContext(unitName="MAIN_DATABASE_PER")
	private EntityManager entityManager;
	
	@Override
	protected EntityManager getEntityManager() {
		return entityManager;
	}

	public AccountSchemeView saveAccountSchemeView(AccountSchemeView accountSchemeView) {
		return persist(accountSchemeView);
	}

	public List<AccountSchemeView> saveAccountSchemeViews(List<AccountSchemeView> accountSchemeViews) {
		return persist(accountSchemeViews);
	}

	public AccountSchemeView editAccountSchemeView(AccountSchemeView accountSchemeView) {
		return merge(accountSchemeView);
	}

	public List<AccountSchemeView> editAccountSchemeViews(List<AccountSchemeView> accountSchemeViews) {
		return merge(accountSchemeViews);
	}

	public void removeAccountSchemeView(AccountSchemeView accountSchemeView) {
		remove(getAccountSchemeView(accountSchemeView.getId()));
	}

	public void removeAccountSchemeViews(List<AccountSchemeView> accountSchemeViews) {
		remove(accountSchemeViews);
	}

	public void removeAccountSchemeView(String accountSchemeViewId) {
		remove(accountSchemeViewId);
	}

	public AccountSchemeView getAccountSchemeView(String accountSchemeViewId) {
		return find(AccountSchemeView.class, accountSchemeViewId);
	}

	public List<AccountSchemeView> getAllAccountSchemeViews() {
		return this.find("from AccountSchemeView s");
	}

	public PageModel<RankingListUserDTO> queryAccountSchemeViewDTOsBySchemeId(PageParam pageParam,
			String schemeId,String userType) {
		int type = 0;
		if("02".equals(userType)){
			type = 1;
		}
		schemeId=schemeId.substring(0,3);
		StringBuffer sql = new StringBuffer();
		sql.append(" select s.owner_id AS userId,sum(s.money) as income,");
		sql.append(" CASE isEntMember(s.owner_id) WHEN 1 THEN e.name WHEN 0 THEN r.nick_name END AS userName,");
		sql.append(" CASE isEntMember(s.owner_id) WHEN 1 THEN e.gender WHEN 0 THEN (r.sex - 1) END AS gender,");
		sql.append(" CASE isEntMember(s.owner_id) WHEN 1 THEN e.head_url WHEN 0 THEN r.head_img_url END AS headUrl,");
		sql.append(" CASE isEntMember(s.owner_id) WHEN 1 THEN CASE e.mobile WHEN NULL THEN e.weixin ELSE e.mobile END WHEN 0 THEN '' END AS mobile ");
		sql.append(" FROM wx_user_bonus s ");
		sql.append(" left join ent_member e on s.owner_id = e.member_id ");
		sql.append(" left join rep_open_user r  on s.owner_id = r.open_id ");
		sql.append(" where s.bonus_id in (select w.bonus_id from wx_app_bonus w where w.scheme_id like '"+schemeId+"%') and isEntMember(s.owner_id) = ");
		sql.append(type);
		sql.append(" group by s.owner_id order by sum(s.money)  desc ");
		return this.findNative(sql.toString(), pageParam, new String[]{}, RankingListUserDTO.class);
	}

	@SuppressWarnings("unchecked")
	public MyRankDTO getMyRankDTOByMemberId(String memberId, String schemeId) {
		schemeId=schemeId.substring(0,3);
		StringBuffer sql = new StringBuffer();
		sql.append("select s.owner_id as owner_id, sum(s.money) as income,");
		sql.append("(select count(*)from (select sum(u.money) as income  from ");
		sql.append(" wx_user_bonus u where u.bonus_id in (select k.bonus_id from ");
 		sql.append(" wx_app_bonus k where k.scheme_id like '"+schemeId+"%') group by u.owner_id) a where  a.income >= sum(s.money)) as rank ");
		sql.append(" from wx_user_bonus s where s.bonus_id in (select  w.bonus_id  from wx_app_bonus w");
		sql.append(" where  w.scheme_id like '"+schemeId+"%') and s.owner_id = '");
		sql.append(memberId);
		sql.append("'");
		List<Object> objList  = this.findNative(sql.toString());
		if(!ListUtil.isEmpty(objList)){
			for(Object obj:objList){
				MyRankDTO myRankDTO = new MyRankDTO();
				Object[] objs = (Object[]) obj;
				if(StringUtil.isEmpty(objs[0])){
					return null;
				}
				myRankDTO.setUserId(objs[0]==null?"":String.valueOf(objs[0]));
				myRankDTO.setBonus(objs[1]==null?"":String.valueOf(objs[1]));
				myRankDTO.setRank(objs[2]==null?"":String.valueOf(objs[2]));
				return myRankDTO;
			}	
		}
		return null;
	
		
	}

}
