package cn.ttsales.work.dto;

public class QyClaimLotteryDTO {
	private String userId;
	private String openId;
	private String deptId;
	private String lotteryType;
	private String schemeId;

	public QyClaimLotteryDTO(String userId, String openId, String deptId,
			String lotteryType, String schemeId) {
		super();
		this.userId = userId;
		this.openId = openId;
		this.deptId = deptId;
		this.lotteryType = lotteryType;
		this.schemeId = schemeId;
	}

	public QyClaimLotteryDTO() {
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getLotteryType() {
		return lotteryType;
	}

	public void setLotteryType(String lotteryType) {
		this.lotteryType = lotteryType;
	}

	public String getSchemeId() {
		return schemeId;
	}

	public void setSchemeId(String schemeId) {
		this.schemeId = schemeId;
	}
}
