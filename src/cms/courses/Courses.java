package cms.courses;

import java.util.List;

import cms.users.Students;

/**
 * The Courses class represents a course in the Course Management System.
 * It contains information about the course, such as its ID, name, available
 * seats, duration,
 * modules, and enrolled students.
 */
public class Courses {
  private int courseID;
  private String courseName;
  private int seats;
  private int duration;
  private List<Modules> modules;
  private List<Students> students;

  /**
   * Constructs an empty Courses object.
   */
  public Courses() {
  }

  /**
   * Constructs a Courses object with the specified ID, name, available seats, and
   * duration.
   *
   * @param id         the ID of the course
   * @param courseName the name of the course
   * @param seats      the number of available seats in the course
   * @param duration   the duration of the course in weeks
   */
  public Courses(int id, String courseName, int seats, int duration) {
    this.courseID = id;
    this.courseName = courseName;
    this.seats = seats;
    this.duration = duration;
  }

  /**
   * Constructs a Courses object with the specified ID, name, available seats,
   * duration,
   * modules, and enrolled students.
   *
   * @param courseID   the ID of the course
   * @param courseName the name of the course
   * @param seats      the number of available seats in the course
   * @param duration   the duration of the course in weeks
   * @param modules    the list of modules in the course
   * @param students   the list of enrolled students in the course
   */
  public Courses(int courseID, String courseName, int seats, int duration, List<Modules> modules,
      List<Students> students) {
    this.courseID = courseID;
    this.courseName = courseName;
    this.seats = seats;
    this.duration = duration;
    this.modules = modules;
    this.students = students;
  }

  /**
   * Returns the ID of the course.
   *
   * @return the ID of the course
   */
  public int getCourseID() {
    return courseID;
  }

  /**
   * Sets the ID of the course.
   *
   * @param courseID the ID of the course
   */
  public void setCourseID(int courseID) {
    this.courseID = courseID;
  }

  /**
   * Returns the name of the course.
   *
   * @return the name of the course
   */
  public String getCourseName() {
    return courseName;
  }

  /**
   * Sets the name of the course.
   *
   * @param courseName the name of the course
   */
  public void setCourseName(String courseName) {
    this.courseName = courseName;
  }

  /**
   * Returns the number of available seats in the course.
   *
   * @return the number of available seats in the course
   */
  public int getSeats() {
    return seats;
  }

  /**
   * Sets the number of available seats in the course.
   *
   * @param seats the number of available seats in the course
   */
  public void setSeats(int seats) {
    this.seats = seats;
  }

  /**
   * Returns the duration of the course in weeks.
   *
   * @return the duration of the course in weeks
   */
  public int getDuration() {
    return duration;
  }

  /**
   * Sets the duration of the course in weeks.
   *
   * @param duration the duration of the course in weeks
   */
  public void setDuration(int duration) {
    this.duration = duration;
  }

  /**
   * Returns the list of modules in the course.
   *
   * @return the list of modules in the course
   */
  public List<Modules> getModules() {
    return modules;
  }

  /**
   * Sets the list of modules in the course.
   *
   * @param modules the list of modules in the course
   */
  public void setModules(List<Modules> modules) {
    this.modules = modules;
  }

  /**
   * Returns the list of enrolled students in the course.
   *
   * @return the list of enrolled students in the course
   */
  public List<Students> getStudents() {
    return students;
  }

  /**
   * Sets the list of enrolled students in the course.
   *
   * @param students the list of enrolled students in the course
   */
  public void setStudents(List<Students> students) {
    this.students = students;
  }

  /**
   * Adds a student to the course.
   *
   * @param student the student to be added
   */
  public void addStudent(Students student) {
    this.students.add(student);
  }

  /**
   * Removes a student from the course.
   *
   * @param student the student to be removed
   */
  public void removeStudent(Students student) {
    this.students.remove(student);
  }

  /**
   * Adds a module to the course.
   *
   * @param module the module to be added
   */
  public void addModule(Modules module) {
    this.modules.add(module);
  }

  /**
   * Removes a module from the course.
   *
   * @param module the module to be removed
   */
  public void removeModule(Modules module) {
    this.modules.remove(module);
  }
}
