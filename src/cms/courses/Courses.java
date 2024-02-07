package cms.courses;

import java.util.List;

import cms.users.Students;

public class Courses {
  private int courseID;
  private String courseName;
  private int seats;
  private int duration;
  private List<Modules> modules;
  private List<Students> students;

  public Courses() {
  }

  public Courses(int id, String courseName, int seats, int duration) {
    this.courseID = id;
    this.courseName = courseName;
    this.seats = seats;
    this.duration = duration;
  }

  public Courses(int courseID, String courseName, int seats, int duration, List<Modules> modules,
      List<Students> students) {
    this.courseID = courseID;
    this.courseName = courseName;
    this.seats = seats;
    this.duration = duration;
    this.modules = modules;
    this.students = students;
  }

  public int getCourseID() {
    return courseID;
  }

  public void setCourseID(int courseID) {
    this.courseID = courseID;
  }

  public String getCourseName() {
    return courseName;
  }

  public void setCourseName(String courseName) {
    this.courseName = courseName;
  }

  public int getSeats() {
    return seats;
  }

  public void setSeats(int seats) {
    this.seats = seats;
  }

  public int getDuration() {
    return duration;
  }

  public void setDuration(int duration) {
    this.duration = duration;
  }

  public List<Modules> getModules() {
    return modules;
  }

  public void setModules(List<Modules> modules) {
    this.modules = modules;
  }

  public List<Students> getStudents() {
    return students;
  }

  public void setStudents(List<Students> students) {
    this.students = students;
  }

  public void addStudent(Students student) {
    this.students.add(student);
  }

  public void removeStudent(Students student) {
    this.students.remove(student);
  }

  public void addModule(Modules module) {
    this.modules.add(module);
  }

  public void removeModule(Modules module) {
    this.modules.remove(module);
  }
}
