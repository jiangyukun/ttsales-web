package cn.ttsales.work.domain;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the sys_role database table.
 * 
 */
@Entity
@Table(name="sys_role")
public class SysRole extends cn.ttsales.work.domain.common.Common implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="role_id", unique=true, nullable=false, length=40)
	private String roleId;

	@Column(name="is_used", nullable=false)
	private int isUsed;

	@Column(length=200)
	private String remark;

	@Column(name="role_name", nullable=false, length=200)
	private String roleName;

    public SysRole() {
    }

	public String getRoleId() {
		return this.roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public int getIsUsed() {
		return this.isUsed;
	}

	public void setIsUsed(int isUsed) {
		this.isUsed = isUsed;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getRoleName() {
		return this.roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

}