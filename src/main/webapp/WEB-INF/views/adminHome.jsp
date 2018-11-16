<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<head>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <link rel="stylesheet" type="text/css" href="<c:url value='/resources/adminHome.css' />" />
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <jsp:include page='adminHeader.jsp'/>
<style>

.pulse:hover,
.pulse:focus {
          animation: pulse 1s;
  box-shadow: 0 0 0 2em rgba(255, 255, 255, 0);
}


@keyframes pulse {
  0% {
    box-shadow: 0 0 0 0 var(--hover);
  }
}

.pulse {
  --color: #ef6eae;
  --hover: #ef8f6e;
}

</style>
<script>
  
window.onload = function () {

var chart = new CanvasJS.Chart("chartContainer", {
    animationEnabled: true,
    theme: "red",
    title:{
        text: "Simple Line Chart"
    },
    axisY:{
        includeZero: false
    },
    
    data: [{        
        type: "column",          
        dataPoints: [
        	{ y: name }
        ]
    }]
});
chart.render();

}
</script>
</head>
<body style="margin-left: 78px;">
<c:if test="${not empty clients}">
    <div>
        <center><button type="button" data-toggle="modal" data-target="#ClientCreate" class="btn btn-success btn-lg pulse">Add Client</button></center>
        <table class="table table-striped table-dark text-center">
        <tr>
	        <th class="text-center">Name</th>
	        <th class="text-center">Mobile No</th>
	        <th class="text-center">Email Id</th>
	        <th class="text-center">Action</th>
        </tr>
        <c:forEach var="client" items="${clients}">
        <tr>
            <td>${client.name}</td>
            <td>${client.mobileNo}</td>
            <td>${client.emailId}</td>
            <form method="post">
            <td><button type="button" class="btn btn-success" data-toggle="modal" data-target="#${client.id}" >Update</button>
                <button type="button" class="btn btn-success" data-toggle="modal" data-target="#${client.mobileNo}" >View Net Profit</button>
                <button type="submit" class="btn btn-danger" 
                    formaction="/hrms/client/deleteClient">Delete</button>
     <div class="modal fade" id="${client.id}" >
        <div class="modal-dialog modal-sm">
            <div class="modal-content">
                <div class="modal-header">
                    <h4>Enter Client Detail</h4>
                </div>
                <div class="modal-body">
                        <div class="form-group">
                                <input type="text" class="form-control tt" name="name"
                                    value= "${client.name}" placeholder="Client Name" required="required">
                        </div>
                        <div class="form-group">
                                <input type="text" class="form-control tt" name="mobileNo"
                                   value= "${client.mobileNo}" placeholder="Mobile Number" required="required">
                        </div>
                        <div class="form-group">
                                <input type="text" class="form-control tt" name="emailId"
                                   value= "${client.emailId}" placeholder="Email Id" required="required">
                        </div>
                 <div class="modal-footer">
                        <div class="form-group">
                            <button type="submit" class="btn btn-primary btn-block btn-lg" 
                                formaction="/hrms/client/updateClient">Update</button>
                            <button type="button" class="btn btn-danger btn-block btn-lg" 
                                    data-dismiss="modal">Cancel</button>
                            <input type="hidden" name="id" value = "${client.id}">
                        </div>
                </div>
                </div>
            </div>
        </div>
     </div></td>
            </form>
        </tr>
        <tr><td>
        <div class="modal fade" id="${client.mobileNo}" >
           <div class="modal-dialog modal-sm">
               <div class="modal-content">
                   <div class="modal-header">
                       <h4>Enter The Dates</h4>
                   </div>
                   <form method="post" action="/hrms/client/netProfit">
                   <div class="modal-body">
                      <div class="form-group">
                              <label>Start Date</label>
                              <input type="date" class="form-control" name="startDate"
                                  required="required" max="<%= java.time.LocalDate.now() %>" >
                          </div>
                          <div class="form-group">
                              <label>End Date</label>
                              <input type="date" class="form-control" name="endDate"
                                  required="required" max="<%= java.time.LocalDate.now() %>" >
                          </div>
                       <input type="hidden" name="clientId" value="${client.id}">
                   </div>
                   <div class="modal-footer">
                       <div class="form-group">
                              <button type="submit" class="btn btn-primary btn-block btn-lg">Show</button>
                              <button type="button" class="btn btn-danger  btn-block btn-lg" data-dismiss="modal">Close</button>
                       </div>
                   </div>
                   </form>
               </div>
           </div>
        </div></td>
        </tr>
        </c:forEach>
        </table>
    </div>
</c:if>

<c:if test="${not empty designations}">
    <table class="table text-center">
    <tr><td>
    <div class="dsn-right">
        <h3>Designations</h3>
        <div class="scroll">
        <table class="table table-striped text-center ">
        <tr>
            <th class="text-center">Name</th>
            <th class="text-center">Action</th>
        </tr>
        <c:forEach var="designation" items="${designations}">
        <tr>
            <td>${designation.name}</td>
        <form method="post">
            <td><button type="button" class="btn btn-success" data-toggle="modal" data-target="#${designation.id}" >Update</button>
     <div class="modal fade" id="${designation.id}" >
        <div class="modal-dialog modal-sm">
            <div class="modal-content">
                <div class="modal-header">
                    <h4>Enter Designation Detail</h4>
                </div>
                <div class="modal-body">
                        <div class="form-group">
                                <input type="text" class="form-control" name="name"
                                    value= "${designation.name}" placeholder="Name" required="required">
                        </div>
                        <div class="form-group">
                            <button type="submit" class="btn btn-primary btn-block btn-lg" 
                                formaction="/hrms/designation/updateDesignation">Update</button>
                            <button type="button" class="btn btn-danger btn-block btn-lg" 
                                data-dismiss="modal">Cancel</button>
                            <input type="hidden" name="id" value = "${designation.id}">
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
        </div>
    </div></td>
    <td>
    <div class="dsn-left">
        <header>
        <h3>Enter The New Designation</h3>
        </header>
        <form action="hrms/client/createClient" method="post">
        <div class="form-group">
            <input type="text" class="form-control" name="name"
                placeholder="Designation Name" required="required">
       </div>
        <footer>
        <div class="form-group">
            <button type="submit" class="btn btn-primary btn-block btn-lg" 
                formaction="/hrms/designation/createDesignation">Save</button>
            <button type="button" class="btn btn-danger btn-block btn-lg" 
                data-dismiss="modal">Cancel</button>
        </div>
        </form>
        </footer>
    </div>
    <td></tr>
    </table>
    
</c:if>

<c:if test="${not empty projects}">
    <div>
        <button type="button" class="btn btn-outline-success btn-lg pull-right" 
            data-toggle="modal" data-target="#ProjectCreate">Add Project</button>
        <table class="table table-striped text-center">
        <tr>
            <th class="text-center">Project</th>
            <th class="text-center">Client</th>
            <th class="text-center">Action</th>
        </tr>
        <c:forEach var="project" items="${projects}">
        <tr>
            <td>${project.name}</td>
            <td>${project.client.name}</td>
        <form method="post">
            <td><button type="button" class="btn btn-success" data-toggle="modal" data-target="#${project.id}a" >View Net Profit</button>
            <button type="button" class="btn btn-success" data-toggle="modal" data-target="#${project.id}" >Update</button>
                <button type="submit" class="btn btn-danger" 
                    formaction="delete">Delete</button>
     <div class="modal fade" id="${project.id}" >
        <div class="modal-dialog modal-sm">
            <div class="modal-content">
                <div class="modal-header">
                    <h4>Enter Project Detail</h4>
                </div>
                <div class="modal-body">
                        <div class="form-group">
                                <label>Project Name</label>
                                <input type="text" class="form-control" name="name"
                                    value= "${project.name}" required="required">
                        </div>
                        <div class="form-group">
                            <label>Client Name</label>
                                <input type="text" class="form-control" name="name"
                                    value= "${project.client.name}" required="required">
                        </div>
                        <div class="modal-footer">
                        <div class="form-group">
                            <button type="submit" class="btn btn-primary btn-block btn-lg" 
                                formaction="update">Update</button>
                            <button type="button" class="btn btn-danger btn-block btn-lg" 
                                    data-dismiss="modal">Cancel</button>
                            <input type="hidden" name="id" value = "${project.id}">
                        </div>
                        </div>
                </div>
            </div>
        </div>
     </div></td>
            </form>
        
        <td>
        <div class="modal fade" id="${project.id}a" >31px
                 <div class="modal-dialog modal-sm">
                     <div class="modal-content">
                         <div class="modal-header">
                             <h4>Enter The Dates</h4>
                         </div>
                         <form method="post" action="netProfit">
                         <div class="modal-body">
                            <div class="form-group">
                                    <label>Start Date</label>
                                    <input type="date" class="form-control" name="startDate"
                                        required="required" max="<%= java.time.LocalDate.now() %>" >
                            </div>
                            <div class="form-group">
                                    <label>End Date</label>
                                    <input type="date" class="form-control" name="endDate"
                                        required="required" max="<%= java.time.LocalDate.now() %>" >
                            </div>
                             <input type="hidden" name="projectId" value="${project.id}">
                             <input type="hidden" name="projectClientId" value="${project.client.id}">                             
                         </div>
                         <div class="modal-footer">
                             <div class="form-group">
                                    <button type="submit" class="btn btn-primary btn-block btn-lg">Show</button>
                                    <button type="button" class="btn btn-danger btn-block btn-lg" data-dismiss="modal">Close</button>
                             </div>
                         </div>
                         </form>
                     </div>
                 </div>
              </div>
              </td>
        </tr>
        
        </c:forEach>
        </table>
    </div>
</c:if>

<c:if test="${not empty employees}">
    <div>
        <form method="get">
        <button type="button" class="btn btn-outline-success btn-lg" 
            data-toggle="modal" data-target="#EmployeeCreate">Add Employee</button>
        </form>
        <table class="table table-striped text-center">
        <tr>
            <th class="text-center">Name</th>
            <th class="text-center">Mobile No</th>
            <th class="text-center">Email Id</th>
            <th class="text-center">Designation</th>
            <th class="text-center">Salary</th>
            <th class="text-center">Action</th>
        </tr>
        <c:forEach var="employee" items="${employees}">
        <tr>
            <td>${employee.name}</td>
            <td>${employee.mobileNo}</td>
            <td>${employee.emailId}</td>
            <td>${employee.designation.name}</td>
            <td>${employee.salary}</td>
            <td><div class="actiondropdown">
				  <button class="btn btn-success">Select Action</button>
				  <div class="dropdown-content">
						     <form action="/hrms/employee/deleteEmployee" method="post">
							    
		                        <a href="#" data-toggle="modal" data-target="#${employee.id}">
		                         <img class="img" title="Put Increment" src="<c:url value = '/resources/img/salary.svg' />"></a>
		                        <a href="#" data-toggle="modal" data-target="#${employee.mobileNo}">
		                        <img class="img" title="View Employee Revenue" src="<c:url value = '/resources/img/revenue.svg' />"></a>
		                        <button type="submit"><img class="img" title="Delete Employee" src="<c:url value='/resources/img/deleteEmp.svg'/>"></button>
		                        <input type="hidden" name="id" value="${employee.id}">
		                    </form>
						    <div class="dropdown">
						    <a href="#" class="dropdown-toggle"  data-toggle="dropdown">
                                    <img class="img" title="Assign Project" src="<c:url value='/resources/img/project.svg' />">
                                    <span class="caret"></span></a>
						    <ul class="dropdown-menu dropdown-menu-left">
						      <c:forEach var ="project" items="${allProjects}">
						      <c:if test="${!employee.projects.contains(project)}">
						      <form action="/hrms/employee/assignProject" method="post">
						      <li><button type="submit">${project.name}</button></li>
						      <input type="hidden" name="projectId" value="${project.id}">
						      <input type="hidden" name="emailId" value="${employee.emailId}">
						      </form>
						      </c:if>
						      </c:forEach>
						    </ul>
						  </div>
				  </div>
				</div>
            </td>
        </tr>
        <tr> 
             <td><div class="modal fade" id="${employee.id}" >
                 <div class="modal-dialog modal-sm">
                     <div class="modal-content">
                         <div class="modal-header">
                             <button type="button" class="close btn-danger" data-dismiss="modal">&times;</button>
                             <h4>Enter Increment Salary & Hourly Rate</h4>
                         </div>
                         <form action="/hrms/employee/increment" method="post">
                         <div class="modal-body">
                                 <div class="form-group">
                                     <label>Salary</label>
                                         <input type="text" class="form-control" name="salary"
                                             value="${employee.salary}" required="required">
                                 </div>
                                 <div class="form-group">
                                     <label>Hourly Rate</label>
                                         <input type="text" class="form-control" name="hourlyRate"
                                            value="${employee.hourlyRate}" required="required">
                                 </div>
                                 <input type="hidden" name="emailId" value="${employee.emailId}">
                                 <input type="hidden" name="updateDate" value="<%= java.time.LocalDate.now() %>">
                         </div>
                         <div class="modal-footer">
	                         <div class="form-group">
                                  <button type="submit" class="btn btn-primary btn-block btn-lg" >Increment</button>
                                  <button type="button" class="btn btn-danger btn-block btn-lg" 
                                      data-dismiss="modal">Cancel</button>
                             </div>
                         </div>
                         </form>
                         </div>
                     </div>
                 </div>
              </td>
              
              <td>
              <div class="modal fade" id="${employee.mobileNo}" >
                 <div class="modal-dialog modal-sm">
                     <div class="modal-content">
                         <div class="modal-header">
                             <h4>Enter Dates</h4>
                         </div>
                         <c:if test="${fn:length(employee.attendance) != 0}">
                         <form method="post" action="/hrms/employee/profit">
                         <div class="modal-body">
					        <div class="form-group">
				                <label>Start Date</label>
				                <input type="date" class="form-control" name="startDate"
				                    required="required" min="${employee.attendance[0].attendDate}" 
				                    max="${employee.attendance[fn:length(employee.attendance)-1].attendDate}" >
					        </div>
				            <div class="form-group">
				                <label>End Date</label>
				                <input type="date" class="form-control" name="endDate"
				                    required="required" min="${employee.attendance[0].attendDate}" 
                                       max="${employee.attendance[fn:length(employee.attendance)-1].attendDate}" >
				            </div>
					         <input type="hidden" name="empEmailId" value="${employee.emailId}">
                         </div>
                         <div class="modal-footer">
                             <div class="form-group">
                                  <button type="submit" class="btn btn-primary btn-block btn-lg">Show</button>
                                  <button type="button" class="btn btn-danger btn-block btn-lg" data-dismiss="modal">Close</button>
                             </div>
                         </div>
                         </form>
                         </c:if>
                         <c:if test="${fn:length(employee.attendance) == 0}">
                         <div class="modal-body">
                               <label>No Entries</label>
                         </div>
                         <div class="modal-footer">
                             <button type="button" class="btn btn-danger btn-block btn-lg" data-dismiss="modal">Close</button>
                         </div>
                         </c:if>
                     </div>
                 </div>
              </div>
              </td>
          </tr>
              
              
        </c:forEach>
        </table>
        
    </div>
</c:if>
    
    <div class="modal fade" id="ClientCreate" >
        <div class="modal-dialog modal-sm">
            <div class="modal-content">
                <div class="modal-header text-center">
                    <h4>Enter Client Detail</h4>
                </div>
                <div class="modal-body text-center">
                    <form action="hrms/client/createClient" method="post">
                        <div class="form-group">
                                <input type="text" class="form-control" name="name"
                                    placeholder="Client Name" required="required">
                        </div>
                        <div class="form-group">
                                <input type="text" class="form-control" name="mobileNo"
                                   placeholder="Mobile Number" required="required">
                        </div>
                        <div class="form-group">
                                <input type="text" class="form-control" name="emailId"
                                   placeholder="Email Id" required="required">
                        </div>
                        <div class="form-group">
                            <button type="submit" class="btn btn-primary btn-block btn-lg" 
                                formaction="/hrms/client/createClient">Save</button>
                            <button type="button" class="btn btn-danger btn-block btn-lg" 
                                data-dismiss="modal">Cancel</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
     </div>
     
     <div class="modal fade" id="ProjectCreate" >
        <div class="modal-dialog modal-sm">
            <div class="modal-content">
                <div class="modal-header">
                    <h4>Enter Project Detail</h4>
                </div>
                <div class="modal-body">
                    <form action="create" method="post">
                        <div class="form-group">
                            <label>Project Name</label>
                                <input type="text" class="form-control" name="name"
                                    required="required">
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
     
     <div class="modal fade" id="EmployeeCreate">
        <div class="modal-dialog modal-sm">
            <div class="modal-content">
                <div class="modal-header">
                    <h4>Enter Employee Detail</h4>
                </div>
                <div class="modal-body">
                    <form action="/hrms/employee/createEmployee" method="post">
                        <div class="form-group">
                            <label>Employee Name</label>
                                <input type="text" class="form-control" name="name"
                                    required="required">
                        </div>
                        <div class="form-group">
                            <label>Mobile Number</label>
                                <input type="text" class="form-control" name="mobileNo"
                                    required="required">
                        </div>
                        <div class="form-group">
                            <label>Email id</label>
                                <input type="text" class="form-control" name="emailId"
                                    required="required">
                        </div>
                        <div class="form-group">
                                <label>Designation</label>
                                <select class="form-control" name="designationId">
                                    <c:forEach var="designation" items="${allDesignation}">
                                         <option value="${designation.id}">${designation.name}</option>
                                    </c:forEach>
                                </select>
                        </div>
                        <div class="form-group">
                            <label>salary</label>
                                <input type="text" class="form-control" name="salary"
                                    required="required">
                        </div>
                        <div class="form-group">
                            <label>Hourly Rate</label>
                                <input type="text" class="form-control" name="hourlyRate"
                                    required="required">
                        </div>
                        <input type="hidden" name="updateDate" value="<%= java.time.LocalDate.now() %>">
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
     
  <c:if test="${not empty Profit}">
     <div class="revenue">
        <table class="table table-striped text-center">
            <tr>
               <th class="text-center">Cost To Company</th>
	           <th class="text-center">Company Revenue</th>
	           <th class="text-center">Net Profit</th>
            </tr>
            <tr>
                <td>${CostToCompany}</td>
                <td>${BilAmount}</td>
                <td>${Profit}</td>
            </tr>
        </table>
     </div>
  </c:if>
  
  <c:if test="${not empty Profit}">
     <div class="revenue">
        <h3>Clients</h3>
     </div>
     <div class="revenue">
        <h3>Projects</h3>
     </div>
     <div class="revenue">
        <h3>Employees</h3>
     </div>
  </c:if>
     <div id="chartContainer" style="height: 300px; width: 100%;"></div>
<script src="https://canvasjs.com/assets/script/canvasjs.min.js"></script>
<c:set var="alphabet" value="${names}" scope="page" />

<body/>
<c:if test="${not empty names}">
<script>
    alert ("${message}");
    var someJsVar = "<c:out value='${names}'/>";
    	alert (someJsVar);
    	var arrayLength = someJsVar.length;
    	someJsVar.forEach(function(item, index, array) { console.log(item, index); }); // Apple 0 // Banana 1

</script>
</c:if>
</html>