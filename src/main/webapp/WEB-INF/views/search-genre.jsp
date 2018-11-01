<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <title>Dvd Store</title>
  </head>
  <body>
    <h1 align = "center">Search Genre</h1>
    <br>
    <form action="search-genre-submit" method="get"> 
    <button style="position:absolute; top:20px; right:0px" type="submit" formaction="usercontroller/sign-out" formmethod="POST"> Logout </button>
      <div>
        <table>
          <tr>
            <td>
              <label for="id">Enter id of the genre that you want to search for: </label>
            </td>
            <td>
              <input type="number" id="genre-id" name="genre-id" required>
            </td>
            <td>
              <button type="submit" name = "genremenu-button" value="search-genre-submit">Search</button>       
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
              <th> Genre Id </th>
              <th> Genre Name </th>
              <th> Dvds of this genre </th>
              </tr>
            <tr>
              <td> ${genre.id} </td>
              <td> ${genre.name} </td>
              <td>
                <c:forEach var="dvd" items="${genre.dvds}"> 
                  ${dvd.movieName}  
                </c:forEach>
              </td>
              </tr>
          </tr>
        </table>
    </form>
  </body>
</html>
