package com.project.controller.process;

import java.io.IOException;

import com.project.routes.Route;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public abstract class Overview extends Movible{

    public void ShowFXML (Stage stage, String path, int width, int height) throws IOException {
        Parent parent = Route.loadFXML(path);
        Scene scene = new Scene(parent,width,height);
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.setResizable(false);
        moving(scene, stage);
        stage.show();
    }

}
