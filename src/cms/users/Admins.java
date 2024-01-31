package cms.users;

public class Admins extends Users {
  private String adminID;

  public Admins(String username, String email, String password, String adminID) {
    super(username, email, password);
    this.adminID = adminID;
  }

  public void setAdminID(String adminID) {
    this.adminID = adminID;
  }

  public String getAdminID() {
    return adminID;
  }

}
