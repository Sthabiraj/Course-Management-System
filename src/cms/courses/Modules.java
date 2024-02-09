package cms.courses;

public class Modules {
  private int moduleID;
  private String moduleName;
  private int courseID;
  private String tutorName;

  public Modules(int moduleID, String moduleName, int courseID, String tutorName) {
    this.moduleID = moduleID;
    this.moduleName = moduleName;
    this.courseID = courseID;
    this.tutorName = tutorName;
  }

  public int getModuleID() {
    return moduleID;
  }

  public void setModuleID(int moduleID) {
    this.moduleID = moduleID;
  }

  public String getModuleName() {
    return moduleName;
  }

  public void setModuleName(String moduleName) {
    this.moduleName = moduleName;
  }

  public int getCourseID() {
    return courseID;
  }

  public void setCourseID(int courseID) {
    this.courseID = courseID;
  }

  public String getTutorName() {
    return tutorName;
  }

  public void setTutorName(String tutorName) {
    this.tutorName = tutorName;
  }

}
