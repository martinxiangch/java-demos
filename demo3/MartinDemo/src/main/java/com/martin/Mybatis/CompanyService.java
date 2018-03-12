package com.martin.Mybatis;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface CompanyService {
    
    @Select("Select * from company ORDER BY id")
    @Results({
        @Result(property="companyname",column="name", javaType=String.class)
    })
    List<Company> findAll();
    
    @Update("UPDATE company SET name=#{name}, website=#{website} WHERE id=#{id}")
    void update(Company company);
}
