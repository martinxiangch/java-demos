package com.martin.cms;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.print.attribute.standard.Severity;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.apache.catalina.security.SecurityUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.apache.shiro.subject.Subject;
import org.glassfish.jersey.internal.Errors.ErrorMessage;
import org.glassfish.jersey.server.ManagedAsync;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Component
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/my")
public class MyController {

    @RequiresRoles("admin")
    @GET
    @Path("/getlist")
    public List<String> getList() {
        Subject subject=SecurityUtils.getSubject();
        return Arrays.asList("aaa","bbb","ccc");
    }
    
    @RequiresPermissions("add")
    @GET
    @Path("/getasync")
    @ManagedAsync
    public void getasynclist(@Suspended final AsyncResponse response) {
        CompletableFuture
        .supplyAsync(() -> Stream.generate(Math::random)
                                .limit(10)
                                .map(item->{return (int)(item*100);})
                                .collect(Collectors.toList()))
        .thenApply(list-> response.resume(list));
        
    }
    
    
    
    @GET
    @Path("/login/{uname}/{pwd}")
    public String login(@PathParam("uname") String userName, @PathParam("pwd") String pwd) {
        String result="succed!";
        Subject subject=SecurityUtils.getSubject();
        UsernamePasswordToken token=new UsernamePasswordToken(userName, pwd);
        token.setRememberMe(true);
        try {
            subject.login(token);
            
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            String sStackTrace = sw.toString();
            
            result="Error:"+ e.getMessage()+";"+sStackTrace;
        }
        
        return result;
        
    }
    
/*   
    @ExceptionHandler(AuthorizationException.class)
    public @ResponseBody AuthorizationException handleException(AuthorizationException e) {
        return e;
    }*/
    
    
    public static void printException(Exception e) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        String sStackTrace = sw.toString();
        String result="Error:"+ e.getMessage()+";"+sStackTrace;
        System.out.println(result);
    }
    
    
}
