package cn.ttsales.org.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * The persistent class for the org_structure database table.
 * 
 */
@Entity
@Table(name = "org_structure")
@SuppressWarnings("serial")
public class OrgStructure extends OrgBaseEntity implements Serializable {

	@Id
	@GeneratedValue(generator = "myGenerator")
	@GenericGenerator(name = "myGenerator", strategy = "cn.ttsales.work.core.jpa.MyUUIDGenerator")
	@Column(name = "org_struct_id")
	private String orgStructId;

	@Column(name = "org_struct_name")
	private String orgStructName;

	@Column(name = "p_org_struct_id")
	private String parentOrgStructId;

	@Column(name = "org_level")
	private Integer orgLevel;

	@Column(name = "has_children")
	private Integer hasChildren;

	@Column(name = "org_type")
	private Integer orgType;

	@Column(name = "region_type")
	private Integer regionType;
	
	@Column(name = "org_id")
	private String orgId;

	@Column(name = "wx_org_id")
	private String wxOrgId;

	@Column(name = "state")
	private String state;

	public String getOrgStructId() {
		return orgStructId;
	}

	public void setOrgStructId(String orgStructId) {
		this.orgStructId = orgStructId;
	}

	public String getOrgStructName() {
		return orgStructName;
	}

	public void setOrgStructName(String orgStructName) {
		this.orgStructName = orgStructName;
	}

	public String getParentOrgStructId() {
		return parentOrgStructId;
	}

	public void setParentOrgStructId(String parentOrgStructId) {
		this.parentOrgStructId = parentOrgStructId;
	}

	public Integer getOrgLevel() {
		return orgLevel;
	}

	public void setOrgLevel(Integer orgLevel) {
		this.orgLevel = orgLevel;
	}

	public Integer getHasChildren() {
		return hasChildren;
	}

	public void setHasChildren(Integer hasChildren) {
		this.hasChildren = hasChildren;
	}

	public Integer getOrgType() {
		return orgType;
	}

	public void setOrgType(Integer orgType) {
		this.orgType = orgType;
	}

	public Integer getRegionType() {
		return regionType;
	}

	public void setRegionType(Integer regionType) {
		this.regionType = regionType;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getWxOrgId() {
		return wxOrgId;
	}

	public void setWxOrgId(String wxOrgId) {
		this.wxOrgId = wxOrgId;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

}