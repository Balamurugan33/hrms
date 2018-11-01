<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="com.ideas2it.dvdstore.model.Dvd"%> 
<%@page import="com.ideas2it.dvdstore.model.Genre"%> 
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <title>Dvd Store</title>
  </head>
  <body>
    <h1 align = "center"> Purchase Dvds </h1>
    <br>
    <form:form method="POST" action="add-order-submit" modelAttribute="dvd"> 
    <button style="position:absolute; top:20px; right:0px" type="submit" name = "login-button" value="sign-out" formaction="usercontroller/sign-out" formmethod="POST"> Logout </button>
      <div>
        <table>
            <tr>
              <th> Dvd Id </th>
              <th> Movie Name </th>
              <th> Release Date </th>
              <th> Language </th>
              <th> Price </th>
              <th> Stock </th>
              <th> Genres </th>
              <th> </th>
              <th> Quantity </th>
            </tr>
            <c:forEach var="dvd" items="${dvdCollection}">
              <tr>
                <td> ${dvd.id} </td>
                <td> ${dvd.movieName} </td>
                <td> ${dvd.releaseDate} </td>
                <td> ${dvd.language} </td>
                <td> ${dvd.price} </td>
                <td> ${dvd.quantity} </td>

                <td>
                  <c:forEach var="genre" items="${dvd.genres}"> 
                     ${genre.name}  
                  </c:forEach>
                </td>
                <td> <input type="checkbox" id="selected-dvds" name="selected-dvds" value="${dvd.id}"/> </td>    
                <td> <input type="number" id="dvd-quantities" name="dvd-quantities"/>  </td>
              </tr>
            </c:forEach>
        </table>
      </div>
      <div>
        <button type="submit" name = "add-order-button" value="add-order-submit"> Purchase </button>       
      </div>
    </form:form>
  </body>
</html>
