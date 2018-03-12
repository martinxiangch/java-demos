package com.martin.Sdemo;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;

import com.martin.Bean.SingleEntity;





public class ScheduleDemo {
    
    @Autowired
    private SingleEntity singleEntity;
    
    
    public static final  Logger LOG = LoggerFactory.getLogger(ScheduleDemo.class);
    
 //   @Scheduled(fixedRateString = "${schedule.interval.ms}")
    public void DoTimer() {
        singleEntity.ShowName();
        System.out.println("This is scheduled out!"+ new Date());
        LOG.info("This is scheduled out!"+ new Date());
    }
    
//    @Scheduled(fixedDelay=3000,initialDelay =6000)
    public void DemoTimer2() {
        System.out.println("DemoTimer2: This is scheduled out!"+ new Date());
        LOG.info("DemoTimer2: This is scheduled out!"+ new Date());
    }
}
