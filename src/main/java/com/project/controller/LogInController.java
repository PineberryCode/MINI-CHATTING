package com.project.controller;
import com.project.App;
import com.project.controller.process.FacingProcess;
import com.project.controller.process.RegisterProcess;
import com.project.database.crud.LogIn_CRUD;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LogInController {

    private RegisterProcess registerProcess;
    private FacingProcess facingProcess;
    private LogIn_CRUD login_CRUD;

    @FXML
    private TextField tfUser;
    @FXML
    private PasswordField pfPassword;

    private void ValidateCredentials (Stage stage) throws IOException {
        String username = tfUser.getText();
        String password = pfPassword.getText();

        login_CRUD = new LogIn_CRUD();
        boolean validating = login_CRUD.validate(username, password);
        if (!validating) {
            System.out.println("Incorrect credentials.");
        } else {
            facingProcess = new FacingProcess();
            facingProcess.ShowFXML(stage, "view/Facing", 631, 431);
        }
    }

    @FXML
    private void btnLogIn () throws IOException {
        Stage stage = App.primaryStage;
        stage.close();

        this.ValidateCredentials(stage);
    }

    @FXML
    private void linkRegister () throws IOException {
        Stage stage = App.primaryStage;
        stage.close();

        registerProcess = new RegisterProcess();
        registerProcess.ShowFXML(stage, "view/Register", 356, 340);
    }

}
