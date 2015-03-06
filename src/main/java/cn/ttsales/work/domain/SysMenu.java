package cn.ttsales.work.domain;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the sys_menu database table.
 * 
 */
@Entity
@Table(name="sys_menu")
public class SysMenu extends cn.ttsales.work.domain.common.Common implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="menu_id", unique=true, nullable=false, length=40)
	private String menuId;

	@Column(name="is_used", nullable=false)
	private int isUsed;

	@Column(name="menu_icon", length=50)
	private String menuIcon;

	@Column(name="menu_level", nullable=false)
	private int menuLevel;

	@Column(name="menu_name", nullable=false, length=50)
	private String menuName;

	@Column(name="menu_url", length=200)
	private String menuUrl;

	@Column(name="order_number", nullable=false)
	private int orderNumber;

	@Column(name="parent_menu_id", nullable=false, length=40)
	private String parentMenuId;

	@Column(length=200)
	private String remark;

    public SysMenu() {
    }

	public String getMenuId() {
		return this.menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	public int getIsUsed() {
		return this.isUsed;
	}

	public void setIsUsed(int isUsed) {
		this.isUsed = isUsed;
	}

	public String getMenuIcon() {
		return this.menuIcon;
	}

	public void setMenuIcon(String menuIcon) {
		this.menuIcon = menuIcon;
	}

	public int getMenuLevel() {
		return this.menuLevel;
	}

	public void setMenuLevel(int menuLevel) {
		this.menuLevel = menuLevel;
	}

	public String getMenuName() {
		return this.menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getMenuUrl() {
		return this.menuUrl;
	}

	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}

	public int getOrderNumber() {
		return this.orderNumber;
	}

	public void setOrderNumber(int orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getParentMenuId() {
		return this.parentMenuId;
	}

	public void setParentMenuId(String parentMenuId) {
		this.parentMenuId = parentMenuId;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}