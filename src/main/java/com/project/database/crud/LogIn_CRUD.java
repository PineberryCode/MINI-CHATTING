package com.project.database.crud;

import com.mongodb.MongoException;
import com.project.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class LogIn_CRUD {
    
    @Autowired
    private MongoTemplate mongoTemplate;
    private User user;

    public void validate (String username, String password) {
        try {
            Query query = new Query();
            user = mongoTemplate.findOne(
                query.addCriteria(Criteria.where("username").is(username)),
                User.class
            );
            if (user != null) {
                BCryptPasswordEncoder bCryptPasswordEncoded = new BCryptPasswordEncoder();
                if (bCryptPasswordEncoded.matches(password, user.getPassword())) {
                    System.out.println(":)");
                }
            }
        } catch (MongoException e) {
            System.out.println(e.getMessage());
        }
    }

}
