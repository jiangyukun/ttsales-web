/**
 * Copyright (c) 2014 RATANSFOT.All rights reserved.
 * @filename ProProductSchemeDao.java
 * @package cn.ttsales.work.persistence.pro
 * @author dandyzheng
 * @date 2012-7-27
 */
package cn.ttsales.work.persistence.pro;

import java.util.List;

import cn.ttsales.work.domain.ProProductScheme;



/**
 * ProProductScheme DAO
 * @author dandyzheng
 *
 */
public interface ProProductSchemeDao {
	/**
	 * 保存 ProProductScheme
	 * @param proProductScheme ProProductScheme
	 * @return ProProductScheme 
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public ProProductScheme saveProProductScheme(ProProductScheme proProductScheme);
	
	/**
	 * 批量保存ProProductScheme
	 * @param proProductSchemes ProProductSchemes
	 * @return List<ProProductScheme>
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public List<ProProductScheme> saveProProductSchemes(List<ProProductScheme> proProductSchemes);
	
	/**
	 * 修改ProProductScheme
	 * @param proProductScheme ProProductScheme
	 * @return ProProductScheme
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public ProProductScheme editProProductScheme(ProProductScheme proProductScheme);
	
	/**
	 * 批量修改ProProductScheme
	 * @param proProductSchemes ProProductSchemes
	 * @return List<ProProductScheme>
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public List<ProProductScheme> editProProductSchemes(List<ProProductScheme> proProductSchemes);
	
	/**
	 * 删除ProProductScheme
	 * @param ProProductScheme ProProductScheme
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public void removeProProductScheme(ProProductScheme proProductScheme);
	
	/**
	 * 批量删除ProProductScheme
	 * @param ProProductSchemes ProProductSchemes
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public void removeProProductSchemes(List<ProProductScheme> proProductSchemes);
	
	/**
	 * 根据ProProductScheme' id，删除ProProductScheme
	 * @param proProductSchemeId ProProductScheme's id
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public void removeProProductScheme(String proProductSchemeId);
	
	/**
	 * 根据ProProductScheme' id，获取ProProductScheme
	 * @param proProductSchemeId ProProductScheme's id
	 * @return ProProductScheme
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public ProProductScheme getProProductScheme(String proProductSchemeId); 
	
	/**
	 * 获取所有ProProductScheme
	 * @return List<ProProductScheme>
	 * @author dandyzheng
	 * @date 2013-3-27
	 * @see
	 */
	public List<ProProductScheme> getAllProProductSchemes();

	/**
	 * 根据条件获取ProProductSchemes
	 * @param title
	 * @param summary
	 * @param state
	 * @return
	 * @author zhaoxiaobin
	 * @date 2014-9-15
	 * @see
	 */
	public List<ProProductScheme> getProProductSchemesByConditions(
			String title, String summary, String state);
	
	/**
	 * 根据产品id获取文案
	 * @param productId
	 * @return
	 * @author zhaoxiaobin
	 * @date 2014-9-19
	 * @see
	 */
	public List<ProProductScheme> getProProductSchemesByPorductId(
			String productId);
	
		
}
