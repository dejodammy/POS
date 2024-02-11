/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author Damilola
 */
public class ConnectionProvider {
    public static Connection getCon() {
        try {
            // The following line is optional in recent JDBC versions, as the driver can be automatically registered.
            // Class.forName("com.mysql.jdbc.Driver");

            String url = "jdbc:mysql://localhost:3306/pos?zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC";
            String user = "root";
            String password = "root";

            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace(); // Handle or log the exception appropriately
            return null;
        }
    }
}
