package cn.ttsales.work.dto;

public class SysMemberCountDTO {
	private String memberId;
	private String name;
	private int count;
	private int rank;

	public SysMemberCountDTO() {
		super();
	}

	public SysMemberCountDTO(String memberId, String name, int count, int rank) {
		super();
		this.memberId = memberId;
		this.name = name;
		this.count = count;
		this.rank = rank;
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

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

}
