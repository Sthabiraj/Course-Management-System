/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cms.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import cms.db.Database;
import cms.error.PopupMessage;
import cms.gui.Dashboard;

/**
 *
 * @author biraj
 */
public class Validation {
    PopupMessage popupMessage = new PopupMessage();

    public boolean validateLogin(String email, String password, String mode) {
        // Check if any field is empty
        if (email.equals("") || password.equals("") || mode.equals("Select any one")) {
            popupMessage.showErrorMessage("Input fields cannot be empty!!");
            return false;
        }

        // Define regex patterns
        String emailPattern = "^[A-Za-z0-9+_.-]+@(.+)$"; // Basic email pattern
        String passwordPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$"; // At least 8
                                                                                                      // chars, contains
                                                                                                      // at least one
                                                                                                      // digit, one
                                                                                                      // lower alpha,
                                                                                                      // one upper
                                                                                                      // alpha, one
                                                                                                      // special symbol,
                                                                                                      // no whitespace
        String modePattern = "^[a-zA-Z]{4,}$"; // At least 4 alphabetic characters

        // Create pattern and matcher objects
        Pattern pattern;
        Matcher matcher;

        // Validate email
        pattern = Pattern.compile(emailPattern);
        matcher = pattern.matcher(email);
        if (!matcher.matches()) {
            popupMessage.showErrorMessage("Invalid email");
            return false;
        }

        // Validate password
        pattern = Pattern.compile(passwordPattern);
        matcher = pattern.matcher(password);
        if (!matcher.matches()) {
            popupMessage.showErrorMessage("Invalid password");
            return false;
        }

        // Validate mode
        pattern = Pattern.compile(modePattern);
        matcher = pattern.matcher(mode);
        if (!matcher.matches()) {
            popupMessage.showErrorMessage("Invalid mode");
            return false;
        }

        return true;
    }

    public boolean validateInputs(String username, String email, String password, String mode, String course) {
        // Check if any field is empty
        if (username.equals("") || email.equals("") || password.equals("") || mode.equals("Select any one")
                || course.equals("Select any one")) {
            popupMessage.showErrorMessage("Input fields cannot be empty!!");
            return false;
        }

        // Define regex patterns
        String usernamePattern = "^[A-Za-z\\s]+$"; // Alphabets and spaces only
        String emailPattern = "^[A-Za-z0-9+_.-]+@(.+)$"; // Basic email pattern
        String passwordPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$"; // At least 8
                                                                                                      // chars, contains
                                                                                                      // at least one
                                                                                                      // digit, one
                                                                                                      // lower alpha,
                                                                                                      // one upper
                                                                                                      // alpha, one
                                                                                                      // special symbol,
                                                                                                      // no whitespace
        String modePattern = "^[a-zA-Z]{4,}$"; // At least 4 alphabetic characters

        // Create pattern and matcher objects
        Pattern pattern;
        Matcher matcher;

        // Validate username
        pattern = Pattern.compile(usernamePattern);
        matcher = pattern.matcher(username);
        if (!matcher.matches()) {
            popupMessage.showErrorMessage("Invalid username");
            return false;
        }

        // Validate email
        pattern = Pattern.compile(emailPattern);
        matcher = pattern.matcher(email);
        if (!matcher.matches()) {
            popupMessage.showErrorMessage("Invalid email");
            return false;
        }

        // Validate password
        pattern = Pattern.compile(passwordPattern);
        matcher = pattern.matcher(password);
        if (!matcher.matches()) {
            popupMessage.showErrorMessage("Invalid password");
            return false;
        }

        // Validate mode
        pattern = Pattern.compile(modePattern);
        matcher = pattern.matcher(mode);
        if (!matcher.matches()) {
            popupMessage.showErrorMessage("Invalid mode");
            return false;
        }
        return true;
    }

    public boolean validateInputs(String username, String email, String password, String mode) {
        // Check if any field is empty
        if (username.equals("") || email.equals("") || password.equals("") || mode.equals("Select any one")) {
            popupMessage.showErrorMessage("Input fields cannot be empty!!");
            return false;
        }

        // Define regex patterns
        String usernamePattern = "^[A-Za-z\\s]+$"; // Alphabets and spaces only
        String emailPattern = "^[A-Za-z0-9+_.-]+@(.+)$"; // Basic email pattern
        String passwordPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$"; // At least 8
                                                                                                      // chars, contains
                                                                                                      // at least one
                                                                                                      // digit, one
                                                                                                      // lower alpha,
                                                                                                      // one upper
                                                                                                      // alpha, one
                                                                                                      // special symbol,
                                                                                                      // no whitespace
        String modePattern = "^[a-zA-Z]{4,}$"; // At least 4 alphabetic characters

        // Create pattern and matcher objects
        Pattern pattern;
        Matcher matcher;

        // Validate username
        pattern = Pattern.compile(usernamePattern);
        matcher = pattern.matcher(username);
        if (!matcher.matches()) {
            popupMessage.showErrorMessage("Invalid username");
            return false;
        }

        // Validate email
        pattern = Pattern.compile(emailPattern);
        matcher = pattern.matcher(email);
        if (!matcher.matches()) {
            popupMessage.showErrorMessage("Invalid email");
            return false;
        }

        // Validate password
        pattern = Pattern.compile(passwordPattern);
        matcher = pattern.matcher(password);
        if (!matcher.matches()) {
            popupMessage.showErrorMessage("Invalid password");
            return false;
        }

        // Validate mode
        pattern = Pattern.compile(modePattern);
        matcher = pattern.matcher(mode);
        if (!matcher.matches()) {
            popupMessage.showErrorMessage("Invalid mode");
            return false;
        }

        return true;
    }

    // method to validate name and email
    public boolean validateNameAndEmail(String name, String email) {
        // Check if any field is empty
        if (name.equals("") || email.equals("")) {
            popupMessage.showErrorMessage("Input fields cannot be empty!!");
            return false;
        }

        // Define regex patterns
        String namePattern = "^[A-Za-z\\s]+$"; // Alphabets and spaces only
        String emailPattern = "^[A-Za-z0-9+_.-]+@(.+)$"; // Basic email pattern

        // Create pattern and matcher objects
        Pattern pattern;
        Matcher matcher;

        // Validate name
        pattern = Pattern.compile(namePattern);
        matcher = pattern.matcher(name);
        if (!matcher.matches()) {
            popupMessage.showErrorMessage("Invalid name");
            return false;
        }

        // Validate email
        pattern = Pattern.compile(emailPattern);
        matcher = pattern.matcher(email);
        if (!matcher.matches()) {
            popupMessage.showErrorMessage("Invalid email");
            return false;
        }

        return true;
    }

    // method to validate course inputs
    public boolean validateCourseInputs(String courseName, int seats, int duration) {
        // Check if any field is empty
        if (courseName.equals("") || seats == 0) {
            popupMessage.showErrorMessage("Input fields cannot be empty!!");
            return false;
        }

        Database db = new Database();
        if (db.checkCourseExistence(courseName)) {
            popupMessage.showErrorMessage("Course already exists");
            return false;
        }

        // Define regex patterns
        String courseNamePattern = "^[A-Za-z\\s()]+$"; // Alphabets, spaces, and parentheses only
        String seatsPattern = "^[0-9]{1,}$"; // At least 1 digit
        String durationPattern = "^[0-9]{1,}$"; // At least 1 digit

        // Create pattern and matcher objects
        Pattern pattern;
        Matcher matcher;

        // Validate courseName
        pattern = Pattern.compile(courseNamePattern);
        matcher = pattern.matcher(courseName);
        if (!matcher.matches()) {
            popupMessage.showErrorMessage("Invalid course name");
            return false;
        }

        // Validate seats
        pattern = Pattern.compile(seatsPattern);
        matcher = pattern.matcher(String.valueOf(seats));
        if (!matcher.matches()) {
            popupMessage.showErrorMessage("Invalid seats");
            return false;
        }

        // Validate duration
        pattern = Pattern.compile(durationPattern);
        matcher = pattern.matcher(String.valueOf(duration));
        if (!matcher.matches()) {
            popupMessage.showErrorMessage("Invalid duration");
            return false;
        }

        return true;
    }

    public boolean validateCourseInputs(int id, String courseName, int seats, int duration) {
        // Check if any field is empty
        if (courseName.equals("") || seats == 0) {
            popupMessage.showErrorMessage("Input fields cannot be empty!!");
            return false;
        }

        Database db = new Database();
        if (db.checkCourseExistence(id, courseName)) {
            popupMessage.showErrorMessage("Course already exists");
            return false;
        }

        // Define regex patterns
        String courseNamePattern = "^[A-Za-z\\s()]+$"; // Alphabets, spaces, and parentheses only
        String seatsPattern = "^[0-9]{1,}$"; // At least 1 digit
        String durationPattern = "^[0-9]{1,}$"; // At least 1 digit

        // Create pattern and matcher objects
        Pattern pattern;
        Matcher matcher;

        // Validate courseName
        pattern = Pattern.compile(courseNamePattern);
        matcher = pattern.matcher(courseName);
        if (!matcher.matches()) {
            popupMessage.showErrorMessage("Invalid course name");
            return false;
        }

        // Validate seats
        pattern = Pattern.compile(seatsPattern);
        matcher = pattern.matcher(String.valueOf(seats));
        if (!matcher.matches()) {
            popupMessage.showErrorMessage("Invalid seats");
            return false;
        }

        // Validate duration
        pattern = Pattern.compile(durationPattern);
        matcher = pattern.matcher(String.valueOf(duration));
        if (!matcher.matches()) {
            popupMessage.showErrorMessage("Invalid duration");
            return false;
        }

        return true;
    }

    // method to validate module inputs
    public boolean validateModuleInputs(String moduleName, String courseID, String tutorName) {
        // Check if any field is empty
        if (moduleName.equals("") || courseID.equals("Select any one") || tutorName.equals("Select any one")) {
            popupMessage.showErrorMessage("Input fields cannot be empty!!");
            return false;
        }

        Database db = new Database();
        if (db.checkModuleExistence(moduleName)) {
            popupMessage.showErrorMessage("Module already exists");
            return false;
        }

        // Define regex patterns
        String moduleNamePattern = "^[A-Za-z\\s]+$"; // Alphabets and spaces only
        String courseIDPattern = "^[0-9]{1,}$"; // At least 1 digit
        String tutorNamePattern = "^[A-Za-z\\s]+$"; // Alphabets and spaces only

        // Create pattern and matcher objects
        Pattern pattern;
        Matcher matcher;

        // Validate moduleName
        pattern = Pattern.compile(moduleNamePattern);
        matcher = pattern.matcher(moduleName);
        if (!matcher.matches()) {
            popupMessage.showErrorMessage("Invalid module name");
            return false;
        }

        // Validate courseID
        pattern = Pattern.compile(courseIDPattern);
        matcher = pattern.matcher(courseID);
        if (!matcher.matches()) {
            popupMessage.showErrorMessage("Invalid course ID");
            return false;
        }

        // Validate tutorName
        pattern = Pattern.compile(tutorNamePattern);
        matcher = pattern.matcher(tutorName);
        if (!matcher.matches()) {
            popupMessage.showErrorMessage("Invalid tutor name");
            return false;
        }

        return true;
    }

    public boolean validateModuleInputs(int id, String moduleName, String courseID, String tutorName) {
        // Check if any field is empty
        if (moduleName.equals("") || courseID.equals("Select any one") || tutorName.equals("Select any one")) {
            popupMessage.showErrorMessage("Input fields cannot be empty!!");
            return false;
        }

        Database db = new Database();
        if (db.checkModuleExistence(id, moduleName)) {
            popupMessage.showErrorMessage("Module already exists");
            return false;
        }

        // Define regex patterns
        String moduleNamePattern = "^[A-Za-z\\s]+$"; // Alphabets and spaces only
        String courseIDPattern = "^[0-9]{1,}$"; // At least 1 digit
        String tutorNamePattern = "^[A-Za-z\\s]+$"; // Alphabets and spaces only

        // Create pattern and matcher objects
        Pattern pattern;
        Matcher matcher;

        // Validate moduleName
        pattern = Pattern.compile(moduleNamePattern);
        matcher = pattern.matcher(moduleName);
        if (!matcher.matches()) {
            popupMessage.showErrorMessage("Invalid module name");
            return false;
        }

        // Validate courseID
        pattern = Pattern.compile(courseIDPattern);
        matcher = pattern.matcher(courseID);
        if (!matcher.matches()) {
            popupMessage.showErrorMessage("Invalid course ID");
            return false;
        }

        // Validate tutorName
        pattern = Pattern.compile(tutorNamePattern);
        matcher = pattern.matcher(tutorName);
        if (!matcher.matches()) {
            popupMessage.showErrorMessage("Invalid tutor name");
            return false;
        }

        return true;
    }

    // method to validate tutor inputs
    public boolean validateTutorInputs(int id, String tutorName, String email) {
        // Check if any field is empty
        if (tutorName.equals("") || email.equals("")) {
            popupMessage.showErrorMessage("Input fields cannot be empty!!");
            return false;
        }

        Database db = new Database();
        if (db.checkUserExistence(id, email, "instructor")) {
            popupMessage.showErrorMessage("Tutor already exists");
            return false;
        }

        // Define regex patterns
        String tutorNamePattern = "^[A-Za-z\\s]+$"; // Alphabets and spaces only
        String emailPattern = "^[A-Za-z0-9+_.-]+@(.+)$"; // Basic email pattern

        // Create pattern and matcher objects
        Pattern pattern;
        Matcher matcher;

        // Validate tutorName
        pattern = Pattern.compile(tutorNamePattern);
        matcher = pattern.matcher(tutorName);
        if (!matcher.matches()) {
            popupMessage.showErrorMessage("Invalid tutor name");
            return false;
        }

        // Validate email
        pattern = Pattern.compile(emailPattern);
        matcher = pattern.matcher(email);
        if (!matcher.matches()) {
            popupMessage.showErrorMessage("Invalid email");
            return false;
        }

        return true;
    }

    // Method to validate student inputs
    public boolean validateStudentInputs(int id, String studentName, String email) {
        // Check if any field is empty
        if (studentName.equals("") || email.equals("")) {
            popupMessage.showErrorMessage("Input fields cannot be empty!!");
            return false;
        }

        Database db = new Database();
        if (db.checkUserExistence(id, email, "student")) {
            popupMessage.showErrorMessage("Student already exists");
            return false;
        }

        // Define regex patterns
        String studentNamePattern = "^[A-Za-z\\s]+$"; // Alphabets and spaces only
        String emailPattern = "^[A-Za-z0-9+_.-]+@(.+)$"; // Basic email pattern

        // Create pattern and matcher objects
        Pattern pattern;
        Matcher matcher;

        // Validate studentName
        pattern = Pattern.compile(studentNamePattern);
        matcher = pattern.matcher(studentName);
        if (!matcher.matches()) {
            popupMessage.showErrorMessage("Invalid student name");
            return false;
        }

        // Validate email
        pattern = Pattern.compile(emailPattern);
        matcher = pattern.matcher(email);
        if (!matcher.matches()) {
            popupMessage.showErrorMessage("Invalid email");
            return false;
        }

        return true;
    }

    // Method to validate old password and new password
    public boolean validatePassword(String oldPassword, String newPassword, String password) {
        // Check if any field is empty
        if (oldPassword.equals("") || newPassword.equals("")) {
            popupMessage.showErrorMessage("Input fields cannot be empty!!");
            return false;
        }

        // Define regex patterns
        String passwordPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$"; // At least 8
                                                                                                      // chars, contains
                                                                                                      // at least one
                                                                                                      // digit, one
                                                                                                      // lower alpha,
                                                                                                      // one upper
                                                                                                      // alpha, one
                                                                                                      // special symbol,
                                                                                                      // no whitespace

        // Create pattern and matcher objects
        Pattern pattern;
        Matcher matcher;

        // Validate oldPassword
        if (!oldPassword.equals(password)) {
            popupMessage.showErrorMessage("Invalid old password");
            return false;
        }

        // Validate newPassword
        pattern = Pattern.compile(passwordPattern);
        matcher = pattern.matcher(newPassword);
        if (!matcher.matches()) {
            popupMessage.showErrorMessage("Invalid new password");
            return false;
        }

        // Validate if old and new passwords are same
        if (oldPassword.equals(newPassword)) {
            popupMessage.showErrorMessage("Old and new passwords cannot be same");
            return false;
        }

        return true;
    }

    // Validation for MarksForm
    public boolean validateMarksInputs(String studentID, String moduleID, Float marks) {
        // Checking if moduleID and studentID is not "Select any one"
        if (moduleID.equals("Select any one") || studentID.equals("Select any one")) {
            popupMessage.showErrorMessage("Input fields cannot be empty!!");
            return false;
        } else {
            // Validation for marksObtained
            if (marks < 0 || marks > 100) {
                popupMessage.showErrorMessage("Invalid marks");
                return false;
            }
        }
        return true;
    }
}
