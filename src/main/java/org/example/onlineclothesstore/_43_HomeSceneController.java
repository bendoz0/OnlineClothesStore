package org.example.onlineclothesstore;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Label;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class _43_HomeSceneController {
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
    @FXML
    private Circle iconAccount;
    @FXML
    private Label letterName;
    @FXML
    private Circle iconCart;
    @FXML
    private ImageView imageCart;

    private boolean isVisibleD = false;
    private boolean isVisibleM = false;


    @FXML
    public void initialize() {
        letterName.setText(UserSingleton.getInstance().getUserLetter());

        iconAccount.setOnMouseClicked(event -> {
            try {
                switchToAccountPageScene(event);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        letterName.setOnMouseClicked(event -> {
            try {
                switchToAccountPageScene(event);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        iconCart.setOnMouseClicked(event -> {
            try {
                switchToCartPageScene(event);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        imageCart.setOnMouseClicked(event -> {
            try {
                switchToCartPageScene(event);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public void optionsMenuDonna(){
        isVisibleD = !isVisibleD;
        if(isVisibleD){
            if(isVisibleM){
                isVisibleM = !isVisibleM;
                hiddenM();
            }
            visibleD();
        }else{
            hiddenD();
        }
    }
    public void optionsMenuUomo(){
        isVisibleM = !isVisibleM;
        if(isVisibleM){
            if(isVisibleD){
                isVisibleD = !isVisibleD;
                hiddenD();
            }
            visibleM();
        }else{
            hiddenM();
        }
    }

    private void visibleD(){
        maglietteD.setVisible(true);
        pantaloniD.setVisible(true);
        gonneD.setVisible(true);
        accessoriD.setVisible(true);
    }
    private void hiddenD(){
        maglietteD.setVisible(false);
        pantaloniD.setVisible(false);
        gonneD.setVisible(false);
        accessoriD.setVisible(false);
    }
    private void visibleM(){
        maglietteM.setVisible(true);
        pantaloniM.setVisible(true);
        felpeM.setVisible(true);
        accessoriM.setVisible(true);
    }
    private void hiddenM(){
        maglietteM.setVisible(false);
        pantaloniM.setVisible(false);
        felpeM.setVisible(false);
        accessoriM.setVisible(false);
    }

    @FXML
    private void clothesPage(ActionEvent event) throws IOException {
        //le successive due righe servono per catturare l'evento del bottone e risalire al suo id
        Button source = (Button) event.getSource();
        String id = source.getId();
        //qui chiamare il metodo del main per inviargli il nome della categoria per creare la pagina con quei vestiti
        //il nome della categoria che inviamo è in base all'id del bottone premuto
        _3_Main main = new _3_Main();
        main.sendMessageToServer("CLOTHES", id);
    }


    public void switchToAccountPageScene(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Pag5_AccountPage.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void switchToCartPageScene(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load((Objects.requireNonNull(getClass().getResource("Pag6_CartPage.fxml"))));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
