package com.martin.cms;

import java.util.ArrayList;
import java.util.Collection;


import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/test1",
                produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class StormtrooperController {




    @GetMapping("/getlist")
    @RequiresRoles(logical = Logical.OR, value = {"admin", "officer", "underling"})
    public Collection<String> listTroopers() {
        
        Subject subject= SecurityUtils.getSubject();
        Collection<String> list= new ArrayList<String>();
        if(subject.isAuthenticated())
        {
           
            list.add("aaa");
            list.add("bbb");
            list.add("ccc");
            list.add("ddd");
          //  return list;
        }
        return list;
    }
//
//    @GetMapping(path = "/{id}")
//    @RequiresRoles(logical = Logical.OR, value = {"admin", "officer", "underling"})
//    public Stormtrooper getTrooper(@PathVariable("id") String id) throws NotFoundException {
//
//        Stormtrooper stormtrooper = trooperDao.getStormtrooper(id);
//        if (stormtrooper == null) {
//            throw new NotFoundException(id);
//        }
//        return stormtrooper;
//    }
//
//    @PostMapping()
//    @RequiresRoles(logical = Logical.OR, value = {"admin", "officer"})
//    public Stormtrooper createTrooper(@RequestBody Stormtrooper trooper) {
//
//        return trooperDao.addStormtrooper(trooper);
//    }
//
//    @PostMapping(path = "/{id}")
//    @RequiresRoles("admin")
//    public Stormtrooper updateTrooper(@PathVariable("id") String id, @RequestBody Stormtrooper updatedTrooper) throws NotFoundException {
//
//        return trooperDao.updateStormtrooper(id, updatedTrooper);
//    }
//
//    @DeleteMapping(path = "/{id}")
//    @ResponseStatus(value = HttpStatus.NO_CONTENT)
//    @RequiresRoles("admin")
//    public void deleteTrooper(@PathVariable("id") String id) {
//        trooperDao.deleteStormtrooper(id);
//    }
}
