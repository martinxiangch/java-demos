package com.martin.cms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.martin.cms.model.User;
import com.martin.cms.repository.UserRepository;

import util.MongoUtil;

@Service
public class UserService {
   
    @Autowired
   private UserRepository userRepository;
   
    @Autowired
    private MongoUtil mongoUtil;
    
    private static final String  USER_SEQ="seq_user";
    
    public void Insert(User user) {
        if(user!=null) {
            user.setUid(mongoUtil.getNextSequence(USER_SEQ));
        }
        userRepository.insert(user);
    }
    
    
    public List<User> GetAll() {
        return userRepository.findAll();
    }
}
    

