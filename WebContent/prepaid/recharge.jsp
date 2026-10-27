<%@page import="utils.DBUtil"%>
<%@ page import="java.sql.*"%>

<style>
    .rcharge td{
        font-size: 13px !important;
    }    
    
</style>
<%
    System.out.println("------->"+new java.util.Date());
%>
 <div class="formdiscom" style="margin-top:10px;">
    <fieldset style="border:solid 1px #ccc;">
        <legend class="contenttitle1">Prepaid Meters</legend>
        
            <table style="" class="rcharge">
                <tr>
                    <td>Meter</td>
                    <td>
            <select name="meter" id ="meter" >
            <option value="0">Select meter</option>
            <option value="549652">549652</option>
            <option value="X1399446">X1399446</option>
           
            <%--
          Connection con = new DBUtil().getConnection();
                PreparedStatement pstmt = null;
                ResultSet rs = null;
                System.out.println("before select stmnt");
                try {
                    pstmt = con.prepareStatement("select * from discom");
                    rs = pstmt.executeQuery();
                    while (rs.next()) {
                        System.out.println("Discom Data ====> "+rs.getString("discom_id")+"     "+rs.getString("discom_name"));
            %>
            
            <option value="<%=rs.getString("discom_id")%>"><%=rs.getString("discom_name")%></option>
            
            
            <%}
                } catch (Exception e) {
                    con.close();
                    response.sendRedirect("../errorPage.jsp?message=" + e.getMessage());
                }
            --%>    
            </select>
        </td>


        
<td>Amount</td>
<td>
             <input type="text"  class="edate" id="amount" style="width:110px !important;" >
                        
           
        </td>
        <td>
            <input type="button" class="myButton" value="Renewal" onclick="payAmount()" style="">
        </td>
     
                </tr>
            </table>
    
      
    </fieldset>
</div>
<div id="rechargeHistory"></div>   
<div id="dataHistory" style="padding-top:10px;"></div>   

    
 
  



           