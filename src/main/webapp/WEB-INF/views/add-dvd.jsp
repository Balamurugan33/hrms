<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
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
    <h1 align = "center">Add Dvd</h1>
    <br>
    <form:form method="POST" action="add-dvd-submit" modelAttribute="dvd">
    <button style="position:absolute; top:20px; right:0px" type="submit" formaction="usercontroller/sign-out" formmethod="POST"> Logout </button> 
      <div>
        <table>
        <tr>
          <form:label path="movieName"> Enter movie name: </form:label>
          <form:input path="movieName" type="text" id="movie-name" name="movie-name" required="required"/>
        </tr>
        <br>
        <br>
        <tr>
          <form:label path="releaseDate">Enter release date: </form:label>
          <form:input path="releaseDate" type="date" min="1878-01-01" required="required"/>
        </tr>
        <br>
        <br>
        <tr>
          <form:label path="language">Enter language: </form:label>
          <form:input path="language" type="text" id="language" name="language" required="required" pattern="[a-zA-Z]+" 
                      oninvalid="setCustomValidity('Language can contain only letters')"
                      oninput="setCustomValidity('')"/>
        </tr>
        <br>
        <br>
        <tr>
          <form:label path="price">Enter price: </form:label>
          <form:input path="price" type="number" id="price" name="price" min="0" required="required"/>
        </tr>
        <br>
        <br>
        <tr>
          <form:label path="quantity">Enter quantity: </form:label>
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

<!--
        <td>
        
          <form:checkboxes items="${genreCatalogue}" path="genres" />  
        </td>
-->

        </tr>
        <br>
        <br>
        </table>
      </div>
        <br>
        <br>
      <div>
        <button type="submit" name = "dvdmenu-button" value="add-dvd-submit"> Add</button>       
      </div>
    </form:form>
  </body>
</html>
