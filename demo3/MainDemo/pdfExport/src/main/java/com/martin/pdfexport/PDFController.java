package com.martin.pdfexport;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;

@Component
@Path("/pdf")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PDFController {

    @Autowired
    private ResourceLoader resourceLoader;
//
//    @Autowired
//    public PDFController(ResourceLoader resourceLoader) {
//        this.resourceLoader = resourceLoader;
//    }
    
    // @Produces("application/pdf")
    // @Produces("text/plain")
    @GET
    @Path("/getfile/{id}")
    @Produces("text/plain")
    public String getFile(@PathParam("id") String filename) {

        return String.format("This is a file:%s", filename);
    }

    @GET
    @Path("/getlist")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<String> getList() {
        return Arrays.asList("aaa", "bbb", "ccc");
    }

    @GET
    @Path("/getpdf")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces("application/pdf")
    public Response getMyFile() {
      Resource resource = resourceLoader.getResource("classpath:/static/pdf/javaAction.pdf");
      File file = null;
        try {
            file=resource.getFile();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if (file != null) {
            ResponseBuilder response = Response.ok((Object) file);
            response.header("Content-Disposition", "inline; filename=javaAction.pdf");
            return response.build();
        } else {
                
          return  Response.status(401).build();
        }

    }
    
    @GET
    @Path("/getdemo1")
    @Produces("application/pdf")
    @ResponseBody
    public Response  getdemo1() {
     // Resource resource = resourceLoader.getResource("classpath:/static/pdf/javaAction.pdf");
          ByteArrayInputStream byteArrayInputStream=GeneratePdfReport.WriteUsingiText();
          ResponseBuilder response = Response.ok((Object) byteArrayInputStream);
          response.header("Content-Disposition", "inline; filename=javaAction.pdf");
          return response.build();
    }
    
    
    @GET
    @Path("/getdemo2")
    @Produces("application/pdf")
    @ResponseBody
    public Response  getdemo2() {
     // Resource resource = resourceLoader.getResource("classpath:/static/pdf/javaAction.pdf");
          ByteArrayInputStream byteArrayInputStream=GeneratePdfReport.getPdfmode2();
          ResponseBuilder response = Response.ok((Object) byteArrayInputStream);
          response.header("Content-Disposition", "inline; filename=javaAction.pdf");
          return response.build();
    }
    
    @GET
    @Path("/getdemo3")
    @Produces("application/pdf")
    @ResponseBody
    public Response  getdemo3() {
     // Resource resource = resourceLoader.getResource("classpath:/static/pdf/javaAction.pdf");
          ByteArrayInputStream byteArrayInputStream=GeneratePdfReport.getPdfmode3();
          ResponseBuilder response = Response.ok((Object) byteArrayInputStream);
          response.header("Content-Disposition", "inline; filename=javaAction.pdf");
          return response.build();
    }
    
    @GET
    @Path("/getdemo4")
    @Produces("application/pdf")
    @ResponseBody
    public Response  getdemo4() {
     // Resource resource = resourceLoader.getResource("classpath:/static/pdf/javaAction.pdf");
          ByteArrayInputStream byteArrayInputStream=GeneratePdfReport.getPdfmode4();
          ResponseBuilder response = Response.ok((Object) byteArrayInputStream);
          response.header("Content-Disposition", "inline; filename=javaAction.pdf");
          return response.build();
    }
}
