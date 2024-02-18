package cms.users;

/**
 * The Instructors class represents instructors in the course management system.
 * It extends the Users class and adds an instructor ID field.
 */
public class Instructors extends Users {
  private int instructorID;

  /**
   * Constructs an Instructors object with the specified instructor ID, username,
   * email, and password.
   * 
   * @param instructorID the ID of the instructor
   * @param username     the username of the instructor
   * @param email        the email of the instructor
   * @param password     the password of the instructor
   */
  public Instructors(int instructorID, String username, String email, String password) {
    super(username, email, password);
    this.instructorID = instructorID;
  }

  /**
   * Sets the instructor ID of this instructor.
   * 
   * @param instructorID the ID of the instructor
   */
  public void setInstructorID(int instructorID) {
    this.instructorID = instructorID;
  }

  /**
   * Returns the instructor ID of this instructor.
   * 
   * @return the ID of the instructor
   */
  public int getInstructorID() {
    return instructorID;
  }

}
