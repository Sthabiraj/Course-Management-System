package cms.db;

public class DatabaseInfo {
  private String dbUrl;
  private String dbUsername;
  private String dbPassword;
  private String dbName;

  public DatabaseInfo(String dbUrl, String dbUsername, String dbPassword) {
    this.dbUrl = dbUrl;
    this.dbUsername = dbUsername;
    this.dbPassword = dbPassword;
  }

  public DatabaseInfo(String dbUrl, String dbUsername, String dbPassword, String dbName) {
    this.dbUrl = dbUrl;
    this.dbUsername = dbUsername;
    this.dbPassword = dbPassword;
    this.dbName = dbName;
  }

  public void setDbUrl(String dbUrl) {
    this.dbUrl = dbUrl;
  }

  public void setDbUsername(String dbUsername) {
    this.dbUsername = dbUsername;
  }

  public void setDbPassword(String dbPassword) {
    this.dbPassword = dbPassword;
  }

  public void setDbName(String dbName) {
    this.dbName = dbName;
  }

  public String getDbUrl() {
    return dbUrl;
  }

  public String getDbUsername() {
    return dbUsername;
  }

  public String getDbPassword() {
    return dbPassword;
  }

  public String getDbName() {
    return dbName;
  }
}
