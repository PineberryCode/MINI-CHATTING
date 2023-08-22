package com.project.controller;

import com.project.controller.process.FacingProcess;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FacingController {

    private FacingProcess facingProcess;

    /*
     * Components
     */
    @FXML
    private Label lblUsername;
    @FXML
    TextField input;
    @FXML
    TextField addFriend;
    @FXML
    TextArea txaConversation;
    @FXML
    TableColumn columnFriends;

    @FXML
    private void addingFriend () {
        System.out.println(addFriend.getText().toString());
    }

    @FXML
    private void initialize () {
        facingProcess = new FacingProcess();
        facingProcess.Who(lblUsername);
        columnFriends.setText("List Friends");
    }
    
}
