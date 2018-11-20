<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page isErrorPage="true"%>
<html>
<body>
<title>Error Page</title>
<% if(response.getStatus() == 500){ %>
<div align="center">
   <h3 style="color:red;margin:30px"><strong>Oops!!!.. Something Wrong..!!! Please Try Again Later..!</strong></h3> 
   <a href="/hrms">Go to Home Page</a>
</div>  
<%}else if(response.getStatus() == 404) {%>
<div align="center">
   <img src="<c:url value = '/resources/img/404.jpg' />" width="600px" height="400px"> <br>
   <a href="/hrms">Go to Home Page</a>
</div>
<%} else {%>
<div align="center">
   <img src="<c:url value = '/resources/img/405.png' />" width="600px" height="400px"> <br>
   <a href="/hrms">Go to Login Page</a>
</div>
<%} %>
</body>
</html>
