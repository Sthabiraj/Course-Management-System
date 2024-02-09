package cms.users;

public class Students extends Users {
  private int studentID;
  private String course;

  public Students(String username, String email, String password, String course) {
    super(username, email, password);
    this.course = course;
  }

  public Students(int studentID, String username, String email, String password, String course) {
    super(username, email, password);
    this.studentID = studentID;
    this.course = course;
  }

  public void setStudentID(int studentID) {
    this.studentID = studentID;
  }

  public void setCourse(String course) {
    this.course = course;
  }

  public int getStudentID() {
    return studentID;
  }

  public String getCourse() {
    return course;
  }
}
