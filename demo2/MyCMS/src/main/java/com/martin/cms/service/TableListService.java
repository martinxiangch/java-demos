package com.martin.cms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.martin.cms.model.TableList;
import com.martin.cms.repository.TableListRepository;

import util.MongoUtil;

@Service
public class TableListService {
    @Autowired
   private TableListRepository tableListRepository;
   
    @Autowired
    private MongoUtil mongoUtil;
    
    private static final String  TableList_SEQ="seq_table";
    
    public void Insert(TableList tableList) {
        if(tableList!=null) {
            tableList.setTid(mongoUtil.getNextSequence(TableList_SEQ));
        }
        tableListRepository.insert(tableList);
    }
    
    
    public void Update(TableList tableList) {
        TableList tl= tableListRepository.findOne(tableList.getTid());
        tl.setColumnlist(tableList.getColumnlist());
        tl.setTableName(tableList.getTableName());
        tableListRepository.save(tl);
    }
    
    public List<TableList> GetAll() {
       return tableListRepository.findAll();
    }
    
    public void Delete(int tid) {
         tableListRepository.delete(tid);
     }
}
