package cms.users;

/**
 * The Admins class represents administrators in the Course Management System.
 * It extends the Users class and adds an adminID field.
 */
public class Admins extends Users {
  private String adminID;

  /**
   * Constructs a new Admins object with the specified username, email, password,
   * and adminID.
   *
   * @param username the username of the admin
   * @param email    the email of the admin
   * @param password the password of the admin
   * @param adminID  the ID of the admin
   */
  public Admins(String username, String email, String password, String adminID) {
    super(username, email, password);
    this.adminID = adminID;
  }

  /**
   * Sets the adminID of the admin.
   *
   * @param adminID the ID of the admin
   */
  public void setAdminID(String adminID) {
    this.adminID = adminID;
  }

  /**
   * Returns the adminID of the admin.
   *
   * @return the ID of the admin
   */
  public String getAdminID() {
    return adminID;
  }

}
