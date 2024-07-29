package org.example.onlineclothesstore;

import java.io.*;
import java.net.*;

public class _1_Server {
    private ServerSocket serverSocket;

    public static void main(String[] args) {
        _1_Server server = new _1_Server();
        server.start();
    }

    public void start() {
        try {
            serverSocket = new ServerSocket(4444);
            System.out.println("Server avviato. In attesa di client...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connesso: " + clientSocket.getInetAddress());
                //Starts a separate thread to handle the connection with the client
                _2_ClientHandler clientHandler = new _2_ClientHandler(clientSocket);
                Thread thread = new Thread(clientHandler);
                thread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
