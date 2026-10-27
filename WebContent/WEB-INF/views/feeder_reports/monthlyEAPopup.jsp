<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  
<%@page import="utils.DBUtil"%>
<%@page import="java.util.*" %>
<%@page import="java.sql.*" %>
<%

%>
<div class="modal-content" style="margin-left:25%;margin-top: 100px;width: 50%;">
    <span class="biclose">&times;</span>
    <h4 style="text-align:center;border-bottom: 2px solid #787078cc;padding-bottom: 10px">
    Monthly - EA Report (${meter })</h4>
        
       

<div class="row">   
<p style="color:red;text-align:center;">Note : All parameters should be in Kilowatt hour (KW).                                                                                                     
<div class="col-md-12 ">
<div class="form-group col-md-12">                                                                                         
            <label for="params" class="col-xs-3 col-lg-2 ">Initial (KWh)</label>                              
            <div class="col-sm-4 col-lg-5 controls">                                                                    
                <input type="text"  name="initial" id="initial"  class="form-control" 
                ${(vals[meter].kwh_initial==null || Double.parseDouble(vals[meter].kwh_initial)==0)?"":"disabled"}
                value="${vals[meter].kwh_initial}">
            </div>                                                                                                       
</div>

<div class="form-group col-md-12">                                                                                         
            <label for="params" class="col-xs-3 col-lg-2 ">Final (KWh)</label>                              
            <div class="col-sm-4 col-lg-5 controls">                                                                    
                <input type="text"  name="final" id="final"  class="form-control" 
                ${(vals[meter].kwh_final==null || Double.parseDouble(vals[meter].kwh_final)==0)?"":"disabled"}
                value="${vals[meter].kwh_final}">
            </div>                                                                                                       
</div>

<div class="form-group col-md-12">                                                                                         
            <label for="params" class="col-xs-3 col-lg-2 ">ASU (KWh)</label>                              
            <div class="col-sm-4 col-lg-5 controls">                                                                    
                <input type="text"  name="asu" id="asu"  class="form-control" 
                ${(vals[meter].assessed_units==null || Double.parseDouble(vals[meter].assessed_units)==0)?"":"disabled"}
                value="${vals[meter].assessed_units}">
            </div>                                                                                                       
</div>
<div class="form-group col-md-12">                                                                                         
            <label for="params" class="col-xs-3 col-lg-2 ">MF</label>                              
            <div class="col-sm-4 col-lg-5 controls">                                                                    
                <input type="text"  name="mf" id="mf"  class="form-control"
                value="${vals[meter].emf}">
            </div>                                                                                                       
</div>
<div class="form-group col-md-12">                                                                                         
      <div class="col-sm-8 col-sm-offset-4 col-lg-9 col-lg-offset-3">                                             
         <button type="button" class="btn btn-sm btn-info" onclick="monthlyEAPopupAction('${meter}','${month }')"> Update</button>                  
                                                                  
      </div>                                                                                                       
  </div> 
 
</div>
</div>

    </div>
    
   