package com.project.controller;

import com.project.controller.process.FacingProcess;
import com.project.database.crud.User_CRUD;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class FacingController {

    private FacingProcess facingProcess;

    /*
     * Components
     */
    @FXML
    private Label lblUsername;

    @FXML
    private void initialize () {
        facingProcess = new FacingProcess();
        facingProcess.Who(lblUsername);
    }
    
}
