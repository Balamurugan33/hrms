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
    <h1 align = "center">Modify Dvd</h1>
    <br>
    <form:form action="modify-dvd-submit" method="POST" modelAttribute="dvd"> 
    <button style="position:absolute; top:20px; right:0px" type="submit" formaction="usercontroller/sign-out" formmethod="POST"> Logout </button>
      <div>
        <table>
          <tr>
            <label for="dvd-id">Enter id of the dvd that you want to modify: </label>
            <form:input path="id" type="number" id="dvd-id" name="dvd-id" required="required"/>
          </tr>  
          <tr>
          <br>
          <br>
            <label for="movie-name">Enter movie name: </label>
            <form:input path="movieName" type="text" id="movie-name" name="movie-name" required="required"/>
          </tr>
          <br>
          <br>
          <tr>
            <label for="release-date">Enter release date: </label>
            <form:input path="releaseDate" type="date" min="1878-01-01" required="required"/>
          </tr>
          <br>
          <br>
          <tr>
            <label for="language">Enter language: </label>
            <form:input path="language" type="text" id="language" name="language" required="required" pattern="[a-zA-Z]+" 
                   oninvalid="setCustomValidity('Language can contain only letters')"
                   oninput="setCustomValidity('')"/>
          </tr>
          <br>
          <br>
          <tr>
            <label for="price">Enter price: </label>
            <form:input path="price" type="number" id="price" name="price" min="0" required="required"/>
          </tr>
          <br>
          <br>
          <tr>
            <label for="quantity">Enter quantity: </label>
            <form:input path="quantity" type="number" id="quantity" name="quantity" min="1" required="required"/>
          </tr>
          <tr>
            <c:forEach var="genre" items="${genreCatalogue}">
              <tr>
                <td> ${genre.id} </td>
                <td> ${genre.name} </td>
                <td> <form:checkbox path="genres" id="selected-genres" name="selected-genres" value="${genre.id}"/> </td>           
              </tr>
            </c:forEach>
          </tr>
          <tr>
            <td>
              <button type="submit" name = "dvdmenu-button" value="modify-dvd-submit">Modify</button>       
            </td>
          </tr>
        </table>
      </div>
    </form:form>
  </body>
</html>
