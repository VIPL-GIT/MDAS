<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:forEach items="${rflist}" var="gw">      
        <option value="${gw.endnode_mac_address}">${gw.endnode_mac_address}</option>        
</c:forEach> 