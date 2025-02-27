/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Package;

import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author carlo
 */
public class DatabaseConnection {
    public static java.sql.Connection initializeDatabase() 
        throws SQLException, ClassNotFoundException 
    { 
        
        // Initialize all the information regarding 
        // Database Connection 
        String dbDriver = "com.mysql.jdbc.Driver"; 
        //String dbURL = "jdbc:mysql://localhost:3306/";
        String dbURL = "mysql://root:OzigdBviNqDNWcEEkJgoHhZDDicFPoiu@mysql-2494ec8a.railway.internal:3306/railway";
        // Database name to access 
        String dbName = "railway"; 
        String dbUsername = "root"; 
        String dbPassword = "OzigdBviNqDNWcEEkJgoHhZDDicFPoiu";
        
        
   /*
        String dbDriver = "com.mysql.jdbc.Driver"; 
        String dbURL = "jdbc:mysql://localhost:3306/"; 
        String dbName = "vacation"; 
        String dbUsername = "carlos"; 
        String dbPassword = "barral206";
        Class.forName(dbDriver);
        */
        java.sql.Connection con =  DriverManager.getConnection(dbURL+dbName,dbUsername,dbPassword); 
        return con; 
    } 
}
