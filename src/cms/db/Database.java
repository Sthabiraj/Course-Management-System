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

/**
 * The Database class represents a database connection and provides methods for
 * creating tables, adding values, checking user existence, and more.
 */
public class Database {
    private Connection con;

    DatabaseInfo dbInfo = new DatabaseInfo("jdbc:mysql://localhost:3306/", "root", "", "cms");

    /**
     * Creates the database.
     */
    public void createDatabase() {
        try {
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

    /**
     * Creates a table in the database.
     * 
     * @param tableName the name of the table to create
     */
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

    /**
     * Creates a table in the database with an additional column.
     * 
     * @param tableName the name of the table to create
     * @param column    the name of the additional column
     */
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

    /**
     * Adds values to the specified table.
     * 
     * @param username the username value
     * @param email    the email value
     * @param password the password value
     * @param mode     the mode value
     */
    public void addValues(String username, String email, String password, String mode) {
        try {
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

    /**
     * Adds values to the specified table with an additional column.
     * 
     * @param username the username value
     * @param email    the email value
     * @param password the password value
     * @param mode     the mode value
     * @param course   the course value
     */
    public void addValues(String username, String email, String password, String mode, String course) {
        try {
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

    /**
     * Checks if a user exists in the specified table.
     * 
     * @param email the email value
     * @param mode  the mode value
     * @return true if the user exists, false otherwise
     */
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

    /**
     * Checks if a user exists in the specified table, excluding the specified ID.
     * 
     * @param id    the ID value to exclude
     * @param email the email value
     * @param mode  the mode value
     * @return true if the user exists, false otherwise
     */
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

    /**
     * Checks if a user can login with the specified email and password in the
     * specified table.
     * 
     * @param email    the email value
     * @param password the password value
     * @param mode     the mode value
     * @return true if the user can login, false otherwise
     */
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

    /**
     * Closes the database connection.
     */
    private void closeConnection() {
        try {
            if (con != null) {
                con.close();
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    /**
     * Checks if the specified database exists.
     * 
     * @param databaseName the name of the database
     * @return true if the database exists, false otherwise
     */
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

    /**
     * Checks if the specified table exists.
     * 
     * @param tableName the name of the table
     * @return true if the table exists, false otherwise
     */
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

    /**
     * Adds an activity to the database.
     * 
     * @param email        the email of the user performing the activity
     * @param mode         the mode of the activity (e.g., "Admin", "Instructor",
     *                     "Student")
     * @param activityName the name of the activity
     */
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
    /**
     * Retrieves activities from the database and populates them into a JTable.
     * 
     * @param tableVar the JTable to populate with activities
     */
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
    /**
     * Retrieves the number of rows in the specified table.
     * 
     * @param tableName the name of the table
     * @return the number of rows in the table, or 0 if an error occurs
     */
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
    /**
     * Adds a new course to the database.
     * 
     * @param courseName the name of the course
     * @param seats      the number of available seats for the course
     * @param duration   the duration of the course in weeks
     */
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
    /**
     * Updates the information of a course in the database.
     * 
     * @param id         the ID of the course to be updated
     * @param courseName the new name of the course
     * @param seats      the new number of seats available for the course
     * @param duration   the new duration of the course
     */
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
    /**
     * Deletes a course from the database based on the provided ID.
     * 
     * @param id the ID of the course to be deleted
     */
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
    /**
     * Checks if a course exists in the database.
     * 
     * @param courseName the name of the course to check
     * @return true if the course exists, false otherwise
     */
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

    /**
     * Checks if a course with the given ID and course name exists in the database.
     * 
     * @param id         the ID of the course to check
     * @param courseName the name of the course to check
     * @return true if a course with the given ID and course name exists, false
     *         otherwise
     */
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
    /**
     * Fetches the courses from the database.
     * 
     * @return a list of Courses objects retrieved from the database
     */
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
    /**
     * Fetches courses from the database based on the given course ID.
     * 
     * @param courseID the ID of the course to fetch
     * @return a list of Courses objects matching the given course ID
     */
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
    /**
     * Retrieves the course ID associated with a given tutor name from the database.
     * 
     * @param tutorName the name of the tutor
     * @return the course ID associated with the tutor name, or 0 if not found or an
     *         error occurs
     */
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
    /**
     * Fetches courses from the database based on the given course name.
     * 
     * @param courseName the name of the course to fetch
     * @return a list of Courses objects matching the given course name
     */
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
    /**
     * Fetches course names from the database and populates them into a JComboBox.
     * 
     * @param comboBox the JComboBox to populate with course names
     */
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
    /**
     * Fetches course IDs from the database and populates them into a JComboBox.
     * 
     * @param comboBox the JComboBox to populate with course IDs
     */
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
    /**
     * Fetches instructor names from the database and populates them into a
     * JComboBox.
     * 
     * @param comboBox the JComboBox to populate with instructor names
     */
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
    /**
     * Adds a module to the database.
     * 
     * @param moduleName The name of the module.
     * @param courseID   The ID of the course to which the module belongs.
     * @param tutorName  The name of the tutor for the module.
     */
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
    /**
     * Updates the information of a module in the database.
     * 
     * @param id         The ID of the module to be updated.
     * @param moduleName The new name of the module.
     * @param courseID   The new course ID associated with the module.
     * @param tutorName  The new tutor name associated with the module.
     */
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
    /**
     * Deletes a module from the database based on the given ID.
     * 
     * @param id the ID of the module to be deleted
     */
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
    /**
     * Fetches modules from the database.
     * 
     * @return a list of Modules fetched from the database
     */
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
    /**
     * Fetches modules from the database based on the given tutor name.
     * 
     * @param tutorName the name of the tutor
     * @return a list of Modules objects retrieved from the database
     */
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
    /**
     * Fetches modules from the database based on the given course ID.
     * 
     * @param courseID the ID of the course
     * @return a list of Modules objects retrieved from the database
     */
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
    /**
     * Retrieves the course name associated with the given email and mode.
     * 
     * @param email the email of the user
     * @param mode  the mode (e.g., "Student", "Instructor")
     * @return the course name associated with the email and mode, or an empty
     *         string if not found
     */
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
    /**
     * Retrieves the course ID for a given course name from the database.
     * 
     * @param courseName the name of the course
     * @return the course ID if found, or 0 if not found or an error occurs
     */
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
    /**
     * Checks if a module exists in the database.
     * 
     * @param moduleName the name of the module to check
     * @return true if the module exists, false otherwise
     */
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

    /**
     * Checks if a module with the given ID and module name exists in the database.
     * 
     * @param id         the ID of the module to be checked
     * @param moduleName the name of the module to be checked
     * @return true if a module with the given ID and module name exists, false
     *         otherwise
     */
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
    /**
     * Fetches the list of instructors from the database.
     * 
     * @return the list of instructors fetched from the database
     */
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
    /**
     * Fetches instructors from the database based on the given course ID.
     * 
     * @param courseID the ID of the course
     * @return a list of Instructors associated with the course
     */
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
    /**
     * Retrieves the names of tutors associated with a given course ID from the
     * database.
     *
     * @param courseID the ID of the course
     * @return an array of tutor names
     */
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
    /**
     * Updates the information of an instructor in the database.
     * 
     * @param id       the ID of the instructor to update
     * @param username the new username of the instructor
     * @param email    the new email of the instructor
     */
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
    /**
     * Deletes an instructor from the database based on the provided ID.
     * 
     * @param id the ID of the instructor to be deleted
     */
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
    /**
     * Fetches the list of students from the database.
     * 
     * @return the list of students fetched from the database
     */
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
    /**
     * Fetches a list of students from the database based on the given course name.
     * 
     * @param courseName the name of the course
     * @return a list of Students objects representing the students in the course
     */
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
    /**
     * Updates the information of a student in the database.
     * 
     * @param id       the ID of the student to be updated
     * @param username the new username of the student
     * @param email    the new email of the student
     * @param course   the new course of the student
     */
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
    /**
     * Deletes a student from the database based on the provided student ID.
     * 
     * @param id the ID of the student to be deleted
     */
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
    /**
     * Retrieves the module IDs associated with a given tutor name and populates
     * them into a JComboBox.
     * 
     * @param tutorName The name of the tutor.
     * @param comboBox  The JComboBox to populate with module IDs.
     */
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
    /**
     * Retrieves the student IDs for a given course name and populates them into a
     * JComboBox.
     * 
     * @param courseName The name of the course.
     * @param comboBox   The JComboBox to populate with student IDs.
     */
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
    /**
     * Retrieves the course ID associated with a given module ID from the database.
     * 
     * @param moduleID the ID of the module
     * @return the course ID associated with the module, or 0 if not found or an
     *         error occurs
     */
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
    /**
     * Retrieves the name of a course based on the given course ID.
     *
     * @param courseID the ID of the course
     * @return the name of the course as a String
     */
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
    /**
     * Adds marks for a specific module and student to the database.
     * 
     * @param moduleID      the ID of the module
     * @param studentID     the ID of the student
     * @param marksObtained the marks obtained by the student
     * @param grade         the grade obtained by the student
     * @param eligibility   the eligibility status of the student
     */
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
    /**
     * Checks if marks exist for a given module ID and student ID.
     * 
     * @param moduleID  the ID of the module
     * @param studentID the ID of the student
     * @return true if marks exist, false otherwise
     */
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
    /**
     * Retrieves the username associated with the given email from the specified
     * mode.
     *
     * @param email the email address to search for
     * @param mode  the mode to search in (e.g., "Admin", "Student", "Instructor")
     * @return the username associated with the email, or an empty string if not
     *         found
     */
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
    /**
     * Retrieves the ID associated with the given email from the specified mode.
     * 
     * @param email The email for which to retrieve the ID.
     * @param mode  The mode (table name) from which to retrieve the ID.
     * @return The ID associated with the given email, or 0 if not found or an error
     *         occurs.
     */
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
    /**
     * Updates the username and email of a record in the specified mode's table
     * based on the given ID.
     *
     * @param id       the ID of the record to update
     * @param username the new username to set
     * @param email    the new email to set
     * @param mode     the mode indicating the table to update (e.g., "Student",
     *                 "Teacher")
     */
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
    /**
     * Updates the password of a user in the specified mode (table) of the database.
     * 
     * @param id       the ID of the user whose password needs to be updated
     * @param password the new password to be set for the user
     * @param mode     the mode (table) in which the user's password needs to be
     *                 updated
     */
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
    /**
     * Fetches the marks from the database for a given student ID.
     * 
     * @param studentID the ID of the student
     * @return a list of Marks objects representing the marks obtained by the
     *         student
     */
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
    /**
     * Retrieves the module name from the database based on the given module ID.
     * 
     * @param moduleID the ID of the module
     * @return the module name as a String
     */
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
    /**
     * Checks if a student with the given student ID exists in the database.
     * 
     * @param studentID the ID of the student to check
     * @return true if the student exists, false otherwise
     */
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
    /**
     * Calculates the average grade for a given student ID based on their obtained
     * marks.
     * 
     * @param studentID the ID of the student
     * @return the average grade as a String
     */
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
    /**
     * Calculates the eligibility of a student based on their obtained marks.
     * 
     * @param studentID the ID of the student
     * @return a string indicating the eligibility status of the student
     */
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
