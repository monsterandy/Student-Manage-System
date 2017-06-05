package mydatabase;

import java.sql.*;

import javax.swing.JOptionPane;

public class Database {
    // JDBC 驱动名及数据库 URL
    static final String JDBC_DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    static final String DB_URL = "jdbc:sqlserver://www.ddboom.com:1433;";
    private String _conn;
    public static Connection conn = null;

    public Database(String user,String password){
        this._conn=DB_URL + "databaseName=studentDB;user="+user+";password="+password+";";
        this.connect();
    }

    public Connection connect(){
        try {
            DriverManager.registerDriver(new com.microsoft.sqlserver.jdbc.SQLServerDriver());
        } catch (SQLException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "驱动注册失败！");
        }
        try {
            conn = DriverManager.getConnection(_conn);
        } catch (SQLException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "数据库连接失败！");
        }
        return conn;
    }
    public void disconnect(){
        try {
            conn.close();
        } catch (SQLException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "数据库断开连接错误！");
        }
    }
}
