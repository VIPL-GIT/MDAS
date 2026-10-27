<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="col-md-12">
<div class="col-sm-3 col-lg-3 controls">
<select class="" id="zc_type" style="width:100%;height:25px;">
<option value="zc">Zero Consumption</option>
<option value="zc3m">Zero Consumption in last 3 months</option>
<option value="zc6m">Zero Consumption in last 6 months</option>
<option value="zc9m">Zero Consumption in last 9 months</option>
<option value="zc12m">Zero Consumption in last 12 months</option>
<option value="cle1006m">Consumption <100 ( last 6 months)</option>
<option value="nc">Negative Consumption</option>
<option value="hc">High Consumption</option>
<option value="lc">Low Consumption</option>
<option value="pf">PF >-0.8 to <0.8</option>
<option value="kwh_tod">TOD Consumption (KWh)</option>
<option value="kvah_tod">TOD Consumption (KVAh)</option>
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
<button class="btn" onclick="getZeroConsumptionAction()">Get Details</button>
</div>
</div>

<div id="zero_consumption" class="col-md-12" style="margin-top:5px;">
<div id="zc_graph" class="col-md-12" style="border: 1px solid;border-radius: 5px;height: 250px"></div>
<div id="zc_table" class="col-md-12"></div>

</div>