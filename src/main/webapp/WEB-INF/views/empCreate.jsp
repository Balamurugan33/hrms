<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "form"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<body>
<jsp:include page='empHeader.jsp'/>
<c:if test="${empty employee}">
    <h3 align="center"> Enter the Details </h3> 
</c:if>

<form method="post">  
    <table align="center">
    <tr>
        <td>Name:</td> 
        <td><input type="text" value="${employee.name}" name="name" pattern="[A-Za-z\s]+" maxlength=30 required/></td>  
    </tr>
    <tr>
        <td>MobileNo:</td>
        <td><input type="text" value="${employee.mobileNo}" name="mobileNo" pattern="[6789]{1}[0-9]{9}" maxlength=10 required/></td>
    </tr>
    <tr>  
        <td>Mail:</td>
        <td><input type="email" value="${employee.emailId}" name="emailId" required/></td>
    </tr> 
    
    
    <c:if test="${empty employee}">
    <tr>  
        <td>Designation:</td>
    </tr>
    
        <c:forEach var="designation" items="${designations}">
    <tr></td>
        <td><input type="radio" name="designationId" value="${designation.id}" <c:if test = "${employee.designation.name.equals('designation.name')}"> checked </c:if> /> ${designation.name} &nbsp
        </td>
    </tr>
        </c:forEach>
    </c:if>
    <tr>
        <c:if test="${empty employee}">
        <td><button type="submit" formaction="/hrms/employee/createEmployee" value="create">Save</button></td>
        </c:if>
        <c:if test="${not empty employee}">
        <td><button type="submit" formaction="/hrms/employee/updateEmployee" value="updateCustomer">Update</button></td>
        </c:if>
    </tr>
    </table>
    <input type="hidden" name="id" value="${employee.id}">
</form> 
</body>

<c:if test="${not empty message}">
<script>
    alert ("${message}");
</script>
</c:if>
</html>