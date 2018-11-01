<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="com.ideas2it.dvdstore.model.Customer"%> 
<%@page import="com.ideas2it.dvdstore.model.Address"%> 
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <title>Dvd Store</title>
  </head>
  <body>
    <h1 align = "center">Create an account</h1>
    <br>
    <form:form method="POST" action="add-customer-submit" modelAttribute="customer"> 
      <div>
        <table>
          <tr height = "50">
            <td width = "150">
              <label for="name"><b>Name </label>
            </td>
            <td>
              <form:input path="name" type="text" id="name" name="name" align="center" required="required"/>
            </td>
          </tr>
          <tr height = "50">
            <td width = "150">
              <label for="phone-number"> <b> Phone Number </label>
            </td>
            <td>
              <form:input path="phoneNumber" type="number" id="phone-number" name="phone-number" required="required"/>
            </td>
          </tr>
          <tr height = "50">
            <td width = "150">
              <label for="email-id"> <b> Email Address </label>
            </td>
            <td>
              <form:input path="user.emailId" type="email" id="email-id" name="email-id" required="required"/>
            </td>
          </tr>
          <tr height = "50">
            <td width = "150">
              <label for="password"> <b> Password  </label>
            </td>
            <td>
              <form:input path="user.password" type="password" id="password" name="password" required="required"/>
            </td>
          </tr>
              <form:input path="user.role" type="hidden" id="password" name="password" value="Customer"/>
          <tr height = "50">
            <td width = "150">
              <label for="street"> <b> Street </b></label>
            </td>
            <td>
              <form:input path="address.street" type="text" id="street" name="street" required="required"/>
            </td>
          </tr>
          <tr height = "50">
            <td width = "150">
              <label for="city"> <b> City </b></label>
            </td>
            <td>
              <form:input path="address.city" type="text" id="city" name="city" required="required"/>
            </td>
          </tr>
          <tr height = "50">
            <td width = "150">
              <label for="state"> <b>State</b></label>
            </td>
            <td>
              <form:input path="address.state" type="text" id="state" name="state" required="required"/>
            </td>
          </tr>
          <tr height = "50">
            <td width = "150">
              <label for="pincode"> <b> Pincode </label>
            </td>
            <td>
              <form:input path="address.pinCode" type="number" id="pincode" name="pincode" required="required"/>
            </td>
          </tr>
        </table>
      </div>
        <br>
        <br>
      <div>
        <button type="submit" name = "add-customer-menu-button" value="add-customer-submit">Create account</button>       
      </div>
    </form:form>
  </body>
</html>
