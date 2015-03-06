package cn.ttsales.work.domain;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

/**
 * The persistent class for the rep_read_count database table.
 * 
 */
@Entity
@Table(name = "rep_read_count")
public class RepReadCount extends cn.ttsales.work.domain.common.Common implements
		Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "myGenerator")
	@GenericGenerator(name = "myGenerator", strategy = "cn.ttsales.work.core.jpa.MyUUIDGenerator")
	@Column(name = "read_count_id", unique = true, nullable = false, length = 40)
	private String readCountId;

	@Column(name = "read_time", length = 19)
	private String readTime;

	@Column(name = "transmit_id", length = 40)
	private String transmitId;

	@Column(name = "user_cross_id", length = 40)
	private String userCrossId;

	@Column(name = "popularize_id")
	private String popularizeId;

	@Column(name = "is_valid")
	private String isValid;

	public RepReadCount() {
	}

	public String getReadCountId() {
		return this.readCountId;
	}

	public void setReadCountId(String readCountId) {
		this.readCountId = readCountId;
	}

	public String getReadTime() {
		return this.readTime;
	}

	public void setReadTime(String readTime) {
		this.readTime = readTime;
	}

	public String getTransmitId() {
		return this.transmitId;
	}

	public void setTransmitId(String transmitId) {
		this.transmitId = transmitId;
	}

	public String getUserCrossId() {
		return this.userCrossId;
	}

	public void setUserCrossId(String userCrossId) {
		this.userCrossId = userCrossId;
	}

	public String getPopularizeId() {
		return popularizeId;
	}

	public void setPopularizeId(String popularizeId) {
		this.popularizeId = popularizeId;
	}

	public String getIsValid() {
		return isValid;
	}

	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}

}