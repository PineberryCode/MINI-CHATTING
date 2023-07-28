package com.project.database.crud;

import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.project.controller.process.LogInProcess;
import com.project.database.MongoClientConnection;
import com.project.model.User;

import lombok.Getter;
import lombok.Setter;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

public class User_CRUD {

    private MongoDatabase database;
    private MongoClient mongoClient;
    private LogInProcess logInProcess;

    @Getter
    private static String username;

    public boolean validate (String username, String password) {
        this.username = username;
        boolean validating = false;
        try {
            database = MongoClientConnection.getInstance().getDatabase();
            mongoClient = MongoClientConnection.getInstance().getMongoClient();
            MongoCollection<Document> collection = database.getCollection("user");
            MongoCursor<Document> cursor = collection.find().iterator();
            
            while (cursor.hasNext()) {
                
                Document userDocument = cursor.next();

                logInProcess = new LogInProcess();
                String decrypted = logInProcess.DecryptData(userDocument.getString("password"));

                if (userDocument.getString("username").equals(username)
                    && decrypted.equals(password)) {
                        validating = true;
                        break;
                } else {
                    validating = false;
                }
            }
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

    public User registration (User user) {
        try {
            database = MongoClientConnection.getInstance().getDatabase();
            mongoClient = MongoClientConnection.getInstance().getMongoClient();
            MongoCollection<Document> collection = database.getCollection("user");

            Document userDocument = new Document()
            .append("email", user.getE_mail())
            .append("username", user.getUsername())
            .append("password", user.getPassword());

            collection.insertOne(userDocument);

            ObjectId userId = userDocument.getObjectId("_id");
            user.setId(userId.toHexString());
            System.out.println("Registered");
        } catch (MongoException e) {
            e.printStackTrace();
        } finally {
            if (mongoClient != null) {
                try {
                    mongoClient.close();
                } catch (MongoException e) {
                    e.printStackTrace();
                }
            }
        }
        return user;
    } 
}
