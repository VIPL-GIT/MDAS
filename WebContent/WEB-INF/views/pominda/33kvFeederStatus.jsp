<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

     <div class="col-md-12" style="margin-top:5px;padding:0px !important;">
          <div class="statistic-box dash2" style="background-color:#ffd15c !important;">
              <i class="ti-bolt statistic_icon"></i>
              <div class="small">33kv Low PF Feeders </div>
              <h2><span class="count-number"><a href="javascript:dashFeederStatusPopup('33','lowpf')">${lowpf }</a></span> 
              </h2>
              
          </div>
     </div>
    <div class="col-md-12" style="margin-top:5px;padding:0px !important;display:none;">
          <div class="statistic-box dash2" style="background-color:#5cceff !important;">
              <i class="ti-bolt statistic_icon"></i>
              <div class="small">33kv Over Voltage Feeders </div>
              <h2><span class="count-number"><a href="javascript:dashFeederStatusPopup('33','overvoltage')">${overvoltage }</a></span> 
              </h2>
              
          </div>
     </div>
     <div class="col-md-12" style="margin-top:5px;padding:0px !important;">
          <div class="statistic-box dash2" style="background-color:#ff9d5c !important;">
              <i class="ti-bolt statistic_icon"></i>
              <div class="small">33kv Under Voltage Feeders </div>
              <h2><span class="count-number"><a href="javascript:dashFeederStatusPopup('33','undervoltage')">${undervoltage }</a></span>
              </h2>
              
          </div>
     </div>
     
     