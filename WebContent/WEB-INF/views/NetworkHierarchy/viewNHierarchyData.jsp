<style>
.nwh5{
 padding-right:5px;
}
</style>
<div id="main-content">
	<!-- BEGIN Breadcrumb -->
	<div id="breadcrumbs">
		<ul class="breadcrumb">
			<li><i class="fa fa-home"></i> <a href="#">Home</a> <span
				class="divider"><i class="fa fa-angle-right"></i></span></li>
			<li class="active">Network Hierarchy</li>
		</ul>
	</div></div>
	<!-- END Breadcrumb -->
	<!-- BEGIN Main Content -->
	<div class="row">
		<div class="col-md-12">
			
				<div class="panel panel-bd">
                        <div class="panel-heading">
					
					<div class="panel-title">
                          <div class="col-md-12">
<!-- 							<label for="textfield2" class="control-label" style="font-size: 14px">Network Hierarchy</label> -->
							
							
							<div class="form-group no-margin" style="margin:0px;">
									
<%-- 											<input type="radio" id="ipfeeder" name="nwtype" value="ipfeeder" onclick="viewIPFeeder('${actionId}')"> --%>
<!-- 											<h5 class="nwh5">I/P Feeder</h5> -->
											
											<input type="radio" id="feeder" name="nwtype" value="feeder" onclick="viewFeeder('${actionId}')" checked>
											<h5 class="nwh5">Feeder</h5>
									
											<input type="radio" id="dtr" disabled name="nwtype" value="dtr" onclick="viewDTRs('${actionId}')">
											<h5 class="nwh5">DTR</h5>
										
											<input type="radio" id="ht"  name="nwtype" value="ht" onclick="viewConsumers('${actionId}')">
											<h5 class="nwh5">Consumer</h5>
											
<%-- 											<input type="radio" id="psl" name="nwtype" value="psl" onclick="viewPSLConsumers('${actionId}')"> --%>
<!-- 											<h5 class="nwh5">PSL</h5> -->
											
									
                            </div>
                            </div>
					</div>					
				</div>
				
<div class="panel-body">
<div class="panel-body" id="networkdata" style="padding:0px !important;">
  </div>              
<div class="row" id="loading" style="display: none;">
				<table width="100%">
					<tr>
						<td align="center"><img src="assets/dist/img/loader.gif"
							width="20" height="20" style="margin-top: 35px" /></td>
					</tr>
				</table>
			</div>
</div>










