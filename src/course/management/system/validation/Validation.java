/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package course.management.system.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author biraj
 */
public class Validation {
    public boolean validateInputs(String username, String email, String password, String mode) {
    // Check if any field is empty
    if (username.equals("") || email.equals("") || password.equals("") || mode.equals("Select any one")) {
        System.out.println("Input fields cannot be empty!!");
        return false;
    }

    // Define regex patterns
    String usernamePattern = "^[a-zA-Z0-9._-]{3,}$"; // At least 3 characters, allows alphanumeric, dot, underscore, and hyphen
    String emailPattern = "^[A-Za-z0-9+_.-]+@(.+)$"; // Basic email pattern
    String passwordPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$"; // At least 8 chars, contains at least one digit, one lower alpha, one upper alpha, one special symbol, no whitespace
    String modePattern = "^[a-zA-Z]{4,}$"; // At least 4 alphabetic characters

    // Create pattern and matcher objects
    Pattern pattern;
    Matcher matcher;

    // Validate username
    pattern = Pattern.compile(usernamePattern);
    matcher = pattern.matcher(username);
    if (!matcher.matches()) {
        System.out.println("Invalid username");
        return false;
    }

    // Validate email
    pattern = Pattern.compile(emailPattern);
    matcher = pattern.matcher(email);
    if (!matcher.matches()) {
        System.out.println("Invalid email");
        return false;
    }

    // Validate password
    pattern = Pattern.compile(passwordPattern);
    matcher = pattern.matcher(password);
    if (!matcher.matches()) {
        System.out.println("Invalid password");
        return false;
    }

    // Validate mode
    pattern = Pattern.compile(modePattern);
    matcher = pattern.matcher(mode);
    if (!matcher.matches()) {
        System.out.println("Invalid mode");
        return false;
    }

    return true;
}
    
}
