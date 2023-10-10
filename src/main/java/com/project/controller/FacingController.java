package com.project.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import com.project.controller.process.FacingProcess;
import com.project.controller.services.Handler;
import com.project.controller.services.UserSocket;
import com.project.database.crud.User_CRUD;
import com.project.model.User;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;

public class FacingController {

    /*
     * Components
     */
    @FXML
    public Label lblUsername;
    @FXML
    TextField input;
    @FXML
    TextField addFriend;
    @FXML
    public TextArea txaConversation;
    @FXML
    TableView<User> tableFriends;
    @FXML
    TableColumn<User, String> columnFriends;

    private static FacingController THE_ONE;
    private FacingProcess facingProcess;
    private User_CRUD user_CRUD;
    private String messaging;
    private boolean actived;

    public boolean isActived() {return actived;}

    public FacingController() {
        THE_ONE = this;
        user_CRUD = new User_CRUD();
        
        /* Avoid freezing */
        Thread userSocketThread = new Thread(new UserSocket());
        userSocketThread.start();
    }

    public static FacingController getInstance () {
        if (THE_ONE == null) {
            THE_ONE = new FacingController();
        }
        return THE_ONE;
    }

    public void setMessaging (String messaging) {this.messaging = messaging;}
    public String getMessaging() {return this.messaging;}

    @FXML
    private void addingFriend () {
        String friend = addFriend.getText();
        if (user_CRUD.isExistsFriend(friend) && friend != "" &&
            friend != lblUsername.getText() && !iterateDuplicates(friend)) {

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
                messaging = lblUsername.getText()+": "+input.getText();
                setMessaging(messaging);

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

        clicking();
    }

    private void clicking () {
        tableFriends.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        tableFriends.setOnMouseClicked(e -> {
            if (e.getClickCount() == 2) {
                User selectedItem = tableFriends.getSelectionModel().getSelectedItem();
                System.out.println("Clicked"+selectedItem.getUsername());
            }
        });
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
