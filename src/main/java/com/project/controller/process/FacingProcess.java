package com.project.controller.process;


import com.project.controller.FacingController;
import com.project.database.crud.User_CRUD;
import com.project.model.User;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class FacingProcess extends Overview {
    final ObservableList<User> data;
    private User_CRUD user_CRUD;
    private User user;

    public FacingProcess () {
        data = FXCollections.observableArrayList();
        user_CRUD = new User_CRUD();
    }

    public void Who (Label label) {
        label.setText(User_CRUD.getUsername());
    }

    public void TableFriendly (TableView<User> tableView, String usernameAccount) {
        user_CRUD.listFriends(tableView, usernameAccount);
    }
}
