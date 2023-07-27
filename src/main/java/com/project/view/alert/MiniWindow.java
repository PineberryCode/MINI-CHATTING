package com.project.view.alert;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public interface MiniWindow {
    
    static void showInformationAlert(String title, String content) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);

        alert.showAndWait();
    }

}
