/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author General
 */
public class DBHandler {
    protected Connection connection;
    protected String server = "127.0.0.1";
    protected String port = "3306";
    protected String driver = "mysql";
    protected String userName = "root";
    protected String password = "7898520Mohamed%40";
    protected String dbName = "tectactoc";
    public Connection getConnection(){
        final String connectionString = "jdbc:" + driver + "://" + server + ":" + port + "/" + dbName +"";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            connection = DriverManager.getConnection(connectionString, userName, password);
            if (connection != null) {
                System.out.println("Connected to the database!");
            } else {
                System.out.println("Failed to make connection!");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return connection;
    }

}
