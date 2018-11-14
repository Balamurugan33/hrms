<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script
    src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
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
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
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
         <td>Salary</td>
         <td>${employee.salary}</td>
    </tr>
   </table>        
   <br>
   <br>
   <a href="#" onclick="openUpdate()"><button type="submit" class="btn btn-success">Edit Profile</button></a>                  
 </div>
</c:if>

<c:if test="${not empty timeSheets}">
 <div id="taskInfo" align="center">
     <button type="button" class="btn btn-outline-success btn-lg pull-right" 
       data-toggle="modal" data-target="#TimeSheetCreate">Add entry</button>
    <br>
    <br>
    <table class="table table-striped text-center">
    <tr>
        <th class="text-center">Date</th>
        <th class="text-center">Project</th>
        <th class="text-center">Worked Hours</th>
    </tr>
    <c:forEach var= "timeSheet" items= "${timeSheets}">
    <tr>
        <td>${timeSheet.entryDate}</td>
        <td>${timeSheet.project.name}</td>
        <td>${timeSheet.workedHours}</td>
        <form method="post">
          <td>
            <button type="button" class="btn btn-success" data-toggle="modal" data-target="#${timeSheet.id}" >Update</button>
            <button type="submit" class="btn btn-danger" formaction="/hrms/projectTask/delete">Delete</button>
            <div class="modal fade" id="${timeSheet.id}">
            <div class="modal-dialog modal-sm">
            <div class="modal-content">
            <div class="modal-header">
              <h4>Update TimeSheet Entry</h4>
            </div>
            <div class="modal-body">
            <div class="form-group">
            <div class="input-group">
              <label>Date</label>
              <input type="text" class="form-control" name="entryDate" value= "${timeSheet.entryDate}" required="required">
            </div>
            </div>
            <div class="modal-body">
            <div class="form-group">
            <div class="input-group">
              <label>Project Name</label>
                <input type="hidden" name="projectId" value = "${timeSheet.project.id}">   
                <input type="text" class="form-control" name="projectName" value= "${timeSheet.project.name}" required="required">
            </div>
            </div>
            <div class="form-group">
              <label>Hours Worked</label>
              <input type="text" class="form-control" name="workedHours" value= "${timeSheet.workedHours}" required="required">
            </div>
            <div class="form-group">
              <button type="submit" class="btn btn-primary btn-block btn-lg" formaction="/hrms/projectTask/update">Update</button>
              <button type="button" class="btn btn-danger btn-block btn-lg" data-dismiss="modal">Cancel</button>
              <input type="hidden" name="id" value = "${timeSheet.id}">            
            </div>
            </div>
            </div>
            </div>
            </div>
          </td>
        </form>
    </tr>
    </c:forEach>
    </table>
         <div class="modal fade" id="TimeSheetCreate" >
        <div class="modal-dialog modal-sm">
            <div class="modal-content">
                <div class="modal-header">
                    <h4>Add entry</h4>
                </div>
                <div class="modal-body">
                    <form action="create" method="post">
                        <div class="form-group">
                            <label>Date</label>
                            <div class="input-group">
                              <input type="date" class="form-control" name="date" value= "">
                            </div>
                        </div>
                        <div class="form-group">
                            <label>Project Name</label>
                            <div class="input-group">
                              <input type="text" class="form-control" name="projectName" value= "${timeSheet.project.name}" required="required">
                            </div>
                        </div>
                        <div class="form-group">
                                <label>Client Name</label>
                                <select class="form-control" name="clientId">
							        <c:forEach var="client" items="${allClients}">
							             <option value="${client.id}">${client.name}</option>
							        </c:forEach>
						        </select>
                        </div>
                        <div class="form-group">
                            <button type="submit" class="btn btn-primary btn-block btn-lg">Save</button>
                            <button type="button" class="btn btn-danger btn-block btn-lg" 
                                data-dismiss="modal">Cancel</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
     </div>
 </div>
</c:if>
    
<c:if test="${not empty projects}">
    <div id=projectInfo align="center">
    <table class="table table-striped text-center">
    <tr>
        <th class="text-center">Project</th>
        <th class="text-center">Client</th>
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
    <table class="table table-striped text-center">
    <tr>
        <th class="text-center">Date</th>
        <th class="text-center">Attendance</th>
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
    <div class="modal fade" id="Update" >
        <div class="modal-dialog modal-sm">
            <div class="modal-content">
                <div class="modal-header">
                    <h4>Update profile info</h4>
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
                            <input type="hidden" name="salary" value = "${employee.salary}">
                            <input type="hidden" name="hourlyRate" value = "${employee.hourlyRate}">
                        </div>
                    </form>
                </div>
            </div>
        </div>
     </div>


<div id="empRevenue" >
        <form method="post" action="/hrms/employee/profit">
        <div class="form-group">
            <div class="input-group">
                <input type="date" class="form-control" name="startDate" id="startDate"
                    placeholder="Start Date" required="required">
            </div>
            <div class="input-group">
                <input type="date" class="form-control" name="endDate" id="endDate"
                    placeholder="End Date" required="required">
            </div>
            <div class="input-group">
                <button type="submit">Show</button>
            </div>
         </div>
         <input type="hidden" id="empId" name="empId">
        </form>
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
</html>
