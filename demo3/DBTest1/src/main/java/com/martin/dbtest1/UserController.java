package com.martin.dbtest1;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;
    
    @GetMapping("/{id}")
    public User findById(@PathVariable Long id) {
        
        List<User> userlist=  this.userRepository.findAll();
        return this.userRepository.findOne(id);
    }
    
}
