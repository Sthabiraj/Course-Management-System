/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package course.management.system;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author biraj
 */
public class ConnectDB {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/course";
        String username = "root";
        String password = "";
        Connection con;
        Statement st;
        try {
            con = DriverManager.getConnection(url, username, password);
            st = con.createStatement();
            System.out.println("Connection Established!");

            // Check if database exists
            String checkDatabaseQuery = "SELECT SCHEMA_NAME FROM INFORMATION_SCHEMA.SCHEMATA WHERE SCHEMA_NAME = 'course'";
            ResultSet resultSet = st.executeQuery(checkDatabaseQuery);
            if (!resultSet.next()) {
                // Create database
                String createDatabaseQuery = "CREATE DATABASE course";
                st.execute(createDatabaseQuery);

                // Create table
                String createTableQuery = "CREATE TABLE STUDENT(id INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY, username VARCHAR(255), email VARCHAR(255), password VARCHAR(255), mode VARCHAR(255))";
                st.execute(createTableQuery);

                System.out.println("Database and table created!");
            } else {
                System.out.println("Database and table already exist!");
            }

            con.close();
        } catch (SQLException ea) {
            System.out.println(ea);
        }
    }

}
