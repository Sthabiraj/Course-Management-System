package cms.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import cms.users.Students;
import cms.users.Users;

public class Database {
    private Connection con;

    DatabaseInfo dbInfo = new DatabaseInfo("jdbc:mysql://localhost:3306/", "root", "");

    public void createDatabase(String databaseName) {
        try {
            dbInfo.setDbName(databaseName);
            con = DriverManager.getConnection(dbInfo.getDbUrl(), dbInfo.getDbUsername(), dbInfo.getDbPassword());
            System.out.println("Connection Established!");
            try (PreparedStatement stmt = con.prepareStatement("CREATE DATABASE " + databaseName)) {
                stmt.execute();
            }
            System.out.println("Database Created Successfully...");
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            closeConnection();
        }
    }

    public void createTable(String tableName) {
        try {
            dbInfo.setDbUrl("jdbc:mysql://localhost:3306/" + dbInfo.getDbName());
            con = DriverManager.getConnection(dbInfo.getDbUrl(), dbInfo.getDbUsername(), dbInfo.getDbPassword());
            System.out.println("Connection Established!");
            try (PreparedStatement stmt = con.prepareStatement(
                    "CREATE TABLE " + tableName
                            + " (id INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY, username VARCHAR(255), email VARCHAR(255), password VARCHAR(255), phone_number VARCHAR(255))")) {
                stmt.execute();
            }
            System.out.println("Table Created Successfully...");
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            closeConnection();
        }
    }

    public void createTable(String tableName, String column) {
        try {
            dbInfo.setDbUrl("jdbc:mysql://localhost:3306/" + dbInfo.getDbName());
            con = DriverManager.getConnection(dbInfo.getDbUrl(), dbInfo.getDbUsername(), dbInfo.getDbPassword());
            System.out.println("Connection Established!");
            try (PreparedStatement stmt = con.prepareStatement(
                    "CREATE TABLE " + tableName
                            + " (id INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY, username VARCHAR(255), email VARCHAR(255), password VARCHAR(255), phone_number VARCHAR(255), "
                            + column + " VARCHAR(255))")) {
                stmt.execute();
            }
            System.out.println("Table Created Successfully...");
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            closeConnection();
        }
    }

    public void addValues(String username, String email, String password, String phoneNumber, String mode) {
        try {
            Users user = new Users(username, email, password, phoneNumber);
            dbInfo.setDbUrl("jdbc:mysql://localhost:3306/" + dbInfo.getDbName());
            con = DriverManager.getConnection(dbInfo.getDbUrl(), dbInfo.getDbUsername(), dbInfo.getDbPassword());
            System.out.println("Connection Established!");
            try (PreparedStatement stmt = con.prepareStatement(
                    "INSERT INTO " + mode + "(username, email, password, phone_number) VALUES(?, ?, ?, ?)")) {
                stmt.setString(1, user.getUsername());
                stmt.setString(2, user.getEmail());
                stmt.setString(3, user.getPassword());
                stmt.setString(4, user.getPhoneNumber());
                stmt.execute();
            }
            System.out.println("Values Inserted Successfully...");
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            closeConnection();
        }
    }

    public void addValues(String username, String email, String password, String phoneNumber, String course,
            String mode) {
        try {
            dbInfo.setDbName(mode);
            dbInfo.setDbUrl("jdbc:mysql://localhost:3306/" + dbInfo.getDbName());
            Students student = new Students(username, email, password, phoneNumber, course);
            con = DriverManager.getConnection(dbInfo.getDbUrl(), dbInfo.getDbUsername(), dbInfo.getDbPassword());
            System.out.println("Connection Established!");
            try (PreparedStatement stmt = con.prepareStatement(
                    "INSERT INTO STUDENT(username, email, password, phone_number, course) VALUES(?, ?, ?, ?, ?)")) {
                stmt.setString(1, student.getUsername());
                stmt.setString(2, student.getEmail());
                stmt.setString(3, student.getPassword());
                stmt.setString(4, student.getPhoneNumber());
                stmt.setString(5, student.getCourse());
                stmt.execute();
            }
            System.out.println("Values Inserted Successfully...");
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            closeConnection();
        }
    }

    private void closeConnection() {
        try {
            if (con != null) {
                con.close();
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    // Method to check if the database exists
    public boolean checkDatabaseExistence(String databaseName) {
        try {
            dbInfo.setDbName(databaseName);
            dbInfo.setDbUrl("jdbc:mysql://localhost:3306/" + dbInfo.getDbName());
            con = DriverManager.getConnection(dbInfo.getDbUrl(), dbInfo.getDbUsername(), dbInfo.getDbPassword());

            boolean databaseExists = false;
            try (PreparedStatement stmt = con
                    .prepareStatement("SELECT SCHEMA_NAME FROM INFORMATION_SCHEMA.SCHEMATA WHERE SCHEMA_NAME = ?")) {
                stmt.setString(1, databaseName);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        databaseExists = true;
                    }
                }
            }

            return databaseExists;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        } finally {
            closeConnection();
        }
    }

    // Method to check if the table exists
    public boolean checkTableExistence(String tableName) {
        try {
            dbInfo.setDbUrl("jdbc:mysql://localhost:3306/" + dbInfo.getDbName());
            con = DriverManager.getConnection(dbInfo.getDbUrl(), dbInfo.getDbUsername(), dbInfo.getDbPassword());

            boolean tableExists = false;
            try (PreparedStatement stmt = con
                    .prepareStatement("SELECT * FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = ?")) {
                stmt.setString(1, tableName);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        tableExists = true;
                    }
                }
            }

            return tableExists;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        } finally {
            closeConnection();
        }
    }
}
