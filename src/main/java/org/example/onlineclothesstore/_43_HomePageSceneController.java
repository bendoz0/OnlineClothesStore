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

public class _43_HomePageSceneController {
    @FXML
    private Button maglietteD;
    @FXML
    private Button pantaloniD;
    @FXML
    private Button gonneD;
    @FXML
    private Button accessoriD;
    @FXML
    private Button maglietteM;
    @FXML
    private Button pantaloniM;
    @FXML
    private Button felpeM;
    @FXML
    private Button accessoriM;

    private int countVisibleD = 0;
    private int countVisibleM = 0;


    public void optionsMenuDonna(){
        countVisibleD++;
        if(countVisibleD%2 == 1){
            maglietteD.setVisible(true);
            pantaloniD.setVisible(true);
            gonneD.setVisible(true);
            accessoriD.setVisible(true);
        }else{
            maglietteD.setVisible(false);
            pantaloniD.setVisible(false);
            gonneD.setVisible(false);
            accessoriD.setVisible(false);
        }
    }
    public void optionsMenuUomo(){
        countVisibleM++;
        if(countVisibleM%2 == 1){
            maglietteM.setVisible(true);
            pantaloniM.setVisible(true);
            felpeM.setVisible(true);
            accessoriM.setVisible(true);
        }else{
            maglietteM.setVisible(false);
            pantaloniM.setVisible(false);
            felpeM.setVisible(false);
            accessoriM.setVisible(false);
        }
    }

    public void switchToAccountPageScene(javafx.event.ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load((Objects.requireNonNull(getClass().getResource("Pag5_AccountPage.fxml"))));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
