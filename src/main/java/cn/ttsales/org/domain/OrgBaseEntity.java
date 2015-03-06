package cn.ttsales.org.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@MappedSuperclass
public class OrgBaseEntity {

	@Column(name = "create_time")
	@Temporal(TemporalType.DATE)
	private Date createTime;

	@Column(name = "last_update_time")
	@Temporal(TemporalType.DATE)
	private Date lastUpdateTime;

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

}
