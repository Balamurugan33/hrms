<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<head>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script
    src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <jsp:include page='adminHeader.jsp'/>
  
  <style>
.revenue {
     background: white;
     display: inline-block;
     padding: 25px;
     border-radius: 20px;
     box-shadow: 10px 10px 10px 10px #8a6bb1; 
     align: center;
     margin-top:30px;
     margin-left:50%;
     }
     
.dropbtn {
    background-color: #4CAF50;
    color: white;
    padding: 16px;
    font-size: 16px;
    border: none;
}

.actiondropdown {
    position: relative;
    display: inline-block;
    text-align:left;
}

.dropdown-content {
    display: none;
    position: absolute;
    background-color: #f1f1f1;
    min-width: 160px;
    box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
    z-index: 1;
}

.dropdown-content a {
    color: black;
    padding: 12px 16px;
    text-decoration: none;
    display: block;
    align:left;
}

.dropdown-content button {
    color: black;
    padding: 12px 16px;
    text-decoration: none;
    background:none;
    outline:none;
    border: none;
    display: block;
}

.dropdown {
    top: 0;
    right: 100%;
    margin-top: -1px;
}

.dropdown-content a:hover {background-color: #ddd;}

.dropdown-content button:hover {background-color: gray;}

.actiondropdown:hover .dropdown-content {display: block;}

.actiondropdown:hover .dropbtn {background-color: #3e8e41;}

 </style>
 
</head>
<body>
<c:if test="${not empty clients}">
    <div>
        <button type="button" data-toggle="modal" data-target="#ClientCreate" class="btn btn-outline-success btn-lg">Add Client</button>
        <table class="table table-striped text-center">
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
                            <div class="input-group">
                                <input type="text" class="form-control" name="name"
                                    value= "${client.name}" placeholder="Client Name" required="required">
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="input-group">
                                <input type="text" class="form-control" name="mobileNo"
                                   value= "${client.mobileNo}" placeholder="Mobile Number" required="required">
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="input-group">
                                <input type="text" class="form-control" name="emailId"
                                   value= "${client.emailId}" placeholder="Email Id" required="required">
                            </div>
                        </div>
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
     </div></td>
            </form>
        </tr>
        </c:forEach>
        </table>
    </div>
</c:if>

<c:if test="${not empty designations}">
    <div>
        <button type="button" class="btn btn-outline-success btn-lg" 
            data-toggle="modal" data-target="#DesignationCreate">Add Designation</button>
        <table class="table table-striped text-center">
        <tr>
            <th class="text-center">Name</th>
            <th class="text-center">Hourly Rate</th>
            <th class="text-center">Action</th>
        </tr>
        <c:forEach var="designation" items="${designations}">
        <tr>
            <td>${designation.name}</td>
            <td>${designation.hourlyRate}</td>
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
                            <div class="input-group">
                                <input type="text" class="form-control" name="name"
                                    value= "${designation.name}" placeholder="Name" required="required">
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="input-group">
                                <input type="text" class="form-control" name="hourlyRate"
                                   value= "${designation.hourlyRate}" placeholder="Hourly Rate" required="required">
                            </div>
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
     </div></td>
            </form>
        </tr>
        </c:forEach>
        </table>
    </div>
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
            <td><button type="button" class="btn btn-success" data-toggle="modal" data-target="#${project.id}" >Update</button>
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
                            <div class="input-group">
                                <label>Project Name</label>
                                <input type="text" class="form-control" name="name"
                                    value= "${project.name}" required="required">
                            </div>
                        </div>
                        <div class="form-group">
                            <label>Client Name</label>
                                <input type="text" class="form-control" name="name"
                                    value= "${project.client.name}" required="required">
                        </div>
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
     </div></td>
            </form>
        </tr>
        </c:forEach>
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
            <td><form action="/hrms/employee/deleteEmployee" method="post">
                <div class="actiondropdown">
				  <button class="btn btn-success">Select Action</button>
				  <div class="dropdown-content">
				    <a href="#" data-toggle="modal" data-target="#${employee.id}">Assign Project</a>
						     <div class="dropdown">
							    <a href="#" class="dropdown-toggle"  data-toggle="dropdown">Assign Project
							    <span class="caret"></span></a>
							    <ul class="dropdown-menu dropdown-menu-right">
							      <c:forEach var ="project" items="${allProjects}">
							      <form action="/hrms/employee/assignProject" method="post">
							      <li><button type="sumbit">${project.name}</button></li>
							      <input type="hidden" name="projectId" value="${project.id}">
							      <input type="hidden" name="emailId" value="${employee.emailId}">
							      </form>
							      </c:forEach>
							    </ul>
							  </div>
				    <a href="#" data-toggle="modal" data-target="#${employee.id}">Increment</a>
				    <a href="#" data-toggle="modal" data-target="#${employee.mobileNo}">View Revenue</a>
				    <button type="submit" class="btn-danger"> <i class="fas fa-times-square"></i>Remove</button>
				  </div>
				  <button type="sumbit">gh${project.name}</button>
				</div>
                <input type="hidden" name="id" value="${employee.id}">
                <input type="hidden" name="id" value="${employee.emailId}">
                </form></td>
        </tr>
             <div class="modal fade" id="${employee.id}" >
                 <div class="modal-dialog modal-sm">
                     <div class="modal-content">
                         <div class="modal-header">
                             <h4>Enter Increment Salary & Hourly Rate</h4>
                         </div>
                         <div class="modal-body">
                             <form action="/hrms/employee/increment" method="post">
                                 <div class="form-group">
                                     <label>Salary</label>
                                     <div class="input-group">
                                         <input type="text" class="form-control" name="salary"
                                             required="required">
                                     </div>
                                 </div>
                                 <div class="form-group">
                                     <label>Hourly Rate</label>
                                     <div class="input-group">
                                         <input type="text" class="form-control" name="hourlyRate"
                                             required="required">
                                     </div>
                                 </div>
                                 <input type="hidden" name="emailId" value="${employee.emailId}">
                                 <input type="hidden" name="updateDate" value="<%= java.time.LocalDate.now() %>">
                                 <div class="form-group">
                                     <button type="submit" class="btn btn-primary btn-block btn-lg" >Increment</button>
                                     <button type="button" class="btn btn-danger btn-block btn-lg" 
                                         data-dismiss="modal">Cancel</button>
                                 </div>
                             </form>
                         </div>
                     </div>
                 </div>
              </div>
              
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
					            <div class="input-group">
					                <label>Start Date</label>
					                <input type="date" class="form-control" name="startDate"
					                    required="required" min="${employee.attendance[0].attendDate}" 
					                    max="${employee.attendance[fn:length(employee.attendance)-1].attendDate}" >
					            </div>
					            <div class="input-group">
					                <label>End Date</label>
					                <input type="date" class="form-control" name="endDate"
					                    required="required" min="${employee.attendance[0].attendDate}" 
                                        max="${employee.attendance[fn:length(employee.attendance)-1].attendDate}" >
					            </div>
					         </div>
					         <input type="hidden" name="empEmailId" value="${employee.emailId}">
                         </div>
                         <div class="modal-footer">
                             <div class="input-group">
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
              
              
              
        </c:forEach>
        </table>
        
    </div>
</c:if>
    
    <div class="modal fade" id="ClientCreate" >
        <div class="modal-dialog modal-sm">
            <div class="modal-content">
                <div class="modal-header">
                    <h4>Enter Client Detail</h4>
                </div>
                <div class="modal-body">
                    <form action="hrms/client/createClient" method="post">
                        <div class="form-group">
                            <div class="input-group">
                                <input type="text" class="form-control" name="name"
                                    placeholder="Client Name" required="required">
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="input-group">
                                <input type="text" class="form-control" name="mobileNo"
                                   placeholder="Mobile Number" required="required">
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="input-group">
                                <input type="text" class="form-control" name="emailId"
                                   placeholder="Email Id" required="required">
                            </div>
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
     
     <div class="modal fade" id="DesignationCreate" >
        <div class="modal-dialog modal-sm">
            <div class="modal-content">
                <div class="modal-header">
                    <h4>Enter Designation Detail</h4>
                </div>
                <div class="modal-body">
                        <form action="hrms/client/createClient" method="post">
                        <div class="form-group">
                            <div class="input-group">
                                <input type="text" class="form-control" name="name"
                                    placeholder="Name" required="required">
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="input-group">
                                <input type="text" class="form-control" name="hourlyRate"
                                   placeholder="Hourly Rate" required="required">
                            </div>
                        </div>
                        <div class="form-group">
                            <button type="submit" class="btn btn-primary btn-block btn-lg" 
                                formaction="/hrms/designation/createDesignation">Save</button>
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
                            <div class="input-group">
                                <input type="text" class="form-control" name="name"
                                    required="required">
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
                            <div class="input-group">
                                <input type="text" class="form-control" name="name"
                                    required="required">
                            </div>
                        </div>
                        <div class="form-group">
                            <label>Mobile Number</label>
                            <div class="input-group">
                                <input type="text" class="form-control" name="mobileNo"
                                    required="required">
                            </div>
                        </div>
                        <div class="form-group">
                            <label>Email id</label>
                            <div class="input-group">
                                <input type="text" class="form-control" name="emailId"
                                    required="required">
                            </div>
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
                            <div class="input-group">
                                <input type="text" class="form-control" name="salary"
                                    required="required">
                            </div>
                        </div>
                        <div class="form-group">
                            <label>Hourly Rate</label>
                            <div class="input-group">
                                <input type="text" class="form-control" name="hourlyRate"
                                    required="required">
                            </div>
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
     
  <c:if test="${not empty EmpProfit}">
     <div class="revenue">
        <table class="table table-striped text-center">
            <tr>
               <th class="text-center">Cost To Company Pay</th>
	           <th class="text-center">Bill Amount</th>
	           <th class="text-center">Profit</th>
            </tr>
            <tr>
                <td>${CostToCompany}</td>
                <td>${BilAmount}</td>
                <td>${EmpProfit}</td>
            </tr>
        </table>
     </div>
  </c:if>
     
<body/>
<c:if test="${not empty message}">
<script>
    alert ("${message}");
</script>

</c:if>
</html>