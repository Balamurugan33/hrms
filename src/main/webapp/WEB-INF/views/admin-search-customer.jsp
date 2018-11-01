<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="com.ideas2it.dvdstore.model.Customer"%>
<%@page import="com.ideas2it.dvdstore.model.Dvd"%>  
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <title>Dvd Store</title>
  </head>
  <body>
    <h1 align = "center">Search Customer</h1>
    <br>
    <form action="customercontroller" method="get"> 
    <button style="position:absolute; top:20px; right:0px" type="submit" name = "login-button" value="sign-out" formaction="usercontroller/sign-out" formmethod="POST"> Logout </button>
      <div>
        <table>
          <tr>
            <td>
              <label for="id">Enter id of the customer that you want to search for: </label>
            </td>
            <td>
              <input type="number" id="customer-id" name="customer-id" required>
            </td>
            <td>
              <button type="submit" name = "admincustomermenu-button" value="search-customer-submit">Search</button>       
            </td>
          </tr>  
        </table>
        <br>
        <br>
        <br>
        <br>
        <table>
          <tr>
            <tr>
              <th> Customer Id </th>
              <th> Name </th>
              <th> Address </th>
              <th> Phone Number </th>
              <th> Email Id </th>
              </tr>
            <tr>
              <td> ${customer.id} </td>
              <td> ${customer.name} </td>
              <td> ${customer.address} </td>
              <td> ${customer.phoneNumber} </td>
              <td> ${customer.emailId} </td>
            </tr>
          </tr>
        </table>
    </form>
  </body>
</html>
