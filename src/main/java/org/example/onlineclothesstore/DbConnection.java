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

    protected void insertSignInUser(String n, String s, String e, String p) {
        try (PreparedStatement pstmt = conn.prepareStatement("INSERT INTO User(Name, Surname, Email, Password) VALUES (?, ?, ?, ?)")) {

            pstmt.setString(1, n);
            pstmt.setString(2, s);
            pstmt.setString(3, e);
            pstmt.setString(4, p);

            /*executeUpdate() è un metodo per inserire righe nel db
            l'intero che ritorna indica quante righe ha inserito e se è <= 0 significa che c'è stato un problema*/
            int rowsInserted = pstmt.executeUpdate();

            closeConnection();

        } catch (SQLException err) {
            System.err.println("ERRORE INSERIMENTO DATI SUL DB: " + err.getMessage());
        }
    }

    protected String selectLogInUser(String e, String p) {
        try (PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM User WHERE Email = ? AND Password = ?")) {
            pstmt.setString(1, e);
            pstmt.setString(2, p);

            ResultSet rs = pstmt.executeQuery();
            boolean found = rs.next(); // Verifica se c'è almeno una riga

            if (found) {
                closeConnection();
                return "true";
            }

        } catch (SQLException err) {
            System.err.println("ERRORE UTENTE NON TROVATO: " + err.getMessage());
        }

        closeConnection();
        return "false";
    }

    //Metodo per chiudere le risorse aperte per il DB
    private void closeConnection(){
        // Chiudere le risorse
        try {
            stmt.close();
            conn.close();
        } catch (SQLException e) {
        System.err.println("ERRORE CHIUSURA CONNESSIONE CON DB: "+e.getMessage());
        }
    }
}
