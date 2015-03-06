package cn.ttsales.work.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * The persistent class for the pro_product_scheme database table.
 * 
 */
@Entity
@Table(name = "pro_product_scheme")
public class ProProductScheme implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "myGenerator")
	@GenericGenerator(name = "myGenerator", strategy = "cn.ttsales.work.core.jpa.MyUUIDGenerator")
	@Column(name = "scheme_id")
	private String schemeId;

	@Lob()
	private byte[] content;

	@Column(name = "end_time")
	private String endTime;

	@Column(name = "is_require_store")
	private String isRequireStore;

	@ManyToOne
	@JoinColumn(name = "product_id")
	private ProProduct proProduct;

	@Column(name = "start_time")
	private String startTime;

	private String state;

	private String summary;

	@Column(name = "thumbnail_url")
	private String thumbnailUrl;

	private String title;
	
	@Column(name = "reward_info")
	private String rewardInfo;

	public ProProductScheme() {
	}

	public ProProductScheme(String schemeId) {
		super();
		this.schemeId = schemeId;
	}

	public String getSchemeId() {
		return this.schemeId;
	}

	public void setSchemeId(String schemeId) {
		this.schemeId = schemeId;
	}

	public byte[] getContent() {
		return this.content;
	}

	public void setContent(byte[] content) {
		this.content = content;
	}

	public String getEndTime() {
		return this.endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getIsRequireStore() {
		return this.isRequireStore;
	}

	public void setIsRequireStore(String isRequireStore) {
		this.isRequireStore = isRequireStore;
	}

	public String getStartTime() {
		return this.startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getSummary() {
		return this.summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getThumbnailUrl() {
		return this.thumbnailUrl;
	}

	public void setThumbnailUrl(String thumbnailUrl) {
		this.thumbnailUrl = thumbnailUrl;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public ProProduct getProProduct() {
		return proProduct;
	}

	public void setProProduct(ProProduct proProduct) {
		this.proProduct = proProduct;
	}

	public String getRewardInfo() {
		return rewardInfo;
	}

	public void setRewardInfo(String rewardInfo) {
		this.rewardInfo = rewardInfo;
	}

}