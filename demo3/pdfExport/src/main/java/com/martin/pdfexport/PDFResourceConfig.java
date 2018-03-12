package com.martin.pdfexport;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;


@Configuration
public class PDFResourceConfig extends ResourceConfig {
    public PDFResourceConfig() {
        register(PDFController.class);
       // packages("com.martin.pdfexport");
    }
}
