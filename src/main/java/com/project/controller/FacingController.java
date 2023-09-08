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
import javafx.scene.input.KeyCode;

public class FacingController {

    private FacingProcess facingProcess;
    private User_CRUD user_CRUD;

    public FacingController() {
        user_CRUD = new User_CRUD();
    }

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
        String friend = addFriend.getText();
        if (user_CRUD.isExistsFriend(friend) && friend != "" 
            && friend != lblUsername.getText() && !iterateDuplicates(friend)) {
            user_CRUD.addingANewFriend(lblUsername.getText(), friend);
            tableFriends.getColumns().clear();
            facingProcess.TableFriendly(tableFriends, lblUsername.getText());
        }
        addFriend.setText("");
    }

    @FXML
    private void typing () {
        input.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER) {
                String msg = input.getText();
                txaConversation.appendText(lblUsername.getText()+"(:) "+msg+"\n");
                input.clear();
                e.consume();
            }
        });
    }

    @FXML
    private void initialize () {
        facingProcess = new FacingProcess();

        facingProcess.Who(lblUsername);
        facingProcess.TableFriendly(tableFriends, lblUsername.getText());
    }

    private boolean iterateDuplicates (String newFriend) {
        boolean duplicate = false;

        for (User user : tableFriends.getItems()) {
            if (user.getUsername().matches(newFriend)) {
                duplicate = true;
            }
        }
        return duplicate;
    }
    
}
