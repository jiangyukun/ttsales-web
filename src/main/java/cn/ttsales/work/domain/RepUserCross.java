package cn.ttsales.work.domain;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;


/**
 * The persistent class for the rep_user_cross database table.
 * 
 */
@Entity
@Table(name="rep_user_cross")
public class RepUserCross extends cn.ttsales.work.domain.common.Common implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "myGenerator")
	@GenericGenerator(name = "myGenerator", strategy = "cn.ttsales.work.core.jpa.MyUUIDGenerator")
	@Column(name="user_cross_id", unique=true, nullable=false, length=40)
	private String userCrossId;

	@Column(name="user_id",length=40)
	private String userId;
	
	@Column(name="type",length=2)
	private String type;
	
	@Column(name="create_time", length=19)
	private String createTime;

    public RepUserCross() {
    }

	public String getUserCrossId() {
		return this.userCrossId;
	}

	public void setUserCrossId(String userCrossId) {
		this.userCrossId = userCrossId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	

}