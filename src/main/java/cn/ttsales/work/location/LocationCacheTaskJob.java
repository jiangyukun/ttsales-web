package cn.ttsales.work.location;



import java.text.ParseException;

import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class LocationCacheTaskJob {
	protected Logger log = Logger.getLogger(LocationCacheTaskJob.class);
    
    @Scheduled(cron = "0 0/5 * * * ?")  
    public void clearCache() throws ParseException{  
    	LocationCacheManager.recoveryLocation(60*5);
    }
    
    
    
}
