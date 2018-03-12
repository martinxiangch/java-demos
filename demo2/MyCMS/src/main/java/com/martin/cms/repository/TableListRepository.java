package com.martin.cms.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.martin.cms.model.TableList;

public interface TableListRepository extends MongoRepository<TableList, Integer> {

}
