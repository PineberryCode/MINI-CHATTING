package com.project.database.crud;

import com.mongodb.MongoException;
import com.project.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;

public class LogIn_CRUD {
    
    @Autowired
    private MongoTemplate mongoTemplate;
    private User user;

    public User validate (String username, String password) {
        try {
            Query query = new Query();
        } catch (MongoException e) {

        }
        return user;
    }

}
