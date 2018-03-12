package com.martin.cms;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.martin.cms.model.TableList;
import com.martin.cms.service.TableListService;

@CrossOrigin
@RestController
@RequestMapping("/admin")
public class CMSRestController {
    
    @Autowired
    private TableListService tableListService;
    
    @RequestMapping(method=RequestMethod.GET,value="/getall",produces="application/json")
    public List<TableList> GetAllTables() {
        return tableListService.GetAll();
    }

    @PostMapping("/insertTable")
    @ResponseBody
    public  String InsertTable(@RequestBody TableList tableList) {
        tableListService.Insert(tableList);
        return "{\"result\":  \"success\"}";
    }
    
    
    @PostMapping("/updateTable")
    @ResponseBody
    public  String UpdateTable(@RequestBody TableList tableList) {
        tableListService.Update(tableList);
        return "{\"result\":  \"success\"}";
    }
    
    @PostMapping("/deleteTable")
    @ResponseBody
    public  String DeleteTable(@RequestBody int tid) {
        tableListService.Delete(tid);
        return "{\"result\":  \"success\"}";
    }
}
