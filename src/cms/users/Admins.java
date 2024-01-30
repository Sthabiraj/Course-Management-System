package cms.users;

public class Admins extends Users {
  private String adminID;

  public Admins(String username, String email, String password, String phoneNumber, String adminID) {
    super(username, email, password, phoneNumber);
    this.adminID = adminID;
  }

  public void setAdminID(String adminID) {
    this.adminID = adminID;
  }

  public String getAdminID() {
    return adminID;
  }

}
