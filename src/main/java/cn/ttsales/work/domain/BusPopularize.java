package cn.ttsales.work.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * The persistent class for the bus_popularize database table.
 * 
 */
@Entity
@Table(name = "bus_popularize")
public class BusPopularize extends cn.ttsales.work.domain.common.Common implements
		Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "myGenerator")
	@GenericGenerator(name = "myGenerator", strategy = "cn.ttsales.work.core.jpa.MyUUIDGenerator")
	@Column(name = "popularize_id", unique = true, nullable = false)
	private String popularizeId;

	@Column(name = "member_id", length = 40)
	private String memberId;
	
	@ManyToOne
	@JoinColumn(name="scheme_id")
 	private ProProductScheme proProductScheme;

	@Column(length = 2)
	private String state;

	@Column(name = "create_time", length = 19)
	private String createTime;

	public BusPopularize() {
	}

	public String getPopularizeId() {
		return popularizeId;
	}

	public void setPopularizeId(String popularizeId) {
		this.popularizeId = popularizeId;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

 

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public ProProductScheme getProProductScheme() {
		return proProductScheme;
	}

	public void setProProductScheme(ProProductScheme proProductScheme) {
		this.proProductScheme = proProductScheme;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	 

}