package org.example.onlineclothesstore;

import java.io.*;
import java.net.Socket;

public class _2_ClientHandler implements Runnable{
    private Socket clientSocket;
    private static BufferedReader in;
    private static PrintWriter out;

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
                else if (clientMessage.equals("DATA-ACCOUNT")){
                    String name = in.readLine();
                    String surname = in.readLine();
                    String email = in.readLine();
                    String password = in.readLine();
                    System.out.println(name +" " +surname+ " " +email+ " " +password);
                }
                else if(clientMessage.equals("QUIT")){
                    closeEverything(clientSocket, in, out);
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
