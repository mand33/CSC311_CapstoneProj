package org.example.csc311_capstoneproj.dbconnection;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.example.csc311_capstoneproj.models.User;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

import java.sql.*;

public class DBConnectivityClass {

    private static String DB_URL;
    private static String DB_USERNAME;
    private static String DB_PASSWORD;

    static {
        try {
            Properties props = new Properties();
            InputStream input = DBConnectivityClass.class.getClassLoader().getResourceAsStream("dbconfig.properties");
            props.load(input);

            DB_URL = props.getProperty("db.url");
            DB_USERNAME = props.getProperty("db.username");
            DB_PASSWORD = props.getProperty("db.password");
        } catch (Exception e) {
            System.out.println("❌ Failed to load database properties.");
            e.printStackTrace();
        }
    }

    public boolean connectToDatabase() {
        boolean hasRegistredUsers = false;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            System.out.println("✅ Connection to Azure MySQL SUCCESSFUL");

            Statement statement = conn.createStatement();
            statement.execute("CREATE TABLE IF NOT EXISTS users (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY, " +
                    "username VARCHAR(200) NOT NULL, " +
                    "email VARCHAR(200) NOT NULL UNIQUE, " +
                    "dob DATE, " +
                    "password VARCHAR(200))");



            //check if we have users in the table users
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
            System.out.println("❌ Connection to Azure MySQL FAILED");
            e.printStackTrace();
        }

        return hasRegistredUsers;
    }

}
