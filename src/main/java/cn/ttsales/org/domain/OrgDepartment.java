package cn.ttsales.org.domain;

import java.io.Serializable;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

/**
 * The persistent class for the org_department database table.
 * 
 */

@Entity
@Table(name = "org_department")
@SuppressWarnings("serial")
public class OrgDepartment extends OrgBaseEntity implements Serializable {

	@Id
	@GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(name = "dept_id")
	private String deptId;

	@Column(name = "dept_name")
	private String deptName;

	@Column(name = "category")
	private String category;

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

}
