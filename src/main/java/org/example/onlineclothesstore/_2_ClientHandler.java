package org.example.onlineclothesstore;

import java.io.*;
import java.net.Socket;

public class _2_ClientHandler implements Runnable{
    private Socket clientSocket;
    private static BufferedReader in;
    private static PrintWriter out;
    private DbConnection db = new DbConnection();

    public _2_ClientHandler(Socket socket) {
        try{
            this.clientSocket = socket;
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        }catch(IOException e){
            closeEverything(socket, in, out);
        }
    }


    @Override
    public void run() {
        try {
            boolean statoClient = false;
            do {
                if (clientSocket.isConnected()) {
                    statoClient = true;
                }
            } while (!statoClient);

            while (true) {
                String clientMessage = in.readLine();

                if(clientMessage.equals("PING")){
                    out.println("PONG");
                    out.flush();
                }
                else if (clientMessage.equals("SIGNIN-ACCOUNT")){
                    String nameSignIn  = in.readLine();
                    String surnameSignIn  = in.readLine();
                    String emailSignIn = in.readLine();
                    String passwordSignIn  = in.readLine();
                    db.connectionToDB();
                    String accountFound = db.insertSignInUser(nameSignIn, surnameSignIn, emailSignIn, passwordSignIn);
                    out.println(accountFound);
                    //System.out.println(nameSignIn  +" "+ surnameSignIn +" "+ emailSignIn +" "+ passwordSignIn );
                }
                else if(clientMessage.equals("LOGIN-ACCOUNT")){
                    String emailLog = in.readLine();
                    String passwordLog = in.readLine();
                    db.connectionToDB();
                    String userFound = db.selectLogInUser(emailLog, passwordLog);
                    out.println(userFound);
                }
                else if(clientMessage.equals("CLOTHES")){
                    String category = in.readLine();
                    db.connectionToDB();
                    // IDEA creare un metodo nella classe DbConnection
                    // per eseguire una query di selezione degli articoli desiderati
                    // e farli caricare in un arraylist. Il metodo della query ritorna un arraylist
                    // che poi da qui viene inviato al client che li elabora.

                }

            }
        }catch(IOException e){
            System.out.println("Connessione chiusa con il client: "+clientSocket.getInetAddress());
            closeEverything(clientSocket, in, out);
        }
    }


    private void closeEverything(Socket socket, BufferedReader in, PrintWriter out){
        try{
            if(out != null){
                out.close();
            }
            if(in != null){
                in.close();
            }
            if(socket != null){
                socket.close();
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
