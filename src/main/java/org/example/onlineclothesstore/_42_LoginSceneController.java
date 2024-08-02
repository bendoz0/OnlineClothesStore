package org.example.onlineclothesstore;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class _42_LoginSceneController {

    public TextField emailVerify;
    public TextField passVerify;

    String email;
    String password;

    public void emailInput(){
        email = emailVerify.getText();
    }
    public void passwordInput(){
        password = passVerify.getText();
    }

    public void sendLogInDataToServer(){
        _3_Main main = new _3_Main();
        main.sendMessageToServer("LOGIN-ACCOUNT", email, password);
    }

    public void switchToHomePageScene(javafx.event.ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load((Objects.requireNonNull(getClass().getResource("Pag1_homePage.fxml"))));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
