/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import com.rtdas.frtuconfig.beans.sldBean;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import utils.DBUtil;
import com.rtdas.frtuconfig.beans.substation_dtls;


public class sldDao {
/**
 *
 * @author:Rizwana.Shaik
 * Date:15-Dec-2012
 * Purpose:To insert Co-ordinates of dropped element.
 */
    public static boolean insertData(String ss_id, String position) throws SQLException {
    	System.out.println("Insertion method ");
        boolean b = true;
        Connection con = null;
        Statement stmt=null, stmt1 = null;
        try{
        con = DBUtil.getConnection();
        stmt = con.createStatement();
        stmt1 = con.createStatement();
        String strQuery;
        ResultSet rs = null;
        int id = 0;
       // System.out.println("position===>" + position);
        String[] pos = position.split("@");
        System.out.println(position+"  "+ pos.length);
        //stmt.executeUpdate("DELETE FROM sld_designer where ss_id='" + ss_id + "'");
        for (int i = 0; i < pos.length; i++) {
        	
        	
            String[] xyPos = pos[i].split(",");
            int k = 0;
            String[] param_feeder_ID = xyPos[k].split("_");
            String xPos = xyPos[k + 1];
            String yPos = xyPos[k + 2];
            String pointSrlno = xyPos[k + 3];
            String pointType = xyPos[k + 4];
            System.out.println("xPos" + xPos);
            String strQuery1 = "";
            if (!xPos.equals("0") && !yPos.equals("0")) {
                strQuery1 = "insert into sld_designer(SS_Id,Param_Id,X_Cord_Value,Y_Cord_Value,feeder_id,point_srlno,is_di_pointType) values(" + ss_id + "," + param_feeder_ID[0] + "," + xPos + "," + yPos + ",'" + param_feeder_ID[1] + "',"+pointSrlno+","+pointType+")";
             System.out.println("strQuery1" + strQuery1);
                stmt1.executeUpdate(strQuery1);
           //     System.out.println("stmt1==>"+stmt1);
            }
           // System.out.println("end");

        }
        } catch (Exception ex) {
        ex.printStackTrace();
    } finally {
        DBUtil.connectionClose(con, stmt, null);
    }
        return b;
    }

    
    
    
    
    public static boolean insertDIS(String allkeys,String allvalues,String date,String time) throws SQLException {
    	System.out.println(date+"Insertion DIS method "+time);
    	
        boolean b = true;
        Connection con = null;
        Statement stmt=null;
        try{
        con = DBUtil.getConnection();
        stmt = con.createStatement();
        String strQuery;
        ResultSet rs = null;
        int id = 0;
        
        
        String allkeyorg=allkeys.replace("dis_","");
        System.out.println(allvalues+"->  "+allkeyorg);
        
        Map<String,String> keyvalue=new HashMap<String,String>();
        
       String[] KeyArray=allkeyorg.split(",");  
       String[] ValueArray=allvalues.split(",");
        
        for(int i=0;i<KeyArray.length;i++){
        		keyvalue.put(KeyArray[i], ValueArray[i]);
        }
        
        for (Map.Entry<String,String> entry : keyvalue.entrySet()) {
        	
            System.out.println("Key = " + entry.getKey() +  ", Value = " + entry.getValue()); 
            String strQuery1 = "insert into rtu_di_data(di_srlno,di_value,date,time,rtu_id) values(" + entry.getKey() + ","+entry.getValue()+","+"'" + date + "'"+","+ "'"+time +"'"+",'50')";
            System.out.println("strQuery1" + strQuery1);
            stmt.executeUpdate(strQuery1);  	
            
            } 
        
        } catch (Exception ex) {
        ex.printStackTrace();
    } finally {
        DBUtil.connectionClose(con, stmt, null);
    }
        return true;
    }
    
    

    
    
    public static ArrayList getData(String ss_id) throws SQLException {
        ArrayList ar = null;
        Connection con = null;
        Statement stmt = null;
        try {
            con = DBUtil.getConnection();
            stmt = con.createStatement();
            ResultSet rs = null;
            String strquery = "select p.param_name,d.param_id,d.x_cord_value,d.y_cord_value from sld_params p inner join sld_designer d where p.param_id=d.param_id and d.ss_id=" + ss_id;
          //  System.out.println("strquery  --> "+strquery);
            rs = stmt.executeQuery(strquery);
            ar = new ArrayList();
            while (rs.next()) {
                sldBean bean = new sldBean();
                bean.setParam_id(rs.getString("param_id"));
                bean.setParam_name(rs.getString("param_name"));
                bean.setX_cord_value(rs.getString("x_cord_value"));
                bean.setY_cord_value(rs.getString("y_cord_value"));
                ar.add(bean);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            DBUtil.connectionClose(con, stmt, null);
        }
        return ar;
    }
    
    
    public static ArrayList getSubstationNames(){
        Connection con=null;
          Statement stmt=null;
          ResultSet rs=null;
          ArrayList substationnames = new ArrayList();
          try
          {
              String sql = "select * from substation_dtls order by ss_nm";
              con = DBUtil.getConnection();
              stmt = con.createStatement();
              rs = stmt.executeQuery(sql);
              while(rs.next())
              {
            	  substation_dtls bean = new substation_dtls();
                  bean.setSs_nm(rs.getString("ss_nm"));
                  bean.setSs_id(rs.getInt("ss_id"));
                  substationnames.add(bean);
              }
          }
          catch(Exception e)
          {
              e.printStackTrace();
          }
          finally
          {
              if(con!=null)
                  try {
                  con.close();
              } catch (SQLException ex) {
                ex.printStackTrace();
              }
          }
          return substationnames;
      }
    
    
    
    
}
