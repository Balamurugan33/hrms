<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="com.ideas2it.dvdstore.model.Customer"%> 
<%@page import="com.ideas2it.dvdstore.model.PurchaseOrder"%> 
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <title>Dvd Store</title>
  </head>
  <body>
    <h1 align = "center">Customer Accounts</h1>
    <br>
    <form method="get">
    <button style="position:absolute; top:20px; right:0px" type="submit" formaction="usercontroller/sign-out" formmethod="POST"> Logout </button> 
      <div>
        <table>
            <tr>
              <th> Customer Id </th>
              <th> Name </th>
              <th> Address </th>
              <th> Phone Number </th>
              <th> Email Address </th>
              <th> Purchase Orders </th>
            </tr>
            <c:forEach var="customer" items="${customerAccounts}">
              <tr>
                <td> ${customer.id} </td>
                <td> ${customer.name} </td>
                <td> ${customer.address} </td>
                <td> ${customer.phoneNumber} </td>
                <td> ${customer.user.emailId} </td>
                <td> ${customer.purchaseOrders} </td>
              </tr>
            </c:forEach>
        </table>
      </div>
    </form>
  </body>
</html>
