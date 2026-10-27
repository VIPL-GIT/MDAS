<%@page  isErrorPage="true" %>
<html>
    <head>

   
    
    </head>
    <body id="main">
        <%
        String errorMessage=request.getParameter("message");
        System.out.println("errorMessage-------->"+errorMessage);
        %>
<h4 style="display: block;color: #D20009;text-indent: 40px;"> <%= errorMessage %> </h4>
    </body>
</html>