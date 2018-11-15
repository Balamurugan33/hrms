<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<head>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" />
<link rel="stylesheet" type="text/css" href="<c:url value='/resources/navigation.css' />" />
</head>
<body>
<div class="navbar" id="main">
  <a href="#" onclick="openNav()">&#9776;</a>
  <div style="margin-left:40%">
      <a href="#" >Welcome ${employee.name}</a>
  </div>
  <div style="float:right">
      <a href="/hrms/user/logout">Sign Out</a>
  </div>
</div>

<div id="mySidenav" class="sidenav">
      <a href="javascript:void(0)" class="closebtn" onclick="closeNav()">&times;</a>
      <a href="/hrms/employee/viewProfile">My Profile</a>
      <a href="/hrms/employee/empProjects">Projects</a>
      <a href="/hrms/employee/empTasks">Time Sheet</a>
      <a href="/hrms/employee/empAttendance">Attendance</a>
</div>
</body>
</html>