package com.project.controller.process;

import java.util.ArrayList;
import java.util.List;

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
    private User user;

    public FacingProcess () {
        data = FXCollections.observableArrayList();
    }

    public void Who (Label label) {
        label.setText(User_CRUD.getUsername());
    }

    public void TableFriendly (TableView<User> tableView) {

        TableColumn<User, String> tableColumn = new TableColumn<>();
        tableColumn.setPrefWidth(tableView.getPrefWidth());
        tableColumn.setText("List Friends");
        
        /*final ObservableList<User> data = FXCollections.observableArrayList(
            /*new User("PineberryCode"),
            new User("Da")
        );*/

        tableColumn.setCellValueFactory(
            new PropertyValueFactory<User, String>("username")
        );

        tableView.getColumns().add(tableColumn);
        //tableView.setItems(data);
    }

    public void TableFriendly (TableView<User> tableView, String addFriend) {

        TableColumn<User, String> tableColumn = new TableColumn<>();
        tableColumn.setPrefWidth(tableView.getPrefWidth());
        tableColumn.setText("List Friends");
        
        //final ObservableList<User> data = FXCollections.observableArrayList();
        user = new User();
        user.setUsername(addFriend);
        data.add(user);

        tableColumn.setCellValueFactory(
            new PropertyValueFactory<User, String>("username")
        );

        tableView.getColumns().add(tableColumn);
        tableView.setItems(data);
    }
}
