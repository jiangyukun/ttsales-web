package cn.ttsales.work.location;

import java.util.Date;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.apache.log4j.Logger;

import cn.ttsales.work.core.util.DateUtil;

public class LocationCacheManager {
	
	private Logger log = Logger.getLogger(LocationCacheManager.class);
	
	private Queue<Location> recoveryQueue =  new ConcurrentLinkedQueue<Location>();
	private Map<String, Location> newestLocationMap =  new ConcurrentHashMap<String, Location>();
	
	private LocationCacheManager() {
		
	}
	public Location get(String openId) {
		return newestLocationMap.get(openId);
	}
	public void set(String openId,String latitude,String longitude,String precision) {
		log.info("LocationCacheManager.set:openId" + openId +" latitude:" + latitude +" longitude:" + longitude +" precision:" + precision);
		Location oldLocation = this.newestLocationMap.get(openId);
		if (oldLocation!= null) {
			oldLocation.setNewest(false);
		}
		Location newLocation = new Location(openId,latitude,longitude,precision,DateUtil.getCurrentTime()); 
		newLocation.setNewest(true);
		this.newestLocationMap.put(openId, newLocation);
		this.recoveryQueue.offer(newLocation);
	} 
	public void recovery(int second) {
//		log.info("LocationCacheManager.recovery started++++");
//		log.info("newestLocationMap.size:"+this.newestLocationMap.size());
//		log.info("recoveryQueue.size:"+this.recoveryQueue.size());
		Location location;
		int count = 0;
		while((location = this.recoveryQueue.peek())!= null) {
			if (location.getCreateTime().getTime() > DateUtil.getCurrentTime().getTime()-second*1000)
				break;
			if (location.isNewest())
				this.newestLocationMap.remove(location.getOpenId());
			this.recoveryQueue.poll();
			count++;
		}
//		log.info("recoveryed "+count +" objects");
//		log.info("LocationCacheManager.recovery ended++++");
//		log.info("newestLocationMap.size:"+this.newestLocationMap.size());
//		log.info("recoveryQueue.size:"+this.recoveryQueue.size());
	}
	
	
	private static LocationCacheManager mgr;
	private static LocationCacheManager getInstance() {
		if(mgr==null)
			mgr = new LocationCacheManager();
		return mgr;
	}
	public static Location getLocation(String openId) {
		return getInstance().get(openId);
	}
	public static void putLocation(String openId,String latitude,String longitude,String precision) {
		getInstance().set(openId, latitude, longitude, precision); 
	}
	public static void recoveryLocation(int second) {
		getInstance().recovery(second);
	}
	
	public static class Location {
		private String openId;
		private String latitude;
		private String longitude;
		private String precision;
		private Date createTime;
		private boolean isNewest;

		protected Location(String openId,String latitude,String longitude,String precision,Date createTime) {
			this.openId = openId;
			this.latitude=latitude;
			this.longitude =longitude;
			this.precision = precision;
			this.createTime = createTime;
		}
		
		protected boolean isNewest() {
			return isNewest;
		}
		protected void setNewest(boolean isNewest) {
			this.isNewest = isNewest;
		}
		public String getOpenId() {
			return openId;
		}
		protected void setOpenId(String openId) {
			this.openId = openId;
		}
		public String getLatitude() {
			return latitude;
		}
		protected void setLatitude(String latitude) {
			this.latitude = latitude;
		}
		public String getLongitude() {
			return longitude;
		}
		protected void setLongitude(String longitude) {
			this.longitude = longitude;
		}
		public String getPrecision() {
			return precision;
		}
		protected void setPrecision(String precision) {
			this.precision = precision;
		}
		public Date getCreateTime() {
			return createTime;
		}
		protected void setCreateTime(Date createTime) {
			this.createTime = createTime;
		}

	}
	
	public static void main(String[] args) {

	}
}
