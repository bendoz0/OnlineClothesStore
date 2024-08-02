package org.example.onlineclothesstore;

import javax.swing.plaf.nimbus.State;
import java.sql.*;

public class DbConnection {
    private static Connection conn;
    private static Statement stmt;


    protected void connectionToDB(){
        try{
            //per caricare il Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            //url per creare la connessione
            String url = "jdbc:mysql://localhost:3306/nvo?useSSL=false&serverTimezone=Europe/Rome";

            conn = DriverManager.getConnection(url, "root", "qwer");

            stmt = conn.createStatement();

        }catch (ClassNotFoundException | SQLException e) {
            System.err.println("ERRORE CONNESSIONE AL DATABASE: " + e.getMessage());
        }
    }

    protected void insertSignInUser(String n, String s, String e, String p){
        try {
            // Eseguire una query
        ResultSet rs = stmt.executeQuery("INSERT INTO User(Name, Suername, Email, Password)VALUES(n, s, e, p)");

            // Chiudere le risorse
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException err) {
            System.err.println("ERRORE INSERIMENTO DATI SUL DB: "+err.getMessage());
        }
    }

    protected void selectLogInUser(String e, String p){
        try {
            // Eseguire una query
            ResultSet rs = stmt.executeQuery("SELECT * FROM User WHERE Email = " + e + " AND Password = " + p);
            System.out.println(rs.getString("Email"));

            // Chiudere le risorse
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException err) {
            System.err.println("ERRORE INSERIMENTO DATI SUL DB: "+err.getMessage());
        }
    }


    /*public static void main(String[] args) {
        try {
            // Caricare il driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Creare una connessione
            String url = "jdbc:mysql://localhost:3306/nvo?useSSL=false&serverTimezone=Europe/Rome";

            Connection conn = DriverManager.getConnection(url, "root", "qwer");

            // Creare uno Statement
            Statement stmt = conn.createStatement();

            // Eseguire una query
            ResultSet rs = stmt.executeQuery("SELECT * FROM user");

            // Elaborare i risultati
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("Name");
                String sur = rs.getString("Surname");
                String em = rs.getString("Email");
                String pa = rs.getString("Password");
                System.out.println(id+" "+name+" "+sur+" "+em+" "+pa);
            }

            // Chiudere le risorse
            rs.close();
            stmt.close();
            conn.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }*/


}
