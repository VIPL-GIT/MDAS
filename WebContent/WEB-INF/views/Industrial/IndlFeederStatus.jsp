<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="col-md-12" style="margin-top:5px;padding:0px !important;">
          <div class="statistic-box dash2" style="background-color:#ffd15c !important;">
             
              <div class="small">SAIDI(dd hh:mm)/SAIFI </div>
              <h2>
<%--               <span class="count-number"><a href="javascript:dashFeederStatusPopup('ss','ss')">${saidi}/${saifi}</a></span> --%>
               <span class="count-number">${saidi}/${saifi}</span>
              </h2>
              
          </div>
     </div>
     
    <div class="col-md-12" style="margin-top:5px;padding:0px !important;">
          <div class="statistic-box dash2" style="background-color:#5cceff !important;">
              <i class="ti-bolt statistic_icon"></i>
              <div class="small">Over Voltage Feeders </div>
              <h2><span class="count-number"><a href="javascript:indlFeederStatusPopup('overvoltage')">${overvoltage }</a></span>
<%--               <h2><span class="count-number">${overvoltage }</span>  --%>
              </h2>
              
          </div>
     </div>
     <div class="col-md-12" style="margin-top:5px;padding:0px !important;">
          <div class="statistic-box dash2" style="background-color:#ff9d5c !important;">
              <i class="ti-bolt statistic_icon"></i>
              <div class="small">Under Voltage Feeders </div>
              <h2><span class="count-number"><a href="javascript:indlFeederStatusPopup('undervoltage')">${undervoltage }</a></span>
<%--               <h2><span class="count-number">${undervoltage }</span> --%>
              </h2>
              
          </div>
     </div>
    <div class="col-md-12" style="padding:0px !important;">
          <div class="statistic-box dash2" style="background-color:#5cffae !important;">
              <i class="ti-bolt statistic_icon"></i>
              <div class="small">Overloaded Feeders </div>
              <h2><span class="count-number"><a href="javascript:indlFeederStatusPopup('overload')">${overload }</a></span>
<%--               <h2><span class="count-number">${overload }</span>  --%>
              </h2>
              
          </div>
     </div> 
     