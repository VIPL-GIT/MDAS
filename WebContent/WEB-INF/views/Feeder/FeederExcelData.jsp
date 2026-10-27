<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<% System.out.println("feeder excel data"); %>
<c:if test="${not empty data}">
        <table  width="70%"  id="exceldata" class="table">
            <c:forEach items="${data}" var="row" varStatus="loop">
            <c:choose>
            <c:when test="${loop.index==0 }">
            <thead>
            <tr class="fdata">
             <c:forEach items="${row.value}" var="cell" >
                        <th style="border:1px solid black;height:20px;width:100px;
                        background-color:${cell.bgColor};color:${cell.textColor};
                        font-weight:${cell.textWeight};font-size:${cell.textSize}pt;">
                        ${cell.content}
                        </th>
                    </c:forEach>
                    <th style='border:1px solid black;'>Upload Status</th>
                    </tr>
            </thead>
             <tbody>
            </c:when>
            <c:otherwise>
            
              <tr>
                <c:forEach items="${row.value}" var="cell" >
                        <td style="border:1px solid black;height:20px;width:100px;
                        background-color:${cell.bgColor};color:${cell.textColor};
                        font-weight:${cell.textWeight};font-size:${cell.textSize}pt;">
                        ${cell.content}
                        </td>
                    </c:forEach>
                    <td style='border:1px solid black;' id="td_${loop.index}"><%="" %></td>
              </tr>
            
            </c:otherwise>
            </c:choose>
                
            </c:forEach>
             </tbody>
        </table>
    </c:if>
    
  <script>  
  document.getElementById("process").style.display='${status eq "valid"?"block":"none"}';
  </script>  
