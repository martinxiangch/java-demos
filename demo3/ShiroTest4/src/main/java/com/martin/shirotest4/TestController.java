package com.martin.shirotest4;

import java.util.Arrays;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;

@Path("/test4")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TestController {

    @GET
    @Path("/getlist1")
    public List<String> getList1() {
     //   Subject subject=SecurityUtils.getSubject();
        return Arrays.asList("aaa","bbb","ccc");
    }
    
    
    @GET
    @Path("/getlist2")
    public List<String> getList2() {
     //   Subject subject=SecurityUtils.getSubject();
        return Arrays.asList("aaa2","bbb","ccc");
    }
    
    @GET
    @Path("/getlist3")
    public List<String> getList3() {
     //   Subject subject=SecurityUtils.getSubject();
        return Arrays.asList("aaa3","bbb","ccc");
    }
    
    @GET
    @Path("/getlist4")
    public List<String> getList4() {
     //   Subject subject=SecurityUtils.getSubject();
        return Arrays.asList("aaa4","bbb","ccc");
    }
    
    @GET
    @Path("/getlist5")
    public List<String> getList5() {
     //   Subject subject=SecurityUtils.getSubject();
        return Arrays.asList("aaa5","bbb","ccc");
    }
}
