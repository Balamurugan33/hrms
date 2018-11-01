<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <title>Dvd Store</title>
  </head>
  <body>
    <h1 align = "center">Search Dvd</h1>
    <br>
    <form action="search-dvd-submit" method="GET"> 
    <button style="position:absolute; top:20px; right:0px" type="submit" formaction="usercontroller/sign-out" formmethod="POST"> Logout </button>
      <div>
        <table>
          <tr>
            <td>
              <label for="id">Enter id of the dvd that you want to search for: </label>
            </td>
            <td>
              <input type="number" id="dvd-id" name="dvd-id" required>
            </td>
            <td>
              <button type="submit" name = "dvdmenu-button" value="search-dvd-submit">Search</button>       
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
              <th> Dvd Id </th>
              <th> Movie Name </th>
              <th> Release Date </th>
              <th> Language </th>
              <th> Price </th>
              <th> Quantity </th>
              <th> Genres </th>
              </tr>
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
        </table>
    </form>
  </body>
</html>
