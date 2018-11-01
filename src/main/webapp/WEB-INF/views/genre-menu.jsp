<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <title>Dvd Store</title>
  </head>
  <body>
    <h1 align = "center">Admin Menu: Genre Operations</h1>
    <br>
    <form action="genrecontroller" method="get">
    <button style="position:absolute; top:20px; right:0px" type="submit" formaction="DvdStoreProject/usercontroller/sign-out" formmethod="POST"> Logout </button>
      <div align = "center">
        <button type="submit" name = "genremenu-button" value="menu-add-genre" formaction="genrecontroller/menu-add-genre">1. Add genre</button>
      </div>
      <br>
      <br>
      <div align = "center">
        <button type="submit" name = "genremenu-button" value="menu-search-genre" formaction="genrecontroller/menu-search-genre">2. Search genre</button> 
      </div>
      <br>
      <br>
      <div align = "center">
        <button type="submit" name = "genremenu-button" value="menu-modify-genre" formaction="genrecontroller/menu-modify-genre">3. Modify genre Details</button> 
      </div>
      <br>
      <br>
      <div align = "center">
        <button type="submit" name = "genremenu-button" value="menu-remove-genre" formaction="genrecontroller/menu-remove-genre">4. Remove genre</button> 
      </div>
      <br>
      <br>
      <div align = "center">
        <button type="submit" name = "genremenu-button" value="menu-recover-genre" formaction="genrecontroller/menu-recover-genre">5. Recover genre</button> 
      </div>
      <br>
      <br>
      <div align = "center">
        <button type="submit" name = "genremenu-button" value="menu-display-genres" formaction="genrecontroller/menu-display-genres">6. Display all genres</button> 
      </div>
    </form>
  </body>
</html>
