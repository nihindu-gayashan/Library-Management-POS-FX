package edu.icet.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import edu.icet.dto.Admin;
import edu.icet.service.ServiceFactory;
import edu.icet.service.custom.AdminService;
import edu.icet.util.ServiceType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

public class AdminSignUpFormController {


    @FXML
    private JFXTextField txtContactNo;

    @FXML
    private JFXTextField txtEmail;

    @FXML
    private JFXTextField txtFirstName;

    @FXML
    private JFXTextField txtLastName;

    @FXML
    private JFXTextField txtPassword;

    @FXML
    private JFXTextField txtUsername;

    @FXML
    private AnchorPane signUpForm;

    private String verifiedotp;

    @FXML
    private JFXTextField txtOtp;

    @FXML
    private JFXButton btnSignUp;

    @FXML
    private JFXButton btnOk;


    @FXML
    void btnOtpOnAction(ActionEvent event) {
        if (Objects.equals(txtOtp.getText(), this.verifiedotp)){
            new Alert(Alert.AlertType.INFORMATION, "Verified").show();
            btnSignUp.setDisable(false);
        }else{
            new Alert(Alert.AlertType.ERROR, "Invalid OTP").show();
        }
    }

    @FXML
    void btnVerifyOnAction(ActionEvent event){
        String email = txtEmail.getText();
        if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            new Alert(Alert.AlertType.ERROR, "Invalid email").show();
        }else {
            String otp = new EmailOtp().sendOTP(txtEmail.getText());
            this.verifiedotp = otp;
            btnOk.setDisable(false);
//        System.out.println(this.verifiedotp);
        }


    }

    @FXML
    void btnSignInOnAction(ActionEvent event)throws IOException {
        URL url = this.getClass().getResource("../../../view/AdminSignInForm.fxml");

        assert url != null;

        Parent load = FXMLLoader.load(url);
        this.signUpForm.getChildren().clear();
        this.signUpForm.getChildren().add(load);

    }

    @FXML
    void btnSignUpOnAction(ActionEvent event) {
        AdminService adminService = ServiceFactory.getInstance().getServiceType(ServiceType.ADMIN);
            String password = txtPassword.getText();
            String encryptedpassword = null;
            try
            {

                MessageDigest m = MessageDigest.getInstance("MD5");
                m.update(password.getBytes());

                byte[] bytes = m.digest();

                StringBuilder s = new StringBuilder();
                for(int i=0; i< bytes.length ;i++)
                {
                    s.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
                }


                encryptedpassword = s.toString();
            }
            catch (NoSuchAlgorithmException e)
            {
                e.printStackTrace();
            }

//            System.out.println("Plain-text password: " + password);
//            System.out.println("Encrypted password using MD5: " + encryptedpassword);

            Admin admin = new Admin(
                    txtFirstName.getText(),
                    txtLastName.getText(),
                    txtContactNo.getText(),
                    txtEmail.getText(),
                    txtUsername.getText(),
                    encryptedpassword
            );
//            System.out.println(admin);

        if (adminService.save(admin)){
            new Alert(Alert.AlertType.INFORMATION, "Successfully Registered").show();
        }else {
            new Alert(Alert.AlertType.ERROR, "Registeration Failed").show();
        }
    }

}
