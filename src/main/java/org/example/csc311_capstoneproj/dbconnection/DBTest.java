package org.example.csc311_capstoneproj.dbconnection;

public class DBTest {
    public static void main(String[] args) {
        DBConnectivityClass db = new DBConnectivityClass();
        boolean result = db.connectToDatabase();

        if (result) {
            System.out.println("✅ Connected successfully — users found.");
        } else {
            System.out.println("✅ Connected — but no users found in the 'users' table.");
        }
    }
}