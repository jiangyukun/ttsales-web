package cn.ttsales.work.domain;

import java.io.Serializable;

import javax.persistence.*;

import cn.ttsales.org.domain.OrgMember;

/**
 * The persistent class for the sys_member_role database table.
 * 
 */
@Entity
@Table(name = "sys_member_role")
public class SysMemberRole extends cn.ttsales.work.domain.common.Common implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "member_role_id", unique = true, nullable = false, length = 40)
	private String memberRoleId;

	/*
	 * @ManyToOne
	 * 
	 * @JoinColumn(name = "member_id") private EntMember entMember;
	 */

	@ManyToOne
	@JoinColumn(name = "member_id")
	private OrgMember entMember;

	@ManyToOne
	@JoinColumn(name = "role_id")
	private SysRole sysRole;

	public SysMemberRole() {
	}

	public String getMemberRoleId() {
		return this.memberRoleId;
	}

	public void setMemberRoleId(String memberRoleId) {
		this.memberRoleId = memberRoleId;
	}

	/*
	 * public EntMember getEntMember() { return entMember; }
	 * 
	 * public void setEntMember(EntMember entMember) { this.entMember =
	 * entMember; }
	 */

	public SysRole getSysRole() {
		return sysRole;
	}

	public OrgMember getEntMember() {
		return entMember;
	}

	public void setEntMember(OrgMember entMember) {
		this.entMember = entMember;
	}

	public void setSysRole(SysRole sysRole) {
		this.sysRole = sysRole;
	}
}