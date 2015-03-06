package cn.ttsales.work.domain.common;

import java.io.Serializable;



public abstract class Common implements Serializable{
	private static final long serialVersionUID = 1L;
	
	/*@Column(name="ENABLE_FLAG", nullable=false, length=1)
	private String enableFlag = booleanFlag(true);
	
	@Column(name="LAST_UPDATE_AT", nullable=false)
	@Temporal(TemporalType.TIMESTAMP)  
	private java.util.Date lastUpdateAt;

	public boolean isEnabled(){
		return booleanValue(enableFlag);
	}
	
	public void setEnableFlag(boolean value){
		enableFlag = booleanFlag(value);
	}
	
	public String getEnableFlag(){
		return enableFlag;
	}

	public java.util.Date getLastUpdateAt() {
		return lastUpdateAt;
	} 
	
	public void setLastUpdateAt(Date lastUpdateAt){
		this.lastUpdateAt = lastUpdateAt;
	}

	protected static boolean booleanValue(String flag){
		return "Y".equals(flag);
	}
	protected static String booleanFlag(boolean value){
		return value?"Y":"N";
	}

	@PrePersist 
    protected void prePersist() { 
    	lastUpdateAt = new java.util.Date();
    } 
 
    @PreUpdate 
    protected void preUpdate() { 
    	lastUpdateAt = new java.util.Date();
    }*/
}
