package com.martin.cms.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "TableList")
public class TableList {
    private List<String> columnlist;

    private String tableName;
    
    @Id
    private int tid;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public List<String> getColumnlist() {
        return columnlist;
    }

    public void setColumnlist(List<String> columnlist) {
        this.columnlist = columnlist;
    }

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }
}
