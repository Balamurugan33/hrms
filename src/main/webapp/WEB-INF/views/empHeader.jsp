<html>
<head>
<style>

.tablink {
    background-color: #555;
    color: white;
    float: left;
    border: none;
    outline: none;
    cursor: pointer;
    padding: 14px 16px;
    font-size: 10px;
    width: 25%
}

.tablink:hover {
    background-color: #777;
}

.sidenav {
    height: 100%;
    width: 0;
    position: fixed;
    z-index: 1;
    top: 0;
    left: 0;
    background-color: #111;
    overflow-x: hidden;
    transition: 0.5s;
    padding-top: 60px;
}

.sidebtn {
    padding: 8px 8px 8px 32px;
    text-decoration: none;
    font-size: 25px;
    color: white;
    border: none;
    outline: none;
    cursor: pointer;
    display: block;
    transition: 0.3s;
    background-color: #111;
}

.sidebtn:hover {
    color: #777;
}

.sidenav .closebtn {
    position: absolute;
    top: 0;
    right: 25px;
    font-size: 36px;
    margin-left: 50px;
    text-decoration: none;
    color: white;
    border: none;
    outline: none;
    cursor: pointer;
    display: block;
    background-color: #111;
}
</style>
</head>
<body>
<button class="tablink"  onclick="openNav()">&#9776; open</button>
<h2 class="tablink">HRMS</h2>
<h2 class="tablink">HRMS</h2>
<button class="tablink" formaction="/HRMS/user/logout">LogOut</button>

<div id="mySidenav" class="sidenav">
  <button class="closebtn" onclick="closeNav()">&times;</button><br>
  <form method="get">
  <button class="sidebtn" formaction="/HRMS/employee/viewProfile">Edit Profile</button><br>
  <button class="sidebtn" formaction="/HRMS/employee/empProjects">Projects</button><br>
  </form>
  <button class="sidebtn" onclick="viewTask()">Time Sheet</button><br>
  <button class="sidebtn" onclick="viewAttendance()">Attendance</button><br>
</div>
<script>

function openNav() {
    document.getElementById("mySidenav").style.width = "250px";
}

function closeNav() {
    document.getElementById("mySidenav").style.width = "0";
}

function viewTask() {
    document.getElementById("taskInfo").style.display='block';
    document.getElementById("projectInfo").style.display='none';
    document.getElementById("attendanceInfo").style.display='none';
}

function viewAttendance() {
    document.getElementById("taskInfo").style.display='none';
    document.getElementById("projectInfo").style.display='none';
    document.getElementById("attendanceInfo").style.display='block';
}
</script>
     
</body>
</html> 
