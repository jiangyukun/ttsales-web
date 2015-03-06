/**
 * Copyright (c) 2012 RATANSOFT.All rights reserved.
 * @filename OrgStoreServiceImpl.java
 * @package cn.ttsales.work.service.ent.impl
 * @author dandyzheng
 * @date 2012-7-27
 */
package cn.ttsales.work.service.ent.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.ttsales.org.domain.OrgStore;
import cn.ttsales.work.core.SASConstants;
import cn.ttsales.work.core.util.BundleUtil;
import cn.ttsales.work.persistence.ent.EntDeptmentDao;
import cn.ttsales.work.persistence.ent.EntMemberDao;
import cn.ttsales.work.persistence.ent.EntStoreDao;
import cn.ttsales.work.service.ent.EntStoreService;
import cn.ttsales.work.web.common.util.QRCodeUtil;

import com.google.zxing.WriterException;


/**
 * EntStore Service Impl
 * @author dandyzheng
 *
 */
@Service("entStoreService")
public class EntStoreServiceImpl implements EntStoreService,Serializable {
	private static final long serialVersionUID = -5391215564717232141L;
	Logger log = Logger.getLogger(EntStoreServiceImpl.class);
	
	@Autowired
	private EntStoreDao entStoreDao;
	@Autowired
	private EntMemberDao entMemberDao;
	@Autowired
	private EntDeptmentDao entDeptmentDao;
	@Transactional
	public OrgStore saveOrgStore(OrgStore entStore) {
		return entStoreDao.saveOrgStore(entStore);
	}

	@Transactional
	public List<OrgStore> saveOrgStores(List<OrgStore> orgStores) {
		return null;
	}

	@Transactional
	public OrgStore editOrgStore(OrgStore orgStore) {
		return entStoreDao.editOrgStore(orgStore);
	}

	@Transactional
	public List<OrgStore> editOrgStores(List<OrgStore> orgStores) {
		return entStoreDao.editOrgStores(orgStores);
	}

	@Transactional
	public void removeOrgStore(OrgStore orgStore) {
		entStoreDao.removeOrgStore(orgStore);
	}

	@Transactional
	public void removeOrgStores(List<OrgStore> orgStores) {
		entStoreDao.removeOrgStores(orgStores);		
	}

	@Transactional
	public void removeOrgStore(String orgStoreId) {
		entStoreDao.removeOrgStore(orgStoreId);		
	}

	public OrgStore getOrgStore(String orgStoreId) {
		return entStoreDao.getOrgStore(orgStoreId);
	}

	public List<OrgStore> getAllOrgStores() {
		return entStoreDao.getAllOrgStores();
	}

	public List<OrgStore> getOrgStoresByDeptId(String id) {
		return entStoreDao.getOrgStoresByDeptId(id);
	}

	public List<OrgStore> getOrgStoresByConditions(String name, String address,
			String contacts) {
		return entStoreDao.getOrgStoresByConditions(name,address,contacts);
	}

	public List<OrgStore> queryOrgStoresByRegionId(String regionId,String schemeId) {
		return entStoreDao.queryOrgStoresByRegionId(regionId,schemeId);
	}

	public List<OrgStore> getOrgStoresByUserId(String userId) {
		return entStoreDao.getOrgStoresByUserId(userId);
	}
	
	
	
	@Transactional
	public byte[] getQCCode(String storeId,String serverName, String contextPath) {

		OrgStore orgStore = entStoreDao.getOrgStore(storeId);
		byte[] qcCode = orgStore.getTwoDimenPic().getBytes();
		if (qcCode == null) {//二维码未生成
			ByteArrayOutputStream out  = new ByteArrayOutputStream();

			String url = BundleUtil.getProperty(SASConstants.RESOURCE_BUNDLE,"sas.url.signInWithStore",serverName,contextPath,orgStore.getStoreId());
			try {
				QRCodeUtil.toQRCode(url, out);
				qcCode = out.toByteArray();
				orgStore.setTwoDimenPic(qcCode.toString());
				entStoreDao.editOrgStore(orgStore);
			} catch (IOException e) {
				log.error(e.getMessage());
				e.printStackTrace();
			} catch (WriterException e) {
				log.error(e.getMessage());
				e.printStackTrace();
			}
			
		}
		return qcCode;
	}

	public List<OrgStore> queryOrgStoreByPopularizeId(String popularizeId,String schemeId) {
		return entStoreDao.queryOrgStoreByPopularizeId(popularizeId,schemeId);
	}

	
}
