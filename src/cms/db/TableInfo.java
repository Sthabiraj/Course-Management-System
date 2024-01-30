package cms.db;

public class TableInfo {
  private String tableName;
  private String columnName;

  public TableInfo() {
  }

  public TableInfo(String tableName) {
    this.tableName = tableName;
  }

  public TableInfo(String tableName, String columnName) {
    this.tableName = tableName;
    this.columnName = columnName;
  }

  public void setTableName(String tableName) {
    this.tableName = tableName;
  }

  public void setColumnName(String columnName) {
    this.columnName = columnName;
  }

  public String getTableName() {
    return tableName;
  }

  public String getColumnName() {
    return columnName;
  }
}
