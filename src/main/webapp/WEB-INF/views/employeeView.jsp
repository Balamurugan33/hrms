<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script
    src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<jsp:include page='empHeader.jsp'/>
<body>

<c:if test="${not empty employeeDetail}">
  <div align="center">
  <table style="float:center" class="table">
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
         <td>Salary</td>
         <td>${employee.designation.salary}</td>
    </tr>
   </table>
 </div>
</c:if>

<c:if test="${not empty tasks}">
 <div id="taskInfo" align="center">
    <table class="table table-striped text-center">
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
    <table class="table table-striped text-center">
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
    <table class="table table-striped text-center">
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
    <div class="modal fade" id="Update" >
        <div class="modal-dialog modal-sm">
            <div class="modal-content">
                <div class="modal-header">
                    <h4>Enter Client Detail</h4>
                </div>
                <div class="modal-body">
                    <form action="/hrms/employee/updateEmployee" method="post">
                        <div class="form-group">
                            <div class="input-group">
                                <input type="text" class="form-control" name="name"
                                    value= "${employee.name}" placeholder="Name" pattern="[A-Za-z\s]+" maxlength=30 required="required">
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="input-group">
                                <input type="text" class="form-control" name="mobileNo"
                                   value= "${employee.mobileNo}" placeholder="Mobile Number" pattern="[6789]{1}[0-9]{9}" maxlength=10 required="required">
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="input-group">
                                <input type="text" class="form-control" name="emailId"
                                   value= "${employee.emailId}" placeholder="Email Id" required="required">
                            </div>
                        </div>
                        <div class="form-group">
                            <button type="submit" class="btn btn-primary btn-block btn-lg">Update</button>
                            <button type="button" class="btn btn-danger btn-block btn-lg" onclick="closeUpdate()">
                                Cancel</button>
                            <input type="hidden" name="id" value = "${employee.id}">
                        </div>
                    </form>
                </div>
            </div>
        </div>
     </div>

</body>

<c:if test="${not empty message}">
<script>
    alert ("${message}");
</script>
</c:if>
<script>
function openUpdate(event) {
    $("#Update").modal("show");
}

function closeUpdate(event) {
    $("#Update").modal("hide");
}
</script>
</html>
