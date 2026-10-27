<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<style>
.round strong{
 margin-left:-30px !important;
}
.round canvas{
 height:150px !important;
}
</style>

<section id="circleBar">
    
     <div class="statistic-box" style="width:100%;">
         <div class="small">11KV Industrial  </br> (${comm_11kv_nonag }/${tot_11kv_nonag })</div>
         
         <div class="col-md-3" style="width:100%;">		
        <div class="round " 
          data-value="${commcount_11kv_nonag }"
          data-size="100"
          data-thickness="6">
          <strong></strong>
          
        </div>
      </div>
     </div>
</section>
     
     