package com.project;

import java.io.IOException;

import com.project.controller.process.LogInProcess;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class App extends Application {

    public static Stage primaryStage;
    private LogInProcess loginProcess;
    //!IMPORTANT
    //Create a file, because when you compile this app... It's create new public and private keys.

    public static void main (String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException, Exception {
        primaryStage = stage;
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initStyle(StageStyle.TRANSPARENT);

        loginProcess = new LogInProcess();
        loginProcess.ShowFXML(stage, "view/LogIn", 572, 431);
        /*String h = loginProcess.DecryptData("f7nFN0E5eETbDwyKsu77Mxly1ME0dnaG8Lb7h8QBze7jZ4t0LHpvDnDP6YPClQntoktb8mbDBcTAmrMvAqJeIwDCuZrW/xpXXQkWyy5H5C8ZlCXww1NJWWWGdZkHHIyZu5bgTbh2wo70Xt1hZR1mY1SLYe4FhZLyZSKJvtVOiJSSxDeNyXHiZpvkj1aZ+AeQScfMeOiEYITPxlme4/zZu0Q5eTDhFaCqErxMl5wXHMI0DfK7fczkLywmizj0X0w7Nom/2I4seY/PC2KKLW/ZTjkKceDJHy/u7hYuqaBWERtLqq6IFCbwyUs6M1bKjeapcBcx1d6yTH6f8drcD3TWmA==");
        System.out.println(h);*/
    }
    
}
