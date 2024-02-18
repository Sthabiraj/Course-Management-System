package cms.db;

/**
 * The DatabaseInfo class represents the information required to connect to a
 * database.
 * It contains the URL, username, password, and optional database name.
 */
public class DatabaseInfo {
  private String dbUrl;
  private String dbUsername;
  private String dbPassword;
  private String dbName;

  /**
   * Constructs a DatabaseInfo object with the specified URL, username, and
   * password.
   * 
   * @param dbUrl      the URL of the database
   * @param dbUsername the username for the database connection
   * @param dbPassword the password for the database connection
   */
  public DatabaseInfo(String dbUrl, String dbUsername, String dbPassword) {
    this.dbUrl = dbUrl;
    this.dbUsername = dbUsername;
    this.dbPassword = dbPassword;
  }

  /**
   * Constructs a DatabaseInfo object with the specified URL, username, password,
   * and database name.
   * 
   * @param dbUrl      the URL of the database
   * @param dbUsername the username for the database connection
   * @param dbPassword the password for the database connection
   * @param dbName     the name of the database
   */
  public DatabaseInfo(String dbUrl, String dbUsername, String dbPassword, String dbName) {
    this.dbUrl = dbUrl;
    this.dbUsername = dbUsername;
    this.dbPassword = dbPassword;
    this.dbName = dbName;
  }

  /**
   * Sets the URL of the database.
   * 
   * @param dbUrl the URL of the database
   */
  public void setDbUrl(String dbUrl) {
    this.dbUrl = dbUrl;
  }

  /**
   * Sets the username for the database connection.
   * 
   * @param dbUsername the username for the database connection
   */
  public void setDbUsername(String dbUsername) {
    this.dbUsername = dbUsername;
  }

  /**
   * Sets the password for the database connection.
   * 
   * @param dbPassword the password for the database connection
   */
  public void setDbPassword(String dbPassword) {
    this.dbPassword = dbPassword;
  }

  /**
   * Sets the name of the database.
   * 
   * @param dbName the name of the database
   */
  public void setDbName(String dbName) {
    this.dbName = dbName;
  }

  /**
   * Returns the URL of the database.
   * 
   * @return the URL of the database
   */
  public String getDbUrl() {
    return dbUrl;
  }

  /**
   * Returns the username for the database connection.
   * 
   * @return the username for the database connection
   */
  public String getDbUsername() {
    return dbUsername;
  }

  /**
   * Returns the password for the database connection.
   * 
   * @return the password for the database connection
   */
  public String getDbPassword() {
    return dbPassword;
  }

  /**
   * Returns the name of the database.
   * 
   * @return the name of the database
   */
  public String getDbName() {
    return dbName;
  }
}
