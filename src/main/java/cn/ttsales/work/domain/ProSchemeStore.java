package cn.ttsales.work.domain;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;


/**
 * The persistent class for the pro_scheme_store database table.
 * 
 */
@Entity
@Table(name="pro_scheme_store")
public class ProSchemeStore implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "myGenerator")
	@GenericGenerator(name = "myGenerator", strategy = "cn.ttsales.work.core.jpa.MyUUIDGenerator")
	@Column(name="scheme_store_id")
	private String schemeStoreId;

	@Column(name="scheme_id")
	private String schemeId;

	private String state;

	@Column(name="store_id")
	private String storeId;

    public ProSchemeStore() {
    }

	public String getSchemeStoreId() {
		return this.schemeStoreId;
	}

	public void setSchemeStoreId(String schemeStoreId) {
		this.schemeStoreId = schemeStoreId;
	}

	public String getSchemeId() {
		return this.schemeId;
	}

	public void setSchemeId(String schemeId) {
		this.schemeId = schemeId;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getStoreId() {
		return this.storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

}