<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <title>Dvd Store</title>
  </head>
  <body>
    <h1 align = "center">Admin Menu: Customer Operations</h1>
    <br>
    <form action="customercontroller" method="get">
    <button style="position:absolute; top:20px; right:0px" type="submit" formaction="DvdStoreProject/usercontroller/sign-out" formmethod="POST"> Logout </button>
      <!-- <div align = "center">
        <button type="submit" name = "admincustomermenu-button" formaction="customercontroller/menu-search-customer">2. Search Customer Account</button> 
      </div> -->
      <br>
      <br>
      <div align = "center">
        <button type="submit" name = "admincustomermenu-button" formaction="customercontroller/display-all-customers">6. Display all Customer accounts</button> 
      </div>
    </form>
  </body>
</html>
