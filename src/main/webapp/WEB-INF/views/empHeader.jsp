<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<head>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" />
<link rel="stylesheet" type="text/css" href="<c:url value='/resources/navigation.css' />" />
</head>
<body>
<div class="navbar" id="main">
  <a href="#" onclick="openNav()">&#9776;</a>
  <div style="margin-left:42%">
      <a href="#"><strong>Welcome ${employee.name}</strong></a>
  </div>
  <div style="float:right">
      <a href="/hrms/user/logout">Sign Out</a>
  </div>
</div>

<div id="mySidenav" class="sidenav">
      <a href="javascript:void(0)" class="closebtn" onclick="closeNav()">&times;</a>
      <a class="cta" href="/hrms/employee/viewProfile"><img src="<c:url value='/resources/img/user.png' />"><span>My Profile</span></a>
      <a class="cta" href="/hrms/employee/empProjects"><img src="<c:url value='/resources/img/project.png' />"><span>Projects</span></a>
      <a class="cta" href="/hrms/employee/empTimesheet"><img src="<c:url value='/resources/img/timesheet.png' />"><span>Time Sheet</span></a>
      <a class="cta" href="/hrms/employee/empAttendance"><img src="<c:url value='/resources/img/attendance.svg' />"><span>Attendance</span></a>
</div>
</body>
</html>