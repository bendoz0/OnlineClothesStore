package org.example.onlineclothesstore;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class _4_WelcomeSceneController {
    public TextField nameInput;
    public TextField surnameInput;
    public TextField emailInput;
    public PasswordField passInput;
    public Button nextBtnS;
    public TextField emailVerify;
    public TextField passVerify;
    public Button nextBtnL;
    public Button backBtn;

    private Stage stage;
    private Scene scene;
    private Parent root;


    public void switchToSigninScene(javafx.event.ActionEvent event) throws IOException {
        root = FXMLLoader.load((Objects.requireNonNull(getClass().getResource("Pag2_Signin.fxml"))));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void switchToLoginScene(javafx.event.ActionEvent event) throws IOException {
        root = FXMLLoader.load((Objects.requireNonNull(getClass().getResource("Pag3_Login.fxml"))));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}

