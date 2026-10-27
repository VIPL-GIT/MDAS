<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- BEGIN Breadcrumb -->
                <div id="breadcrumbs">
                    <ul class="breadcrumb">
                        <li>
                            <i class="fa fa-home"></i>
                            <a href="#">Home</a>
                            <span class="divider"><i class="fa fa-angle-right"></i></span>
                        </li>
                        <li class="active">Device Master</li>
                    </ul>
                </div>
                <!-- END Breadcrumb -->
                
<div class="panel panel-bd">
	<div class="panel-heading">
		<div class="panel-title">
		<h4 class="card-heading"> Add Device </h4>
		
		</div>
	</div>
	<div class="panel-body">
		<form action="#" class="form-horizontal" id="device">
			<div class="row">
				<div class="col-md-12 ">
					<!-- BEGIN Left Side -->
					<div class=" col-md-4 form-group">
						<label for="textfield2" class="col-sm-4 col-lg-5 control-label">Device
							Make<sup>*</sup>
						</label>
						<div class="col-sm-8 col-lg-7 controls">
							<select class="form-control chosen" name="make" id="make"
								tabindex="1">
								<option value=""></option>
								<c:forEach items="${deviceMake}" var="make">
									<option value="${make.deviceMakeId}">${make.deviceMakeName}</option>
								</c:forEach>

							</select>
						</div>
					</div>

					<div class="col-md-4 form-group">
						<label for="textfield2" class="col-sm-4 col-lg-5 control-label">Device
							Type<sup>*</sup>
						</label>
						<div class="col-sm-8 col-lg-7 controls">
							<select class="form-control chosen" name="type" id="type"
								tabindex="1">
								<option value=""></option>
								<c:forEach items="${devicetype}" var="type">
									<option value="${type.deviceTypeId}">${type.deviceTypeName}</option>
								</c:forEach>

							</select>
						</div>
					</div>
					<div class="col-md-4 form-group">
						<label for="textfield2" class="col-sm-6 col-lg-6 control-label">Max.Parameters
							<sup>*</sup>
						</label>
						<div class="col-sm-6 col-lg-6 controls">
							<select class="form-control chosen" name="paramscount"
								id="paramscount" tabindex="1">
								<option value="0"></option>
								<%for(int i=1;i<=15;i++){ %>
								<option value="<%=i%>"><%=i%></option>
								<%} %>

							</select>
						</div>
					</div>


				</div>
				<div class="col-md-offset-2 col-md-8 col-offset-md-2">
					<!-- BEGIN Right Side -->
					
					<fieldset class="scheduler-border">
						<legend class="scheduler-border">Baud Rates</legend>
						<c:forEach items="${baudrates}" var="baud">
							<div class="col-md-3 control-group">
								<input type="checkbox" name="baudrate" value="${baud}" />
								${baud}

							</div>
						</c:forEach>

					</fieldset>
					<!-- </div> -->
				</div>
				<div class="col-md-offset-2 col-md-8 col-offset-md-2">
					<fieldset class="scheduler-border">
						<legend class="scheduler-border">
							Supported Parameters<sup>*</sup>
						</legend>
						<c:forEach var="p" items="${params}">
							<div class="col-md-4 control-group">
								<input type="checkbox" name="supportparams" value="${p[0] }" />
								${p[1] }

							</div>
						</c:forEach>
					</fieldset>
					<div class="form-group">
						<div class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-2">
							<button type="button" class="btn btn-primary"
								onclick="saveDevice()">
								<i class="fa fa-check"></i> Save
							</button>
							<button type="button" class="btn"
								onclick="viewDevice(${actionId})">Cancel</button>
						</div>
					</div>
					<!-- END Right Side -->

				</div>


				<!-- END Right Side -->
			</div>
		</form>
	</div>



</div>

