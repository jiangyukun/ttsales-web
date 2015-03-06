package cn.ttsales.work.domain;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the sys_role_menu database table.
 * 
 */
@Entity
@Table(name="sys_role_menu")
public class SysRoleMenu extends cn.ttsales.work.domain.common.Common implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="role_menu_id", unique=true, nullable=false, length=40)
	private String roleMenuId;

	@ManyToOne
	@JoinColumn(name = "menu_id")
 	private SysMenu sysMenu;

	@ManyToOne
	@JoinColumn(name = "role_id")
 	private SysRole sysRole;

    public SysRoleMenu() {
    }

	public String getRoleMenuId() {
		return this.roleMenuId;
	}

	public void setRoleMenuId(String roleMenuId) {
		this.roleMenuId = roleMenuId;
	}

	public SysMenu getSysMenu() {
		return sysMenu;
	}

	public void setSysMenu(SysMenu sysMenu) {
		this.sysMenu = sysMenu;
	}

	public SysRole getSysRole() {
		return sysRole;
	}

	public void setSysRole(SysRole sysRole) {
		this.sysRole = sysRole;
	}

	 

}