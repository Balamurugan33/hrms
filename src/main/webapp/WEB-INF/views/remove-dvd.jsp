<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="com.ideas2it.dvdstore.model.Dvd"%> 
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <title>Dvd Store</title>
  </head>
  <body>
    <h1 align = "center">Remove Dvd</h1>
    <br>
    <form action="remove-dvd-submit" method="post"> 
    <button style="position:absolute; top:20px; right:0px" type="submit" formaction="usercontroller/sign-out" formmethod="POST"> Logout </button>
      <div>
        <table>
          <tr>
            <td>
              <label for="moviename">Enter id of the dvd that you want to remove: </label>
            </td>
            <td>
              <input type="number" id="dvd-id" name="dvd-id" required>
            </td>
            <td>
              <button type="submit" name = "dvdmenu-button" value="remove-dvd-submit">Remove</button>       
            </td>
          </tr>  
        </table>
      </div>
    </form>
  </body>
</html>
