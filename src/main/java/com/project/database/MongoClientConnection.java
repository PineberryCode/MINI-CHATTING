package com.project.database;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.index.Index;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoException;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class MongoClientConnection {
    
    private String connection = "mongodb+srv://MINDLUNNY:090902@cluster0.cvb3g0s.mongodb.net/?retryWrites=true&w=majority";

    public void getConnection() {

        ServerApi serverApi = ServerApi.builder().version(ServerApiVersion.V1).build();
        MongoClientSettings settings = MongoClientSettings.builder()
        .applyConnectionString(new ConnectionString(connection))
        .serverApi(serverApi).build();

        try (MongoClient mongoClient = MongoClients.create(settings)) {
            try {
                MongoDatabase database = mongoClient.getDatabase("BNKERDB");
                MongoCollection<Document> mongoCollection = database.getCollection("user");
                Document query = new Document("_id", new ObjectId("64c0569a488e6f020645c23f"));
                System.out.println(query);
                Document result = mongoCollection.find(query).first();
                if (result != null) {
                    System.out.println(result.getString("e_mail"));
                } else {
                    System.out.println("Not found any users");
                }
                //database.runCommand(new Document("_id", new ObjectId("64c0569a488e6f020645c23f")));
                //System.out.println("Pinged your deployment. You successfully connected to MongoDB!");
            } catch (MongoException e) {
                e.printStackTrace();
            }
        }
    }
}
