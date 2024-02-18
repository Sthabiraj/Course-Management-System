package cms.users;

/**
 * The Users class represents a user in the Course Management System.
 * It stores the user's username, email, and password.
 */
public class Users {
  private String username;
  private String email;
  private String password;

  /**
   * Constructs a new Users object with the specified username, email, and
   * password.
   * 
   * @param username the username of the user
   * @param email    the email of the user
   * @param password the password of the user
   */
  public Users(String username, String email, String password) {
    this.username = username;
    this.email = email;
    this.password = password;
  }

  /**
   * Sets the username of the user.
   * 
   * @param username the new username to set
   */
  public void setUsername(String username) {
    this.username = username;
  }

  /**
   * Sets the email of the user.
   * 
   * @param email the new email to set
   */
  public void setEmail(String email) {
    this.email = email;
  }

  /**
   * Sets the password of the user.
   * 
   * @param password the new password to set
   */
  public void setPassword(String password) {
    this.password = password;
  }

  /**
   * Returns the username of the user.
   * 
   * @return the username of the user
   */
  public String getUsername() {
    return username;
  }

  /**
   * Returns the email of the user.
   * 
   * @return the email of the user
   */
  public String getEmail() {
    return email;
  }

  /**
   * Returns the password of the user.
   * 
   * @return the password of the user
   */
  public String getPassword() {
    return password;
  }
}
