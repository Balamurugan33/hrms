<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<body>

<form action="/dvdstore/employee/viewCustomers-admin" method="get" align="left">
<button type="submit" style = "margin-right: 10px;" > Back </button><br>
</form>

<c:if test="${not empty message}">
<script>
    alert ("${message}");
</script>
</c:if>

<form action="employee" method="get" align="center">  
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
    </table>
    <button type="button" Onclick="visibleAddress()" style= "text-align:left;">Update</button>
    <button type="button" Onclick="visibleOrders()" style= "text-align:right">View Orders</button>

    <div id="addressInfo" style="display:none">
    <table style= style="float:left" cellspacing="8">
    <tr>
        <th>Address Line:</th>
        <th>City</th>
        <th>State</th>
        <th>Country</th>
        <th>Pincode</th>
    </tr>
    <c:forEach var= "address" items= "${employee.address}">
    <tr>
        <td>${address.addressLine}</td>
        <td>${address.city}</td>
        <td>${address.state}</td>
        <td>${address.country}</td>
        <td>${address.pincode}</td>
    </tr>
    </c:forEach>
    </table>
    </div>
    
    <div id=orderInfo style="display:none">
    <table style= style="float:right" cellspacing=8>
    <tr>
        <th>Order Id</th>
        <th>Dvd Name</th>
        <th>Language</th>
        <th>Price</th>
        <th>Order Date</th>
    </tr>
    <c:forEach var= "order" items= "${employee.orders}">
    <tr>
        <td>${order.orderId}</td>
        <td>${order.dvd.dvdName}</td>
        <td>${order.dvd.language}</td>
        <td>${order.dvd.price}</td>
        <td>${order.orderDate}</td>
    </tr>
    </c:forEach>
    </table>
    </div>
    
</form> 
</body>
<script>
function visibleAddress() {
    document.getElementById("addressInfo").style.display='block';
    document.getElementById("orderInfo").style.display='none';
}

function visibleOrders() {
    document.getElementById("orderInfo").style.display='block';
    document.getElementById("addressInfo").style.display='none';
}

</script>

</html>
