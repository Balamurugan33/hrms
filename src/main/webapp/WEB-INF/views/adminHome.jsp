<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<head>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script
    src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <jsp:include page='adminHeader.jsp'/>
</head>

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
            <th class="text-center">Salary</th>
            <th class="text-center">Hourly Rate</th>
            <th class="text-center">Action</th>
        </tr>
        <c:forEach var="designation" items="${designations}">
        <tr>
            <td>${designation.name}</td>
            <td>${designation.salary}</td>
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
                                <input type="text" class="form-control" name="salary"
                                   value= "${designation.salary}" placeholder="Mobile Number" required="required">
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
                                <select class="form-control" name="clientId">
                                    <c:forEach var="client" items="${allClients}">
                                         <option value="${client.id}" <c:if test = "${client.id==project.client.id}"> selected="selected" </c:if> >${client.name}</option>
                                    </c:forEach>
                                </select>
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
        <button type="submit" class="btn btn-outline-success btn-lg" 
            formaction="/hrms/employee/createProfile">Add Employee</button>
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
            <td>${employee.designation.salary}</td>
            <td><form method="post">
                <button type="button"  data-toggle="modal" data-target="#${employee.id}" class="btn btn-outline-success" >Assign Task</button>
                <button type="submit" class="btn btn-outline-danger">Remove</button>
                <input type="hidden" name="id" value="${employee.id}">
                </form></td>
        </tr>
             <div class="modal fade" id="${employee.id}" >
                 <div class="modal-dialog modal-sm">
                     <div class="modal-content">
                         <div class="modal-header">
                             <h4>Enter Task Detail</h4>
                         </div>
                         <div class="modal-body">
                             <form action="/hrms/employee/createTask" method="post">
                                 <div class="form-group">
                                     <label>Task Name</label>
                                     <div class="input-group">
                                         <input type="text" class="form-control" name="name"
                                             required="required">
                                     </div>
                                 </div>
                                 <div class="form-group">
                                         <label>Project Name</label>
                                         <select class="form-control" name="projectId">
                                             <c:forEach var="project" items="${allProjects}">
                                                  <option value="${project.id}">${project.name}</option>
                                             </c:forEach>
                                         </select>
                                 </div>
                                 <input type="hidden" name="empId" value="${employee.id}">
                                 <input type="hidden" name="taskDate" value="<%= java.time.LocalDate.now() %>">
                                 <div class="form-group">
                                     <button type="submit" class="btn btn-primary btn-block btn-lg" >Assign</button>
                                     <button type="button" class="btn btn-danger btn-block btn-lg" 
                                         data-dismiss="modal">Cancel</button>
                                 </div>
                             </form>
                         </div>
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
                                <input type="text" class="form-control" name="salary"
                                   placeholder="Salary" required="required">
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
     
<body/>
<c:if test="${not empty message}">
<script>
    alert ("${message}");
</script>
</c:if>
</html>