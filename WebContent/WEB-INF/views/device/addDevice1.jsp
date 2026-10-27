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
				<div class="col-md-6 ">
					<!-- BEGIN Left Side -->
					<div class="form-group">
						<label for="textfield2" class="col-sm-4 col-lg-5 control-label">Device
							Make<sup>*</sup>
						</label>
						<div class="col-sm-6 col-lg-6 controls">
							<select class="form-control chosen" name="make" id="make"
								tabindex="1">
								<option value=""></option>
								<c:forEach items="${deviceMake}" var="make">
									<option value="${make.deviceMakeId}">${make.deviceMakeName}</option>
								</c:forEach>

							</select>
						</div>
					</div>

					<div class="form-group">
						<label for="textfield2" class="col-sm-4 col-lg-5 control-label">Device
							Type<sup>*</sup>
						</label>
						<div class="col-sm-6 col-lg-6 controls">
							<select class="form-control chosen" name="type" id="type"
								tabindex="1">
								<option value=""></option>
								<c:forEach items="${devicetype}" var="type">
									<option value="${type.deviceTypeId}">${type.deviceTypeName}</option>
								</c:forEach>

							</select>
						</div>
					</div>
					<div class="form-group">
						<label for="textfield2" class="col-sm-4 col-lg-5 control-label">Parameters
							Count<sup>*</sup>
						</label>
						<div class="col-sm-6 col-lg-6 controls">
							<select class="form-control chosen" name="paramscount"
								id="paramscount" tabindex="1">
								<option value="0"></option>
								<%for(int i=1;i<=50;i++){ %>
								<option value="<%=i%>"><%=i%></option>
								<%} %>

							</select>
						</div>
					</div>


				</div>
				
				<div class="col-md-offset-2 col-md-8 col-offset-md-2">
					
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

