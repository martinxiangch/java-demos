package com.martin.cms;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.table.TableStringConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.martin.cms.model.TableList;
import com.martin.cms.model.User;
import com.martin.cms.service.TableListService;
import com.martin.cms.service.UserService;

import springfox.documentation.swagger2.annotations.EnableSwagger2;
import util.MongoUtil;

@SpringBootApplication
public class MyCmsApplication implements CommandLineRunner {
    private static final Logger LOG = Logger.getLogger(MyCmsApplication.class.getName());

    public static void main(String[] args) {
        SpringApplication.run(MyCmsApplication.class, args);
    }

    @Autowired
    UserService userService;

    @Autowired
    TableListService tablelistService;

    @Override
    public void run(String... arg0) throws Exception {
        TableList tList = new TableList();
        tList.setTableName("user");
        List<String> list = new ArrayList<String>();
        list.add("name");
        list.add("uid");
        list.add("pass");
        list.add("age");
        tList.setColumnlist(list);
       // tablelistService.Insert(tList);

        List<TableList> tableLists = tablelistService.GetAll();
        for (TableList tableList : tableLists) {
            LOG.log(Level.INFO,
                    tableList.getTid() + "," + String.join(",", tableList.getColumnlist()));
        }
    }

    @Bean
    public MongoUtil getMongoUtil() {
        return new MongoUtil();
    }
}
