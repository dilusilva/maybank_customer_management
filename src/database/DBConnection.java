/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * DB connection class
 *
 * @author DILUSHA
 */
public class DBConnection {

    private static Connection con = null;
    private static String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static String PASSWORD = "1234";
    private static String USER_NAME = "root";
    private static String URL = "jdbc:mysql://localhost:3306/customer_management";

    private static DBConnection instance;
    private Connection connection;

    private DBConnection() {
        try {
            Class.forName(DRIVER);
            this.connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            String msg = String.format("Error occoured at creating DB connection %s", e.getMessage());
            System.out.println(msg);
        }
    }

    public static DBConnection getInstance() {
        if (instance == null) {
            synchronized (DBConnection.class) {
                if (instance == null) {
                    instance = new DBConnection();
                }
            }
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }

    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            String msg = String.format("Error occoured at closing DB connection %s", e.getMessage());
            System.out.println(msg);
        }
    }

}
