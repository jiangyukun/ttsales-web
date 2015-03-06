package cn.ttsales.work.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * The persistent class for the ent_deptment_member database table.
 * 
 */
@Entity
@Table(name = "ent_deptment_member")
public class EntDeptmentMember extends cn.ttsales.work.domain.common.Common
		implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "myGenerator")
	@GenericGenerator(name = "myGenerator", strategy = "cn.ttsales.work.core.jpa.MyUUIDGenerator")
	@Column(name = "ent_deptment_member_id", unique = true, nullable = false)
	private String entDeptmentMemberId;

	@Column(name = "member_id", length = 40)
	private String memberId;

	@Column(name = "dept_id", length = 40)
	private String deptId;

	public EntDeptmentMember() {
	}

	public String getEntDeptmentMemberId() {
		return entDeptmentMemberId;
	}

	public void setEntDeptmentMemberId(String entDeptmentMemberId) {
		this.entDeptmentMemberId = entDeptmentMemberId;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

}