package cms.courses;

/**
 * The Modules class represents a module in a course management system.
 * It contains information about the module ID, module name, course ID, and
 * tutor name.
 */
public class Modules {
  private int moduleID;
  private String moduleName;
  private int courseID;
  private String tutorName;

  /**
   * Constructs a new Modules object with the specified module ID, module name,
   * course ID, and tutor name.
   * 
   * @param moduleID   the ID of the module
   * @param moduleName the name of the module
   * @param courseID   the ID of the course the module belongs to
   * @param tutorName  the name of the tutor for the module
   */
  public Modules(int moduleID, String moduleName, int courseID, String tutorName) {
    this.moduleID = moduleID;
    this.moduleName = moduleName;
    this.courseID = courseID;
    this.tutorName = tutorName;
  }

  /**
   * Returns the ID of the module.
   * 
   * @return the module ID
   */
  public int getModuleID() {
    return moduleID;
  }

  /**
   * Sets the ID of the module.
   * 
   * @param moduleID the module ID to set
   */
  public void setModuleID(int moduleID) {
    this.moduleID = moduleID;
  }

  /**
   * Returns the name of the module.
   * 
   * @return the module name
   */
  public String getModuleName() {
    return moduleName;
  }

  /**
   * Sets the name of the module.
   * 
   * @param moduleName the module name to set
   */
  public void setModuleName(String moduleName) {
    this.moduleName = moduleName;
  }

  /**
   * Returns the ID of the course the module belongs to.
   * 
   * @return the course ID
   */
  public int getCourseID() {
    return courseID;
  }

  /**
   * Sets the ID of the course the module belongs to.
   * 
   * @param courseID the course ID to set
   */
  public void setCourseID(int courseID) {
    this.courseID = courseID;
  }

  /**
   * Returns the name of the tutor for the module.
   * 
   * @return the tutor name
   */
  public String getTutorName() {
    return tutorName;
  }

  /**
   * Sets the name of the tutor for the module.
   * 
   * @param tutorName the tutor name to set
   */
  public void setTutorName(String tutorName) {
    this.tutorName = tutorName;
  }

}
