<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<head>
    <style>
    .toggle {
      -webkit-appearance: none;
      -moz-appearance: none;
      appearance: none;
      width: 68px;
      height: 32px;
      display: inline-block;
      position: relative;
      border-radius: 50px;
      overflow: hidden;
      outline: none;
      border: none;
      cursor: pointer;
      background-color: #707070;
      transition: background-color ease 0.3s;
    }

    .toggle:before {
      content: "In Out";
      display: block;
      position: absolute;
      z-index: 2;
      width: 28px;
      height: 28px;
      background: #fff;
      left: 2px;
      top: 2px;
      border-radius: 50%;
      font: 10px/28px Helvetica;
      text-transform: uppercase;
      font-weight: bold;
      text-indent: -22px;
      word-spacing: 37px;
      color: #fff;
      text-shadow: -1px -1px rgba(0,0,0,0.15);
      white-space: nowrap;
      box-shadow: 0 1px 2px rgba(0,0,0,0.2);
      transition: all cubic-bezier(0.3, 1.5, 0.7, 1) 0.3s;
    }

    .toggle:checked {
      background-color: #4CD964;
    }

    .toggle:checked:before {
      left: 42px;
    }
    </style>
</head>
<jsp:include page='empHeader.jsp'/>
<body>

<c:if test="${not empty employeeDetail}">
 <div align="center">
  <table style="float:center">
    <tr>
        <td>Employee ID:</td> 
        <td>${employee.id}</td>  
    </tr>
    <tr>
        <td>Name:</td> 
        <td>${employee.name}</td>  
    </tr>
    <tr>
        <td>MobileNo:</td>
        <td>${employee.mobileNo}</td>
    </tr>
    <tr>  
        <td>Mail:</td>
        <td>${employee.emailId}</td>
    </tr>
    <tr>
         <td>Designation</td>
         <td>${employee.designation.name}</td>
    </tr>
    <tr>
         <td>Designation</td>
         <td>${employee.designation.salary}</td>
    </tr>
   </table>
 </div>
</c:if>

<c:if test="${not empty tasks}">
 <div id="taskInfo" align="center">
    <table>
    <tr>
        <th>Project</th>
        <th>Task</th>
        <th>Start Time</th>
        <th>End Time</th>
    </tr>
    <c:forEach var= "projectTask" items= "${tasks}">
    <tr>
        <td>${projectTask.project.name}</td>
        <td>${projectTask.name}</td>
        <td>${projectTask.startTime}</td>
        <td>${projectTask.endTime}</td>
    </tr>
    </c:forEach>
    </table>
 </div>
</c:if>
    
<c:if test="${not empty projects}">
    <div id=projectInfo align="center">
    <table>
    <tr>
        <th>Project</th>
        <th>Client</th>
    </tr>
    <c:forEach var= "project" items= "${projects}">
    <tr>
        <td>${project.name}</td>
        <td>${project.client.name}</td>
    </tr>
    </c:forEach>
    </table>
    </div>
</c:if>
    
<c:if test="${not empty attendance}">
 <div id=attendanceInfo align="center">
  <c:choose>
    <c:when test="${isChecked=='true'}">
      <input class="toggle" type="checkbox" name="checkAttendance" checked="checked" onclick="checkAttendance(this)" />
    </c:when>    
    <c:when test="${isChecked=='false'}">
      <input class="toggle" type="checkbox" name="checkAttendance" onclick="checkAttendance(this)" />
    </c:when>  
    <c:otherwise>
      <input class="toggle" type="checkbox" name="checkAttendance" onclick="checkAttendance(this)" />
    </c:otherwise>
  </c:choose>
    <br>
    <br>
    <table>
    <tr>
        <th>Date</th>
        <th>Attendance</th>
    </tr>
    <c:forEach var="attendance" items="${attendance}">
    <tr>
        <td>${attendance.attendDate}</td>
        <c:if test="${attendance.status == true}">
        <td>Present</td>
        </c:if>
        <c:if test="${attendance.status == false}">
        <td>Absent</td>
        </c:if>
    </tr>
    </c:forEach>
    </table>
 </div>
</c:if>
</body>

<c:if test="${not empty message}">
<script>
    alert ("${message}");
</script>
</c:if>
<footer>
  <script>
    function checkAttendance(checkbox)
    {
      if (checkbox.checked)
      {        
          window.location = "/hrms/employee/markPresent";
      }
      else {
          window.location = "/hrms/employee/markAbsent";    
      }
    }
  </script>
</footer>
</html>
