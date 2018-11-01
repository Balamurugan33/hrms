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
    <h1 align = "center">Recover Dvd</h1>
    <br>
    <form action="recover-dvd-submit" method="POST"> 
    <button style="position:absolute; top:20px; right:0px" type="submit" formaction="usercontroller/sign-out" formmethod="POST"> Logout </button>
      <div>
        <table>
          <tr>
            <td>
              <label for="moviename">Enter id of the dvd that you want to recover: </label>
            </td>
            <td>
              <input type="number" id="dvd-id" name="dvd-id" required>
            </td>
            <td>
              <button type="submit" name = "dvdmenu-button" value="recover-dvd-submit">Recover</button>       
            </td>
          </tr>  
        </table>
      </div>
    </form>
  </body>
</html>
