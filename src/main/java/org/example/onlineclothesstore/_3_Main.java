package org.example.onlineclothesstore;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.*;
import java.net.ConnectException;
import java.net.Socket;
import java.util.Objects;

public class _3_Main extends Application {
    private static Socket socket;
    private static BufferedWriter out;
    private static BufferedReader in;

    @Override
    public void start(Stage stage) throws IOException {
        //Tentativo di connessione al server
        try {
            socket = new Socket("localhost", 4444);
        }catch(ConnectException e){
            System.err.println("CONNESSIONE AL SERVER FALLITA: " + e.getMessage());
            System.exit(1);
        }

        //Connessione al server
        try{
            createReaderWriter();

            //Creazione di un thread per continuare a testare la connessione al server
            /*Thread connectionChecker = new Thread(this::checkConnection);
            connectionChecker.setDaemon(true);  //spegne il thread quando il start terimina
            connectionChecker.start();*/

            Parent root = FXMLLoader.load((Objects.requireNonNull(getClass().getResource("Pag1_homePage.fxml"))));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private void createReaderWriter() {
        try {
            out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (Exception e) {
            System.out.println("PROBLEM OF CONNECTION");
        }
    }

    /*private void checkConnection(){
        while(true){
            try {
                sendMessageToServer("PING");
                String responseServer = in.readLine();
                if (responseServer.equals("PONG")) {
                    // far comparire una schermata di connessione persa FXML
                    System.out.println(responseServer);
                }else{
                    break;
                }

            }catch(IOException e){
                System.err.println("ERRORE RISPOSTA SERVER: "+e.getMessage());
                // far comparire una schermata di connessione persa FXML
            }
        }
    }*/

    public synchronized void sendMessageToServer(String... messages){
        for(String message : messages){
            try {
                System.out.println(message);
                out.write(message);
                out.flush();
            } catch (IOException e) {
                System.err.println("ERRORE COMUNICAZIONE CON SERVER: "+e.getMessage());
            }
        }
    }


    public static void main(String[] args) {launch(args);}
}
