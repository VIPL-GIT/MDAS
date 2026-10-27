<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<section id="circleBar">
    <div class="statistic-box">
         <div class="small">11KV AG (${comm_11kv_ag }/${tot_11kv_ag })</div>
         
         <div class="col-md-3">		
        <div class="round " 
          data-value="${commcount_11kv_ag }"
          data-size="100"
          data-thickness="6">
          <strong></strong>
          
        </div>
      </div>
     </div>
     <div class="statistic-box">
         <div class="small">11KV Non-AG  (${comm_11kv_nonag }/${tot_11kv_nonag })</div>
         
         <div class="col-md-3">		
        <div class="round " 
          data-value="${commcount_11kv_nonag }"
          data-size="100"
          data-thickness="6">
          <strong></strong>
          
        </div>
      </div>
     </div>
     <div class="statistic-box" >
         <div class="small">33KV (${comm_33kv }/${tot_33kv })</div>
         
         <div class="col-md-3">		
        <div class="round 33kv" 
          data-value="${commcount_33kv }"
          data-size="100"
          data-thickness="6">
          <strong></strong>
          
        </div>
      </div>
     </div>
     <div class="statistic-box" >
         <div class="small">Total  (${tot_comm }/${tot_meters })</div>
         
         <div class="col-md-3">		
        <div class="round total" 
          data-value="${tot_commcount }"
          data-size="100"
          data-thickness="6">
          <strong></strong>
          
        </div>
      </div>
     </div>
<!--      <div class="statistic-box"> -->
<%--          <div class="small">Not Comm.status </br> (${tot_notcomm }/${tot_meters })</div> --%>
         
<!--          <div class="col-md-3">		 -->
<!--         <div class="round notcomm"  -->
<%--           data-value="${not_commcount }" --%>
<!--           data-size="100" -->
<!--           data-thickness="6"> -->
<!--           <strong></strong> -->
          
<!--         </div> -->
<!--       </div> -->
<!--      </div> -->
     </section>
     
     