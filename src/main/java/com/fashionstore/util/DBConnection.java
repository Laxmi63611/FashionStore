package com.fashionstore.util;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {

    private static String host;
    private static String port;
    private static String dbName;
    private static String username;
    private static String password;
    private static String url;

    static {
        // 1. Try to read from environment variables (used in cloud deployment)
        host = System.getenv("MYSQLHOST");
        port = System.getenv("MYSQLPORT");
        dbName = System.getenv("MYSQLDATABASE");
        username = System.getenv("MYSQLUSER");
        password = System.getenv("MYSQLPASSWORD");

        // 2. If environment variables are not set, try to load from local db.properties file
        if (host == null || port == null || dbName == null || username == null || password == null) {
            try (InputStream input = DBConnection.class.getClassLoader().getResourceAsStream("db.properties")) {
                Properties prop = new Properties();
                if (input != null) {
                    prop.load(input);
                    host = prop.getProperty("db.host", "localhost");
                    port = prop.getProperty("db.port", "3306");
                    dbName = prop.getProperty("db.name", "fashion_store1");
                    username = prop.getProperty("db.user", "root");
                    password = prop.getProperty("db.password", "");
                } else {
                    // Fallbacks if no file is found
                    host = "localhost";
                    port = "3306";
                    dbName = "fashion_store1";
                    username = "root";
                    password = "";
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        url = "jdbc:mysql://" + host + ":" + port + "/" + dbName + "?useSSL=false&serverTimezone=UTC&autoReconnect=true";
    }

    // Private constructor — no instantiation
    private DBConnection() {}

    // Always returns a fresh connection — fixes "connection closed" error
    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, username, password);
            System.out.println("✅ Database Connected Successfully");
            return conn;

        } catch (ClassNotFoundException e) {
            System.out.println("❌ MySQL Driver not found");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("❌ Failed to connect to database. URL: " + url);
            e.printStackTrace();
        }
        return null;
    }
}