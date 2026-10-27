<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@page import="java.util.*"%>
<%@page import="com.rtdas.common.models.*"%>
<%@page import="com.rtdas.common.beans.HierarchyBean"%>


<div id="main-content">
	<!-- BEGIN Breadcrumb -->
	<div id="breadcrumbs">
		<ul class="breadcrumb">
			<li><i class="fa fa-home"></i> <a href="#">Home</a> <span
				class="divider"><i class="fa fa-angle-right"></i></span></li>
			<li class="active">Hierarchy Master</li>
		</ul>
	</div>
	<!-- END Breadcrumb -->

	<!-- BEGIN Main Content -->
	<div class="row">
		<div class="col-md-12">
			<div class="panel panel-bd">
				<div class="panel-heading">
					<div class="panel-title">
						<h4>Hierarchy List</h4>
						<c:if test="${! empty titles }">
							<c:forEach items="${titles}" var="title" varStatus="loop">
								<c:choose>
									<c:when test="${title.hierarchy_title eq selLevelTitle}">
										<button class="btn btn-sm  active"
											onclick="viewHdata(${actionId},${loop.index+1})">${title.hierarchy_title}</button>
									</c:when>
									<c:otherwise>
										<button class="btn btn-sm "
											onclick="viewHdata(${actionId},${loop.index+1})">${title.hierarchy_title}</button>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</c:if>
					</div>
				</div>
				<div class="panel-body">

					<div class="clearfix"></div>
					<div class="table-responsive" style="border: 0">


						<%
  int selLevel=Integer.parseInt(request.getAttribute("selectedLevel").toString());
 List<HierarchyTitle> titleList=(List<HierarchyTitle>)request.getAttribute("titles");
  System.out.println("selected level---->"+selLevel);
  int clientId=titleList.get(0).getClient_id();
  //---------------------------------------
  List actionList=new ArrayList();
  try{
  String actionId=request.getAttribute("actionId").toString();
  Map<String,List> actionMap=(Map<String,List>)session.getAttribute("mappedActions");
  actionList=actionMap.get(actionId);
  //System.out.println("------------------------>"+actionList);
  }catch(Exception e){
  	e.printStackTrace();
  }
  
%>
						<!--  <div class="row"> -->
						<div class="col-md-12">
							<div class="panel panel-bd">
								<div class="panel-heading">
									<div class="panel-title">
										<h4 class="card-heading col-md-10" style="text-align: left;">${selLevelTitle}
											List</h4>

										<!--                                     <div class="btn-group pull-right clearfix"> -->
										<%if(!actionList.get(0).equals("0")) {%>
										<button class="btn btn-rounded btn-outline-primary"
											onclick="javascript:addHdata('<%=selLevel%>')">
											Add new record <i class="zmdi zmdi-collection-add"></i>
										</button>
										<%} %>
									</div>
								</div>
							</div>
							<c:if test="${! empty levelData }">
								<div class="panel-body">

									<div class="clearfix"></div>
									<div class="table-responsive" style="border: 0">

										<table class="table table-advance" cellspacing="0"
											width="100%" id="table1">
											<thead>
												<tr>
													<%for(int i=0;i<selLevel;i++){ %>
													<th><%=titleList.get(i).getHierarchy_title() %></th>
													<%} %>
													<%--    <th>${selLevelTitle} Description</th> --%>
													<!--    <th>Client</th> -->
													<th>Action</th>
												</tr>
											</thead>
											<tbody>
												<%
List<Object[]> objList=(List<Object[]>)request.getAttribute("levelData");
//System.out.println("size ------->"+objList.size());
for(Object[] ob:objList){
	//System.out.println(ob[0]+" "+ob[1]+" "+ob[2]+" "+ob[3]);
	%>
												<tr>
													<%for(int i=0;i<selLevel;i++){ %>
													<td><%=ob[3*i].toString().toUpperCase() %></td>
													<%}//for selected level %>
													<%-- <td><%=clientId %></td> --%>
													<td>
														<%if(!actionList.get(2).equals("0")) {%> <a
														href="javascript:editHdata(<%=ob[ob.length-2] %>,<%=selLevel %>)"
														title="edit"><i class="ti ti-pencil"></i></a> &nbsp; <% }if(!actionList.get(1).equals("0")) {%>
														<a
														href="javascript:deleteHdata('<%=ob[ob.length-2] %>','<%=selLevel %>')"
														title="delete"> <i class="ti ti-trash"></i></a> <%} %>
													</td>
												</tr>
												<%}
%>
											</tbody>
										</table>
									</div>
								</div>
						</div>
					</div>
					<!--                 </div>  -->
					</c:if>
					<c:if test="${empty levelData }">
						<center>
							<h3>No Data Available</h3>
						</center>
					</c:if>

				</div>
			</div>
		</div>
	</div>
</div>