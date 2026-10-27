<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="col-md-12">
<div class="col-sm-3 col-lg-3 controls">
<select class="" id="lf_type" style="width:100%;height:25px;">
<option value="le50p">LF < 50%</option>
<!-- <option value="le5p3m">LF < 5% from last 3 months</option> -->
<option value="gr100p">LF > 100%</option>
<!-- <option value="gr100p3m">LF > 100% from last 3 months</option> -->
</select>
</div>

<div class="col-sm-3 col-lg-3 controls">
<select class="" id="year" style="width:100%;height:25px;">
<c:forEach items="${years}" var="y">
 <option value="${y}">${y }</option>
</c:forEach>
</select>
</div>
<div class="col-sm-3 col-lg-3 controls">
<button class="btn" onclick="getLoadFactorAction()">Get Details</button>
</div>
</div>

<div id="load_factor" class="col-md-12" style="margin-top:5px;">
<div id="lf_table" class="col-md-6" ></div>
<div id="lf_graph" class="col-md-6" style="border: 1px solid;border-radius: 5px;height: 250px"></div>
</div>