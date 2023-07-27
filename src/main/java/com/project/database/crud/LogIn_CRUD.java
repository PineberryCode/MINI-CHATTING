package com.project.database.crud;

import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.project.database.MongoClientConnection;
import com.project.model.User;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

public class LogIn_CRUD {
    
    @Autowired
    private MongoTemplate mongoTemplate;
    private User user;
    private MongoDatabase database;
    private MongoClient mongoClient;

    public boolean validate (String username, String password) {
        boolean validating = false;
        try {
            database = MongoClientConnection.getInstance().getDatabase();
            mongoClient = MongoClientConnection.getInstance().getMongoClient();
            MongoCollection<Document> collection = database.getCollection("user");
            //Modify then...
            Document query = new Document("_id", new ObjectId("64c0569a488e6f020645c23f"));
            Document result = collection.find(query).first();
            if (result.getString("username").equals(username) 
            && result.getString("password").equals(password)) {
                validating = true;
            } else {
                validating = false;
            }
            /*if (result != null) {
                System.out.println(result.getString("e_mail"));
            } else {
                System.out.println("Not found any users");
            }*/
        } catch (MongoException e) {
            System.out.println(e.getMessage());
        } finally {
            if (this.mongoClient != null) {
                try {
                    this.mongoClient.close();
                } catch (MongoException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        return validating;
    }

}
