package DAO;
import utils.DBUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;



public class UsersDao {

	
	public String getOfficeName(String officeType,int officeid){
		System.out.println("office name");
	    Connection con = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    String data = "All";
	    try {
	       
	        
	        String sql = "";
	        if (officeType.equals("RO")) {
	            sql = "select discom_id,discom_name from discom";
	            data = data + ":" + "0_All";
	        } else if (officeType.equals("ZO")) {
	            sql = "select * from zone where zoneid="+officeid;
	        } else if (officeType.equals("CO")) {
	            sql = "select circleid,circlename from circle where circleid="+officeid;
	        } else if (officeType.equals("DO")) {            
	            sql = "select divisionid,divisionname from division where divisionid="+officeid;
	        } else if (officeType.equals("SDO")) {
	            sql = "select subdivid,subdivname from subdivision where subdivid="+officeid;
	        }
	        con = new DBUtil().getConnection();
	        pstmt = con.prepareStatement(sql);
	        rs = pstmt.executeQuery();
	        while (rs.next()) {
	            data = rs.getString(2);
	        }
	        

	    } catch (Exception e) {
	        //e.printStackTrace();
	    } finally {
	        
	    }
	    return data;
	}
	
	
}
