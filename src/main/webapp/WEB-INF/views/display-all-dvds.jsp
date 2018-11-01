<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="com.ideas2it.dvdstore.model.Dvd"%> 
<%@page import="com.ideas2it.dvdstore.model.Genre"%> 
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <title>Dvd Store</title>
  </head>
  <body>
    <h1 align = "center">Dvd Collection</h1>
    <br>
    <form method="get"> 
    <button style="position:absolute; top:20px; right:0px" type="submit" formaction="usercontroller/sign-out" formmethod="POST"> Logout </button>
      <div>
        <table>
            <tr>
              <th> Dvd Id </th>
              <th> Movie Name </th>
              <th> Release Date </th>
              <th> Language </th>
              <th> Price </th>
              <th> Quantity </th>
              <th> Genres </th>
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
              </tr>
            </c:forEach>
        </table>
      </div>
    </form>
  </body>
</html>
