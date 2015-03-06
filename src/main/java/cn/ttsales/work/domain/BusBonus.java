package cn.ttsales.work.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * The persistent class for the bus_bonus database table.
 * 
 */
@Entity
@Table(name = "bus_bonus")
public class BusBonus implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "myGenerator")
	@GenericGenerator(name = "myGenerator", strategy = "cn.ttsales.work.core.jpa.MyUUIDGenerator")
	@Column(name = "bonus_id")
	private String bonusId;

	@Column(name = "bonus_name")
	private String bonusName;

	@Column(name = "scheme_id")
	private String schemeId;
	
	@Column(name = "bonus_rule")
	private String bonusRule;
	
	@Column(name = "calculate_sql")
	private String calculateSql;
	
	@Column(name = "calculate_class")
	private String calculateClass;
	

	public BusBonus() {
	}

	public String getBonusId() {
		return this.bonusId;
	}

	public void setBonusId(String bonusId) {
		this.bonusId = bonusId;
	}

	public String getBonusName() {
		return this.bonusName;
	}

	public void setBonusName(String bonusName) {
		this.bonusName = bonusName;
	}

	public String getSchemeId() {
		return this.schemeId;
	}

	public void setSchemeId(String schemeId) {
		this.schemeId = schemeId;
	}

	public String getBonusRule() {
		return bonusRule;
	}

	public void setBonusRule(String bonusRule) {
		this.bonusRule = bonusRule;
	}

	public String getCalculateSql() {
		return calculateSql;
	}

	public void setCalculateSql(String calculateSql) {
		this.calculateSql = calculateSql;
	}

	public String getCalculateClass() {
		return calculateClass;
	}

	public void setCalculateClass(String calculateClass) {
		this.calculateClass = calculateClass;
	}

}