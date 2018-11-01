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
    <h1 align = "center">Modify Genre</h1>
    <br>
    <form:form action="modify-genre-submit" method="POST" modelAttribute="genre"> 
    <button style="position:absolute; top:20px; right:0px" type="submit" formaction="usercontroller/sign-out" formmethod="POST"> Logout </button>
      <div>
        <table>
          <tr>
            <label for="genre-id">Enter id of the genre that you want to modify: </label>
            <form:input path="id" type="number" required="required"/>
          </tr>  
          <tr>
          <br>
          <br>
            <label for="genre-name">Enter genre name: </label>
            <form:input path="name" type="text" required="required"/>
          </tr>
          <tr>
            <td>
              <button type="submit" name = "genremenu-button" value="modify-genre-submit">Modify</button>       
            </td>
          </tr>
        </table>
      </div>
    </form:form>
  </body>
</html>
