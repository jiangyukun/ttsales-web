package cn.ttsales.org.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * The persistent class for the org_store database table.
 * 
 */

@Entity
@Table(name = "org_store")
@SuppressWarnings("serial")
public class OrgStore extends OrgBaseEntity implements Serializable {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(name = "store_id")
	private String storeId;

	@Column(name = "store_name")
	private String storeName;

	@Column(name = "address")
	private String address;

	@Column(name = "contacts")
	private String contacts;

	@Column(name = "tel")
	private String tel;

	@Column(name = "phone")
	private String phone;

	@Column(name = "email")
	private String email;

	@Column(name = "region_id")
	private String regionId;

	@Column(name = "mp")
	private String mp;

	@Column(name = "longitude")
	private Double longitude;

	@Column(name = "latitude")
	private Double latitude;

	@Column(name = "two_dimen_pic")
	private String twoDimenPic;
	
	@Column(name="province")
	private String province;
	
	@Column(name="city")
	private String city;
	
	@Column(name="district")
	private String district;
	
	@Column(name="category")
	private String category;
	

	@ManyToMany
	@JoinTable(name = "org_store_secondary_ass",
		joinColumns = { @JoinColumn(name = "store_id") },
		inverseJoinColumns = { @JoinColumn(name = "secondary_store_id") })
	private List<OrgSecondaryStore> secondaryStores;

	/*
	 * property end
	 */

	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContacts() {
		return contacts;
	}

	public void setContacts(String contacts) {
		this.contacts = contacts;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRegionId() {
		return regionId;
	}

	public void setRegionId(String regionId) {
		this.regionId = regionId;
	}

	public String getMp() {
		return mp;
	}

	public void setMp(String mp) {
		this.mp = mp;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public String getTwoDimenPic() {
		return twoDimenPic;
	}

	public void setTwoDimenPic(String twoDimenPic) {
		this.twoDimenPic = twoDimenPic;
	}

	public List<OrgSecondaryStore> getSecondaryStores() {
		return secondaryStores;
	}

	public void setSecondaryStores(List<OrgSecondaryStore> secondaryStores) {
		this.secondaryStores = secondaryStores;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

}
