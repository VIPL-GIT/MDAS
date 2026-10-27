<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@page import="java.util.*" %>

    <c:if test="${!empty deviceconfig}">
 <table class="table table-advance" cellspacing="0" width="100%" id="table1">
 <thead>
  <tr>
   <th>Sl No</th>
   <th>Register Address</th>
   <th>Description</th>
   <th>MF</th>
   <th>Register Size</th>
   <th>Functional Code</th>
   <th>Energy Flag</th>
   <th>Default Flag</th>
   <th>Parameter Type</th>   
   <th>Actions</th>
  </tr>
</thead>
<tbody>
  <c:forEach items="${deviceconfig}" var="config" varStatus="loop">
   <tr> 
   <td>${loop.index+1}</td>    
    <td>
    <input type="hidden" name="ids" id="id_${loop.index+1}" value="${config.memory_map_id}"/>
    <input type="text" name="register_address" id="register_address_${loop.index+1}" value="${config.register_address}"/>
    </td>
    <td><input type="text" name="desc" id="desc_${loop.index+1}" value="${config.description}"/></td>
    <td>
    <select class="" name="mf" id="mf_${loop.index+1}"  tabindex="1">        
      <c:forEach items="${mfs}" var="mf">
        <option value="${mf}" ${config.mf eq mf?'selected':'' }>${mf}</option>
      </c:forEach>                                                     
                                          
   </select> 
    </td>
    <td>
    <select class="" name="register_size" id="register_size_${loop.index+1}"  tabindex="1">        
      <c:forEach items="${rsizes}" var="rs">
        <option value="${rs}" ${config.register_size eq rs?'selected':'' }>${rs}</option>
      </c:forEach>                                                     
                                          
   </select> 
    </td>
    <td>
    <select class="" name="function_code" id="function_code_${loop.index+1}"  tabindex="1">        
      <c:forEach items="${fCodes}" var="fc">
        <option value="${fc.key}" ${config.function_code eq fc.key?'selected':'' }>${fc.value}</option>
      </c:forEach>                                                     
                                          
   </select>
    </td>
    <td><label class="radio-inline">
    <input type="checkbox" id="eflg_${loop.index+1}" name="energyParam" ${config.energy_flg==1?'checked':''}/>
    </label>
    </td>
    <td><label class="radio-inline">
    <input type="checkbox" id="default_add_flg_${loop.index+1}" name="default_add_flg" ${config.default_add_flg==1?'checked':''}/> 
    </label>
    </td>
    <td>
    <select class="" name="names" id="name_${loop.index+1}"  tabindex="1">        
      <c:forEach items="${params}" var="pm">
        <option value="${pm.parameterId}" ${config.parameter_name eq pm.parameterId?'selected':'' }>${pm.parameterName}</option>
      </c:forEach>                                                     
                                          
   </select>
    </td>
    <td>
<%--     <a class="btn btn-circle show-tooltip ${fn:length(config.parameter_name)==0?'disabled':''}" title="Edit" --%>
<%--      href="javascript:updateDeviceConfig(${config.memory_map_id },${loop.index+1})"><i class="ti ti-pencil"></i></a> --%>
<!-- 								&nbsp;&nbsp;&nbsp; -->
	<a class="btn btn-circle show-tooltip" title="Delete" href="javascript:deleteDeviceConfigById(${config.memory_map_id })"><i class="ti ti-trash"></i></a>
    </td>
    
   </tr>
  </c:forEach>
  </tbody>
 </table>
 <table width="100%">
 <tr>
 <td>
 <div class="form-group">                                                                                         
                        <div class="col-md-offset-10 col-md-2">                                             
                           <button type="button" class="btn btn-sm btn-primary" onclick="updateAllDeviceConfig()"><i class="fa fa-check"></i> Update All</button>                   
                                                                                    
                        </div>                                                                                                       
                    </div>
                    </td>
 </tr>
 </table> 
</c:if>
<c:if test="${empty deviceconfig}">
<center><h3>No Data Available</h3></center>
</c:if>

                       