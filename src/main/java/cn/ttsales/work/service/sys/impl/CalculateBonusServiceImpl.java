/**
 * Copyright (c) 2012 RATANSOFT.All rights reserved.
 * @filename SysSaleResultServiceImpl.java
 * @package cn.ttsales.work.service.sys.impl
 * @author dandyzheng
 * @date 2012-7-27
 */
package cn.ttsales.work.service.sys.impl;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.ttsales.work.persistence.sys.CalculateBonusDao;
import cn.ttsales.work.service.sys.CalculateBonusService;

@Service("calculateBonusService")
public class CalculateBonusServiceImpl implements CalculateBonusService,
		Serializable {
	private static final long serialVersionUID = -5391215564717232141L;

	@Autowired
	private CalculateBonusDao calculateBonusDao;

	@Transactional
	public void calculateBonusByBonusId(String bonusId) {
		calculateBonusDao.calculateBonusByBonusId(bonusId);
	}
}
