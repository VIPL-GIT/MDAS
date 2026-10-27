<%@page import="java.util.regex.Matcher"%>
<%@page import="java.util.regex.Pattern"%>
<%@page import="utils.DBUtil"%>
<%@ page import="java.sql.*"%>
<%!    PreparedStatement pstmt = null;
       ResultSet rs = null;
       Connection con = null; 
%>
<%  String meterId = null;
    String meterLoc = null;
    String sql = null;
    int searchType = 0;
    try {
        con = new DBUtil().getConnection();
        String optVal = request.getParameter("opt");
        
        
     //   searchType = Integer.parseInt(request.getParameter("searchType"));
        searchType = 8;
        
        meterLoc = request.getParameter("meterLoc").trim();
        meterId = request.getParameter("meterId");
        String data = "";
        System.out.println("searchType = "+searchType+" optVal = "+optVal + " meterLoc = "+meterLoc + " SearchContent = "+meterId);
        System.out.println("<=====  SearchMeters  =====>" + searchType);
        if (searchType < 8 || searchType == 11) {
            if (meterLoc.toUpperCase().equals("DTR")) {
                if (searchType == 11) {  //discom is selected
                    sql = "select m.meterid,m.meter_serial_no,d.dtrname from meter m,dtr d where m.dtrid in (select dtrid from dtr where feederid in ( select feederid from feeder where substationid in (select substationid from substation where section_id in(select section_id from section where mandalid in(select mandalid from mandal where subdivid in(select subdivid from subdivision where divisionid in ( select divisionid from division where circleid in (select circleid from circle where zoneid in(select zoneid from zone where discom_id=?))))))))) and m.dtrid=d.dtrid and m.meter_serial_no like ? order by d.dtrname";
                }
                if (searchType == 1) {  //zone is selected
                    sql = "select m.meterid,m.meter_serial_no,d.dtrname from meter m,dtr d where m.dtrid in (select dtrid from dtr where feederid in ( select feederid from feeder where substationid in (select substationid from substation where section_id in(select section_id from section where mandalid in(select mandalid from mandal where subdivid in(select subdivid from subdivision where divisionid in ( select divisionid from division where circleid in (select circleid from circle where zoneid=?)))))))) and m.dtrid=d.dtrid and m.meter_serial_no like ? order by d.dtrname";
                }
                if (searchType == 2) {  //circle is selected
                    sql = "select m.meterid,m.meter_serial_no,d.dtrname from meter m,dtr d where m.dtrid in (select dtrid from dtr where feederid in ( select feederid from feeder where substationid in (select substationid from substation where section_id in(select section_id from section where mandalid in(select mandalid from mandal where subdivid in(select subdivid from subdivision where divisionid in ( select divisionid from division where circleid=?))))))) and m.dtrid=d.dtrid and m.meter_serial_no like ? order by d.dtrname";
                }
                if (searchType == 3) {  //division is selected
                    sql = "select m.meterid,m.meter_serial_no,d.dtrname from meter m,dtr d where m.dtrid in (select dtrid from dtr where feederid in ( select feederid from feeder where substationid in (select substationid from substation where section_id in(select section_id from section where mandalid in(select mandalid from mandal where subdivid in(select subdivid from subdivision where divisionid =?)))))) and m.dtrid=d.dtrid and m.meter_serial_no like ? order by d.dtrname";
                }
                if (searchType == 4) {  //subdivision is selected
                    sql = "select m.meterid,m.meter_serial_no,d.dtrname from meter m,dtr d where m.dtrid in (select dtrid from dtr where feederid in ( select feederid from feeder where substationid in (select substationid from substation where section_id in(select section_id from section where mandalid in(select mandalid from mandal where subdivid=?))))) and m.dtrid=d.dtrid and m.meter_serial_no like ? order by d.dtrname";
                }
                if (searchType == 5) {  //mandal is selected
                    sql = "select m.meterid,m.meter_serial_no,d.dtrname from meter m,dtr d where m.dtrid in (select dtrid from dtr where feederid in ( select feederid from feeder where substationid in (select substationid from substation where section_id in(select section_id from section where mandalid=?)))) and m.dtrid=d.dtrid and m.meter_serial_no like ? order by d.dtrname";
                }
                if (searchType == 6) {  //section is selected
                    sql = "select m.meterid,m.meter_serial_no,d.dtrname from meter m,dtr d where m.dtrid in (select dtrid from dtr where feederid in ( select feederid from feeder where substationid in (select substationid from substation where section_id=?))) and m.dtrid=d.dtrid and m.meter_serial_no like ? order by d.dtrname";
                }
                if (searchType == 7) {  //ss is selected
                    sql = "select m.meterid,m.meter_serial_no,d.dtrname from meter m,dtr d where m.dtrid in (select dtrid from dtr where feederid in ( select feederid from feeder where substationid=?)) and m.dtrid=d.dtrid and m.meter_serial_no like ? order by d.dtrname";
                }
                if (searchType == 8) {  //ss is selected
                    sql = "select m.meterid,m.meter_serial_no,d.dtrname from meter m,dtr d where m.dtrid in (select dtrid from dtr where feederid =? ) and m.dtrid=d.dtrid and m.meter_serial_no like ? order by d.dtrname";
                }                
                System.out.println("query--->" + sql);
                pstmt = con.prepareStatement(sql);
                pstmt.setString(1, optVal);
                pstmt.setString(2, meterId + "%");
                rs = pstmt.executeQuery();
                int sno = 1;
                while (rs.next()) {
                    data = rs.getString("meterid") + "_" + rs.getString("meter_serial_no") + "-" + sno + ") " + rs.getString(3) + "-" + rs.getString("meter_serial_no") + ":" + data;
                    sno++;
                }//end while

            }
            if (meterLoc.toUpperCase().equals("FEEDER")) {
                if (searchType == 11) {  //discom is selected
//                    sql = "select m.meterid,m.meter_serial_no,d.dtrname from meter m,dtr d where m.dtrid in (select dtrid from dtr where feederid in ( select feederid from feeder where substationid in (select substationid from substation where section_id in(select section_id from section where mandalid in(select mandalid from mandal where subdivid in(select subdivid from subdivision where divisionid in ( select divisionid from division where circleid in (select circleid from circle where zoneid in(select zoneid from zone where discom_id=?))))))))) and m.dtrid=d.dtrid and m.meter_serial_no like ? order by d.dtrname";
                      sql = "select m.meterid,m.meter_serial_no,d.feedername from meter m,feeder d where m.feederid "
                              + "in ( select feederid from feeder where substationid "
                              + "in ( select substationid from substation where section_id "
                              + "in ( select section_id from section where mandalid "
                              + "in ( select mandalid from mandal where subdivid in(select subdivid from subdivision where divisionid "
                              + "in ( select divisionid from division where circleid in (select circleid from circle where zoneid "
                              + "in ( select zoneid from zone where discom_id=?)))))))) and m.feederid=d.feederid and m.meter_serial_no like ? order by d.feedername";  
                }
                if (searchType == 1) {  //zone is selected
//                    sql = "select m.meterid,m.meter_serial_no,d.dtrname from meter m,dtr d where m.dtrid in (select dtrid from dtr where feederid in ( select feederid from feeder where substationid in (select substationid from substation where section_id in(select section_id from section where mandalid in(select mandalid from mandal where subdivid in(select subdivid from subdivision where divisionid in ( select divisionid from division where circleid in (select circleid from circle where zoneid=?)))))))) and m.dtrid=d.dtrid and m.meter_serial_no like ? order by d.dtrname";
                    sql = "select m.meterid,m.meter_serial_no,d.feedername from meter m,feeder d where m.feederid "
                            + "in ( select feederid from feeder where substationid "
                            + "in ( select substationid from substation where section_id "
                            + "in ( select section_id from section where mandalid "
                            + "in ( select mandalid from mandal where subdivid in(select subdivid from subdivision where divisionid "
                            + "in ( select divisionid from division where circleid in (select circleid from circle where zoneid =?))))))) and m.feederid=d.feederid and m.meter_serial_no like ? order by d.feedername";
                }
                if (searchType == 2) {  //circle is selected
//                    sql = "select m.meterid,m.meter_serial_no,d.dtrname from meter m,dtr d where m.dtrid in (select dtrid from dtr where feederid in ( select feederid from feeder where substationid in (select substationid from substation where section_id in(select section_id from section where mandalid in(select mandalid from mandal where subdivid in(select subdivid from subdivision where divisionid in ( select divisionid from division where circleid=?))))))) and m.dtrid=d.dtrid and m.meter_serial_no like ? order by d.dtrname";
                      sql = "select m.meterid,m.meter_serial_no,d.feedername from meter m,feeder d where m.feederid "
                              + "in ( select feederid from feeder where substationid "
                              + "in ( select substationid from substation where section_id "
                              + "in ( select section_id from section where mandalid "
                              + "in ( select mandalid from mandal where subdivid in(select subdivid from subdivision where divisionid "
                              + "in ( select divisionid from division where circleid =? )))))) and m.feederid=d.feederid and m.meter_serial_no like ? order by d.feedername";  
                }
                if (searchType == 3) {  //division is selected
//                    sql = "select m.meterid,m.meter_serial_no,d.dtrname from meter m,dtr d where m.dtrid in (select dtrid from dtr where feederid in ( select feederid from feeder where substationid in (select substationid from substation where section_id in(select section_id from section where mandalid in(select mandalid from mandal where subdivid in(select subdivid from subdivision where divisionid =?)))))) and m.dtrid=d.dtrid and m.meter_serial_no like ? order by d.dtrname";
                      sql = "select m.meterid,m.meter_serial_no,d.feedername from meter m,feeder d where m.feederid "
                              + "in ( select feederid from feeder where substationid "
                              + "in ( select substationid from substation where section_id "
                              + "in ( select section_id from section where mandalid "
                              + "in ( select mandalid from mandal where subdivid "
                              + "in(select subdivid from subdivision where divisionid = ? ))))) and m.feederid=d.feederid and m.meter_serial_no like ? order by d.feedername";  
                }
                if (searchType == 4) {  //subdivision is selected
//                    sql = "select m.meterid,m.meter_serial_no,d.dtrname from meter m,dtr d where m.dtrid in (select dtrid from dtr where feederid in ( select feederid from feeder where substationid in (select substationid from substation where section_id in(select section_id from section where mandalid in(select mandalid from mandal where subdivid=?))))) and m.dtrid=d.dtrid and m.meter_serial_no like ? order by d.dtrname";
                     sql = "select m.meterid,m.meter_serial_no,d.feedername from meter m,feeder d where m.feederid "
                             + "in ( select feederid from feeder where substationid "
                             + "in ( select substationid from substation where section_id "
                             + "in ( select section_id from section where mandalid "
                             + "in ( select mandalid from mandal where subdivid = ?)))) and m.feederid=d.feederid and m.meter_serial_no like ? order by d.feedername";
                }
                if (searchType == 5) {  //mandal is selected
//                    sql = "select m.meterid,m.meter_serial_no,d.dtrname from meter m,dtr d where m.dtrid in (select dtrid from dtr where feederid in ( select feederid from feeder where substationid in (select substationid from substation where section_id in(select section_id from section where mandalid=?)))) and m.dtrid=d.dtrid and m.meter_serial_no like ? order by d.dtrname";
                      sql = "select m.meterid,m.meter_serial_no,d.feedername from meter m,feeder d where m.feederid "
                              + "in ( select feederid from feeder where substationid "
                              + "in ( select substationid from substation where section_id "
                              + "in ( select section_id from section where mandalid = ?))) and m.feederid=d.feederid and m.meter_serial_no like ? order by d.feedername";
                }
                if (searchType == 6) {  //section is selected
//                    sql = "select m.meterid,m.meter_serial_no,d.dtrname from meter m,dtr d where m.dtrid in (select dtrid from dtr where feederid in ( select feederid from feeder where substationid in (select substationid from substation where section_id=?))) and m.dtrid=d.dtrid and m.meter_serial_no like ? order by d.dtrname";
                      sql = "select m.meterid,m.meter_serial_no,d.feedername from meter m,feeder d where m.feederid "
                              + "in ( select feederid from feeder where substationid "
                              + "in ( select substationid from substation where section_id =? )) and m.feederid=d.feederid and m.meter_serial_no like ? order by d.feedername";  
                }
                if (searchType == 7) {  //ss is selected
//                    sql = "select m.meterid,m.meter_serial_no,d.dtrname from meter m,dtr d where m.dtrid in (select dtrid from dtr where feederid in ( select feederid from feeder where substationid=?)) and m.dtrid=d.dtrid and m.meter_serial_no like ? order by d.dtrname";
                      sql = "select m.meterid,m.meter_serial_no,d.feedername from meter m,feeder d where m.feederid "
                              + "in ( select feederid from feeder where substationid =? ) and m.feederid=d.feederid and m.meter_serial_no like ? order by d.feedername";  
                }
                System.out.println("query--->" + sql);
                pstmt = con.prepareStatement(sql);
                pstmt.setString(1, optVal);
                pstmt.setString(2, meterId + "%");
                rs = pstmt.executeQuery();
                int sno = 1;
                while (rs.next()) {
                    data = rs.getString("meterid") + "_" + rs.getString("meter_serial_no") + "-" + sno + ") " + rs.getString(3) + "-" + rs.getString("meter_serial_no") + ":" + data;
                    sno++;
                }//end while

            }
            
         if (meterLoc.toUpperCase().equals("HT")) {
//             sql = "SELECT mt.meterid,mt.meter_serial_no,ht.consumername FROM discom dc,zone zn,circle cr,division dv,subdivision subdiv,town t,substation ss,feeder fd,ht_consumer ht,meter mt "
//                    + "WHERE dc.discom_id=zn.discom_id AND zn.zoneid=cr.zoneid AND cr.circleid=dv.circleid AND dv.divisionid=subdiv.divisionid "
//                    + "AND subdiv.subdivid=t.subdivid AND t.townid=ss.townid AND ss.substationid=fd.substationid AND fd.feederid=ht.feederid "
//                    + "AND ht.consumerid=mt.htconsumerid AND "; //feeder meters
//sql = "select m.meterid,m.meter_serial_no,d.consumername from meter m,ht_consumer d where m.htconsumerid in (select consumerid from ht_consumer where feederid in (select feederid from feeder where substationid in (select substationid from substation where section_id in (select section_id from section where mandalid in (select mandalid from mandal where subdivid in (select subdivid from subdivision where divisionid in ( select divisionid from division where circleid in (select circleid from circle where zoneid in(select zoneid from zone where discom_id=?))))))))) and m.htconsumerid=d.consumerid and m.meter_serial_no like ? order by d.consumername";
            sql="select m.meterid,m.meter_serial_no,d.consumername from "
                    + "meter m,ht_consumer d,feeder fd,substation ss,section sec,mandal mdl,subdivision sd,division di,"
                    + "circle c,zone z,discom dc where m.htconsumerid=d.consumerid and d.feederid=fd.feederid and fd.substationid=ss.substationid and ss.section_id=sec.section_id and sec.mandalid=mdl.mandalid and mdl.subdivid=sd.subdivid and sd.divisionid=di.divisionid and di.circleid=c.circleid and c.zoneid=z.zoneid and z.discom_id=dc.discom_id and ";
             if (searchType == 11) {  //discom is selected
                sql = sql + "dc.discom_id=?";
            }
             if (searchType == 1) {  //zone is selected
                sql = sql + "z.zoneid=?";
            }
           if (searchType == 2) {  //circle is selected
                sql = sql + "c.circleid=?";
            }
            if (searchType == 3) {  //division is selected
                sql = sql + "di.divisionid=?";
            }
               if (searchType == 4) {  //sub division is selected
                sql = sql + "sd.subdivid=?";
            }
             if (searchType == 5) {  //mandal is selected
                sql = sql + "mdl.mandalid=?";
            }
            if (searchType == 6) {  //section is selected
                sql = sql + "sec.section_id=?";
            }
             if (searchType == 7) {  //ss is selected
                sql = sql + " ss.substationid=?";
            }
            sql=sql+" and m.meter_serial_no like ? order by ss.substationname,fd.feedername,d.consumername";
                                                                                                             
            System.out.println(meterId+"\tquery--->" + sql);
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, optVal);
            pstmt.setString(2, meterId + "%");
            rs = pstmt.executeQuery();
                int sno = 1;
                while (rs.next()) {
                    data = rs.getString("meterid") + "_" + rs.getString("meter_serial_no") + "-" + sno + ") " + rs.getString(3) + "-" + rs.getString("meter_serial_no") + ":" + data;
                    System.out.println(" data === "+data);
                    sno++;
                }//end while
        }// for HT
         if (meterLoc.toUpperCase().equals("LT")) {
//             sql = "SELECT mt.meterid,mt.meter_serial_no,ht.consumername FROM discom dc,zone zn,circle cr,division dv,subdivision subdiv,town t,substation ss,feeder fd,ht_consumer ht,meter mt "
//                    + "WHERE dc.discom_id=zn.discom_id AND zn.zoneid=cr.zoneid AND cr.circleid=dv.circleid AND dv.divisionid=subdiv.divisionid "
//                    + "AND subdiv.subdivid=t.subdivid AND t.townid=ss.townid AND ss.substationid=fd.substationid AND fd.feederid=ht.feederid "
//                    + "AND ht.consumerid=mt.htconsumerid AND "; //feeder meters
//sql = "select m.meterid,m.meter_serial_no,d.consumername from meter m,ht_consumer d where m.htconsumerid in (select consumerid from ht_consumer where feederid in (select feederid from feeder where substationid in (select substationid from substation where section_id in (select section_id from section where mandalid in (select mandalid from mandal where subdivid in (select subdivid from subdivision where divisionid in ( select divisionid from division where circleid in (select circleid from circle where zoneid in(select zoneid from zone where discom_id=?))))))))) and m.htconsumerid=d.consumerid and m.meter_serial_no like ? order by d.consumername";
            sql="select m.meterid,m.meter_serial_no,d.consumername from "
                    + "meter m,lt_consumer d,feeder fd,substation ss,section sec,mandal mdl,subdivision sd,division di,"
                    + "circle c,zone z,discom dc where m.ltconsumerid=d.consumerid and d.feederid=fd.feederid and fd.substationid=ss.substationid and ss.section_id=sec.section_id and sec.mandalid=mdl.mandalid and mdl.subdivid=sd.subdivid and sd.divisionid=di.divisionid and di.circleid=c.circleid and c.zoneid=z.zoneid and z.discom_id=dc.discom_id and ";
             if (searchType == 11) {  //discom is selected
                sql = sql + "dc.discom_id=?";
            }
             if (searchType == 1) {  //zone is selected
                sql = sql + "z.zoneid=?";
            }
           if (searchType == 2) {  //circle is selected
                sql = sql + "c.circleid=?";
            }
            if (searchType == 3) {  //division is selected
                sql = sql + "di.divisionid=?";
            }
               if (searchType == 4) {  //sub division is selected
                sql = sql + "sd.subdivid=?";
            }
             if (searchType == 5) {  //mandal is selected
                sql = sql + "mdl.mandalid=?";
            }
            if (searchType == 6) {  //section is selected
                sql = sql + "sec.section_id=?";
            }
             if (searchType == 7) {  //ss is selected
                sql = sql + " ss.substationid=?";
            }
            sql=sql+" and m.meter_serial_no like ? order by ss.substationname,fd.feedername,d.consumername";
                                                                                                             
            System.out.println(meterId+"\tquery--->" + sql);
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, optVal);
            pstmt.setString(2, meterId + "%");
            rs = pstmt.executeQuery();
                int sno = 1;
                while (rs.next()) {
                    data = rs.getString("meterid") + "_" + rs.getString("meter_serial_no") + "-" + sno + ") " + rs.getString(3) + "-" + rs.getString("meter_serial_no") + ":" + data;
                    System.out.println(" data === "+data);
                    sno++;
                }//end while
        }// for lT
         if (meterLoc.toUpperCase().equals("OA")) {
             sql="select m.meterid,m.meter_serial_no,d.consumername from "
                    + "meter m,open_access d,feeder fd,substation ss,section sec,mandal mdl,subdivision sd,division di,"
                    + "circle c,zone z,discom dc where m.open_access_id=d.consumerid and d.feederid=fd.feederid and fd.substationid=ss.substationid and ss.section_id=sec.section_id and sec.mandalid=mdl.mandalid and mdl.subdivid=sd.subdivid and sd.divisionid=di.divisionid and di.circleid=c.circleid and c.zoneid=z.zoneid and z.discom_id=dc.discom_id and ";
             if (searchType == 11) {  //discom is selected
                sql = sql + "dc.discom_id=?";
            }
             if (searchType == 1) {  //zone is selected
                sql = sql + "z.zoneid=?";
            }
           if (searchType == 2) {  //circle is selected
                sql = sql + "c.circleid=?";
            }
            if (searchType == 3) {  //division is selected
                sql = sql + "di.divisionid=?";
            }
               if (searchType == 4) {  //sub division is selected
                sql = sql + "sd.subdivid=?";
            }
             if (searchType == 5) {  //mandal is selected
                sql = sql + "mdl.mandalid=?";
            }
            if (searchType == 6) {  //section is selected
                sql = sql + "sec.section_id=?";
            }
             if (searchType == 7) {  //ss is selected
                sql = sql + " ss.substationid=?";
            }
            sql=sql+" and m.meter_serial_no like ? order by ss.substationname,fd.feedername,d.consumername";
                                                                                                             
           // System.out.println(meterId+"\tquery--->" + sql);
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, optVal);
            pstmt.setString(2, meterId + "%");
            rs = pstmt.executeQuery();
                int sno = 1;
                while (rs.next()) {
                    data = rs.getString("meterid") + "_" + rs.getString("meter_serial_no") + "-" + sno + ") " + rs.getString(3) + "-" + rs.getString("meter_serial_no") + ":" + data;
                    System.out.println(" data === "+data);
                    sno++;
                }//end while
        }// for OA
     if (meterLoc.toUpperCase().equals("REG")) {
             sql="select m.meterid,m.meter_serial_no,d.consumername from "
                    + "meter m,re_generator d,feeder fd,substation ss,section sec,mandal mdl,subdivision sd,division di,"
                    + "circle c,zone z,discom dc where m.re_generator_id=d.consumerid and d.feederid=fd.feederid and fd.substationid=ss.substationid and ss.section_id=sec.section_id and sec.mandalid=mdl.mandalid and mdl.subdivid=sd.subdivid and sd.divisionid=di.divisionid and di.circleid=c.circleid and c.zoneid=z.zoneid and z.discom_id=dc.discom_id and ";
             if (searchType == 11) {  //discom is selected
                sql = sql + "dc.discom_id=?";
            }
             if (searchType == 1) {  //zone is selected
                sql = sql + "z.zoneid=?";
            }
           if (searchType == 2) {  //circle is selected
                sql = sql + "c.circleid=?";
            }
            if (searchType == 3) {  //division is selected
                sql = sql + "di.divisionid=?";
            }
               if (searchType == 4) {  //sub division is selected
                sql = sql + "sd.subdivid=?";
            }
             if (searchType == 5) {  //mandal is selected
                sql = sql + "mdl.mandalid=?";
            }
            if (searchType == 6) {  //section is selected
                sql = sql + "sec.section_id=?";
            }
             if (searchType == 7) {  //ss is selected
                sql = sql + " ss.substationid=?";
            }
            sql=sql+" and m.meter_serial_no like ? order by ss.substationname,fd.feedername,d.consumername";
                                                                                                             
           // System.out.println(meterId+"\tquery--->" + sql);
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, optVal);
            pstmt.setString(2, meterId + "%");
            rs = pstmt.executeQuery();
                int sno = 1;
                while (rs.next()) {
                    data = rs.getString("meterid") + "_" + rs.getString("meter_serial_no") + "-" + sno + ") " + rs.getString(3) + "-" + rs.getString("meter_serial_no") + ":" + data;
                    //System.out.println(" data === "+data);
                    sno++;
                }//end while
        }// for REG    
        } else if (searchType == 8) {   //search by location            
            if (meterLoc.toUpperCase().equals("DTR")) {
                sql = "SELECT m.meterid,m.meter_serial_no,dt.dtrname FROM meter m,dtr dt WHERE m.dtrid=dt.dtrid  AND dt.dtrname LIKE '%" + optVal + "%' ORDER BY m.meter_serial_no";
            }else if (meterLoc.toUpperCase().equals("FEEDER")) {
                sql = "SELECT m.meterid,m.meter_serial_no,fd.feedername FROM meter m,feeder fd WHERE m.feederid=fd.feederid  AND fd.feedername LIKE '%" + optVal + "%' ORDER BY m.meter_serial_no";
            }else if (meterLoc.toUpperCase().equals("HT")) {
                sql = "SELECT m.meterid,m.meter_serial_no,ht.consumername FROM meter m,ht_consumer ht WHERE m.htconsumerid=ht.consumerid  AND ht.consumername LIKE '%" + optVal + "%' ORDER BY m.meter_serial_no";
            }
            else if (meterLoc.toUpperCase().equals("LT")) {
                sql = "SELECT m.meterid,m.meter_serial_no,ht.consumername FROM meter m,lt_consumer ht WHERE m.ltconsumerid=ht.consumerid  AND ht.consumername LIKE '%" + optVal + "%' ORDER BY m.meter_serial_no";
            }
            else if (meterLoc.toUpperCase().equals("OA")) {
                sql = "SELECT m.meterid,m.meter_serial_no,ht.consumername FROM meter m,open_access ht WHERE m.open_access_id=ht.consumerid  AND ht.consumername LIKE '%" + optVal + "%' ORDER BY m.meter_serial_no";
            }
            else if (meterLoc.toUpperCase().equals("REG")) {
                sql = "SELECT m.meterid,m.meter_serial_no,ht.consumername FROM meter m,re_generator ht WHERE m.re_generator_id=ht.consumerid  AND ht.consumername LIKE '%" + optVal + "%' ORDER BY m.meter_serial_no";
            }
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();
            int sno = 1;
            while (rs.next()) {
                data = rs.getString("meterid") + "_" + rs.getString("meter_serial_no") + "-" + sno + ") " + rs.getString(3) + "-" + rs.getString("meter_serial_no") + ":" + data;
                sno++;
            }//end while

        }//search by location
        else if (searchType == 9) {   //search by meter            
            if (meterLoc.toUpperCase().equals("DTR")) {
                sql = "SELECT m.meterid,m.meter_serial_no,dt.dtrname FROM meter m,dtr dt WHERE m.dtrid=dt.dtrid  AND m.meter_serial_no LIKE '%" + optVal + "%' ORDER BY m.meter_serial_no";
            }else if (meterLoc.toUpperCase().equals("FEEDER")) {
//                sql = "SELECT m.meterid,m.meter_serial_no,dt.dtrname FROM meter m,dtr dt WHERE m.dtrid=dt.dtrid  AND m.meter_serial_no LIKE '%" + optVal + "%' ORDER BY m.meter_serial_no";
                sql = "SELECT m.meterid,m.meter_serial_no,fd.feedername FROM meter m,feeder fd WHERE m.feederid=fd.feederid  AND m.meter_serial_no LIKE '%" + optVal + "%' ORDER BY m.meter_serial_no";
            }else if (meterLoc.toUpperCase().equals("HT")) {
//                sql = "SELECT m.meterid,m.meter_serial_no,dt.dtrname FROM meter m,dtr dt WHERE m.dtrid=dt.dtrid  AND m.meter_serial_no LIKE '%" + optVal + "%' ORDER BY m.meter_serial_no";
                sql = "SELECT m.meterid,m.meter_serial_no,ht.consumername FROM meter m,ht_consumer ht WHERE m.htconsumerid=ht.consumerid  AND m.meter_serial_no LIKE '%" + optVal + "%' ORDER BY m.meter_serial_no";
            }
            else if (meterLoc.toUpperCase().equals("LT")) {
//                sql = "SELECT m.meterid,m.meter_serial_no,dt.dtrname FROM meter m,dtr dt WHERE m.dtrid=dt.dtrid  AND m.meter_serial_no LIKE '%" + optVal + "%' ORDER BY m.meter_serial_no";
                sql = "SELECT m.meterid,m.meter_serial_no,ht.consumername FROM meter m,lt_consumer ht WHERE m.ltconsumerid=ht.consumerid  AND m.meter_serial_no LIKE '%" + optVal + "%' ORDER BY m.meter_serial_no";
            }
            else if (meterLoc.toUpperCase().equals("OA")) {
                sql = "SELECT m.meterid,m.meter_serial_no,ht.consumername FROM meter m,open_acess ht WHERE m.open_access_id=ht.consumerid  AND m.meter_serial_no LIKE '%" + optVal + "%' ORDER BY m.meter_serial_no";
            }
            else if (meterLoc.toUpperCase().equals("REG")) {
                sql = "SELECT m.meterid,m.meter_serial_no,ht.consumername FROM meter m,re_generator ht WHERE m.re_generator_id=ht.consumerid  AND m.meter_serial_no LIKE '%" + optVal + "%' ORDER BY m.meter_serial_no";
            }
           // System.out.println("sql ht = "+sql);
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();
            int sno = 1;
            while (rs.next()) {
                data = rs.getString("meterid") + "_" + rs.getString("meter_serial_no") + "-" + sno + ") " + rs.getString(3) + "-" + rs.getString("meter_serial_no") + ":" + data;
                sno++;
            }//end while

        }//search by meter
        else if (searchType == 10) {   //UnMapped Meters

            Pattern pattern = Pattern.compile("[^a-z0-9 ]|\\s", Pattern.CASE_INSENSITIVE);
            Matcher matcher = null;
            boolean isSpecialCharExist = false;
            if ("".equals(optVal) || optVal.length() <= 0) {
                sql = "select * from meter m  where feederid is null and dtrid is null and htconsumerid is null and ltconsumerid is null order by meter_serial_no";
            } else {
                sql = "select * from meter m  where feederid is null and dtrid is null and htconsumerid is null and ltconsumerid is null and meter_serial_no like '%" + optVal + "%' order by meter_serial_no";
            }
            pstmt = con.prepareStatement(sql);
            //pstmt.setString(1, meterId + "%");
            rs = pstmt.executeQuery();
            //System.out.println("unMapped Meters--->" + sql);
            String meter_serial_no = "";
            int sno = 1;
            while (rs.next()) {
                meter_serial_no = rs.getString("meter_serial_no").trim();
                matcher = pattern.matcher(meter_serial_no);
                isSpecialCharExist = matcher.find();
                if (isSpecialCharExist) {
                    //System.out.println("unmapped Meter==>"+rs.getString("meter_serial_no"));
                    continue;
                }
                data = rs.getString("meterid") + "_" + rs.getString("meter_serial_no") + "-" + sno + ") " + rs.getString("meter_serial_no") + ":" + data;
                sno++;
            }//end while
        }

        System.out.println("searched data--->" + data);
        out.print(data);
        
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        con.close();
    }
%>