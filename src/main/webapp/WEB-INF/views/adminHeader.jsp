<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<head>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" />
<link rel="stylesheet" type="text/css" href="<c:url value='/resources/adminnav.css' />" />
</head>
<body>

<nav class="navbar navbar-default">
  <div class="navbar-header">
      <a class="navbar-brand" href="#"><img src="<c:url value='/resources/img/IDEAS2IT.png' />"></a>
 </div>
  <div class="container-fluid">
    <h3 align ="center" class="navbar-text">IDEA HRMS</h3>
    <ul class="nav navbar-nav navbar-right">
      <li><a href="/hrms/user/logout"><span class="glyphicon glyphicon-log-out"></span> Logout</a></li>
    </ul>
  </div>
</nav>

<div class="sidenav">
	  
	  
	  <a href="/hrms/employee/displayEmployee"><img class="img" title="View Employees" src="<c:url value='/resources/img/employees.png' />"></a>
	  <a href="/hrms/project/displayAll"><img class="img" title="View Projects" src="<c:url value='/resources/img/projects.png' />"></a>
	  <a href="/hrms/client/displayClient"><img class="img" title="View Clients" src="<c:url value='/resources/img/clients.png' />"></a>
	  <a href="/hrms/designation/displayDesignation"><img class="img" title="View Designations" src="<c:url value='/resources/img/designations.png' />"></a>
</div>
</body>
</html> 
