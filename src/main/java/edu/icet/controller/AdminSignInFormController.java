package edu.icet.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;

public class AdminSignInFormController {

    @FXML
    private JFXTextField txtPassword;

    @FXML
    private JFXTextField txtUsername;

    @FXML
    private AnchorPane signInForm;

    @FXML
    void btnSignInOnAction(ActionEvent event) {

    }

    @FXML
    void btnSignUpOnAction(ActionEvent event) throws IOException {
        URL url = this.getClass().getResource("../../../view/AdminSignUpForm.fxml");

        assert url != null;

        Parent load = FXMLLoader.load(url);
        this.signInForm.getChildren().clear();
        this.signInForm.getChildren().add(load);

    }

}
