package com.project.controller.process;

import org.springframework.beans.factory.annotation.Autowired;

import com.project.database.crud.User_CRUD;
import com.project.model.User;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class FacingProcess extends Overview {

    public FacingProcess () {}

    public void Who (Label label) {
        label.setText(User_CRUD.getUsername());
    }

    public void TableFriendly (TableView<User> tableView) {

        TableColumn<User, String> tableColumn = new TableColumn<>();
        tableColumn.setPrefWidth(tableView.getPrefWidth());
        tableColumn.setText("List Friends");
        
        final ObservableList<User> data = FXCollections.observableArrayList();

        User user = new User();
        user.setUsername("PineberryCode");

        data.add(user);

        tableColumn.setCellValueFactory(
            new PropertyValueFactory<User, String>("username")
        );

        tableView.getColumns().add(tableColumn);
        tableView.setItems(data);

        System.out.println(user.getUsername());
    }
}
