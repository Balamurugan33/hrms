<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<head/>
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
    <table>
    <tr>
        <th>Date</th>
        <th>Attendance</th>
    </tr>
    <c:forEach var="attendance" items="${attendance}">
    <tr>
        <td>${attendance.attendDate}</td>
        <c:if test="${attendance.status}">
        <td>Present</td>
        </c:if>
        <c:if test="! ${attendance.status}">
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

</html>
