<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="com.ideas2it.dvdstore.model.Dvd"%> 
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <title>Dvd Store</title>
  </head>
  <body>
    <h1 align = "center">Edit Account info</h1>
    <br>
    <form:form action="modify-customer-submit" method="POST" modelAttribute="customer"> 
    <button style="position:absolute; top:20px; right:0px" type="submit" formaction="usercontroller/sign-out" formmethod="POST"> Logout </button>
      <div>
        <table>
          <tr height = "50">
            <td width = "150">
              <form:input path="id" type="hidden" name ="id" required="required"/>
            </td>
          </tr>  
          <tr height = "50">
            <td width = "150">
              <label for="name"> <b> Name: </b> </label>
            </td>
            <td>
              <form:input path="name" type="text" required="required"/>
            </td>
          </tr>
          <tr height = "50">
            <td width = "150">
              <label for="phone-number"> <b> Phone number: </b> </label>
            </td>
            <td>
              <form:input path="phoneNumber" type="number" required="required"/>
            </td>
          </tr>  
          <tr height = "50">
            <td width = "150">
              <form:input path="address.id" type="hidden" name ="id" required="required"/>
            </td>   
          </tr>     
          <tr height = "50">
            <td width = "150">
              <label for="street"> <b> Street </b></label>
            </td>
            <td>
              <form:input path="address.street" type="text" required="required"/>
            </td>
          </tr>
          <tr height = "50">
            <td width = "150">
              <label for="city"> <b> City </b></label>
            </td>
            <td>
              <form:input path="address.city" type="text" required="required"/>
            </td>
          </tr>
          <tr height = "50">
            <td width = "150">
              <label for="state"> <b>State</b></label>
            </td>
            <td>
              <form:input path="address.state" type="text" required="required"/>
            </td>
          </tr>
          <tr height = "50">
            <td width = "150">
              <label for="pincode"> <b> Pincode </label>
            </td>
            <td>
              <form:input path="address.pinCode" type="number" required="required"/>
            </td>
          </tr>
          <tr>
            <td>
              <button type="submit" name = "modify-customer-button" value="modify-customer-submit">Modify</button>       
            </td>
          </tr>
        </table>
      </div>
    </form:form>
  </body>
</html>
