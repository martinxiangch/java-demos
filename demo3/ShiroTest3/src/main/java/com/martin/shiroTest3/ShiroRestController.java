package com.martin.shiroTest3;

import java.util.Arrays;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Component;



@Component
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/test3")
public class ShiroRestController {

    @GET
    @Path("/list")
    @RequiresRoles(logical = Logical.OR, value = {"admin", "officer", "underling"})
    public List<String> getList() {
        Subject subject= SecurityUtils.getSubject();
        System.out.println("isAuthenticated:"+subject.isAuthenticated());
        return Arrays.asList("aaa","bbb","ccc");
    }
    
    @GET
    @Path("/list1")
    @RequiresRoles(logical = Logical.OR, value = {"admin", "officer", "underling"})
    public List<String> getList1() {
        Subject subject= SecurityUtils.getSubject();
        System.out.println("isAuthenticated:"+subject.isAuthenticated());
        return Arrays.asList("aaa1","bbb1","ccc1");
    }
}
