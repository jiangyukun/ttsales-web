package cn.ttsales.work.dto;

public class MyBonusDetailDTO {
	private String id;
	private String productName;
	private String bonusName;
	private float money;
	private String createDate;
	
	public MyBonusDetailDTO() {}

	public MyBonusDetailDTO(String id, String productName, String bonusName, float money,
			String createDate) {
		this.id = id;
		this.productName = productName;
		this.bonusName = bonusName;
		this.money = money;
		this.createDate = createDate;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getBonusName() {
		return bonusName;
	}

	public void setBonusName(String bonusName) {
		this.bonusName = bonusName;
	}

	public float getMoney() {
		return money;
	}

	public void setMoney(float money) {
		this.money = money;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
}
