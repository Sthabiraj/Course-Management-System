package cms.courses;

public class Marks {
  private String courseName;
  private float marks;
  private String grade;

  public Marks(String courseName, float marks, String grade) {
    this.courseName = courseName;
    this.marks = marks;
    this.grade = grade;
  }

  public String getCourseName() {
    return courseName;
  }

  public float getMarks() {
    return marks;
  }

  public String getGrade() {
    return grade;
  }

  public void setCourseName(String courseName) {
    this.courseName = courseName;
  }

  public void setMarks(float marks) {
    this.marks = marks;
  }

  public void setGrade(String grade) {
    this.grade = grade;
  }
}
