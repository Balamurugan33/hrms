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
    <h1 align = "center">My Account</h1>
    <br>
    <form action="customercontroller" method="get"> 
    <button style="position:absolute; top:20px; right:0px" type="submit" name = "login-button" value="sign-out" formaction="usercontroller/sign-out" formmethod="POST"> Logout </button>
      <div align="center">
        <br>
        <br>
        <br>
        <br>
        <table>
          <tr>
            <tr>
              <th> Customer Id </th>
              <th> Name </th>
              <th> Delivery Address </th>
              <th> Phone Number </th>
              </tr>
            <tr>
              <td> ${customer.id} </td>
              <td> ${customer.name} </td>
              <td> 
                   ${customer.address.street},
                   ${customer.address.city},
                   ${customer.address.state},
                   ${customer.address.pinCode}
 
              </td>
              <td> ${customer.phoneNumber} </td>
              <td> <button type="submit" formaction="menu-modify-customer" formmethod="GET"> Edit </button></td>
            </tr>
          </tr>
        </table>
    </form>
  </body>
</html>
