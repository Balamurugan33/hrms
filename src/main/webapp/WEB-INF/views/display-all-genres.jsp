<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="com.ideas2it.dvdstore.model.Genre"%> 
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <title>Dvd Store</title>
  </head>
  <body>
    <h1 align = "center">Genre Catalogue</h1>
    <br>
    <form method="get">
    <button style="position:absolute; top:20px; right:0px" type="submit" formaction="usercontroller/sign-out" formmethod="POST"> Logout </button> 
      <div>
        <table>
            <tr>
              <th> Genre Id </th>
              <th> Name </th>
            </tr>
            <c:forEach var="genre" items="${genreCatalogue}">
              <tr>
                <td> ${genre.id} </td>
                <td> ${genre.name} </td>
              </tr>
            </c:forEach>
        </table>
      </div>
    </form>
  </body>
</html>
