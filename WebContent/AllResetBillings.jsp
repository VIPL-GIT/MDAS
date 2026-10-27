<%@page import="java.util.*"%>
<%@page import="com.rtdas.meterdata.beans.Auto_reset_bean"%>
<%@page import="com.rtdas.common.service.*" %>
<%@page import="com.rtdas.common.models.*" %>
<%@page import="com.rtdas.reports.dao.*" %>
 <%@page import="org.springframework.web.context.WebApplicationContext" %> 
<%@page import="org.springframework.web.context.support.WebApplicationContextUtils"%> 
<%!
  Auto_reset_bean bb = null;
%>
<%
/*   String searchType = request.getParameter("searchType"); 
String searchTypeID = request.getParameter("opt"); */   
 String selectedMonth = request.getParameter("selectedMonth"); 
//System.out.println(selectedMonth+" getting month");
 selectedMonth=selectedMonth.split("/")[1]+"-"+selectedMonth.split("/")[0];
 String type=request.getParameter("meterLoc");  
 
 
String locTypeAccess="";
       try{
           
        locTypeAccess=session.getAttribute("locTypeAccess")!=null?session.getAttribute("locTypeAccess").toString():"";
       }catch(Exception e){}
%>    
<div class="table-responsive">
<table id="example" cellpadding="0" cellspacing="0" border="0"  class="stdtable stdtablecb smallfont" style="width:100%">
        <thead>
            <tr>
                <th>SL.No</th>                
                <th>Meter Sr.No</th>
              <% if(type.equals("all")){%> 
                  <th>Type</th>
                <%}%>
                <th>Account No</th>
                <th>DATETIME</th>
                
                <%for(int i=0;i<8;i++){%>
                <th>C<%=i+1%>KWH_I</th>
                <%}%>
                <%for(int i=0;i<8;i++){%>
                <th>C<%=i+1%>KWH_E</th>
                <%}%>
                <%for(int i=0;i<8;i++){%>
                <th>C<%=i+1%>KWH_ABS</th>
                <%}%>
                <%for(int i=0;i<8;i++){%>
                <th>C<%=i+1%>KVAH_I</th>
                <%}%>
                <%for(int i=0;i<8;i++){%>
                <th>C<%=i+1%>KVAH_E</th>
                <%}%>
                <%for(int i=0;i<8;i++){%>
                <th>C<%=i+1%>KVAH_ABS</th>
                <%}%>
                
                
                <th>CMDKW_I</th>
                <th>CMDKW_E</th>
                <th>CMDKW_ABS</th>
                
                <th>CMDKVA_I</th>
                <th>CMDKVA_E</th>
                <th>CMDKVA_ABS</th>
                
                <th>MDKW_I</th>
                <th>MDKW_E</th>
                <th>MDKW_ABS</th>
                
                <th>MDKVA_I</th>
                <th>MDKVA_E</th>
                <th>MDKVA_ABS</th>
                
                <th>KWH_I</th>
                <th>KWH_E</th>
                <th>KWH_ABS</th>
                <th>KVAH_I</th>
                <th>KVAH_E</th>
                <th>KVAH_ABS</th>
                
                <th>PF</th>
                
                <th>CMDKW_I_CURRENT</th>
                <th>CMDKW_E_CURRENT</th>
                <th>CMDKW_ABS_CURRENT</th>
                <th>CMDKVA_I_CURRENT</th>
                <th>CMDKVA_E_CURRENT</th>
                <th>CMDKVA_ABS_CURRENT</th>

            </tr>
        </thead>
        <tbody>
<%
//----------------------------------------------------------------------------------------- 

WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(this.getServletContext());
	  
	   MeterService meterService = (MeterService) wac.getBean("meterService");
	   HierarchyTitleService titleService = (HierarchyTitleService) wac.getBean("titleService");
	   HierarchyService hservice = (HierarchyService) wac.getBean("hierarchyService");
	   
String userHierarchyid=(session.getAttribute("hierarchyid")!=null?session.getAttribute("hierarchyid").toString():null);
	    		String[] userHierarchyids=(userHierarchyid!=null?userHierarchyid.split(","):null);
	    		List<HierarchyTitle> titleList=titleService.listTitles(1);
	    		System.out.println("size -->  "+titleList.size());
	    		
	    		
	    		List<Integer> ssIds=new ArrayList();
//------------------getting all child ids--------------------- 
	    		

	    		int level=0;
	    		if(userHierarchyids!=null){
	    			Hierarchy userHierarchy=hservice.getHierarchy(Integer.parseInt(userHierarchyids[0]));
	    		 for(int i=0;i<titleList.size();i++){
	    			HierarchyTitle htitle=(HierarchyTitle)titleList.get(i);
	    			if(htitle.getHierarchy_type_id()==userHierarchy.getHierarchy_type_id()){
	    				level=i+1;	    				
	    		       }
	    		     }//for
	    		 }//if
	    		if(userHierarchyids!=null && (level==2 || level==3)){  //for SDO users and SS users user
	    			for(int i=0;i<userHierarchyids.length;i++){
	    			  ssIds.add(Integer.parseInt(userHierarchyids[i]));
	    			}
	    			//to get ssids
	    			List<Object[]> sslist=new ArrayList();
	    			for(int i=0;i<userHierarchyids.length;i++){
	    			  sslist.addAll(hservice.listAllChildsByParentId( level, titleList.size(), Integer.parseInt(userHierarchyids[i])));
	    		    }
	    			for(Object[] objArray:sslist) {
		    			ssIds.add(Integer.parseInt(objArray[0].toString()));
		    		}
	    			
	    		}
	    		else if (userHierarchyids!=null && level==1 ){    //for company level user
	    			//to get ssids
	    			List<Object[]> sslist=new ArrayList();
	    			for(int i=0;i<userHierarchyids.length;i++){
	    			  sslist.addAll(hservice.listAllChildsByParentId( level, titleList.size(), Integer.parseInt(userHierarchyids[i])));
	    		    }
	    			//to get SDO ids
	    			List<Object[]> sdolist=new ArrayList();
	    			for(int i=0;i<userHierarchyids.length;i++){
	    				sdolist.addAll(hservice.listAllChildsByParentId( level+1, titleList.size(), Integer.parseInt(userHierarchyids[i])));
	    			}
	    			for(Object[] objArray:sslist) {
		    			ssIds.add(Integer.parseInt(objArray[0].toString()));
		    		}
	    			for(Object[] objArray:sdolist) {
		    			ssIds.add(Integer.parseInt(objArray[0].toString()));
		    		}
	    		}
	    		else{           //for main admin
	    			//to add SDO ids
	    			List<Hierarchy> hie=hservice.getHierarchyByType(titleList.get(0).getHierarchy_type_id());
	    			for(Hierarchy h:hie){
	    				//to get ssids
		    			List<Object[]> sslist=hservice.listAllChildsByParentId( 1, titleList.size(), h.getHierarchy_id());
		    			//to get SDO ids
		    			List<Object[]> sdolist=hservice.listAllChildsByParentId( 2, titleList.size(), h.getHierarchy_id());
		    			for(Object[] objArray:sslist) {
			    			ssIds.add(Integer.parseInt(objArray[0].toString()));
			    		}
		    			for(Object[] objArray:sdolist) {
			    			ssIds.add(Integer.parseInt(objArray[0].toString()));
			    		}
	    			}//for
	    		}
   //------------------------------------
	    		List<Meter> metersList=meterService.listMetersByLocation(ssIds, 0); //0 to get all the meters
	    		List<Meter> unmapped=meterService.getUnmappedMeters();
	    		metersList.addAll(unmapped);	
	    		List<String> meters=new ArrayList();
	    		if(!metersList.isEmpty()){
	    			for(Meter mt:metersList){
	    				meters.add(mt.getMeter_sl_no());
	    			}
	    		}
	   System.out.println("meters for billing summary ------->"+meters); 		
//----------------------------------------------------------------------------------------------------------
 ArrayList<Auto_reset_bean> arrList = new com.rtdas.meterdata.daoimpl.AlertsAndDiagnosisDao().getAutoResetBillingsUPCL_new(selectedMonth,type,locTypeAccess,meters);
 int sno=1;
 if(arrList!=null){
//     sno=arrList.size();
 }
           for(int row=0;row<arrList.size();row++){
               bb = null;
               bb = (Auto_reset_bean)arrList.get(row);
%>
            <tr>
                <td><%=sno%></td>
             <%--   <td><%=bb.getCircle()%></td>
                <td><%=bb.getDivision()%></td>
                <td><%=bb.getSection()%></td>
                <td><%=bb.getConsumerName()%></td>  --%>              
<td><%=bb.getMeter_serial_no()==null?"" : bb.getMeter_serial_no()%></td>
 <% if(type.equals("all")){%> 
                  <td><%=bb.getMetertype()%></td>
                <%}%>
<td><%=bb.getCONSUMERNO()==null?"" : bb.getCONSUMERNO()%></td> 
<td><%=bb.getDatetime()==null?"" : bb.getDatetime().split(" ")[0]%></td> 

<td><%=bb.getC1KWH_I()==null?"" : bb.getC1KWH_I()%></td>
<td><%=bb.getC2KWH_I()==null?"" : bb.getC2KWH_I()%></td>
<td><%=bb.getC3KWH_I()==null?"" : bb.getC3KWH_I()%></td>
<td><%=bb.getC4KWH_I()==null?"" : bb.getC4KWH_I()%></td>
<td><%=bb.getC5KWH_I()==null?"" : bb.getC5KWH_I()%></td>
<td><%=bb.getC6KWH_I()==null?"" : bb.getC6KWH_I()%></td>
<td><%=bb.getC7KWH_I()==null?"" : bb.getC7KWH_I()%></td>
<td><%=bb.getC8KWH_I()==null?"" : bb.getC8KWH_I()%></td>

<td><%=bb.getC1KWH_E()==null?"" : bb.getC1KWH_E()%></td>
<td><%=bb.getC2KWH_E()==null?"" : bb.getC2KWH_E()%></td>
<td><%=bb.getC3KWH_E()==null?"" : bb.getC3KWH_E()%></td>
<td><%=bb.getC4KWH_E()==null?"" : bb.getC4KWH_E()%></td>
<td><%=bb.getC5KWH_E()==null?"" : bb.getC5KWH_E()%></td>
<td><%=bb.getC6KWH_E()==null?"" : bb.getC6KWH_E()%></td>
<td><%=bb.getC7KWH_E()==null?"" : bb.getC7KWH_E()%></td>
<td><%=bb.getC8KWH_E()==null?"" : bb.getC8KWH_E()%></td>

<td><%=bb.getC1KWH_ABS()==null?"" : bb.getC1KWH_ABS()%></td>
<td><%=bb.getC2KWH_ABS()==null?"" : bb.getC2KWH_ABS()%></td>
<td><%=bb.getC3KWH_ABS()==null?"" : bb.getC3KWH_ABS()%></td>
<td><%=bb.getC4KWH_ABS()==null?"" : bb.getC4KWH_ABS()%></td>
<td><%=bb.getC5KWH_ABS()==null?"" : bb.getC5KWH_ABS()%></td>
<td><%=bb.getC6KWH_ABS()==null?"" : bb.getC6KWH_ABS()%></td>
<td><%=bb.getC7KWH_ABS()==null?"" : bb.getC7KWH_ABS()%></td>
<td><%=bb.getC8KWH_ABS()==null?"" : bb.getC8KWH_ABS()%></td>

<td><%=bb.getC1KVAH_I()==null?"" : bb.getC1KVAH_I()%></td>
<td><%=bb.getC2KVAH_I()==null?"" : bb.getC2KVAH_I()%></td>
<td><%=bb.getC3KVAH_I()==null?"" : bb.getC3KVAH_I()%></td>
<td><%=bb.getC4KVAH_I()==null?"" : bb.getC4KVAH_I()%></td>
<td><%=bb.getC5KVAH_I()==null?"" : bb.getC5KVAH_I()%></td>
<td><%=bb.getC6KVAH_I()==null?"" : bb.getC6KVAH_I()%></td>
<td><%=bb.getC7KVAH_I()==null?"" : bb.getC7KVAH_I()%></td>
<td><%=bb.getC8KVAH_I()==null?"" : bb.getC8KVAH_I()%></td>

<td><%=bb.getC1KVAH_E()==null?"" : bb.getC1KVAH_E()%></td>
<td><%=bb.getC2KVAH_E()==null?"" : bb.getC2KVAH_E()%></td>
<td><%=bb.getC3KVAH_E()==null?"" : bb.getC3KVAH_E()%></td>
<td><%=bb.getC4KVAH_E()==null?"" : bb.getC4KVAH_E()%></td>
<td><%=bb.getC5KVAH_E()==null?"" : bb.getC5KVAH_E()%></td>
<td><%=bb.getC6KVAH_E()==null?"" : bb.getC6KVAH_E()%></td>
<td><%=bb.getC7KVAH_E()==null?"" : bb.getC7KVAH_E()%></td>
<td><%=bb.getC8KVAH_E()==null?"" : bb.getC8KVAH_E()%></td>

<td><%=bb.getC1KVAH_ABS()==null?"" : bb.getC1KVAH_ABS()%></td>
<td><%=bb.getC2KVAH_ABS()==null?"" : bb.getC2KVAH_ABS()%></td>
<td><%=bb.getC3KVAH_ABS()==null?"" : bb.getC3KVAH_ABS()%></td>
<td><%=bb.getC4KVAH_ABS()==null?"" : bb.getC4KVAH_ABS()%></td>
<td><%=bb.getC5KVAH_ABS()==null?"" : bb.getC5KVAH_ABS()%></td>
<td><%=bb.getC6KVAH_ABS()==null?"" : bb.getC6KVAH_ABS()%></td>
<td><%=bb.getC7KVAH_ABS()==null?"" : bb.getC7KVAH_ABS()%></td>
<td><%=bb.getC8KVAH_ABS()==null?"" : bb.getC8KVAH_ABS()%></td>

<td><%=bb.getCMDKW_I()==null?"" : bb.getCMDKW_I()%></td>
<td><%=bb.getCMDKW_E()==null?"" : bb.getCMDKW_E()%></td>
<td><%=bb.getCMDKW_ABS()==null?"" : bb.getCMDKW_ABS()%></td>

<td><%=bb.getCMDKVA_I()==null?"" : bb.getCMDKVA_I()%></td>
<td><%=bb.getCMDKVA_E()==null?"" : bb.getCMDKVA_E()%></td>
<td><%=bb.getCMDKVA_ABS()==null?"" : bb.getCMDKVA_ABS()%></td>

<td><%=bb.getMDKW_I()==null?"" : bb.getMDKW_I()%></td>
<td><%=bb.getMDKW_E()==null?"" : bb.getMDKW_E()%></td>
<td><%=bb.getMDKW_ABS()==null?"" : bb.getMDKW_ABS()%></td>

<td><%=bb.getMDKVA_I()==null?"" : bb.getMDKVA_I()%></td>
<td><%=bb.getMDKVA_E()==null?"" : bb.getMDKVA_E()%></td>
<td><%=bb.getMDKVA_ABS()==null?"" : bb.getMDKVA_ABS()%></td>

<td><%=bb.getKWH_I()==null?"" : bb.getKWH_I()%></td>
<td><%=bb.getKWH_E()==null?"" : bb.getKWH_E()%></td>
<td><%=bb.getKWH_ABS()==null?"" : bb.getKWH_ABS()%></td>

<td><%=bb.getKVAH_I()==null?"" : bb.getKVAH_I()%></td>
<td><%=bb.getKVAH_E()==null?"" : bb.getKVAH_E()%></td>
<td><%=bb.getKVAH_ABS()==null?"" : bb.getKVAH_ABS()%></td>

<td><%=bb.getPF()==null?"" : bb.getPF()%></td>

<td><%=bb.getCMDKW_I_CURRENT()==null?"" : bb.getCMDKW_I_CURRENT()%></td>
<td><%=bb.getCMDKW_E_CURRENT()==null?"" : bb.getCMDKW_E_CURRENT()%></td>
<td><%=bb.getCMDKW_ABS_CURRENT()==null?"" : bb.getCMDKW_ABS_CURRENT()%></td>

<td><%=bb.getCMDKVA_I_CURRENT()==null?"" : bb.getCMDKVA_I_CURRENT()%></td>
<td><%=bb.getCMDKVA_E_CURRENT()==null?"" : bb.getCMDKVA_E_CURRENT()%></td>
<td><%=bb.getCMDKVA_ABS_CURRENT()==null?"" : bb.getCMDKVA_ABS_CURRENT()%></td>

                
            </tr>
<% sno++;}%>
        </tbody>
        
    </table>
        </div>
