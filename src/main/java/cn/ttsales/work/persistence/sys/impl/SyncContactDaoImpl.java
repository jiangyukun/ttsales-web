/**
 * Copyright (c) 2013 RATANSOFT.All rights reserved.
 * @filename SysSaleResultDaoImpl.java
 * @package cn.ttsales.work.persistence.sys.impl
 * @author dandyzheng
 * @date 2012-7-27
 */
package cn.ttsales.work.persistence.sys.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import cn.ttsales.work.core.SASConstants;
import cn.ttsales.work.core.jpa.AbstractFacade;
import cn.ttsales.work.core.util.StringUtil;
import cn.ttsales.work.persistence.sys.SyncContactDao;
import cn.ttsales.work.wxapi.corp.pojo.User;


 
@Repository("syncContactDao")
public class SyncContactDaoImpl extends AbstractFacade implements SyncContactDao {

	@PersistenceContext(unitName="MAIN_DATABASE_PER")
	private EntityManager entityManager;
	
	@Override
	protected EntityManager getEntityManager() {
		return entityManager;
	}
	
	@SuppressWarnings("unchecked")
	public List<User> getSyncUsersByState(String state) {
		String sql = "select  m.member_id,m.name,m.nick_name,m.weixin,m.position," +
				"m.phone,m.mobile,m.email,m.gender,m.state,group_concat(d.wx_dept_id) as dept " +
				"from ent_deptment_member e,ent_member m,ent_deptment d " +
				"where e.dept_id = d.dept_id and e.member_id = m.member_id and m.state = '"+state+"' group by e.member_id";
		List<Object[]> results = this.findNative(sql);
		List<User> users = new ArrayList<User>();
		for (Object[] result : results) {
			User user = new User();
			user.setUserid(changeResultCell(result[0]));
			user.setName(changeResultCell(result[1]));
			user.setDepartment(getDepts(changeResultCell(result[10])));
			user.setPosition(changeResultCell(result[4]));
			user.setMobile(changeResultCell(result[6]));
			user.setGender(changeResultCell(result[8]));
			user.setTel(changeResultCell(result[5]));
			user.setEmail(changeResultCell(result[7]));
			user.setWeixinid(changeResultCell(result[3]));
			if(state==SASConstants.SYNC_STATE_UPDATE){
				user.setEnable("1");
			}
 			users.add(user);
		}	
		return users;
	}

	

	public void updateUserState(String userid,String state) {
		String sql = "update ent_member t set t.state = '"+state+"' where t.member_id = '"+userid+"'";
		this.executeNative(sql);
		if(state==SASConstants.SYNC_STATE_DELETEED){
			String sql2 = "delete  from ent_deptment_member  where member_id = '"+userid+"'";
			this.executeNative(sql2);
		}
  	}
	
	private List<Integer> getDepts(String deptsStr) {
		List<Integer> depts = new ArrayList<Integer>();
		String[] deptIds = deptsStr.split(",");
		for (String ids : deptIds) {
			depts.add(Integer.valueOf(ids));
		}
		return depts;
	}
	
	private String  changeResultCell(Object obj){
		String str = String.valueOf(obj);
		if(StringUtil.isEmpty(str)||str.equals("null")){
			return null; 
		}
		return str;
		
	}

	@SuppressWarnings("unchecked")
	public User getSyncUserByStateAndMemberId(String state, String memberId) {
		String sql = "select  m.member_id,m.name,m.nick_name,m.weixin,m.position," +
				"m.phone,m.mobile,m.email,m.gender,m.state,group_concat(d.wx_dept_id) as dept " +
				"from ent_deptment_member e,ent_member m,ent_deptment d " +
				"where m.member_id = '"+memberId+"' and e.dept_id = d.dept_id and e.member_id = m.member_id and m.state = '"+state+"' group by e.member_id";
		List<Object[]> results = this.findNative(sql);
		List<User> users = new ArrayList<User>();
		for (Object[] result : results) {
			User user = new User();
			user.setUserid(changeResultCell(result[0]));
			user.setName(changeResultCell(result[1]));
			user.setDepartment(getDepts(changeResultCell(result[10])));
			user.setPosition(changeResultCell(result[4]));
			user.setMobile(changeResultCell(result[6]));
			user.setGender(changeResultCell(result[8]));
			user.setTel(changeResultCell(result[5]));
			user.setEmail(changeResultCell(result[7]));
			user.setWeixinid(changeResultCell(result[3]));
			if(state==SASConstants.SYNC_STATE_UPDATE){
				user.setEnable("1");
			}
 			users.add(user);
		}	
		return users.get(0); 
	}

	@SuppressWarnings("unchecked")
	public User getSyncUserByMemberId(String memberId) {
		String sql = "select  m.member_id,m.name,m.nick_name,m.weixin,m.position," +
				"m.phone,m.mobile,m.email,m.gender,m.state,group_concat(d.wx_dept_id) as dept " +
				"from ent_deptment_member e,ent_member m,ent_deptment d " +
				"where m.member_id = '"+memberId+"' and e.dept_id = d.dept_id and e.member_id = m.member_id  group by e.member_id";
		List<Object[]> results = this.findNative(sql);
		List<User> users = new ArrayList<User>();
		for (Object[] result : results) {
			User user = new User();
			user.setUserid(changeResultCell(result[0]));
			user.setName(changeResultCell(result[1]));
			user.setDepartment(getDepts(changeResultCell(result[10])));
			user.setPosition(changeResultCell(result[4]));
			user.setMobile(changeResultCell(result[6]));
			user.setGender(changeResultCell(result[8]));
			user.setTel(changeResultCell(result[5]));
			user.setEmail(changeResultCell(result[7]));
			user.setWeixinid(changeResultCell(result[3]));
			user.setStatus(changeResultCell(result[9]));
			if(user.getStatus()==SASConstants.SYNC_STATE_UPDATE){
				user.setEnable("1");
			}
 			users.add(user);
		}	
		return users.get(0); 
	}
	
 }
