package com.project.controller;

import com.project.controller.process.FacingProcess;
import com.project.model.User;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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
    TableView<User> tableFriends;
    @FXML
    TableColumn<User, String> columnFriends;

    @FXML
    private void addingFriend () {
        facingProcess.TableFriendly(tableFriends, addFriend.getText());
        addFriend.setText("");
    }

    @FXML
    private void initialize () {
        facingProcess = new FacingProcess();
        facingProcess.Who(lblUsername);
        facingProcess.TableFriendly(tableFriends);
    }
    
}
