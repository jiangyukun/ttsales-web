package cn.ttsales.work.domain;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;


/**
 * The persistent class for the rep_transmit database table.
 * 
 */
@Entity
@Table(name="rep_transmit")
public class RepTransmit extends cn.ttsales.work.domain.common.Common implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "myGenerator")
	@GenericGenerator(name = "myGenerator", strategy = "cn.ttsales.work.core.jpa.MyUUIDGenerator")
	@Column(name="transmit_id", unique=true, nullable=false, length=40)
	private String transmitId;

	@Column(name="popularize_id")
	private String popularizeId;

	@Column(name="p_transmit_id", length=40)
	private String pTransmitId;

	@Column(name="tra_time", length=19)
	private String traTime;

	@Column(name="tra_type", length=2)
	private String traType;

	@Column(name="user_cross_id", length=40)
	private String userCrossId;
	
	@Column(name="first_transmit_id", length=40)
	private String firstTransmitId;

    public RepTransmit() {
    }

	public String getTransmitId() {
		return this.transmitId;
	}

	public void setTransmitId(String transmitId) {
		this.transmitId = transmitId;
	}

	public String getPopularizeId() {
		return this.popularizeId;
	}

	public void setPopularizeId(String popularizeId) {
		this.popularizeId = popularizeId;
	}

	public String getPTransmitId() {
		return this.pTransmitId;
	}

	public void setPTransmitId(String pTransmitId) {
		this.pTransmitId = pTransmitId;
	}

	public String getTraTime() {
		return this.traTime;
	}

	public void setTraTime(String traTime) {
		this.traTime = traTime;
	}

	public String getTraType() {
		return this.traType;
	}

	public void setTraType(String traType) {
		this.traType = traType;
	}

	public String getUserCrossId() {
		return this.userCrossId;
	}

	public void setUserCrossId(String userCrossId) {
		this.userCrossId = userCrossId;
	}

	public String getFirstTransmitId() {
		return firstTransmitId;
	}

	public void setFirstTransmitId(String firstTransmitId) {
		this.firstTransmitId = firstTransmitId;
	}

}