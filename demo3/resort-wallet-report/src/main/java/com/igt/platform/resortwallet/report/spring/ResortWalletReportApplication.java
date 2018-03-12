package com.igt.platform.resortwallet.report.spring;

import org.glassfish.jersey.server.ResourceConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.igt.platform.resortwallet.report.service.ReportService;
import com.igt.platform.resortwallet.report.service.ReportServiceImpl;

@SpringBootApplication
public class ResortWalletReportApplication {

    Logger logger= LoggerFactory.getLogger(ResortWalletReportApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(ResortWalletReportApplication.class, args);
	}
	
	@Bean
    public ResourceConfig resourceConfig() throws Exception {
        ResourceConfig rc = new ResourceConfig();
        rc.register(reportService());
        return rc;
    }
	
	@Bean
	public ReportService reportService() {
	    logger.info("begin to init report service");
	    ReportServiceImpl service=new ReportServiceImpl();
	    
	    return service;
	}
}
