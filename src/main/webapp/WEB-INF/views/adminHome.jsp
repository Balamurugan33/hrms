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
        <button type="button" onclick="openClientCreate()" class="btn btn-outline-success btn-lg">Add Client</button>
        <table>
        <tr>
	        <th>Name</th>
	        <th>Mobile No</th>
	        <th>Email Id</th>
        </tr>
        <c:forEach var="client" items="${clients}">
        <tr>
            <td>${client.name}</td>
            <td>${client.mobileNo}</td>
            <td>${client.emailId}</td>
            <form method="post">
            <td><button type="submit" class="btn btn-success" formaction="/hrms/client/searchClient" >Update</button>
                <button type="submit" class="btn btn-danger" formaction="/hrms/client/deleteClient">Delete</button></td>
            <input type="hidden" name=id value="${client.emailId}">
            </form>
        </tr>
        </c:forEach>
        </table>
    </div>
</c:if>

<c:if test="${not empty projects}">
    <div>
        <button type="button" class="btn btn-outline-success btn-lg">Add Project</button>
        <table>
        <tr>
            <th>Project</th>
            <th>Client</th>
        </tr>
        <c:forEach var="project" items="${projects}">
        <tr>
            <td>${project.name}</td>
            <td>${project.client.name}</td>
        </tr>
        </c:forEach>
        </table>
    </div>
</c:if>

<c:if test="${not empty employees}">
    <div>
        <form method="get">
        <button type="submit" class="btn btn-outline-success btn-lg" formaction="/hrms/employee/createProfile">Add Employee</button>
        </form>
        <table>
        <tr>
            <th>Name</th>
            <th>Mobile No</th>
            <th>Email Id</th>
            <th>Designation</th>
            <th>Salary</th>
            <th>Action</th>
        </tr>
        <c:forEach var="employee" items="${employees}">
        <tr>
            <td>${employee.name}</td>
            <td>${employee.mobileNo}</td>
            <td>${employee.emailId}</td>
            <td>${employee.designation.name}</td>
            <td>${employee.designation.salary}</td>
            <form method="post">
            <td><button type="submit" class="btn btn-outline-success" >Add</button>
                <button type="submit" class="btn btn-outline-danger">Remove</button></td>
            </form>
        </tr>
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
                        <c:if test="${not empty client}">
                        <div class="form-group">
                            <button type="submit" class="btn btn-primary btn-block btn-lg" formaction="/hrms/client/updateClient">
                                Update</button>
                            <input type="hidden" name="id" value = "${client.id}">
                        </div>
                        </c:if>
                        <c:if test="${empty client}">
                        <div class="form-group">
                            <button type="submit" class="btn btn-primary btn-block btn-lg" formaction="/hrms/client/createClient">
                                Save</button>
                        </div>
                        </c:if>
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
<c:if test="${not empty client}">
<script>
   $("#ClientCreate").modal("show");
</script>
</c:if>
<script>
function openClientCreate(event) {
    $("#ClientCreate").modal("show");
}
</script>
</html>