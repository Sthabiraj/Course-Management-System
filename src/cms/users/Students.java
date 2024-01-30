package cms.users;

public class Students extends Users {
  private String studentID;
  private String course;

  public Students(String username, String email, String password, String phoneNumber, String course) {
    super(username, email, password, phoneNumber);
    this.course = course;
  }

  public Students(String username, String email, String password, String phoneNumber, String studentID, String course) {
    super(username, email, password, phoneNumber);
    this.studentID = studentID;
    this.course = course;
  }

  public void setStudentID(String studentID) {
    this.studentID = studentID;
  }

  public void setCourse(String course) {
    this.course = course;
  }

  public String getStudentID() {
    return studentID;
  }

  public String getCourse() {
    return course;
  }
}
