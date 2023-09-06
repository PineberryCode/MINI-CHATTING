package com.project.database.crud;

import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.UpdateResult;
import com.project.controller.process.LogInProcess;
import com.project.database.MongoClientConnection;
import com.project.model.User;

import lombok.Getter;
import lombok.Setter;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

public class User_CRUD {

    private MongoDatabase database;
    private MongoClient mongoClient;
    private LogInProcess logInProcess;

    @Getter
    @Setter
    private static String username;

    /* The MongoDB connection is Singleton that's It isn't necessary add "finally block"
     * to close the connection.
    */
    /*
     * printStackTrace() -> Desarrollo, Depuración.
     * getMessage() -> Usuarios finales.
     */

    public boolean validate (String username, String password) throws Exception {
        User_CRUD.setUsername(username);
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

                if (userDocument.getString("username").equals(getUsername())
                    && decrypted.equals(password)) {
                        validating = true;
                        break;
                } else {
                    validating = false;
                }
            }
        } catch (MongoException e) {e.printStackTrace();}

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
            .append("password", user.getPassword())
            .append("friends", user.usernameFriends);

            collection.insertOne(userDocument);

            ObjectId userId = userDocument.getObjectId("_id");
            user.setId(userId.toHexString());
            System.out.println("Registered");
        } catch (MongoException e) {e.printStackTrace();}

        return user;
    }

    public boolean isExistsFriend (String username) {
        boolean status = false;
        
        try {
            database = MongoClientConnection.getInstance().getDatabase();
            mongoClient = MongoClientConnection.getInstance().getMongoClient();

            MongoCollection<Document> collection = database.getCollection("user");
            MongoCursor<Document> cursor = collection.find().iterator();

            while (cursor.hasNext()) {
                
                Document userDocument = cursor.next();

                if (userDocument.getString("username").equals(username)) {
                    status = true;
                    System.out.println("Currently exists");
                    break;
                } else {
                    status = false;
                    System.out.println("Not exists this username: "+username);
                }
            }
        } catch (MongoException e) {e.printStackTrace();}

        return status;
    }

    public void addingANewFriend (String Yo, String myFriend) { 
        String out = "";
        try {
            database = MongoClientConnection.getInstance().getDatabase();
            mongoClient = MongoClientConnection.getInstance().getMongoClient();
            MongoCollection<Document> collection = database.getCollection("user");
            
            Bson filterYo = Filters.eq("username", Yo);
            Bson updateYo = Updates.push("friends", myFriend);

            UpdateResult resultYo = collection.updateOne(filterYo,updateYo);
            
            /*
             * The variables is wrote for more understanding.
             * This part of the code is for the account of the username 
             * added as a "friend" to update their friends list.
             */

             Bson filterFriend = Filters.eq("username", myFriend);
             Bson updateFriend = Updates.push("friends", Yo);

            UpdateResult resultFriend = collection.updateOne(filterFriend, updateFriend);

            out = (resultYo.getModifiedCount() > 0 && resultFriend.getModifiedCount() > 0) ?
            Yo + " and "+myFriend + " are friend right now!" :
            myFriend + "not exists :(";
            System.out.println(out);

        } catch (MongoException e) {e.printStackTrace();}
    }
}
