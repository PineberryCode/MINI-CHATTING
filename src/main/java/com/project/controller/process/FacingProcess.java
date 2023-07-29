package com.project.controller.process;

import com.project.database.crud.User_CRUD;

import javafx.scene.control.Label;
import javafx.scene.control.TableView;

public class FacingProcess extends Overview {

    User_CRUD user_CRUD;

    public FacingProcess () {user_CRUD = new User_CRUD();}

    public void Who (Label label) {
        label.setText(user_CRUD.getUsername());
    }

    public void TableFriendly (TableView<String> tableView) {
        
    }
}
