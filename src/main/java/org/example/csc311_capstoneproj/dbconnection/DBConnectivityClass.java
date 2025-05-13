package org.example.csc311_capstoneproj.dbconnection;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.example.csc311_capstoneproj.models.User;

import java.sql.*;

public class DBConnectivityClass {
    final static String DB_NAME="MovieDiaryDB";
    final static String DB_URL = "" +DB_NAME+ "?useSSL=true";  //Your DB info here
    final static String USERNAME = "";
    final static String PASSWORD = "";

    public boolean connectToDatabase() {
        boolean hasRegistredUsers = false;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            Statement statement = conn.createStatement();
            statement.executeUpdate("CREATE DATABASE IF NOT EXISTS "+DB_NAME+"");
            statement.close();
            conn.close();

            //Second, connect to the database and create the table "users" if cot created
            conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            statement = conn.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS users (" + "id INT( 10 ) NOT NULL PRIMARY KEY AUTO_INCREMENT,"
                    + "username VARCHAR(200) NOT NULL,"
                    + "email VARCHAR(200) NOT NULL UNIQUE,"
                    + "dob DATE)"
                    + "password VARCHAR(200)";
            statement.executeUpdate(sql);

            //check if we have users in the table users
            statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) FROM users");

            if (resultSet.next()) {
                int numUsers = resultSet.getInt(1);
                if (numUsers > 0) {
                    hasRegistredUsers = true;
                }
            }

            statement.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return hasRegistredUsers;
    }
}
