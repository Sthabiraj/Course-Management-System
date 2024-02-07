/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cms.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cms.error.PopupMessage;

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
        String usernamePattern = "^[a-zA-Z0-9._-]{3,}$"; // At least 3 characters, allows alphanumeric, dot, underscore,
                                                         // and hyphen
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
        String usernamePattern = "^[a-zA-Z0-9._-]{3,}$"; // At least 3 characters, allows alphanumeric, dot, underscore,
                                                         // and hyphen
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

    // method to validate course inputs
    public boolean validateCourseInputs(String courseName, int seats, int duration) {
        // Check if any field is empty
        if (courseName.equals("") || seats == 0) {
            popupMessage.showErrorMessage("Input fields cannot be empty!!");
            return false;
        }

        // Define regex patterns
        String courseNamePattern = "^[a-zA-Z ]{3,}$"; // At least 3 characters, and space
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
}
