package com.project.controller;

import javafx.stage.Stage;

import java.io.IOException;
import com.project.controller.process.RegisterProcess;

public class RegisterController {

    private RegisterProcess registerProcess;

    public RegisterController(Stage stage) throws IOException{
        registerProcess = new RegisterProcess();
        registerProcess.ShowFXML(stage, "view/Register", 356, 340);
    }

}