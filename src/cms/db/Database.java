package cms.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import cms.users.Students;
import cms.users.Users;

public class Database {
    private Connection con;

    DatabaseInfo dbInfo = new DatabaseInfo("jdbc:mysql://localhost:3306/", "root", "", "cms");

    public void createDatabase() {
        try {
            // dbInfo.setDbName(databaseName);
            con = DriverManager.getConnection(dbInfo.getDbUrl(), dbInfo.getDbUsername(), dbInfo.getDbPassword());
            System.out.println("Connection Established!");
            try (PreparedStatement stmt = con.prepareStatement("CREATE DATABASE " + dbInfo.getDbName())) {
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
                            + " (id INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY, username VARCHAR(255), email VARCHAR(255), password VARCHAR(255))")) {
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
                            + " (id INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY, username VARCHAR(255), email VARCHAR(255), password VARCHAR(255), "
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

    public void addValues(String username, String email, String password, String mode) {
        try {
            System.out.println(dbInfo.getDbName());
            Users user = new Users(username, email, password);
            dbInfo.setDbUrl("jdbc:mysql://localhost:3306/" + dbInfo.getDbName());
            con = DriverManager.getConnection(dbInfo.getDbUrl(), dbInfo.getDbUsername(), dbInfo.getDbPassword());
            System.out.println("Connection Established!");
            try (PreparedStatement stmt = con.prepareStatement(
                    "INSERT INTO " + mode.toLowerCase() + "(username, email, password) VALUES(?, ?, ?)")) {
                stmt.setString(1, user.getUsername());
                stmt.setString(2, user.getEmail());
                stmt.setString(3, user.getPassword());
                stmt.execute();
                addActivity(email, mode, "added");
            }
            System.out.println("Values Inserted Successfully...");
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            closeConnection();
        }
    }

    public void addValues(String username, String email, String password, String mode, String course) {
        try {
            System.out.println(dbInfo.getDbName());
            dbInfo.setDbUrl("jdbc:mysql://localhost:3306/" + dbInfo.getDbName());
            Students student = new Students(username, email, password, course);
            con = DriverManager.getConnection(dbInfo.getDbUrl(), dbInfo.getDbUsername(), dbInfo.getDbPassword());
            System.out.println("Connection Established!");
            try (PreparedStatement stmt = con.prepareStatement(
                    "INSERT INTO " + mode.toLowerCase() + "(username, email, password, course) VALUES(?, ?, ?, ?)")) {
                stmt.setString(1, student.getUsername());
                stmt.setString(2, student.getEmail());
                stmt.setString(3, student.getPassword());
                stmt.setString(4, student.getCourse());
                stmt.execute();
                addActivity(email, mode, "added");
            }
            System.out.println("Values Inserted Successfully...");
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            closeConnection();
        }
    }

    // Method for login that takes email, password, and mode as parameters and looks
    // into the database to check if the user exists
    public boolean login(String email, String password, String mode) {
        try {
            dbInfo.setDbUrl("jdbc:mysql://localhost:3306/" + dbInfo.getDbName());
            con = DriverManager.getConnection(dbInfo.getDbUrl(), dbInfo.getDbUsername(), dbInfo.getDbPassword());
            System.out.println("Connection Established!");
            try (PreparedStatement stmt = con.prepareStatement(
                    "SELECT * FROM " + mode.toLowerCase() + " WHERE email = ? AND password = ?")) {
                stmt.setString(1, email);
                stmt.setString(2, password);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        return true;
                    }
                }
            }
            return false;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
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

    // Method to add activities into the DB
    public void addActivity(String email, String mode, String activityName) {
        try {
            dbInfo.setDbUrl("jdbc:mysql://localhost:3306/" + dbInfo.getDbName());
            con = DriverManager.getConnection(dbInfo.getDbUrl(), dbInfo.getDbUsername(), dbInfo.getDbPassword());
            System.out.println("Connection Established!");
            try (PreparedStatement stmt = con.prepareStatement(
                    "INSERT INTO activity (activity_name) VALUES(?)")) {
                stmt.setString(1,
                        mode + ": " + email + " recently " + activityName + ". Time: " + java.time.LocalDateTime.now());
                stmt.execute();
            }
            System.out.println("Values Inserted Successfully...");
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            closeConnection();
        }
    }

    // Method to get activities from the DB
    public void getActivities(JTable tableVar) {
        try {
            dbInfo.setDbUrl("jdbc:mysql://localhost:3306/" + dbInfo.getDbName());
            con = DriverManager.getConnection(dbInfo.getDbUrl(), dbInfo.getDbUsername(), dbInfo.getDbPassword());
            System.out.println("Connection Established!");
            try (PreparedStatement stmt = con.prepareStatement("SELECT * FROM activity")) {
                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        String dbData[] = { String.valueOf(rs.getInt("id")), rs.getString("activity_name") };
                        DefaultTableModel tblModel = (DefaultTableModel) tableVar.getModel();
                        tblModel.addRow(dbData);
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            closeConnection();
        }
    }
}
