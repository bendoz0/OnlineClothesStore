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
    private static PrintWriter out;
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
            Thread connectionChecker = new Thread(this::checkConnection);
            connectionChecker.setDaemon(true);  //spegne il thread quando il start terimina
            connectionChecker.start();

            Parent root = FXMLLoader.load((Objects.requireNonNull(getClass().getResource("Pag1_welcomePage.fxml"))));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private void createReaderWriter() {
        try {
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (Exception e) {
            System.out.println("PROBLEM OF CONNECTION");
        }
    }

    private void checkConnection(){
        while(true){
            try {
                Thread.sleep(10000);
                sendMessageToServer("PING");
                String responseServer = in.readLine();
                if (responseServer.equals("PONG")) {
                    // far comparire una schermata di connessione persa FXML
                    System.out.println(responseServer);
                }else{
                    break;
                }

            }catch(IOException | InterruptedException e){
                System.err.println("ERRORE RISPOSTA SERVER: "+e.getMessage());
                // far comparire una schermata di connessione persa FXML e far chiudere il client
                // con messaggio di riporvare più tardi
            }
        }
    }

    public synchronized void sendMessageToServer(String... messages){
        for(String message : messages){
            try {
                System.out.println(message);
                out.println(message);
                out.flush();
            } catch (Exception e) {
                System.err.println("ERRORE COMUNICAZIONE CON SERVER(scrittura): "+e.getMessage());
            }
        }
    }

    public synchronized String reciveMessagefromServer(){
        try {
            String found = in.readLine();
            return found;
        } catch (IOException e) {
            System.err.println("ERRORE COMUNICAZIONE CON SERVER(lettura): "+e.getMessage());
        }
        return "false";
    }


    public static void main(String[] args) {launch(args);}
}
