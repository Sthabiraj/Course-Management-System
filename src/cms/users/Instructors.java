package cms.users;

public class Instructors extends Users {
  private int instructorID;

  public Instructors(int instructorID, String username, String email, String password) {
    super(username, email, password);
    this.instructorID = instructorID;
  }

  public void setInstructorID(int instructorID) {
    this.instructorID = instructorID;
  }

  public int getInstructorID() {
    return instructorID;
  }

}
