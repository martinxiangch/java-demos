package com.igt.platform.resortwallet.report.service;

import java.io.IOException;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.springframework.web.bind.annotation.ResponseBody;

import com.igt.platform.resortwallet.report.model.ReportRequest;


@Consumes("application/json")
@Path("resort-wallet/v1/reports") 
public interface ReportService {
    @GET
    @Path("/useraccess")
    @Produces("application/pdf")
    @ResponseBody
    Response getUserAccessReport(ReportRequest request ) throws IOException;
    
    @GET
    @Path("/useractivity")
    @Produces("application/pdf")
    @ResponseBody
    Response getUserActivityReport(ReportRequest request ) throws IOException;
}
