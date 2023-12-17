package util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    public static Connection getConnection() {
            try {
                // Load the JDBC driver
                Class.forName("com.mysql.cj.jdbc.Driver"); //JDBC driver class

                // Get connection properties from the property file
                String connectionString = PropertyUtil.getPropertyString();
                
                // Establish the connection
                return DriverManager.getConnection(connectionString);
            } catch(Exception ex){
                System.out.println(ex.getMessage());
                System.out.println("couldn't connect!");
                throw new RuntimeException(ex);
            }
    }
}