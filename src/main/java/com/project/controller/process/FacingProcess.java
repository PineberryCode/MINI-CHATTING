package com.project.controller.process;


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

    public void TableFriendly (TableView<User> tableView) { //Default new User

        TableColumn<User, String> tableColumn = new TableColumn<>();
        tableColumn.setPrefWidth(tableView.getPrefWidth());
        tableColumn.setText("List Friends");

        tableColumn.setCellValueFactory(
            new PropertyValueFactory<User, String>("username")
        );

        tableView.getColumns().add(tableColumn);

    }

    public void TableFriendly (TableView<User> tableView, String addFriend) {

        if (tableView.getItems().isEmpty()) {
            tableView.getColumns().clear();
        }

        TableColumn<User, String> tableColumn = new TableColumn<>();
        tableColumn.setPrefWidth(tableView.getPrefWidth());
        tableColumn.setText("List Friends");
        
        user = new User();
        user.setUsername(addFriend); // <-- Adding for the table
        //user.usernameFriends.add(addFriend); // <-- Adding for the user
        

        tableColumn.setCellValueFactory(
            new PropertyValueFactory<User, String>("username")
        );

        /* Denegate to add the same friend --> */
        if (!data.stream().anyMatch(username -> username.getUsername().equals(user.getUsername()))) {
            data.add(user);
            data.forEach((s) -> System.out.println(s.getUsername()));
            tableView.getColumns().add(tableColumn);
            tableView.setItems(data);
        } else {
            System.out.println("Already exists!");
        }
    }
}
