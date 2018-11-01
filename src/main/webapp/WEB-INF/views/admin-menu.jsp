<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <title>Dvd Store</title>
  </head>
  <body>
    <h1 align = "center">Admin Menu</h1>
    <br>
    <form method="get">
    <button style="position:absolute; top:20px; right:0px" type="submit" name="login-button" value="sign-out" formaction="usercontroller/sign-out" formmethod="POST"> Logout </button>
      <div align = "center">
        <button type="submit" formaction="${contextPath}/admin-customer-menu.jsp">1. Customers</button>
      </div>
      <br>
      <br>
      <div align = "center">
        <button type="submit" formaction="${contextPath}/dvd-menu.jsp">2. Dvds</button> 
      </div>
      <br>
      <br>
      <div align = "center">
        <button type="submit" formaction="${contextPath}/genre-menu.jsp">3. Genres</button> 
      </div>
    </form>
  </body>
</html>
