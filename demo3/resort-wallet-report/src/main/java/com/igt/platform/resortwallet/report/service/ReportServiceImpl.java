package com.igt.platform.resortwallet.report.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.springframework.stereotype.Component;

import com.igt.platform.resortwallet.report.model.ReportRequest;
import com.igt.platform.resortwallet.report.util.GeneratePdfReport;


@Component
public class ReportServiceImpl implements ReportService {

    @Override
    public Response getUserAccessReport(ReportRequest request) throws IOException {
        ByteArrayInputStream byteArrayInputStream=GeneratePdfReport.getPdfFileStream();
        ResponseBuilder response = Response.ok((Object) byteArrayInputStream);
        response.header("Content-Disposition", "inline; filename=UserAccessReport.pdf");
        return response.build();
    }

    @Override
    public Response getUserActivityReport(ReportRequest request) throws IOException {
        ByteArrayInputStream byteArrayInputStream=GeneratePdfReport.getPdfFileStream();
        ResponseBuilder response = Response.ok((Object) byteArrayInputStream);
        response.header("Content-Disposition", "inline; filename=UserActivityReport.pdf");
        return response.build();
    }

}
