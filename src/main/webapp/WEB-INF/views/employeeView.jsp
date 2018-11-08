<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<body>
<jsp:include page='empHeader.jsp'/>

<table style="float:center">
    <tr>
        <td>Employee ID:</td> 
        <td>${employee.employeeId}</td>  
    </tr>
    <tr>
        <td>Name:</td> 
        <td>${name}</td>  
    </tr>
    <tr>
        <td>MobileNo:</td>
        <td>${employee.mobileNo}</td>
    </tr>
    <tr>  
        <td>Mail:</td>
        <td>${employee.mailId}</td>
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

<div id="taskInfo" style="display:none">
    <table>
    <tr>
        <th>Project</th>
        <th>Task</th>
        <th>Start Time</th>
        <th>End Time</th>
    </tr>
    <c:forEach var= "projectTask" items= "${employee.projectTask}">
    <tr>
        <td>${projectTask.project.name}</td>
        <td>${projectTask.name}</td>
        <td>${projectTask.startTime}</td>
        <td>${projectTask.endTime}</td>
    </tr>
    </c:forEach>
    </table>
</div>
    
<c:if test="&{not empty projects}">
    <div id=projectInfo>
    <table>
    <tr>
        <th>Project</th>
        <th>Client</th>
    </tr>
    <c:forEach var= "project" items= "${projects}">
    <tr>
        <td>${project.name}</td>
        <td>${project.clint}</td>
    </tr>
    </c:forEach>
    </table>
    </div>
</c:if>
    
<div id=attendanceInfo style="display:none">
    <table align="center">
    <tr>
        <th>Date</th>
        <th>Attendance</th>
    </tr>
    <c:forEach(var="attendance" items="${employee.attendance}")>
    <tr>
        <td>${attendance.date}</td>
        <c:if test="${attendance.status} == 1">
        <td>Present</td>
        </c:if>
        <c:if test="${attendance.status} == 0">
        <td>Absent</td>
        </c:if>
    </tr>
    </c:forEach>
    </table>
</div>
</body>

<c:if test="${not empty message}">
<script>
    alert ("${message}");
</script>
</c:if>

</html>
