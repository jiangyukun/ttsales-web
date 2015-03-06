package cn.ttsales.work.dto;

public class SysSalerCountDTO {
	private String memberId;
	private String name;
	private int rushSuccessCount;
	private int rushFailCount;
	private int rushAllCount;
	private int receptionCount;
	private String successProb;
 
	public SysSalerCountDTO() {
		super();
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getRushSuccessCount() {
		return rushSuccessCount;
	}

	public void setRushSuccessCount(int rushSuccessCount) {
		this.rushSuccessCount = rushSuccessCount;
	}

	public int getRushFailCount() {
		return rushFailCount;
	}

	public void setRushFailCount(int rushFailCount) {
		this.rushFailCount = rushFailCount;
	}

	public int getReceptionCount() {
		return receptionCount;
	}

	public void setReceptionCount(int receptionCount) {
		this.receptionCount = receptionCount;
	}

	public String getSuccessProb() {
		return successProb;
	}

	public void setSuccessProb(String successProb) {
		this.successProb = successProb;
	}

	public int getRushAllCount() {
		return rushAllCount;
	}

	public void setRushAllCount(int rushAllCount) {
		this.rushAllCount = rushAllCount;
	}

	 
}
