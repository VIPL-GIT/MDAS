<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="java.util.*"%>

<!-- BEGIN Breadcrumb -->
                <div id="breadcrumbs">
                    <ul class="breadcrumb">
                        <li>
                            <i class="fa fa-home"></i>
                            <a href="#">Home</a>
                            <span class="divider"><i class="fa fa-angle-right"></i></span>
                        </li>
                        <li class="active">Hierarchy Master</li>
                    </ul>
                </div>
                <!-- END Breadcrumb -->
                
<div class="panel panel-bd">
                        <div class="panel-heading">
	<div class="panel-title">
		<h4 class="card-heading">Edit<c:if test="${!empty selLevelTitle}">${selLevelTitle}</c:if>
		</h4>
	</div>
	</div>
	<input type="hidden" id="htype" value="${selLevelType}" />
	<%
List<Object[]> current=(List<Object[]>)request.getAttribute("currentHierarchy");
Object[] objArray=current.get(0);

%>
	<div class="panel-body">
		<form class="form-horizontal" method="post" id="editData">
			<c:choose>
				<c:when test="${selectedLevel==1}">
					<div class="form-group">
						<label class="col-sm-3 col-lg-2 control-label">${selLevelTitle}
							Name<sup>*</sup>
						</label>
						<div class="col-sm-4 col-lg-5 controls">
							<input type="text" id="title" placeholder="Title"
								class="form-control" value="${hdata.hierarchy_name}" />
						</div>
					</div>
				</c:when>
				<c:otherwise>
					<c:forEach items="${titles}" var="title" varStatus="loop">
						<c:if test="${loop.index+1<selectedLevel }">
							<div class="form-group">
								<label class="col-sm-3 col-lg-2 control-label">${title.hierarchy_title}<sup>*</sup></label>
								<div class="col-sm-4 col-lg-5 controls">
									<c:choose>
										<c:when test="${loop.index+1==1}">
											<select class="form-control chosen"
												id='level_${loop.index+1}'
												<c:if test="${selectedLevel!=loop.index+2}">onchange="getHierarchyData(this.value,'level_${loop.index+2}')" </c:if>
												data-placeholder="Choose a Category" tabindex="1">
												<option value="0"></option>
												<c:forEach items="${firstLevelData}" var="firstLevel">
													<c:choose>
														<c:when
															test="${firstLevel.hierarchy_id==currentHierarchy.get(0)[1] }">
															<option value="${firstLevel.hierarchy_id}" selected>${firstLevel.hierarchy_name}</option>
														</c:when>
														<c:otherwise>
															<option value="${firstLevel.hierarchy_id}">${firstLevel.hierarchy_name}</option>
														</c:otherwise>
													</c:choose>
												</c:forEach>
											</select>
										</c:when>
										<c:otherwise>
											<select class="form-control chosen"
												id='level_${loop.index+1}'
												<c:if test="${selectedLevel!=loop.index+2}">onchange="getHierarchyData(this.value,'level_${loop.index+2}')" </c:if>
												data-placeholder="Choose a Category" tabindex="1">
												<option value="0"></option>
												<option value="${currentHierarchy.get(0)[(loop.index*3)+1]}"
													selected>${currentHierarchy.get(0)[loop.index*3]}</option>
											</select>
										</c:otherwise>
									</c:choose>
								</div>
							</div>
						</c:if>
					</c:forEach>
					<div class="form-group">
						<label class="col-sm-3 col-lg-2 control-label">${selLevelTitle}
							Name<sup>*</sup>
						</label>
						<div class="col-sm-4 col-lg-5 controls">
							<input type="text" name="title" id="title" placeholder="Title"
								class="form-control" value="${hdata.hierarchy_name}" />
						</div>
					</div>
				</c:otherwise>
			</c:choose>

			<div class="form-group">
				<label class="col-sm-3 col-lg-2 control-label">${selLevelTitle}
					Code</label>
				<div class="col-sm-4 col-lg-5 controls">
					<input type="text" id="code" placeholder="Code"
						class="form-control" value="${hdata.code}" />
				</div>
			</div>
			<div class="form-group">
				<label for="password1" class="col-sm-3 col-lg-2 control-label">Active
					Status</label>
				<div class="col-sm-4 col-lg-5 controls">
					<select class="form-control chosen" name="status" id="status"
						tabindex="1">

						<option value="1"
							<c:if test="${hdata.active_flg==1 }">selected</c:if>>Active
						</option>

						<option value="0"
							<c:if test="${hdata.active_flg==0 }">selected</c:if>>Inactive
						</option>

					</select>
				</div>
			</div>
			<div class="form-group">
			<c:choose> 
    <c:when test='${clientId eq 0}'>  
				<label class="col-sm-3 col-lg-2 control-label">Client</label>
				<div class="col-sm-4 col-lg-5 controls">
					<select class="form-control chosen" id="client"
						data-placeholder="Choose a Category" tabindex="1">
						<option value=""></option>
						<c:forEach items="${clients}" var="client">
							<c:choose>
								<c:when test="${client.client_id==hdata.client_id}">
									<option value="${client.client_id}" selected>${client.client_name}</option>
								</c:when>
								<c:otherwise>
									<option value="${client.client_id}">${client.client_name}</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>

					</select>
				</div>
				 </c:when><c:otherwise>
<div id="clientdiv"><input type="hidden" value="${clientId}" id="client"/></div>
</c:otherwise>
</c:choose> 
			</div>

			<div class="form-group">
				<div class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-2">
					<button type="button" class="btn btn-primary"
						onclick="updateHdata('${selectedLevel-1}','${hdata.hierarchy_id}')">
						<i class="fa fa-check"></i> Update
					</button>
					<button type="button" class="btn"
						onclick="viewHdata('${actionId}','${selectedLevel}')">Cancel</button>
				</div>
			</div>
		</form>


	</div>
	</div>