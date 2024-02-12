package cms.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import cms.courses.Courses;
import cms.courses.Marks;
import cms.courses.Modules;
import cms.users.Instructors;
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
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            closeConnection();
        }
    }

    // Method to check if the user exists
    public boolean checkUserExistence(String email, String mode) {
        try {
            dbInfo.setDbUrl("jdbc:mysql://localhost:3306/" + dbInfo.getDbName());
            con = DriverManager.getConnection(dbInfo.getDbUrl(), dbInfo.getDbUsername(), dbInfo.getDbPassword());
            try (PreparedStatement stmt = con
                    .prepareStatement("SELECT * FROM " + mode.toLowerCase() + " WHERE email = ?")) {
                stmt.setString(1, email);
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

    public boolean checkUserExistence(int id, String email, String mode) {
        try {
            dbInfo.setDbUrl("jdbc:mysql://localhost:3306/" + dbInfo.getDbName());
            con = DriverManager.getConnection(dbInfo.getDbUrl(), dbInfo.getDbUsername(), dbInfo.getDbPassword());

            boolean userExists = false;
            try (PreparedStatement stmt = con
                    .prepareStatement("SELECT * FROM " + mode.toLowerCase() + " WHERE id != ? AND email = ?")) {
                stmt.setInt(1, id);
                stmt.setString(2, email);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        userExists = true;
                    }
                }
            }

            return userExists;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
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
            try (PreparedStatement stmt = con.prepareStatement(
                    "INSERT INTO activity (activity_name) VALUES(?)")) {
                stmt.setString(1,
                        mode + ": " + email + " recently " + activityName + ". Time: " + java.time.LocalDateTime.now());
                stmt.execute();
            }
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

    // Method to get length of any table
    public int getLength(String tableName) {
        try {
            dbInfo.setDbUrl("jdbc:mysql://localhost:3306/" + dbInfo.getDbName());
            con = DriverManager.getConnection(dbInfo.getDbUrl(), dbInfo.getDbUsername(), dbInfo.getDbPassword());
            try (PreparedStatement stmt = con.prepareStatement("SELECT COUNT(*) FROM " + tableName)) {
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        return rs.getInt(1);
                    }
                }
            }
            return 0;
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        } finally {
            closeConnection();
        }
    }

    // Method to add courses into the DB
    public void addCourses(String courseName, int seats, int duration) {
        try {
            dbInfo.setDbUrl("jdbc:mysql://localhost:3306/" + dbInfo.getDbName());
            con = DriverManager.getConnection(dbInfo.getDbUrl(), dbInfo.getDbUsername(), dbInfo.getDbPassword());
            try (PreparedStatement stmt = con.prepareStatement(
                    "INSERT INTO courses (course_name, seats, duration) VALUES(?, ?, ?)")) {
                stmt.setString(1, courseName);
                stmt.setInt(2, seats);
                stmt.setInt(3, duration);
                stmt.execute();
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            closeConnection();
        }
    }

    // Method to update courses in the DB
    public void updateCourses(int id, String courseName, int seats, int duration) {
        try {
            dbInfo.setDbUrl("jdbc:mysql://localhost:3306/" + dbInfo.getDbName());
            con = DriverManager.getConnection(dbInfo.getDbUrl(), dbInfo.getDbUsername(), dbInfo.getDbPassword());
            try (PreparedStatement stmt = con.prepareStatement(
                    "UPDATE courses SET course_name = ?, seats = ?, duration = ? WHERE id = ?")) {
                stmt.setString(1, courseName);
                stmt.setInt(2, seats);
                stmt.setInt(3, duration);
                stmt.setInt(4, id);
                stmt.execute();
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            closeConnection();
        }
    }

    // Method to delete courses from the DB
    public void deleteCourse(int id) {
        try {
            dbInfo.setDbUrl("jdbc:mysql://localhost:3306/" + dbInfo.getDbName());
            con = DriverManager.getConnection(dbInfo.getDbUrl(), dbInfo.getDbUsername(), dbInfo.getDbPassword());
            try (PreparedStatement stmt = con.prepareStatement("DELETE FROM courses WHERE id = ?")) {
                stmt.setInt(1, id);
                stmt.execute();
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            closeConnection();
        }
    }

    // Method to check if the course exists
    public boolean checkCourseExistence(String courseName) {
        try {
            dbInfo.setDbUrl("jdbc:mysql://localhost:3306/" + dbInfo.getDbName());
            con = DriverManager.getConnection(dbInfo.getDbUrl(), dbInfo.getDbUsername(), dbInfo.getDbPassword());

            boolean courseExists = false;
            try (PreparedStatement stmt = con
                    .prepareStatement("SELECT * FROM courses WHERE course_name = ?")) {
                stmt.setString(1, courseName);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        courseExists = true;
                    }
                }
            }

            return courseExists;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        } finally {
            closeConnection();
        }
    }

    public boolean checkCourseExistence(int id, String courseName) {
        try {
            dbInfo.setDbUrl("jdbc:mysql://localhost:3306/" + dbInfo.getDbName());
            con = DriverManager.getConnection(dbInfo.getDbUrl(), dbInfo.getDbUsername(), dbInfo.getDbPassword());

            boolean courseExists = false;
            try (PreparedStatement stmt = con
                    .prepareStatement("SELECT * FROM courses WHERE id != ? AND course_name = ?")) {
                stmt.setInt(1, id);
                stmt.setString(2, courseName);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        courseExists = true;
                    }
                }
            }

            return courseExists;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        } finally {
            closeConnection();
        }
    }

    // Method to fetch courses from the database
    public List<Courses> fetchCoursesFromDatabase() {
        List<Courses> courses = new ArrayList<>();
        try {
            dbInfo.setDbUrl("jdbc:mysql://localhost:3306/" + dbInfo.getDbName());
            con = DriverManager.getConnection(dbInfo.getDbUrl(), dbInfo.getDbUsername(), dbInfo.getDbPassword());
            try (PreparedStatement stmt = con.prepareStatement("SELECT * FROM courses")) {
                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        int id = rs.getInt("id");
                        String name = rs.getString("course_name");
                        int seats = rs.getInt("seats");
                        int duration = rs.getInt("duration");

                        // Create a Course object and add it to the list
                        Courses course = new Courses(id, name, seats, duration);
                        courses.add(course);
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            closeConnection();
        }
        return courses;
    }

    // Method to fetch courses with course id as parameter from the database
    public List<Courses> fetchCoursesFromDatabase(int courseID) {
        List<Courses> courses = new ArrayList<>();
        try {
            dbInfo.setDbUrl("jdbc:mysql://localhost:3306/" + dbInfo.getDbName());
            con = DriverManager.getConnection(dbInfo.getDbUrl(), dbInfo.getDbUsername(), dbInfo.getDbPassword());
            try (PreparedStatement stmt = con.prepareStatement("SELECT * FROM courses WHERE id = ?")) {
                stmt.setInt(1, courseID);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        int id = rs.getInt("id");
                        String name = rs.getString("course_name");
                        int seats = rs.getInt("seats");
                        int duration = rs.getInt("duration");

                        // Create a Course object and add it to the list
                        Courses course = new Courses(id, name, seats, duration);
                        courses.add(course);
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            closeConnection();
        }
        return courses;
    }

    // Method to get course id with tutor name as parameter
    public int getCourseId(String tutorName) {
        try {
            int courseID = 0;
            dbInfo.setDbUrl("jdbc:mysql://localhost:3306/" + dbInfo.getDbName());
            con = DriverManager.getConnection(dbInfo.getDbUrl(), dbInfo.getDbUsername(), dbInfo.getDbPassword());
            try (PreparedStatement stmt = con
                    .prepareStatement("SELECT course_id FROM modules WHERE tutor_name = ?")) {
                stmt.setString(1, tutorName);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        courseID = rs.getInt("course_id");
                    }
                }
            }

            return courseID;
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        } finally {
            closeConnection();
        }
    }

    // Method to fetch courses with course name as parameter from the database
    public List<Courses> fetchCoursesFromDatabase(String courseName) {
        List<Courses> courses = new ArrayList<>();
        try {
            dbInfo.setDbUrl("jdbc:mysql://localhost:3306/" + dbInfo.getDbName());
            con = DriverManager.getConnection(dbInfo.getDbUrl(), dbInfo.getDbUsername(), dbInfo.getDbPassword());
            try (PreparedStatement stmt = con.prepareStatement("SELECT * FROM courses WHERE course_name = ?")) {
                stmt.setString(1, courseName);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        int id = rs.getInt("id");
                        String name = rs.getString("course_name");
                        int seats = rs.getInt("seats");
                        int duration = rs.getInt("duration");

                        // Create a Course object and add it to the list
                        Courses course = new Courses(id, name, seats, duration);
                        courses.add(course);
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            closeConnection();
        }
        return courses;
    }

    // Method to fetch course_name from the database and add it to the combo box
    public void fetchCourseNamesFromDatabase(JComboBox<String> comboBox) {
        try {
            dbInfo.setDbUrl("jdbc:mysql://localhost:3306/" + dbInfo.getDbName());
            con = DriverManager.getConnection(dbInfo.getDbUrl(), dbInfo.getDbUsername(), dbInfo.getDbPassword());
            try (PreparedStatement stmt = con.prepareStatement("SELECT course_name FROM courses")) {
                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        comboBox.addItem(rs.getString("course_name"));
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            closeConnection();
        }
    }

    // Method to fetch course_id from the database and add it to the combo box
    public void fetchCourseIDsFromDatabase(JComboBox<String> comboBox) {
        try {
            dbInfo.setDbUrl("jdbc:mysql://localhost:3306/" + dbInfo.getDbName());
            con = DriverManager.getConnection(dbInfo.getDbUrl(), dbInfo.getDbUsername(), dbInfo.getDbPassword());
            try (PreparedStatement stmt = con.prepareStatement("SELECT id FROM courses")) {
                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        comboBox.addItem(String.valueOf(rs.getInt("id")));
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            closeConnection();
        }
    }

    // Method to fetch username of instructor from the database
    public void fetchInstructorNamesFromDatabase(JComboBox<String> comboBox) {
        try {
            dbInfo.setDbUrl("jdbc:mysql://localhost:3306/" + dbInfo.getDbName());
            con = DriverManager.getConnection(dbInfo.getDbUrl(), dbInfo.getDbUsername(), dbInfo.getDbPassword());
            try (PreparedStatement stmt = con.prepareStatement("SELECT username FROM instructor")) {
                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        comboBox.addItem(rs.getString("username"));
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            closeConnection();
        }
    }

    // Method to add modules into the DB
    public void addModules(String moduleName, int courseID, String tutorName) {
        try {
            dbInfo.setDbUrl("jdbc:mysql://localhost:3306/" + dbInfo.getDbName());
            con = DriverManager.getConnection(dbInfo.getDbUrl(), dbInfo.getDbUsername(), dbInfo.getDbPassword());
            try (PreparedStatement stmt = con.prepareStatement(
                    "INSERT INTO modules (module_name, course_id, tutor_name) VALUES(?, ?, ?)")) {
                stmt.setString(1, moduleName);
                stmt.setInt(2, courseID);
                stmt.setString(3, tutorName);
                stmt.execute();
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            closeConnection();
        }
    }

    // Method to update modules in the DB
    public void updateModules(int id, String moduleName, int courseID, String tutorName) {
        try {
            dbInfo.setDbUrl("jdbc:mysql://localhost:3306/" + dbInfo.getDbName());
            con = DriverManager.getConnection(dbInfo.getDbUrl(), dbInfo.getDbUsername(), dbInfo.getDbPassword());
            try (PreparedStatement stmt = con.prepareStatement(
                    "UPDATE modules SET module_name = ?, course_id = ?, tutor_name = ? WHERE id = ?")) {
                stmt.setString(1, moduleName);
                stmt.setInt(2, courseID);
                stmt.setString(3, tutorName);
                stmt.setInt(4, id);
                stmt.execute();
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            closeConnection();
        }
    }

    // Method to delete modules from the DB
    public void deleteModule(int id) {
        try {
            dbInfo.setDbUrl("jdbc:mysql://localhost:3306/" + dbInfo.getDbName());
            con = DriverManager.getConnection(dbInfo.getDbUrl(), dbInfo.getDbUsername(), dbInfo.getDbPassword());
            try (PreparedStatement stmt = con.prepareStatement("DELETE FROM modules WHERE id = ?")) {
                stmt.setInt(1, id);
                stmt.execute();
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            closeConnection();
        }
    }

    // Method to fetch modules from the database
    public List<Modules> fetchModulesFromDatabase() {
        List<Modules> modules = new ArrayList<>();
        try {
            dbInfo.setDbUrl("jdbc:mysql://localhost:3306/" + dbInfo.getDbName());
            con = DriverManager.getConnection(dbInfo.getDbUrl(), dbInfo.getDbUsername(), dbInfo.getDbPassword());
            try (PreparedStatement stmt = con.prepareStatement("SELECT * FROM modules")) {
                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        int id = rs.getInt("id");
                        String name = rs.getString("module_name");
                        int courseID = rs.getInt("course_id");
                        String tutorName = rs.getString("tutor_name");

                        // Create a Module object and add it to the list
                        Modules module = new Modules(id, name, courseID, tutorName);
                        modules.add(module);
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            closeConnection();
        }
        return modules;
    }

    // Method to fetch modules using tutor name as parameter
    public List<Modules> fetchModulesFromDatabase(String tutorName) {
        List<Modules> modules = new ArrayList<>();
        try {
            dbInfo.setDbUrl("jdbc:mysql://localhost:3306/" + dbInfo.getDbName());
            con = DriverManager.getConnection(dbInfo.getDbUrl(), dbInfo.getDbUsername(), dbInfo.getDbPassword());
            try (PreparedStatement stmt = con.prepareStatement("SELECT * FROM modules WHERE tutor_name = ?")) {
                stmt.setString(1, tutorName);
                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        int id = rs.getInt("id");
                        String name = rs.getString("module_name");
                        int courseID = rs.getInt("course_id");
                        String tutor = rs.getString("tutor_name");

                        // Create a Module object and add it to the list
                        Modules module = new Modules(id, name, courseID, tutor);
                        modules.add(module);
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            closeConnection();
        }
        return modules;
    }

    // Method to fetch modules using course id as parameter
    public List<Modules> fetchModulesFromDatabase(int courseID) {
        List<Modules> modules = new ArrayList<>();
        try {
            dbInfo.setDbUrl("jdbc:mysql://localhost:3306/" + dbInfo.getDbName());
            con = DriverManager.getConnection(dbInfo.getDbUrl(), dbInfo.getDbUsername(), dbInfo.getDbPassword());
            try (PreparedStatement stmt = con.prepareStatement("SELECT * FROM modules WHERE course_id = ?")) {
                stmt.setInt(1, courseID);
                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        int id = rs.getInt("id");
                        String name = rs.getString("module_name");
                        int courseId = rs.getInt("course_id");
                        String tutor = rs.getString("tutor_name");

                        // Create a Module object and add it to the list
                        Modules module = new Modules(id, name, courseId, tutor);
                        modules.add(module);
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            closeConnection();
        }
        return modules;
    }

    // Method to get course name with email and mode as parameters
    public String getCourse(String email, String mode) {
        try {
            String courseName = "";
            dbInfo.setDbUrl("jdbc:mysql://localhost:3306/" + dbInfo.getDbName());
            con = DriverManager.getConnection(dbInfo.getDbUrl(), dbInfo.getDbUsername(), dbInfo.getDbPassword());
            try (PreparedStatement stmt = con.prepareStatement(
                    "SELECT course FROM " + mode.toLowerCase() + " WHERE email = ?")) {
                stmt.setString(1, email);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        courseName = rs.getString("course");
                    }
                }
            }
            return courseName;
        } catch (SQLException e) {
            System.out.println(e);
            return "";
        } finally {
            closeConnection();
        }
    }

    // Method to get course id with course name as parameter
    public int getCourseID(String courseName) {
        try {
            int courseID = 0;
            dbInfo.setDbUrl("jdbc:mysql://localhost:3306/" + dbInfo.getDbName());
            con = DriverManager.getConnection(dbInfo.getDbUrl(), dbInfo.getDbUsername(), dbInfo.getDbPassword());
            try (PreparedStatement stmt = con
                    .prepareStatement("SELECT id FROM courses WHERE course_name = ?")) {
                stmt.setString(1, courseName);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        courseID = rs.getInt("id");
                    }
                }
            }

            return courseID;
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        } finally {
            closeConnection();
        }
    }

    // Method to check if the module already exists
    public boolean checkModuleExistence(String moduleName) {
        try {
            dbInfo.setDbUrl("jdbc:mysql://localhost:3306/" + dbInfo.getDbName());
            con = DriverManager.getConnection(dbInfo.getDbUrl(), dbInfo.getDbUsername(), dbInfo.getDbPassword());

            boolean moduleExists = false;
            try (PreparedStatement stmt = con
                    .prepareStatement("SELECT * FROM modules WHERE module_name = ?")) {
                stmt.setString(1, moduleName);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        moduleExists = true;
                    }
                }
            }

            return moduleExists;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        } finally {
            closeConnection();
        }
    }

    public boolean checkModuleExistence(int id, String moduleName) {
        try {
            dbInfo.setDbUrl("jdbc:mysql://localhost:3306/" + dbInfo.getDbName());
            con = DriverManager.getConnection(dbInfo.getDbUrl(), dbInfo.getDbUsername(), dbInfo.getDbPassword());

            boolean moduleExists = false;
            try (PreparedStatement stmt = con
                    .prepareStatement("SELECT * FROM modules WHERE id != ? AND module_name = ?")) {
                stmt.setInt(1, id);
                stmt.setString(2, moduleName);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        moduleExists = true;
                    }
                }
            }

            return moduleExists;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        } finally {
            closeConnection();
        }
    }

    // Method to fetch instructor data from the database
    public List<Instructors> fetchInstructorsFromDatabase() {
        List<Instructors> instructors = new ArrayList<>();
        try {
            dbInfo.setDbUrl("jdbc:mysql://localhost:3306/" + dbInfo.getDbName());
            con = DriverManager.getConnection(dbInfo.getDbUrl(), dbInfo.getDbUsername(), dbInfo.getDbPassword());
            try (PreparedStatement stmt = con.prepareStatement("SELECT * FROM instructor")) {
                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        int id = rs.getInt("id");
                        String username = rs.getString("username");
                        String email = rs.getString("email");
                        String password = rs.getString("password");

                        // Create an Instructor object and add it to the list
                        Instructors instructor = new Instructors(id, username, email, password);
                        instructors.add(instructor);
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            closeConnection();
        }
        return instructors;
    }

    // Method to fetch instructors with course id as parameter from the database
    public List<Instructors> fetchInstructorsFromDatabase(int courseID) {
        String tutorName[] = getTutorNames(courseID); // Get tutor name using course id
        List<Instructors> instructors = new ArrayList<>();
        for (String tutor : tutorName) {
            System.out.println(tutor);
            try {
                dbInfo.setDbUrl("jdbc:mysql://localhost:3306/" + dbInfo.getDbName());
                con = DriverManager.getConnection(dbInfo.getDbUrl(), dbInfo.getDbUsername(), dbInfo.getDbPassword());
                try (PreparedStatement stmt = con.prepareStatement("SELECT * FROM instructor WHERE username = ?")) {
                    stmt.setString(1, tutor);
                    try (ResultSet rs = stmt.executeQuery()) {
                        while (rs.next()) {
                            int id = rs.getInt("id");
                            String username = rs.getString("username");
                            String email = rs.getString("email");
                            String password = rs.getString("password");

                            // Create an Instructor object and add it to the list
                            Instructors instructor = new Instructors(id, username, email, password);
                            instructors.add(instructor);
                        }
                    }
                }
            } catch (SQLException e) {
                System.out.println(e);
            } finally {
                closeConnection();
            }
        }
        return instructors;
    }

    // Method to get all tutor names with course id as parameter
    public String[] getTutorNames(int courseID) {
        try {
            Set<String> tutorNameSet = new LinkedHashSet<>();
            dbInfo.setDbUrl("jdbc:mysql://localhost:3306/" + dbInfo.getDbName());
            con = DriverManager.getConnection(dbInfo.getDbUrl(), dbInfo.getDbUsername(), dbInfo.getDbPassword());
            try (PreparedStatement stmt = con.prepareStatement("SELECT tutor_name FROM modules WHERE course_id = ?")) {
                stmt.setInt(1, courseID);
                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        tutorNameSet.add(rs.getString("tutor_name"));
                    }
                }
            }
            String[] tutorNames = tutorNameSet.toArray(new String[0]);
            return tutorNames;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            closeConnection();
        }
    }

    // Method to update instructors username and email only in the DB
    public void updateInstructors(int id, String username, String email) {
        try {
            dbInfo.setDbUrl("jdbc:mysql://localhost:3306/" + dbInfo.getDbName());
            con = DriverManager.getConnection(dbInfo.getDbUrl(), dbInfo.getDbUsername(), dbInfo.getDbPassword());
            try (PreparedStatement stmt = con
                    .prepareStatement("UPDATE instructor SET username = ?, email = ? WHERE id = ?")) {
                stmt.setString(1, username);
                stmt.setString(2, email);
                stmt.setInt(3, id);
                stmt.execute();
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            closeConnection();
        }
    }

    // Method to delete instructors from the DB
    public void deleteInstructor(int id) {
        try {
            dbInfo.setDbUrl("jdbc:mysql://localhost:3306/" + dbInfo.getDbName());
            con = DriverManager.getConnection(dbInfo.getDbUrl(), dbInfo.getDbUsername(), dbInfo.getDbPassword());
            try (PreparedStatement stmt = con.prepareStatement("DELETE FROM instructor WHERE id = ?")) {
                stmt.setInt(1, id);
                stmt.execute();
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            closeConnection();
        }
    }

    // Method to fetch students from the database
    public List<Students> fetchStudentsFromDatabase() {
        List<Students> students = new ArrayList<>();
        try {
            dbInfo.setDbUrl("jdbc:mysql://localhost:3306/" + dbInfo.getDbName());
            con = DriverManager.getConnection(dbInfo.getDbUrl(), dbInfo.getDbUsername(), dbInfo.getDbPassword());
            try (PreparedStatement stmt = con.prepareStatement("SELECT * FROM student")) {
                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        int id = rs.getInt("id");
                        String username = rs.getString("username");
                        String email = rs.getString("email");
                        String password = rs.getString("password");
                        String course = rs.getString("course");

                        // Create a Student object and add it to the list
                        Students student = new Students(id, username, email, password, course);
                        students.add(student);
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            closeConnection();
        }
        return students;
    }

    // Method to fetch students from the database with course name as parameter
    public List<Students> fetchStudentsFromDatabase(String courseName) {
        List<Students> students = new ArrayList<>();
        try {
            dbInfo.setDbUrl("jdbc:mysql://localhost:3306/" + dbInfo.getDbName());
            con = DriverManager.getConnection(dbInfo.getDbUrl(), dbInfo.getDbUsername(), dbInfo.getDbPassword());
            try (PreparedStatement stmt = con
                    .prepareStatement("SELECT * FROM student WHERE course = ?")) {
                stmt.setString(1, courseName);
                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        int id = rs.getInt("id");
                        String username = rs.getString("username");
                        String email = rs.getString("email");
                        String password = rs.getString("password");
                        String course = rs.getString("course");

                        // Create a Student object and add it to the list
                        Students student = new Students(id, username, email, password, course);
                        students.add(student);
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            closeConnection();
        }
        return students;
    }

    // Method to update students username, email, and course in the DB
    public void updateStudents(int id, String username, String email, String course) {
        try {
            dbInfo.setDbUrl("jdbc:mysql://localhost:3306/" + dbInfo.getDbName());
            con = DriverManager.getConnection(dbInfo.getDbUrl(), dbInfo.getDbUsername(), dbInfo.getDbPassword());
            try (PreparedStatement stmt = con
                    .prepareStatement("UPDATE student SET username = ?, email = ?, course = ? WHERE id = ?")) {
                stmt.setString(1, username);
                stmt.setString(2, email);
                stmt.setString(3, course);
                stmt.setInt(4, id);
                stmt.execute();
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            closeConnection();
        }
    }

    // Method to delete students from the DB
    public void deleteStudent(int id) {
        try {
            dbInfo.setDbUrl("jdbc:mysql://localhost:3306/" + dbInfo.getDbName());
            con = DriverManager.getConnection(dbInfo.getDbUrl(), dbInfo.getDbUsername(), dbInfo.getDbPassword());
            try (PreparedStatement stmt = con.prepareStatement("DELETE FROM student WHERE id = ?")) {
                stmt.setInt(1, id);
                stmt.execute();
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            closeConnection();
        }
    }

    // Method to get all id of module from the DB with tutor name and combo box as
    // parameter
    public void getModuleID(String tutorName, JComboBox<String> comboBox) {
        try {
            dbInfo.setDbUrl("jdbc:mysql://localhost:3306/" + dbInfo.getDbName());
            con = DriverManager.getConnection(dbInfo.getDbUrl(), dbInfo.getDbUsername(), dbInfo.getDbPassword());

            try (PreparedStatement stmt = con
                    .prepareStatement("SELECT id FROM modules WHERE tutor_name = ?")) {
                stmt.setString(1, tutorName);
                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        comboBox.addItem(String.valueOf(rs.getInt("id")));
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            closeConnection();
        }
    }

    // Method to get all id of student from the DB with course name and combo box as
    // parameter
    public void getStudentID(String courseName, JComboBox<String> comboBox) {
        try {
            dbInfo.setDbUrl("jdbc:mysql://localhost:3306/" + dbInfo.getDbName());
            con = DriverManager.getConnection(dbInfo.getDbUrl(), dbInfo.getDbUsername(), dbInfo.getDbPassword());

            try (PreparedStatement stmt = con
                    .prepareStatement("SELECT id FROM student WHERE course = ?")) {
                stmt.setString(1, courseName);
                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        comboBox.addItem(String.valueOf(rs.getInt("id")));
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            closeConnection();
        }
    }

    // Method to get course id from the DB with course id as parameter
    public int getCourseID(int moduleID) {
        try {
            int courseID = 0;
            dbInfo.setDbUrl("jdbc:mysql://localhost:3306/" + dbInfo.getDbName());
            con = DriverManager.getConnection(dbInfo.getDbUrl(), dbInfo.getDbUsername(), dbInfo.getDbPassword());

            try (PreparedStatement stmt = con
                    .prepareStatement("SELECT course_id FROM modules WHERE id = ?")) {
                stmt.setInt(1, moduleID);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        courseID = rs.getInt("course_id");
                    }
                }
            }

            return courseID;
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        } finally {
            closeConnection();
        }
    }

    // Method to get course name from the DB with course id as parameter
    public String getCourseName(int courseID) {
        try {
            String courseName = "";
            dbInfo.setDbUrl("jdbc:mysql://localhost:3306/" + dbInfo.getDbName());
            con = DriverManager.getConnection(dbInfo.getDbUrl(), dbInfo.getDbUsername(), dbInfo.getDbPassword());

            try (PreparedStatement stmt = con
                    .prepareStatement("SELECT course_name FROM courses WHERE id = ?")) {
                stmt.setInt(1, courseID);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        courseName = rs.getString("course_name");
                    }
                }
            }

            return courseName;
        } catch (SQLException e) {
            System.out.println(e);
            return "";
        } finally {
            closeConnection();
        }
    }

    // Method to add marks into the DB
    public void addMarks(int moduleID, int studentID, Float marksObtained, String grade, String eligibility) {
        try {
            dbInfo.setDbUrl("jdbc:mysql://localhost:3306/" + dbInfo.getDbName());
            con = DriverManager.getConnection(dbInfo.getDbUrl(), dbInfo.getDbUsername(), dbInfo.getDbPassword());
            try (PreparedStatement stmt = con.prepareStatement(
                    "INSERT INTO marks (module_id, student_id, obtained_marks, grade, eligibility) VALUES(?, ?, ?, ?, ?)")) {
                stmt.setInt(1, moduleID);
                stmt.setInt(2, studentID);
                stmt.setFloat(3, marksObtained);
                stmt.setString(4, grade);
                stmt.setString(5, eligibility);
                stmt.execute();
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            closeConnection();
        }
    }

    // Method to check if the marks already exists
    public boolean checkMarksExistence(int moduleID, int studentID) {
        try {
            boolean marksExists = false;
            dbInfo.setDbUrl("jdbc:mysql://localhost:3306/" + dbInfo.getDbName());
            con = DriverManager.getConnection(dbInfo.getDbUrl(), dbInfo.getDbUsername(), dbInfo.getDbPassword());

            try (PreparedStatement stmt = con
                    .prepareStatement("SELECT * FROM marks WHERE module_id = ? AND student_id = ?")) {
                stmt.setInt(1, moduleID);
                stmt.setInt(2, studentID);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        marksExists = true;
                    }
                }
            }

            return marksExists;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        } finally {
            closeConnection();
        }
    }

    // Method to get username and email from the DB
    public String getUsername(String email, String mode) {
        try {
            String username = "";
            dbInfo.setDbUrl("jdbc:mysql://localhost:3306/" + dbInfo.getDbName());
            con = DriverManager.getConnection(dbInfo.getDbUrl(), dbInfo.getDbUsername(), dbInfo.getDbPassword());

            try (PreparedStatement stmt = con
                    .prepareStatement("SELECT username FROM " + mode.toLowerCase() + " WHERE email = ?")) {
                stmt.setString(1, email);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        username = rs.getString("username");
                    }
                }
            }

            return username;
        } catch (SQLException e) {
            System.out.println(e);
            return "";
        } finally {
            closeConnection();
        }
    }

    // Method to get id from the DB
    public int getID(String email, String mode) {
        try {
            int id = 0;
            dbInfo.setDbUrl("jdbc:mysql://localhost:3306/" + dbInfo.getDbName());
            con = DriverManager.getConnection(dbInfo.getDbUrl(), dbInfo.getDbUsername(), dbInfo.getDbPassword());

            try (PreparedStatement stmt = con
                    .prepareStatement("SELECT id FROM " + mode.toLowerCase() + " WHERE email = ?")) {
                stmt.setString(1, email);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        id = rs.getInt("id");
                    }
                }
            }

            return id;
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        } finally {
            closeConnection();
        }
    }

    // Method to update username and email in the DB
    public void updateUsernameAndEmail(int id, String username, String email, String mode) {
        try {
            dbInfo.setDbUrl("jdbc:mysql://localhost:3306/" + dbInfo.getDbName());
            con = DriverManager.getConnection(dbInfo.getDbUrl(), dbInfo.getDbUsername(), dbInfo.getDbPassword());

            try (PreparedStatement stmt = con
                    .prepareStatement("UPDATE " + mode.toLowerCase() + " SET username = ?, email = ? WHERE id = ?")) {
                stmt.setString(1, username);
                stmt.setString(2, email);
                stmt.setInt(3, id);
                stmt.execute();
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            closeConnection();
        }
    }

    // Method to update password in the DB
    public void updatePassword(int id, String password, String mode) {
        try {
            dbInfo.setDbUrl("jdbc:mysql://localhost:3306/" + dbInfo.getDbName());
            con = DriverManager.getConnection(dbInfo.getDbUrl(), dbInfo.getDbUsername(), dbInfo.getDbPassword());

            try (PreparedStatement stmt = con
                    .prepareStatement("UPDATE " + mode.toLowerCase() + " SET password = ? WHERE id = ?")) {
                stmt.setString(1, password);
                stmt.setInt(2, id);
                stmt.execute();
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            closeConnection();
        }
    }

    // Method to fetch marks from the database
    public List<Marks> fetchMarksFromDatabase(int studentID) {
        List<Marks> marks = new ArrayList<>();
        try {
            dbInfo.setDbUrl("jdbc:mysql://localhost:3306/" + dbInfo.getDbName());
            con = DriverManager.getConnection(dbInfo.getDbUrl(), dbInfo.getDbUsername(), dbInfo.getDbPassword());
            try (PreparedStatement stmt = con.prepareStatement("SELECT * FROM marks WHERE student_id = ?")) {
                stmt.setInt(1, studentID);
                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        int moduleID = rs.getInt("module_id");
                        Float markObtained = rs.getFloat("obtained_marks");
                        String grade = rs.getString("grade");
                        // Create a Marks object and add it to the list
                        Marks mark = new Marks(getModuleName(moduleID), markObtained, grade);
                        marks.add(mark);
                    }
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Not graded yet", "Error", JOptionPane.ERROR_MESSAGE);
            System.out.println(e);
        } finally {
            closeConnection();
        }
        return marks;
    }

    // Method that takes module id and as parameter and returns the module name
    public String getModuleName(int moduleID) {
        try {
            String moduleName = "";
            dbInfo.setDbUrl("jdbc:mysql://localhost:3306/" + dbInfo.getDbName());
            con = DriverManager.getConnection(dbInfo.getDbUrl(), dbInfo.getDbUsername(), dbInfo.getDbPassword());

            try (PreparedStatement stmt = con
                    .prepareStatement("SELECT module_name FROM modules WHERE id = ?")) {
                stmt.setInt(1, moduleID);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        moduleName = rs.getString("module_name");
                    }
                }
            }

            return moduleName;
        } catch (SQLException e) {
            System.out.println(e);
            return "";
        } finally {
            closeConnection();
        }
    }

    // Method to check if student id is valid
    public boolean checkStudentID(int studentID) {
        try {
            boolean studentExists = false;
            dbInfo.setDbUrl("jdbc:mysql://localhost:3306/" + dbInfo.getDbName());
            con = DriverManager.getConnection(dbInfo.getDbUrl(), dbInfo.getDbUsername(), dbInfo.getDbPassword());

            try (PreparedStatement stmt = con
                    .prepareStatement("SELECT * FROM student WHERE id = ?")) {
                stmt.setInt(1, studentID);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        studentExists = true;
                    }
                }
            }

            return studentExists;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        } finally {
            closeConnection();
        }
    }

    // Method to calculate the average grade of a student
    public String calculateAverageGrade(int studentID) {
        try {
            String averageGrade = "";
            dbInfo.setDbUrl("jdbc:mysql://localhost:3306/" + dbInfo.getDbName());
            con = DriverManager.getConnection(dbInfo.getDbUrl(), dbInfo.getDbUsername(), dbInfo.getDbPassword());
            int totalModules = 0;
            int totalMarks = 0;
            try (PreparedStatement stmt = con.prepareStatement("SELECT * FROM marks WHERE student_id = ?")) {
                stmt.setInt(1, studentID);
                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        totalModules++;
                        totalMarks += rs.getFloat("obtained_marks");
                    }
                }
            }

            if (totalModules != 0) {
                float averageMarks = totalMarks / totalModules;
                if (averageMarks >= 90) {
                    averageGrade = "A+";
                } else if (averageMarks >= 80) {
                    averageGrade = "A";
                } else if (averageMarks >= 70) {
                    averageGrade = "B";
                } else if (averageMarks >= 60) {
                    averageGrade = "C";
                } else if (averageMarks >= 50) {
                    averageGrade = "D";
                } else {
                    averageGrade = "F";
                }
            } else {
                averageGrade = "N/A";
            }

            return averageGrade;
        } catch (SQLException e) {
            System.out.println(e);
            return "";
        } finally {
            closeConnection();
        }
    }

    // Method to calculate the eligibility of a student
    public String calculateEligibility(int studentID) {
        try {
            String eligibility = "";
            dbInfo.setDbUrl("jdbc:mysql://localhost:3306/" + dbInfo.getDbName());
            con = DriverManager.getConnection(dbInfo.getDbUrl(), dbInfo.getDbUsername(), dbInfo.getDbPassword());
            int totalModules = 0;
            int totalMarks = 0;
            try (PreparedStatement stmt = con.prepareStatement("SELECT * FROM marks WHERE student_id = ?")) {
                stmt.setInt(1, studentID);
                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        totalModules++;
                        totalMarks += rs.getFloat("obtained_marks");
                    }
                }
            }

            if (totalModules != 0) {
                float averageMarks = totalMarks / totalModules;
                if (averageMarks >= 40) {
                    eligibility = "Eligible to go to next semester";
                } else {
                    eligibility = "Not eligible to go to next semester";
                }
            } else {
                eligibility = "N/A";
            }

            return eligibility;
        } catch (SQLException e) {
            System.out.println(e);
            return "";
        } finally {
            closeConnection();
        }
    }

}
