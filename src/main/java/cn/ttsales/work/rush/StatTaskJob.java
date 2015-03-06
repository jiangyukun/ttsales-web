/**
 * Copyright (c) 2014 RATANSOFT.All rights reserved.
 * @filename RecordReadTask.java
 * @package cn.ttsales.work.web.task
 * @author dandyzheng
 * @date 2014-8-26
 */
package cn.ttsales.work.rush;



import java.text.ParseException;
import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import cn.ttsales.work.core.util.DateUtil;
import cn.ttsales.work.service.sys.TransmitTempService;

/**
 * @author dandyzheng
 *
 */
@Component
public class StatTaskJob {
	protected Logger log = Logger.getLogger(StatTaskJob.class);
 	@Autowired
 	private TransmitTempService transmitTempService;
 	 
     
    @Scheduled(cron = "0 0 4 * * ?")  
    public void statYesterday() throws ParseException{  
    	Date yest = DateUtil.addDate(new Date(), -1);
    	String date = DateUtil.getDateString(yest);
     	transmitTempService.updateTransmitTemp(date);
    }   
    
    @Scheduled(cron = "0 0/10 * * * ?")  
    public void statToday(){  
    	transmitTempService.updateTransmitTemp(DateUtil.getCurrentDateStr()); 
    }  
}
