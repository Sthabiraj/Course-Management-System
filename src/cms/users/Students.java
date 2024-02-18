package cms.users;

/**
 * Represents a student in the course management system.
 * Inherits from the Users class.
 */
public class Students extends Users {
  private int studentID;
  private String course;

  /**
   * Constructs a new Students object with the specified username, email,
   * password, and course.
   * 
   * @param username The username of the student.
   * @param email    The email address of the student.
   * @param password The password of the student.
   * @param course   The course of the student.
   */
  public Students(String username, String email, String password, String course) {
    super(username, email, password);
    this.course = course;
  }

  /**
   * Constructs a new Students object with the specified student ID, username,
   * email, password, and course.
   * 
   * @param studentID The ID of the student.
   * @param username  The username of the student.
   * @param email     The email address of the student.
   * @param password  The password of the student.
   * @param course    The course of the student.
   */
  public Students(int studentID, String username, String email, String password, String course) {
    super(username, email, password);
    this.studentID = studentID;
    this.course = course;
  }

  /**
   * Sets the student ID of the student.
   * 
   * @param studentID The ID of the student.
   */
  public void setStudentID(int studentID) {
    this.studentID = studentID;
  }

  /**
   * Sets the course of the student.
   * 
   * @param course The course of the student.
   */
  public void setCourse(String course) {
    this.course = course;
  }

  /**
   * Gets the student ID of the student.
   * 
   * @return The ID of the student.
   */
  public int getStudentID() {
    return studentID;
  }

  /**
   * Gets the course of the student.
   * 
   * @return The course of the student.
   */
  public String getCourse() {
    return course;
  }
}
