package org.example.onlineclothesstore;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class _42_LoginSceneController {
    @FXML
    private TextField emailVerify;
    @FXML
    private TextField passVerify;
    @FXML
    private Label emailErrorLabel;
    @FXML
    private Label passwordErrorLabel;
    @FXML
    private Button nextBtnL;

    String email;
    String password;


    /*
    - questo metodo viene chiamato dal bottone "Continue" nella pagina di LogIn
    - questo metodo prende come parametro l'evento di Click del bottone "Continue"
    - il parametro e serve poi per passarlo al metodo switchToHomePageScene, per cambiare scena.
    */
    public void initialize(javafx.event.ActionEvent e){
        nextBtnL.setOnAction(event -> {
            email = emailVerify.getText();
            password = passVerify.getText();

            try {
                sendLogInDataToServer(email, password, e);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
    }

    public void sendLogInDataToServer(String email, String password, javafx.event.ActionEvent e) throws IOException{
        _3_Main main = new _3_Main();
        main.sendMessageToServer("LOGIN-ACCOUNT", email, password);

        String userFound = main.reciveMessagefromServer();

        if(userFound.equals("true")){
            System.out.println(userFound);
            emailErrorLabel.setText("*");
            passwordErrorLabel.setText("*");
            //mettere lo switch che porta alla home page
            switchToHomePageScene(e);
        }else{
            //far comaparire messaggio di email o password sbagliati
            System.out.println(userFound);
            emailErrorLabel.setText("Email e/o Password SBAGLIATI!");
            passwordErrorLabel.setText("Password e/o Email SBAGLIATI!");
        }
    }

    public void switchToHomePageScene(javafx.event.ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load((Objects.requireNonNull(getClass().getResource("Pag4_HomePage.fxml"))));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToWelcomePageScene(javafx.event.ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load((Objects.requireNonNull(getClass().getResource("Pag1_welcomePage.fxml"))));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
