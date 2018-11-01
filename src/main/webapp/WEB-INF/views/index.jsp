<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="com.ideas2it.dvdstore.model.Customer"%> 
<%@page import="com.ideas2it.dvdstore.model.User"%> 
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <title>Dvd Store</title>
  </head>
  <body>
    <h1 align = "center"> Sign in to Dvd Store</h1>
    <br>
    <form action="usercontroller" method="post">
      <div align = "center">
        <table>
          <tr>
            <td><b> Email address </b></td>
          </tr>
          <tr>
            <td> <input type="email" id="email-id" name="email-id"> </td> 
          </tr>
          <tr> <td> <br/> </td> </tr>
          <tr>
            <td><b> Password </b></td>
          </tr>
          <tr>
            <td> <input type="password" id="password" name="password"> </td> 
          </tr>
        </table>
      </div>
      <br>
      <div align="center">
        <button type="submit" name="login-button" value="sign-in" formaction="usercontroller/sign-in-submit" formmethod="POST">Sign in </button>
      <br>
      <br>
      <br>
      <br>
        <button type="submit" name="login-button" value="sign-up" formaction="customercontroller/menu-add-customer" formmethod="GET"> New User? Sign up </button>
      </div>
    </form>
  </body>
</html>
