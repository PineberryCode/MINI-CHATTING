package com.project;

import java.io.IOException;

import org.bson.Document;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoException;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import com.project.controller.LogInController;
import com.project.controller.process.LogInProcess;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class App extends Application {

    public static Stage primaryStage;
    private LogInProcess loginProcess;
    private LogInController logInController;

    public static void main (String[] args) {
        String connection = "mongodb+srv://MINDLUNNY:090902@cluster0.cvb3g0s.mongodb.net/?retryWrites=true&w=majority";

        ServerApi serverApi = ServerApi.builder().version(ServerApiVersion.V1).build();
        MongoClientSettings settings = MongoClientSettings.builder()
        .applyConnectionString(new ConnectionString(connection))
        .serverApi(serverApi).build();

        try (MongoClient mongoClient = MongoClients.create(settings)) {
            try {
                MongoDatabase database = mongoClient.getDatabase("BNKERDB");
                database.runCommand(new Document("ping",1));
                System.out.println("Pinged your deployment. You successfully connected to MongoDB!");
            } catch (MongoException e) {
                e.printStackTrace();
            }
        }

        launch(args);
    }

    @Override
    public void start(Stage arg0) throws IOException {
        this.primaryStage = arg0;
        arg0.initStyle(StageStyle.UNDECORATED);
        arg0.initStyle(StageStyle.TRANSPARENT);

        loginProcess = new LogInProcess();
        loginProcess.ShowFXML(arg0, "view/LogIn", 572, 431);
    }
    
}
