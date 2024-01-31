package cms.users;

public class Instructors extends Users {
  private String instructorID;

  public Instructors(String username, String email, String password, String instructorID) {
    super(username, email, password);
    this.instructorID = instructorID;
  }

  public void setInstructorID(String instructorID) {
    this.instructorID = instructorID;
  }

  public String getInstructorID() {
    return instructorID;
  }

}
