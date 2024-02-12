package cms.courses;

public class Marks {
  private String moduleName;
  private float marks;
  private String grade;

  public Marks(String moduleName, float marks, String grade) {
    this.moduleName = moduleName;
    this.marks = marks;
    this.grade = grade;
  }

  public String getModuleName() {
    return moduleName;
  }

  public float getMarks() {
    return marks;
  }

  public String getGrade() {
    return grade;
  }

  public void setModuleName(String moduleName) {
    this.moduleName = moduleName;
  }

  public void setMarks(float marks) {
    this.marks = marks;
  }

  public void setGrade(String grade) {
    this.grade = grade;
  }
}
