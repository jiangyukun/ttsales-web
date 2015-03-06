/**
 * Copyright (c) 2012 RATANSOFT.All rights reserved.
 * @filename RepReadCountServiceImpl.java
 * @package cn.ttsales.work.service.rep.impl
 * @author dandyzheng
 * @date 2012-7-27
 */
package cn.ttsales.work.service.rep.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.ttsales.work.domain.RepReadCount;
import cn.ttsales.work.persistence.rep.RepReadCountDao;
import cn.ttsales.work.service.rep.RepReadCountService;


/**
 * RepReadCount Service Impl
 * @author dandyzheng
 *
 */
@Service("repReadCountService")
public class RepReadCountServiceImpl implements RepReadCountService,Serializable {
	private static final long serialVersionUID = -5391215564717232141L;
	
	@Autowired
	private RepReadCountDao repReadCountDao;

	@Transactional
	public RepReadCount saveRepReadCount(RepReadCount repReadCount) {
		return repReadCountDao.saveRepReadCount(repReadCount);
	}

	@Transactional
	public List<RepReadCount> saveRepReadCounts(List<RepReadCount> repReadCounts) {
		return null;
	}

	@Transactional
	public RepReadCount editRepReadCount(RepReadCount repReadCount) {
		return repReadCountDao.editRepReadCount(repReadCount);
	}

	@Transactional
	public List<RepReadCount> editRepReadCounts(List<RepReadCount> repReadCounts) {
		return repReadCountDao.editRepReadCounts(repReadCounts);
	}

	@Transactional
	public void removeRepReadCount(RepReadCount RepReadCount) {
		repReadCountDao.removeRepReadCount(RepReadCount);
	}

	@Transactional
	public void removeRepReadCounts(List<RepReadCount> RepReadCounts) {
		repReadCountDao.removeRepReadCounts(RepReadCounts);		
	}

	@Transactional
	public void removeRepReadCount(String repReadCountId) {
		repReadCountDao.removeRepReadCount(repReadCountId);		
	}

	public RepReadCount getRepReadCount(String repReadCountId) {
		return repReadCountDao.getRepReadCount(repReadCountId);
	}

	public List<RepReadCount> getAllRepReadCounts() {
		return repReadCountDao.getAllRepReadCounts();
	}

	public int querySomeOneValidReadCount(String userId, String... userIds) {
		return repReadCountDao.querySomeOneValidReadCount(userId, userIds);
	}
}
