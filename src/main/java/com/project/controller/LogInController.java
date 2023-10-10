package com.project.controller;
import com.project.App;
import com.project.controller.process.FacingProcess;
import com.project.controller.process.LogInProcess;
import com.project.controller.process.RegisterProcess;
import com.project.database.crud.User_CRUD;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LogInController {

    private RegisterProcess registerProcess;
    private FacingProcess facingProcess;
    private User_CRUD user_CRUD;
    private LogInProcess logInProcess;

    @FXML
    private TextField tfUser;
    @FXML
    private PasswordField pfPassword;

    private void ValidateCredentials (Stage stage) throws IOException, Exception {
        String username = tfUser.getText();
        String password = pfPassword.getText();

        user_CRUD = new User_CRUD();
        boolean validating = user_CRUD.validate(username, password);
        
        if (!username.isEmpty() || !password.isEmpty()) {
            if (!validating) {
                System.out.println("Incorrect credentials");
            } else {
                facingProcess = new FacingProcess();
                facingProcess.ShowFXML(stage, "view/Facing", 572, 391);
            }
        } else {
            System.out.println("Please, type your credentials.");
        }
    }

    @FXML
    private void btnLogIn () throws IOException, Exception {
        Stage stage = App.primaryStage;
        stage.close();

        this.ValidateCredentials(stage);
    }

    @FXML
    private void linkRegister () throws IOException {
        Stage stage = App.primaryStage;
        stage.close();

        registerProcess = new RegisterProcess();
        registerProcess.ShowFXML(stage, "view/Register", 396, 340);
    }
    //Note: @FXML controller and !@FXML in process dir || (@FXML && !@FXML) in controller dir
}
