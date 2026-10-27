package utils;


import utils.DBUtil;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author dell
 */
public class HomeBarGraph_bkp {
    public Map<String,String> getMappedMeters(String discom){
        Map<String,String> resultMap = new HashMap<String,String>();
        try{
            Connection con = new DBUtil().getConnection();
            String query = "select m.meter_serial_no,m.modem_serial_no,dis.discom_id,dis.discom_name,"
                                        + "z.zoneid,z.zonename,c.circleid,c.circlename,di.divisionid,di.divisionname,"
                                        + "sd.subdivid,sd.subdivname,mdl.mandalid,mdl.mandalname,sec.section_id,sec.sectionname,"
                                        + "s.substationid,s.substationname,f.feederid,f.feedername "
                                        + "from meter m,feeder f,substation s,section sec,mandal mdl,subdivision sd,division di,circle c,zone z,discom dis "
                                        + "where m.feederid=f.feederid and f.substationid=s.substationid and "
                                        + "s.section_id=sec.section_id and sec.mandalid=mdl.mandalid and mdl.subdivid=sd.subdivid and "
                                        + "sd.divisionid=di.divisionid and di.circleid=c.circleid and c.zoneid=z.zoneid and z.discom_id=dis.discom_id and dis.discom_id='"+discom+"' order by m.meter_serial_no";              
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()){
                resultMap.put(rs.getString("meter_serial_no"),rs.getString("meter_serial_no"));
            }//while
            
            query = "select m.meter_serial_no,m.modem_serial_no,dis.discom_id,dis.discom_name,"
                                        + "z.zoneid,z.zonename,c.circleid,c.circlename,di.divisionid,di.divisionname,"
                                        + "sd.subdivid,sd.subdivname,mdl.mandalid,mdl.mandalname,sec.section_id,sec.sectionname,"
                                        + "s.substationid,s.substationname,f.feederid,f.feedername "
                                        + "from meter m,feeder f,substation s,section sec,mandal mdl,subdivision sd,division di,circle c,zone z,discom dis,ht_consumer ht "
                                        + "where m.htconsumerid=ht.consumerid and "
                                        + "ht.feederid=f.feederid and "
                                        + "f.substationid=s.substationid and "
                                        + "s.section_id=sec.section_id and "
                                        + "sec.mandalid=mdl.mandalid and mdl.subdivid=sd.subdivid and "
                                        + "sd.divisionid=di.divisionid and di.circleid=c.circleid and c.zoneid=z.zoneid and z.discom_id=dis.discom_id and dis.discom_id='"+discom+"' order by m.meter_serial_no";              
            
            System.out.println("Query = "+query);
            stmt = null;
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while(rs.next()){
                resultMap.put(rs.getString("meter_serial_no"),rs.getString("meter_serial_no"));
            }//while

            query = "select m.meter_serial_no,m.modem_serial_no,dis.discom_id,dis.discom_name,"
                                        + "z.zoneid,z.zonename,c.circleid,c.circlename,di.divisionid,di.divisionname,"
                                        + "sd.subdivid,sd.subdivname,mdl.mandalid,mdl.mandalname,sec.section_id,sec.sectionname,"
                                        + "s.substationid,s.substationname,f.feederid,f.feedername "
                                        + "from meter m,feeder f,substation s,section sec,mandal mdl,subdivision sd,division di,circle c,zone z,discom dis,dtr dt "
                                        + "where m.dtrid=dt.dtrid and "
                                        + "dt.feederid=f.feederid and "
                                        + "f.substationid=s.substationid and "
                                        + "s.section_id=sec.section_id and "
                                        + "sec.mandalid=mdl.mandalid and mdl.subdivid=sd.subdivid and "
                                        + "sd.divisionid=di.divisionid and di.circleid=c.circleid and c.zoneid=z.zoneid and z.discom_id=dis.discom_id and dis.discom_id='"+discom+"' order by m.meter_serial_no";              
            
            System.out.println("Query = "+query);
            stmt = null;
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while(rs.next()){
                resultMap.put(rs.getString("meter_serial_no"),rs.getString("meter_serial_no"));
            }//while
			

            con.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return resultMap;
    }
    public String communicatingMeters(String selectedDate) {
    	
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        int comlist = 0;
        String temp = "";
        String datas = "";
        String dates = "";
       
        
        try {
            con = new DBUtil().getConnection();
            temp = selectedDate;
           // System.out.println("selectedDate in bar graph = "+selectedDate);
            for (int i = 0; i < 7; i++) {
                dates = dates + temp + ",";

//                ps = con.prepareStatement("select count(meter_no) as meterCommCount from meter_comm_track_stats where meter_comm_date = ?");
                ps = con.prepareStatement("select comm from daysummary where  comm_date=?");
              
                ps.setString(1, temp);
                rs = ps.executeQuery();
                if(rs.next()){
                  comlist=rs.getInt(1);
                }
                temp = substract_1_Day(temp+" 00:00:00");
                datas = datas + comlist + ",";
            }
            datas = datas.substring(0,datas.length()-1);
	    dates = dates.substring(0,dates.length()-1);
	    datas = dates + ";" + datas;
        } catch (Exception e) {
            e.printStackTrace();
        }
       
        return datas;
    }

    public String nonCommunicatingMeters(String selectedDate) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        int noncomlist = 0;
        String datas = "";
        String temp = "";
        int a;
        try {
            con = new DBUtil().getConnection();
            temp = selectedDate;
            //System.out.println("Not Comm Meters = "+temp);
            for (int i = 0; i < 7; i++) {
                temp = temp.replace("/","-");
                ps = con.prepareStatement("select notcomm from daysummary where  comm_date=?");
               
                ps.setString(1, temp);
                rs = ps.executeQuery();
                if(rs.next()){
                	noncomlist=rs.getInt(1);
                  }
                temp = substract_1_Day(temp+" 00:00:00");
                datas = datas + noncomlist + ",";
            } // for
            datas = datas.substring(0,datas.length()-1);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return datas;
    }
public String substract_1_Day(String str){
    String dateFormat = "";
    try{
//          SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
//          SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");   
          SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
//          int day = Integer.parseInt(str.substring(0,2));
//          int mon = Integer.parseInt(str.substring(3,5));
//          int year = Integer.parseInt(str.substring(6,10));
          int year = Integer.parseInt(str.substring(0,4));
          int mon = Integer.parseInt(str.substring(5,7));
          int day = Integer.parseInt(str.substring(8,10));

          int hrs = Integer.parseInt(str.substring(11,13));
          int min = Integer.parseInt(str.substring(14,16));
          int sec = Integer.parseInt(str.substring(17));

		Calendar c = Calendar.getInstance();
		c.set(year,mon-1,day,hrs,min,sec);//Meter starting time 1988 JAN 1st 00:00:00
		c.add(Calendar.MINUTE,(int)(-1440)*1);
//		c.add(Calendar.MINUTE,(int)(-1440)*0);                
		java.util.Date date1 = c.getTime();
		dateFormat=sdf.format(date1);
//                System.out.println(dateFormat);
    }catch(Exception e){
        e.printStackTrace();
    }
    return dateFormat;
  }//add_1_Day()
    public String neverCommunicatingMeters(String selectedDate) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        int neverlist = 0;
        String never = "";
        String temp = "";
        int a;
        try {
            con = new DBUtil().getConnection();
            temp = selectedDate;

            for (int i = 0; i < 7; i++) {
   
                ps = con.prepareStatement("select nevcomm from daysummary where  comm_date=?");
              
                ps.setString(1, temp);
                rs = ps.executeQuery();
                if(rs.next()){
                	neverlist=rs.getInt(1);
                  }

                temp = substract_1_Day(temp+" 00:00:00");
                never = never + neverlist + ",";
            } // for
            never = never.substring(0,never.length()-1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return never;
    }

    public static void main(String[] args) {
        HomeBarGraph_bkp hbg = new HomeBarGraph_bkp();
        // hbg.communicatingMeters("2017/06/14");
//        String communication_list = hbg.communicatingMeters("2017/06/15");
//        String Non_communication_list = hbg.nonCommunicatingMeters("2017/06/15");
//        String Never_communication_list = hbg.neverCommunicatingMeters("2017/06/15");

//        System.out.println("======>" + communication_list + ";" + Non_communication_list + ";" + Never_communication_list);
    }
}
