/**
 * Copyright (c) 2013 RATANSOFT.All rights reserved.
 * @filename SysSaleResultDaoImpl.java
 * @package cn.ttsales.work.persistence.sys.impl
 * @author dandyzheng
 * @date 2012-7-27
 */
package cn.ttsales.work.persistence.sys.impl;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.springframework.stereotype.Repository;

import cn.ttsales.work.core.jpa.AbstractFacade;
import cn.ttsales.work.core.util.ArrayUtil;
import cn.ttsales.work.core.util.JsonUtil;
import cn.ttsales.work.core.util.ListUtil;
import cn.ttsales.work.core.util.StringUtil;
import cn.ttsales.work.domain.RepTransmit;
import cn.ttsales.work.domain.RepUserCross;
import cn.ttsales.work.dto.MyPerformanceDTO;
import cn.ttsales.work.dto.PerformanceAnalyzeDTO;
import cn.ttsales.work.dto.PerformanceRankDTO;
import cn.ttsales.work.dto.PopulStatDTO;
import cn.ttsales.work.dto.PopulStatSumDTO;
import cn.ttsales.work.dto.SchemePerformanceDTO;
import cn.ttsales.work.dto.SysMemberCountDTO;
import cn.ttsales.work.dto.SysSalerCountDTO;
import cn.ttsales.work.dto.TrackUserDTO;
import cn.ttsales.work.persistence.sys.TransmitTempDao;


/**
 * SysSaleResult Dao Impl
 * @author dandyzheng
 *
 */
@Repository("transmitTempDao")
public class TransmitTempDaoImpl extends AbstractFacade implements TransmitTempDao {

	@PersistenceContext(unitName="MAIN_DATABASE_PER")
	private EntityManager entityManager;
	
	@Override
	protected EntityManager getEntityManager() {
		return entityManager;
	}
	
	public void updateTransmitTemp(String date) {
		String deleteSql = "delete  from transmit_temp  where date = '"+date+"';";
		this.executeNative(deleteSql);
		String addSql = "insert into transmit_temp select "+
						"'"+date+"' as date, "+	
					    "t.transmit_id, "+
						"t.popularize_id, "+
					    "t.user_cross_id, "+
						"substring(t.tra_time,1,10) as tra_time, "+
					    "t.p_transmit_id, "+
						"t.first_transmit_id, "+
					    "t2.user_cross_id as first_user_cross_id, "+
					    "t.tra_type, "+
					    "count(distinct case "+
					            "when r.read_time like '%"+date+"%' then r.read_count_id "+
					            "else null "+
					        "end) as read_count, "+
				        "count(distinct case "+
				                "when r.read_time like '%"+date+"%' and r.is_valid = 1 then r.read_count_id "+
				                "else null "+
				            "end) as read_valid_count, "+
				    	"count(distinct case "+
				                "when r.read_time like '%"+date+"%' and r.is_valid = 0 then r.read_count_id "+
				                "else null "+
				            "end) as read_invalid_count, "+
					    "count(distinct case "+
					            "when b.create_time like '%"+date+"%' then b.reserve_id "+
					            "else null "+
					        "end) as reserve_count, "+
						"count(distinct case "+
					            "when b.arrive_time like '%"+date+"%'  then b.reserve_id "+
					            "else null "+
					        "end) as arrive_count "+
					"from "+
					    "rep_transmit t "+
					    	"left join "+
					    "rep_transmit t2 on t.first_transmit_id = t2.transmit_id "+
					        "left join "+
					    "rep_read_count r ON t.transmit_id = r.transmit_id "+
					        "left join "+
					    "bus_reserve b ON t.transmit_id = b.transmit_id "+
					"where "+
					    "t.tra_time like '%"+date+"%' or r.read_time like '%"+date+"%' " +
					    "or b.create_time like '%"+date+"%' or b.arrive_time like '%"+date+"%' "+
					"group by t.transmit_id;";
		this.executeNative(addSql);
	}

	@SuppressWarnings("unchecked")
	public List<MyPerformanceDTO> getMyPerformance(String selectMonth,
			String userId) {
		List<MyPerformanceDTO> myPerformanceDTOs = new ArrayList<MyPerformanceDTO>();
		String sql = "SELECT "+
					    "p.popularize_id, "+
						"a.advertiser_name, "+
						"o.title, "+
					    "count(distinct case "+
					            "when t.tra_time like '%"+selectMonth+"%' and t.tra_type='01' then t.transmit_id "+
					            "else null "+
					        "end) as transmit_01_count, "+
						"count(distinct case "+
					            "when t.tra_time like '%"+selectMonth+"%' and t.tra_type='02' then t.transmit_id "+
					            "else null "+
					        "end) as transmit_02_count, "+
					    "sum(case when t.tra_type='01' then read_valid_count else 0 end) as read_01_count, "+
						"sum(case when t.tra_type='02' then read_valid_count else 0 end) as read_02_count, "+
					    "sum(case when t.tra_type='01' then reserve_count else 0 end) as reserve_01_count, "+
						"sum(case when t.tra_type='02' then reserve_count else 0 end) as reserve_02_count, "+
					    "sum(case when t.tra_type='01' then arrive_count else 0 end) as arrive_01_count, "+
						"sum(case when t.tra_type='02' then arrive_count else 0 end) as arrive_02_count "+
					"FROM "+
					    "bus_popularize p "+
					        "left join "+
					    "transmit_temp t ON p.popularize_id = t.popularize_id "+
						"left join pro_product_scheme o on o.scheme_id = p.scheme_id  "+
						"left join pro_product s on s.product_id = o.product_id  "+
						"left join ent_advertiser a on a.advertiser_id = s.advertiser_id "+
					"where "+
					    "p.member_id = '"+userId+"' "+
					        "and t.date like '%"+selectMonth+"%' and p.state <> '00'"+
					"group by  p.popularize_id";
		List<Object[]> results = this.findNative(sql);
		for (int i = 0; i < results.size(); i++) {
			Object[] result = results.get(i);
			MyPerformanceDTO myPerformanceDTO = new MyPerformanceDTO();
			myPerformanceDTO.setPopularizeId(String.valueOf(result[0]));
			myPerformanceDTO.setAdvertiserName(String.valueOf(result[1]));
			myPerformanceDTO.setTitle(String.valueOf(result[2]));
			myPerformanceDTO.setTransmit01Count(Integer.valueOf(String.valueOf(result[3])));
			myPerformanceDTO.setTransmit02Count(Integer.valueOf(String.valueOf(result[4])));
			myPerformanceDTO.setRead01Count(Integer.valueOf(String.valueOf(result[5])));
			myPerformanceDTO.setRead02Count(Integer.valueOf(String.valueOf(result[6])));
			myPerformanceDTO.setReserve01Count(Integer.valueOf(String.valueOf(result[7])));
			myPerformanceDTO.setReserve02Count(Integer.valueOf(String.valueOf(result[8])));
			myPerformanceDTO.setArrive01Count(Integer.valueOf(String.valueOf(result[9])));
			myPerformanceDTO.setArrive02Count(Integer.valueOf(String.valueOf(result[10])));
			myPerformanceDTOs.add(myPerformanceDTO);
		}
		return myPerformanceDTOs;
	}

	@SuppressWarnings("unchecked")
	public List<PerformanceAnalyzeDTO> getPerformanceAnalyze(String popularizeId,
			int showType, String userId) {
		List<RepUserCross> users = this.find("from RepUserCross s where s.userId = ?",userId);
		String userCrossId = users.get(0).getUserCrossId();
		List<PerformanceAnalyzeDTO> performanceAnalyzeDTOs = new ArrayList<PerformanceAnalyzeDTO>();
		String baseSql = "";
		if(showType == 1){
			baseSql ="SELECT "+ 
					   "case u.type "+
					        "when '01' then '匿名用户' "+
					        "when '02' then m.nick_name "+
					        "when "+
					            "'03' "+
					        "then "+
					            "case "+
					                "when u.user_id is null then '匿名用户' "+
					                "else case when e.nick_name is null then '匿名用户' else e.nick_name end "+
					            "end "+
					    "end as nickname, "+
					    "t.first_user_cross_id, "+
					    "?"+
					"FROM "+
					    "transmit_temp t "+
					        "left join "+
					    "rep_user_cross u ON t.first_user_cross_id = u.user_cross_id "+
					        "left join "+
					    "rep_open_user e ON u.user_id = e.open_id "+
					        "left join "+
					    "ent_member m ON u.user_id = m.member_id "+
					"where "+
					    "t.popularize_id = '"+popularizeId+"' "+
					        "and t.first_user_cross_id is not null  and u.user_cross_id is not null "+
					"group by t.first_user_cross_id "+
					"order by count desc "+
					"limit 5;";
		}else{
			baseSql = "SELECT "+
				    "case u.type "+
				        "when '01' then '匿名用户' "+
				        "when '02' then m.nick_name "+
				        "when '03' "+
				        "then "+
				            "case "+
				                "when u.user_id is null then '匿名用户' "+
				                "else case when e.nick_name is null then '匿名用户' else e.nick_name end "+
				            "end "+
				    "end as nickname, "+
				    "t.user_cross_id, "+
				    "?"+
				"FROM "+
				    "transmit_temp t "+
				        "left join "+
				    "rep_user_cross u ON t.user_cross_id = u.user_cross_id left join rep_open_user e ON u.user_id = e.open_id left join ent_member m on u.user_id = m.member_id "+
				"where "+
				    "t.popularize_id = '"+popularizeId+"' and  t.user_cross_id != '"+userCrossId+"'  and u.user_cross_id is not null "+
				"group by t.user_cross_id "+
				"order by count desc "+
				"limit 5;";
		}
		 String transmitCountSql = baseSql.replace("?", "count(distinct transmit_id) as count ");
		 List<Object[]> transmitResults = this.findNative(transmitCountSql);
		 performanceAnalyzeDTOs.addAll(getPerformanceAnalyzeDTOs("transmit",transmitResults));
		 
		 String readCountSql = baseSql.replace("?", "sum(read_valid_count) as count ");
		 List<Object[]> readResults = this.findNative(readCountSql);
		 performanceAnalyzeDTOs.addAll(getPerformanceAnalyzeDTOs("read",readResults));
		 
		 String reaserveCountSql = baseSql.replace("?", "sum(reserve_count) as count ");
		 List<Object[]> reaserveResults = this.findNative(reaserveCountSql);
		 performanceAnalyzeDTOs.addAll(getPerformanceAnalyzeDTOs("reserve",reaserveResults));
		 
		 String arriveCountSql = baseSql.replace("?", "sum(arrive_count) as count ");
		 List<Object[]> arriveResults = this.findNative(arriveCountSql);
		 performanceAnalyzeDTOs.addAll(getPerformanceAnalyzeDTOs("arrive",arriveResults));
		return performanceAnalyzeDTOs;
	}

	private List<PerformanceAnalyzeDTO> getPerformanceAnalyzeDTOs(
			String type, List<Object[]> transmitResults) {
		 List<PerformanceAnalyzeDTO> performanceAnalyzeDTOs = new ArrayList<PerformanceAnalyzeDTO>();
		 for (int i = 0; i < transmitResults.size(); i++) {
			 Object[] result = transmitResults.get(i);
			 PerformanceAnalyzeDTO performanceAnalyzeDTO = new PerformanceAnalyzeDTO();
			 performanceAnalyzeDTO.setNickName(String.valueOf(result[0]));
			 performanceAnalyzeDTO.setUserCrossId(String.valueOf(result[1]));
			 int count = Integer.valueOf(String.valueOf(result[2]));
			 if(count==0){
				 continue;
			 }
			 performanceAnalyzeDTO.setCount(count);
			 performanceAnalyzeDTO.setType(type);
			 performanceAnalyzeDTOs.add(performanceAnalyzeDTO);
		 }
		return performanceAnalyzeDTOs;
	}

	@SuppressWarnings("unchecked")
	public List<TrackUserDTO> getTrackUserDTOs(String popularizeId,String userCrossId) {
		String baseSql = "SELECT t.transmit_id,t.p_transmit_id,t.user_cross_id," +
				"case u.type when '01' then '匿名用户' when '02' then e.nick_name  when '03' then case when u.user_id is null then '匿名用户' else case when m.nick_name is null then '匿名用户' else m.nick_name end end end as nickname," +
				"case u.type when '01' then '' when '02' then e.head_url when '03' then m.head_img_url end as head_url,t.tra_time, " +
				"case u.type when '01' then '' when '02' then e.gender when '03' then (m.sex-1) end as gender " +
				"FROM rep_transmit t left join rep_user_cross u ON t.user_cross_id = u.user_cross_id " +
				"left join rep_open_user m ON u.user_id = m.open_id " +
				"left join ent_member e ON u.user_id = e.member_id "+
				"where t.transmit_id = '?'";
		List<TrackUserDTO> resultUsers = null;
		List<RepTransmit> repTransmits = this.find("from RepTransmit s where s.popularizeId=? and s.userCrossId=? order by s.traTime desc",popularizeId,userCrossId);
		for (int i = 0; i < repTransmits.size(); i++) {
			String transmitId = repTransmits.get(i).getTransmitId();
			String queryId = transmitId;
			List<TrackUserDTO> users = new ArrayList<TrackUserDTO>();
			while(!queryId.equals("0")){
	 			String sql=baseSql.replace("?",queryId);
	 			List<Object[]> result = this.findNative(sql);
	 			TrackUserDTO trackUserDTO = new TrackUserDTO();
	 			//转发数据丢失
	 			if(ListUtil.isEmpty(result)){
	 				List<Object[]> entMembers = this.find(" select  s.name,s.headUrl,s.gender from EntMember s where s.memberId = ( select b.memberId from BusPopularize b where b.popularizeId = ?)",popularizeId);
	 				String baseSql2 =" SELECT t.transmit_id,t.p_transmit_id,t.user_cross_id," +
	 						"case u.type when '01' then '匿名用户' when '02' then e.nick_name  when '03' then case when u.user_id is null then '匿名用户' else case when m.nick_name is null then '匿名用户' else m.nick_name end end end as nickname," +
	 						"case u.type when '01' then '' when '02' then e.head_url when '03' then m.head_img_url end as head_url,t.tra_time, " +
	 						"case u.type when '01' then '' when '02' then e.gender when '03' then (m.sex-1) end as gender " +
	 						"FROM rep_transmit t left join rep_user_cross u ON t.user_cross_id = u.user_cross_id " +
	 						"left join rep_open_user m ON u.user_id = m.open_id " +
	 						"left join ent_member e ON u.user_id = e.member_id "+
	 						"where t.popularize_id = '"+popularizeId+"'order by t.tra_time ";
	 				List<Object[]> res = this.findNative(baseSql2);
					trackUserDTO.setTraTime(String.valueOf(res.get(0)[5]));
					trackUserDTO.setNickName(String.valueOf(entMembers.get(0)[0]));
					trackUserDTO.setHeadUrl(String.valueOf(entMembers.get(0)[1]));
					trackUserDTO.setGender(String.valueOf(entMembers.get(0)[2]));
					users.add(0,trackUserDTO);
					break;
	 			}else{
					trackUserDTO.setTransmitId(String.valueOf(result.get(0)[0]));
					trackUserDTO.setpTransmitId(String.valueOf(result.get(0)[1]));
					trackUserDTO.setUserCrossId(String.valueOf(result.get(0)[2]));
					trackUserDTO.setNickName(String.valueOf(result.get(0)[3]));
					trackUserDTO.setHeadUrl(String.valueOf(result.get(0)[4]));
					trackUserDTO.setTraTime(String.valueOf(result.get(0)[5]));
					trackUserDTO.setGender(String.valueOf(result.get(0)[6]));
	 			}
				users.add(0,trackUserDTO);
				queryId=trackUserDTO.getpTransmitId();
			}
			if(resultUsers==null){
				resultUsers=users;
			}else if(resultUsers.size()>=users.size()){
				resultUsers=users;
			}
		}
		return resultUsers;
	}

	@SuppressWarnings("unchecked")
	public JSONObject getUserPerformance(String popularizeId,
			String userCrossId, int showType) {
		JSONObject result = new JSONObject();
		String sql ="SELECT "+
					"count(distinct transmit_id), "+
					"sum(read_valid_count), "+
					"sum(reserve_count), "+
					"sum(arrive_count) "+
				"FROM "+
				    "transmit_temp t "+
				"where "+
				    "t.popularize_id = '"+popularizeId+"' "+
				        "and t.user_cross_id = '"+userCrossId+"'";
 		List<Object[]> results = this.findNative(sql);
		result.put("transmitCount", String.valueOf(results.get(0)[0]));		
		result.put("readCount", String.valueOf(results.get(0)[1]));		
		result.put("reserveCount", String.valueOf(results.get(0)[2]));		
		result.put("arriveCount", String.valueOf(results.get(0)[3]));		
 		return result;
	}

	@SuppressWarnings("unchecked")
	public List<PopulStatDTO> getPopulStatDTOs(String schemeId,
			String startDate, String endDate) {
		List<PopulStatDTO> populStatDTOs = new ArrayList<PopulStatDTO>();
		String sql = "SELECT "+
					    "s.scheme_id, t.date,count(case when t.tra_time = t.date then transmit_id else null end), "+
						"sum(t.read_valid_count), "+
						"sum(t.reserve_count), "+
						"sum(t.arrive_count) "+
					"FROM "+
					    "transmit_temp t "+
					        "left join "+
					    "bus_popularize b ON t.popularize_id = b.popularize_id "+
					        "left join "+
					    "pro_product_scheme s ON s.scheme_id = b.scheme_id "+
					"where "+
					    "s.scheme_id = '"+schemeId+"' "+
					        "and t.date >= '"+startDate+"' "+
					        "and t.date <= '"+endDate+"' "+
					"group by t.date";
		List<Object[]> results = this.findNative(sql);
		for (int i = 0; i < results.size(); i++) {
			Object[] result = results.get(i);
			PopulStatDTO populStatDTO = new PopulStatDTO();
			populStatDTO.setSchemeId(String.valueOf(result[0]));
			populStatDTO.setDate(String.valueOf(result[1]));
			populStatDTO.setTransmitCount(Integer.valueOf(String.valueOf(result[2])));
			populStatDTO.setReadCount(Integer.valueOf(String.valueOf(result[3])));
			populStatDTO.setReserveCount(Integer.valueOf(String.valueOf(result[4])));
			populStatDTO.setArriveCount(Integer.valueOf(String.valueOf(result[5])));
			populStatDTOs.add(populStatDTO);
		}
		return populStatDTOs;
	}

	@SuppressWarnings("unchecked")
	public PopulStatSumDTO getPopulStatSumDTO(String schemeId,
			String startDate, String endDate) {
		PopulStatSumDTO populStatSumDTO = new PopulStatSumDTO();
		String sql = "SELECT "+
			    "s.scheme_id,count(case when t.tra_time = t.date then transmit_id else null end), "+
				"sum(t.read_valid_count), "+
				"sum(t.reserve_count), "+
				"sum(t.arrive_count) "+
			"FROM "+
			    "transmit_temp t "+
			        "left join "+
			    "bus_popularize b ON t.popularize_id = b.popularize_id "+
			        "left join "+
			    "pro_product_scheme s ON s.scheme_id = b.scheme_id "+
			"where "+
			    "s.scheme_id = '"+schemeId+"' "+
			        "and t.date >= '"+startDate+"' "+
			        "and t.date <= '"+endDate+"' ";
		List<Object[]> results = this.findNative(sql);
		populStatSumDTO.setSchemeId(String.valueOf(results.get(0)[0]));
		populStatSumDTO.setAllTransmitCount(Integer.valueOf(String.valueOf(results.get(0)[1])));
		populStatSumDTO.setAllReadCount(Integer.valueOf(String.valueOf(results.get(0)[2])));
		populStatSumDTO.setAllReserveCount(Integer.valueOf(String.valueOf(results.get(0)[3])));
		populStatSumDTO.setAllArriveCount(Integer.valueOf(String.valueOf(results.get(0)[4])));
		return populStatSumDTO;
	}

	public List<SysMemberCountDTO> getSysMemberCountDTOs(String type,
			String schemeId, String startDate, String endDate) {
		String sql = "";
		if(type.equals("transmit_count")){
			sql = "SELECT "+
					"b.member_id, "+
					"e.name, "+
					"count(distinct case when t.tra_time >='"+startDate+"' and t.tra_time<='"+endDate+"' then t.transmit_id else null end) as count "+
				"FROM "+
				    "transmit_temp t "+
				        "left join "+
				    "bus_popularize b ON t.popularize_id = b.popularize_id "+
						"left join  "+
					"ent_member e on b.member_id = e.member_id "+
				"where "+
				    "b.scheme_id = '"+schemeId+"' "+
				        "and t.date >= '"+startDate+"' "+
				        "and t.date <= '"+endDate+"' "+
 				"group by b.member_id "+
				"order by count desc limit 10;";
		}else{
			sql = "SELECT "+
					"b.member_id, "+
					"e.name, "+
					"sum("+type+") as count "+
				"FROM "+
				    "transmit_temp t "+
				        "left join "+
				    "bus_popularize b ON t.popularize_id = b.popularize_id "+
						"left join  "+
					"ent_member e on b.member_id = e.member_id "+
				"where "+
				    "b.scheme_id = '"+schemeId+"' "+
				        "and t.date >= '"+startDate+"' "+
				        "and t.date <= '"+endDate+"' "+
 				"group by b.member_id "+
				"order by count desc limit 10;";
		}
		@SuppressWarnings("unchecked")
		List<Object[]> results = this.findNative(sql);
		List<SysMemberCountDTO> sysMemberCountDTOs = new ArrayList<SysMemberCountDTO>();
		for (int i = 0; i < results.size(); i++) {
			Object[] result = results.get(i);
			SysMemberCountDTO sysMemberCountDTO = new SysMemberCountDTO();
			sysMemberCountDTO.setMemberId(String.valueOf(result[0]));
			sysMemberCountDTO.setName(String.valueOf(result[1]));
			sysMemberCountDTO.setCount(Integer.valueOf(String.valueOf(result[2])));
			sysMemberCountDTO.setRank(i+1);
			sysMemberCountDTOs.add(sysMemberCountDTO);
		}
		return sysMemberCountDTOs;
	}

	@SuppressWarnings("unchecked")
	public List<SysSalerCountDTO> getSysSalerCountDTOsByConditions(
			String deptId, String startDate, String endDate) {
			DecimalFormat df=new DecimalFormat("00.00"); 
			String sql = "SELECT "+
						     "r.member_id, "+
						     "m.name, "+
						    "count(distinct case "+
						            "when r.rush_state = '01' then b.reserve_id "+
						            "else null "+
						        "end) as rush_success, "+
						    "count(distinct case "+
						            "when r.rush_state = '02' then b.reserve_id "+
						            "else null "+
						        "end) as rush_fail, "+
						    "count(distinct case "+
						            "when "+
 						                    " b.rush_member_id = r.member_id "+
						                    "1?"+
						            "then "+
						                "b.reserve_id "+
						            "else null "+
						        "end) as reception "+
						"FROM "+
						    "bus_rush_record r "+
						        "left join "+
						    "bus_reserve b ON r.reserve_id = b.reserve_id "+
							"left join ent_deptment_member d on r.member_id = d.member_id "+
						    "left join ent_member m on m.member_id = r.member_id "+
						"where 1=1"+
						    "2? "+
						"group by r.member_id ";
	
			String part1 = "";
			String part2 = "";
			if(!StringUtil.isEmpty(startDate)){
				part1=" and b.arrive_time >= '"+startDate+"' ";
				part2=" and r.rush_time >= '"+startDate+"' ";
			}
			if(!StringUtil.isEmpty(endDate)){
				part1=" and b.arrive_time <= '"+endDate+"' ";
				part2=" and r.rush_time <= '"+endDate+"' ";
			}
			sql=sql.replace("1?", part1);
			sql=sql.replace("2?", part2);
			List<Object[]> results = this.findNative(sql);
			List<SysSalerCountDTO> sysSalerCountDTOs = new ArrayList<SysSalerCountDTO>();
			for (int i = 0; i < results.size(); i++) {
				Object[] result = results.get(i);
				SysSalerCountDTO sysSalerCountDTO = new SysSalerCountDTO();
				if(StringUtil.isEmpty(result[0])){
					continue;
				}
				sysSalerCountDTO.setMemberId(String.valueOf(result[0]));
				sysSalerCountDTO.setName(String.valueOf(result[1]));
				sysSalerCountDTO.setRushSuccessCount(Integer.valueOf(String.valueOf(result[2])));
				sysSalerCountDTO.setRushFailCount(Integer.valueOf(String.valueOf(result[3])));
				sysSalerCountDTO.setRushAllCount(sysSalerCountDTO.getRushSuccessCount()+sysSalerCountDTO.getRushFailCount());
				sysSalerCountDTO.setReceptionCount(Integer.valueOf(String.valueOf(result[4])));
				Double d=0d;
				if(sysSalerCountDTO.getRushAllCount()!=0){
					 d = sysSalerCountDTO.getReceptionCount()*1.0/sysSalerCountDTO.getRushAllCount()*100;
				 }
    			sysSalerCountDTO.setSuccessProb(df.format(d)+"%");
				sysSalerCountDTOs.add(sysSalerCountDTO);
			}
			return sysSalerCountDTOs;
 	}
	
	public JSONObject queryPerformanceRank(String popularizeId, String rankRange) {
		String conSql = "";
		JSONObject ranks = new JSONObject();
		JsonConfig config = new JsonConfig();
		Object[] params = new Object[] {popularizeId};
		
		String baseSql = " SELECT condition1 AS userCrossId, " +
					" 	CASE ruc.type  " +
					" 	WHEN '01' THEN '匿名用户'  " +
					" 	WHEN '02' THEN  " +
					" 		CASE " +
					" 			WHEN ISNULL(em.nick_name ) THEN '匿名用户' " +
					" 			ELSE em.nick_name  " +
					" 		END " +
					" 	WHEN '03' THEN  " +
					" 		CASE  " +
					" 			WHEN ISNULL(rou.nick_name) THEN '匿名用户' " +
					" 			ELSE rou.nick_name " +
					" 		END " +
					" 	END AS nickName , " +
					" 	condition2 AS count ";
		
		if ("all".equals(rankRange)) {
			baseSql = baseSql.replace(" condition1 ", " rt.user_cross_id ");
			conSql = " FROM transmit_temp rt " +
					" LEFT JOIN rep_user_cross ruc ON rt.user_cross_id = ruc.user_cross_id " +
					" LEFT JOIN rep_open_user rou ON rou.open_id = ruc.user_id " +
					" LEFT JOIN ent_member em ON em.member_id = ruc.user_id " +
					" WHERE rt.popularize_id = ? AND rt.p_transmit_id <> '0' " + 
					" GROUP BY rt.user_cross_id HAVING count > 0 " + 
					" ORDER BY count DESC, nickName DESC " + 
					" LIMIT 5;";
		} else {
			baseSql = baseSql.replace("condition1", "rt.first_user_cross_id");
			conSql = " FROM transmit_temp rt " +
					" LEFT JOIN rep_user_cross ruc ON rt.first_user_cross_id = ruc.user_cross_id " +
					" LEFT JOIN rep_open_user rou ON rou.open_id = ruc.user_id " +
					" LEFT JOIN ent_member em ON em.member_id = ruc.user_id " +
					" WHERE rt.popularize_id = ? AND rt.first_user_cross_id IS NOT NULL " + 
					" GROUP BY rt.first_user_cross_id HAVING count > 0 " + 
					" ORDER BY count DESC, nickName DESC " + 
					" LIMIT 5;";
		}
		
		String transmitCountSql = baseSql.replace("condition2", " count(distinct transmit_id) ");
		ranks.put("transmit", JsonUtil.fromList(this.findNative(transmitCountSql + conSql, params, PerformanceRankDTO.class), config));
		 
		String readCountSql = baseSql.replace("condition2", " sum(read_valid_count) ");
		ranks.put("read", JsonUtil.fromList(this.findNative(readCountSql + conSql, params, PerformanceRankDTO.class), config));
		 
		String reserveCountSql = baseSql.replace("condition2", " sum(reserve_count) ");
		ranks.put("reserve", JsonUtil.fromList(this.findNative(reserveCountSql + conSql, params, PerformanceRankDTO.class), config));
		 
		String arriveCountSql = baseSql.replace("condition2", " sum(arrive_count) ");
		ranks.put("arrive", JsonUtil.fromList(this.findNative(arriveCountSql + conSql, params, PerformanceRankDTO.class), config));
		
		return ranks;
	}

	public SchemePerformanceDTO queryMemberPerformanceInScheme(String popularizeId) {
		List<SchemePerformanceDTO> spds = new ArrayList<SchemePerformanceDTO>();
		
		String compleSql = " SELECT a.*, b.* FROM ( " +
				" SELECT bp.scheme_id AS shememId, count(DISTINCT rt.transmit_id) AS myTrasmitCount, " + 
				" SUM(rt.read_valid_count) AS myReadCount, SUM(rt.reserve_count) AS myReversCount, " + 
				" SUM(rt.arrive_count) AS myArriveCount " + 
				" FROM bus_popularize bp " + 
				" LEFT JOIN transmit_temp rt ON bp.popularize_id = rt.popularize_id " + 
				" WHERE bp.popularize_id = ? AND rt.p_transmit_id = '0' " + 
				" GROUP BY bp.scheme_id " + 
				" ) a LEFT JOIN ( " + 
				" SELECT bp.scheme_id AS shememId, count(DISTINCT rt.transmit_id) AS allTrasmitCount, " + 
				" SUM(rt.read_valid_count) AS allReadCount, SUM(rt.reserve_count) AS allReversCount, " + 
				" SUM(rt.arrive_count) AS allArriveCount " + 
				" FROM bus_popularize bp " + 
				" LEFT JOIN transmit_temp rt ON bp.popularize_id = rt.popularize_id " + 
				" WHERE bp.popularize_id = ? " + 
				" GROUP BY bp.scheme_id" + 
				" ) b ON a.shememId = b.shememId";
		
		spds = this.findNative(compleSql, new Object[]{popularizeId, popularizeId}, SchemePerformanceDTO.class);
		if (!ArrayUtil.isEmpty(spds)) {
			return spds.get(0);
		} else {
			return new SchemePerformanceDTO();
		}
	}
}
