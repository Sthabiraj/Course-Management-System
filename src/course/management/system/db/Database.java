/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package course.management.system.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author biraj
 */
public class Database {
    Connection con;
    Statement st;

    private String url = "jdbc:mysql://localhost:3306/";
    private String username = "root";
    private String password = "";

    public void createDatabase() {
        try {
            con = DriverManager.getConnection(url, username, password);
            st = con.createStatement();
            System.out.println("Connection Established!");
            st.execute("CREATE DATABASE course");
            System.out.println("Database Created Successfully...");
        } catch (SQLException ea) {
            System.out.println(ea);
        }
    }

    public void createTable() {
        url = "jdbc:mysql://localhost:3306/course";
        try {
            con = DriverManager.getConnection(url, username, password);
            st = con.createStatement();
            System.out.println("Connection Established!");
            st.execute(
                    "CREATE TABLE STUDENT(id INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY, username VARCHAR(255), email VARCHAR(255), password VARCHAR(255), mode VARCHAR(255))");
            System.out.println("Table Created Successfully...");
        } catch (SQLException ea) {
            System.out.println(ea);
        }
    }

    public void addValues(String username, String email, String password, String mode) {
        url = "jdbc:mysql://localhost:3306/course";
        try {
            con = DriverManager.getConnection(url, this.username, this.password);
            st = con.createStatement();
            System.out.println("Connection Established!");
            st.execute("INSERT INTO STUDENT(username, email, password, mode) VALUES('" + username + "','" + email
                    + "','" + password + "','" + mode + "')");
            System.out.println("Values Inserted Successfully...");
        } catch (SQLException ea) {
            System.out.println(ea);
        }
    }
}
