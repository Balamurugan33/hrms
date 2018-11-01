<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <title>Dvd Store</title>
  </head>
  <body>
    <h1 align = "center">Admin Menu: Dvd Operations</h1>
    <br>
    <form action="dvdcontroller" method="get">
    <button style="position:absolute; top:20px; right:0px" type="submit" formaction="DvdStoreProject/usercontroller/sign-out" formmethod="POST"> Logout </button>
      <div align = "center">
        <button type="submit" formaction="dvdcontroller/menu-add-dvd">1. Add dvd</button>
      </div>
      <br>
      <br>
      <div align = "center">
        <button type="submit" formaction="dvdcontroller/menu-search-dvd">2. Search Dvd</button> 
      </div>
      <br>
      <br>
      <div align = "center">
        <button type="submit" formaction="dvdcontroller/menu-modify-dvd">3. Modify Dvd Details</button> 
      </div>
      <br>
      <br>
      <div align = "center">
        <button type="submit" formaction="dvdcontroller/menu-remove-dvd">4. Remove Dvd</button> 
      </div>
      <br>
      <br>
      <div align = "center">
        <button type="submit" formaction="dvdcontroller/menu-recover-dvd">5. Recover Dvd</button> 
      </div>
      <br>
      <br>
      <div align = "center">
        <button type="submit" formaction="dvdcontroller/menu-display-dvds">6. Display all Dvds</button> 
      </div>
    </form>
  </body>
</html>
