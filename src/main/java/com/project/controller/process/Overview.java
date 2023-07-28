package com.project.controller.process;

import java.io.IOException;

import com.project.routes.Route;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public abstract class Overview {

    private double xOffset = 0;
    private double yOffset = 0;

    public void ShowFXML (Stage stage, String path, int width, int height) throws IOException {
        Parent parent = Route.loadFXML(path);
        Scene scene = new Scene(parent,width,height);
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.centerOnScreen();
        moving(scene, stage);
        stage.show();
    }

    public void moving (Scene scene, Stage stage) {
        scene.setOnMousePressed((MouseEvent e) -> {
            xOffset = e.getSceneX();
            yOffset = e.getSceneY();
        });
        scene.setOnMouseDragged((MouseEvent e) -> {
            stage.setX(e.getScreenX() - xOffset);
            stage.setY(e.getScreenY() - yOffset);
        }); 
    }

}
