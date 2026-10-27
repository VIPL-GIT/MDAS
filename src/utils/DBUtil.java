package utils;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DBUtil {

    private static Properties dbConfigProp = new Properties();


    public static Connection getConnection() {
    	Connection connection = null;
        try {
        	Properties p=new Properties();
            p.load(DBUtil.class.getClassLoader().getResourceAsStream("./resources/database.properties"));
	         String driverClass=p.getProperty("database.driver");
	         String url=p.getProperty("database.url");
	         String user=p.getProperty("database.user");
	         String password=p.getProperty("database.password");
                     
	   	    Class.forName(driverClass);           
	        connection = DriverManager.getConnection(url,user,password);
        }
       catch (Exception e) {
            e.printStackTrace();
            System.out.println("Exceeption Occured:" + e.getMessage());
            //LoginData.status = e.getMessage();
        }
        return connection;
    }

  

    public static void main(String[] args) {
      Connection con=DBUtil.getConnection();
      System.out.println("connection ==> "+con);
    }
    
    public static void connectionClose(Connection con,Statement pstmt,ResultSet rs){
        try {
            if (con != null) {
                con.close();
            }
            if (pstmt != null) {
                pstmt.close();
            }
            if (rs != null) {
                rs.close();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            //System.out.println("Connection Closed Exception");
        }
    }
}
