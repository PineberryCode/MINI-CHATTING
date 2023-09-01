package com.project.controller;

import com.project.controller.process.FacingProcess;
import com.project.database.crud.User_CRUD;
import com.project.model.User;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FacingController {

    private FacingProcess facingProcess;
    private User_CRUD user_CRUD;

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
        boolean exists =  user_CRUD.isExistsFriend(addFriend.getText());
        if (exists) {
            facingProcess.TableFriendly(tableFriends, addFriend.getText());
        } else {
            System.out.println("Not exists");
        }
        addFriend.setText("");
    }

    @FXML
    private void initialize () {
        facingProcess = new FacingProcess();
        user_CRUD = new User_CRUD();

        facingProcess.Who(lblUsername);
        facingProcess.TableFriendly(tableFriends);
    }
    
}
