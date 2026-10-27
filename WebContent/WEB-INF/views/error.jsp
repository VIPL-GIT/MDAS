    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<!-- <meta http-equiv="refresh" content="5;url=login.epx" />  -->
<title>Error...</title>
</head>
<body>

<div class="alert alert-danger col-md-offset-3 col-md-6">
<strong>Error :</strong> ${exception }  <br>
<strong>Exception Stack Trace :</strong>
<c:forEach items="${exception.stackTrace}" var="ste">
	${ste}
</c:forEach>
</div>

</body>
</html>