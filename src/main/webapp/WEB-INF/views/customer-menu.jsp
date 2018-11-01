<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <title>Dvd Store</title>
  </head>
  <body>
    <h1 align = "center">My Account</h1>
    <br>
    <form action="customercontroller" method="GET">
    <button style="position:absolute; top:20px; right:0px" type="submit" name = "login-button" value="sign-out" formaction="usercontroller/sign-out" formmethod="POST"> Logout </button>
      <div align = "center">
        <button type="submit" formaction="${contextPath}/customercontroller/search-customer-submit" formmethod="GET"> 1. View My account details </button>
      </div>
      <br>
      <br>
      <div align = "center">
        <button type="submit" formaction="${contextPath}/customercontroller/menu-add-order"> 2. Order Dvds </button>
      </div>
      <br>
      <br>
      <div align = "center">
        <button type="submit" formaction="${contextPath}/customercontroller/search-orders-submit"> 3. View Orders </button>
      </div>
    </form>
  </body>
</html>
