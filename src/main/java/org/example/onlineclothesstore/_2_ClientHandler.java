package org.example.onlineclothesstore;

import java.io.*;
import java.net.Socket;

public class _2_ClientHandler implements Runnable{
    private Socket clientSocket;
    private static BufferedReader in;
    private static BufferedWriter out;

    public _2_ClientHandler(Socket socket) {
        try{
            this.clientSocket = socket;
            out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        }catch(IOException e){
            closeEverything(socket, in, out);
        }
    }


    @Override
    public void run() {
        System.out.println("punto 1");

        while(true){

            System.out.println("punto 2");

            String clientMessage;
            try {
                System.out.println("punto 3");

                clientMessage = in.readLine();

                System.out.println("punto 4");

                System.out.println(clientMessage);

                System.out.println("punto 5");

                /*if(clientMessage.equals("PING")){
                    System.out.println("sono qui");
                    out.write("PONG\n");
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
                }*/
            } catch (IOException e) {
                System.err.println("ERRORE LETTURA DATI DAL CLIENT: "+e.getMessage());
            }
        }
    }


    private void closeEverything(Socket socket, BufferedReader in, BufferedWriter out){
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
