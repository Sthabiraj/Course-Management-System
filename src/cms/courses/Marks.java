package cms.courses;

/**
 * The Marks class represents the marks and grade of a module in a course.
 * It provides methods to get and set the module name, marks, and grade.
 */
public class Marks {
  private String moduleName;
  private float marks;
  private String grade;

  /**
   * Constructs a Marks object with the specified module name, marks, and grade.
   * 
   * @param moduleName the name of the module
   * @param marks the marks obtained in the module
   * @param grade the grade obtained in the module
   */
  public Marks(String moduleName, float marks, String grade) {
    this.moduleName = moduleName;
    this.marks = marks;
    this.grade = grade;
  }

  /**
   * Returns the module name.
   * 
   * @return the module name
   */
  public String getModuleName() {
    return moduleName;
  }

  /**
   * Returns the marks obtained in the module.
   * 
   * @return the marks obtained
   */
  public float getMarks() {
    return marks;
  }

  /**
   * Returns the grade obtained in the module.
   * 
   * @return the grade obtained
   */
  public String getGrade() {
    return grade;
  }

  /**
   * Sets the module name.
   * 
   * @param moduleName the new module name
   */
  public void setModuleName(String moduleName) {
    this.moduleName = moduleName;
  }

  /**
   * Sets the marks obtained in the module.
   * 
   * @param marks the new marks obtained
   */
  public void setMarks(float marks) {
    this.marks = marks;
  }

  /**
   * Sets the grade obtained in the module.
   * 
   * @param grade the new grade obtained
   */
  public void setGrade(String grade) {
    this.grade = grade;
  }
}
