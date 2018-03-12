package com.martin.shirotest4;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.apache.shiro.ShiroException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.springframework.stereotype.Component;



@Provider
@Component
public class GeneralExceptionMapper implements ExceptionMapper<ShiroException> {

    @Override
    public Response toResponse(ShiroException ex) {
        printException(ex);
        return Response.status(ex instanceof UnauthenticatedException ? Response.Status.UNAUTHORIZED : Response.Status.FORBIDDEN)
                .entity(ex.getMessage())
                .type(MediaType.TEXT_PLAIN_TYPE)
                .build();
    }
    
    public static void printException(Exception e) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        String sStackTrace = sw.toString();
        String result="Error:"+ e.getMessage()+";"+sStackTrace;
        System.out.println(result);
    }
}