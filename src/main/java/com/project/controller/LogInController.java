package com.project.controller;
import com.project.App;
import com.project.controller.process.FacingProcess;
import com.project.controller.process.LogInProcess;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

public class LogInController {

    private RegisterController registerController;
    private LogInProcess logInProcess;
    private FacingProcess facingProcess;

    /*public LogInController (Stage stage) throws IOException {
        logInProcess = new LogInProcess();
        logInProcess.ShowFXML(stage, "view/LogIn", 572, 431);
    }*/

    @FXML
    private TextField tfUser;
    @FXML
    private PasswordField pfPassword;

    @FXML
    private void btnLogIn () throws IOException {
        Stage stage = App.primaryStage;
        stage.close();

        facingProcess = new FacingProcess();
        facingProcess.ShowFXML(stage, "view/Facing", 631, 431);
    }

    @FXML
    private void linkRegister () throws IOException {
        Stage stage = App.primaryStage;
        stage.close();

        registerController = new RegisterController(stage);
    }

}
