package com.project.database;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoException;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import com.project.database.config.Parameters;

import lombok.Getter;

public class MongoClientConnection {
    
    private static MongoClientConnection THE_ONE;
    
    @Getter
    private MongoDatabase database;
    @Getter
    private MongoClient mongoClient;

    private MongoClientConnection() {

        ServerApi serverApi = ServerApi.builder().version(ServerApiVersion.V1).build();
        MongoClientSettings settings = MongoClientSettings.builder()
        .applyConnectionString(new ConnectionString(Parameters.connection))
        .serverApi(serverApi).build();

        try {
            mongoClient = MongoClients.create(settings);
            database = this.mongoClient.getDatabase("BNKERDB");
        } catch (MongoException e) {
            e.printStackTrace();
        }
    }

    public static synchronized MongoClientConnection getInstance() {
        if (THE_ONE == null) {
            THE_ONE = new MongoClientConnection();
        }
        return THE_ONE;
    }
}
