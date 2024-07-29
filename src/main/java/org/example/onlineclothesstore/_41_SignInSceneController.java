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

public class _41_SignInSceneController {
    @FXML
    private TextField nameInput;
    @FXML
    private TextField surnameInput;
    @FXML
    private Label nameErrorLabel;
    @FXML
    private Label surnameErrorLabel;
    @FXML
    private Button nextBtnS;

    private boolean correctData = false;

    public void initialize() {  //metodo per verificare la validità dei dati
        nameInput.setOnKeyReleased(event -> {   //viene invocato ogni volta che si deseleziona il textField
            String name = nameInput.getText();
            if (name.matches("[a-zA-Z]+")) {
                nameErrorLabel.setText("*");
                correctData = true;
            } else {
                correctData = false;
                nameErrorLabel.setText("Il nome può contenere solo lettere.");
                nextBtnS.setDisable(true); // Disabilita il pulsante "Continua"
            }
        });

        surnameInput.setOnKeyReleased(event -> {
            String surname = surnameInput.getText();
            if (surname.matches("[a-zA-Z]+")) {
                surnameErrorLabel.setText("*");
                correctData = true;
            } else {
                correctData = false;
                surnameErrorLabel.setText("Il cognome può contenere solo lettere.");
                nextBtnS.setDisable(true);
            }

            // Abilita il pulsante "Continua" solo se entrambi i campi sono validi (correctData=true)
            if(correctData) {
                nextBtnS.setDisable(false);
            }
        });
    }

    //metodo per inviare i dati dell'account al server e registrarli nel DB
    public void sendDataAccountToServer(){
        _3_Main main = new _3_Main();
        main.sendMessageToServer("DATA-ACCOUNT");
    }

    public void switchToHomePageScene(javafx.event.ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load((Objects.requireNonNull(getClass().getResource("Pag1_homePage.fxml"))));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
