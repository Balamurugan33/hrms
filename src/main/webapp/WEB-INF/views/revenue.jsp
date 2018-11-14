<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<head>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script
    src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <jsp:include page='adminHeader.jsp'/>
  <style>
.revenue {
     background: white;
     display: inline-block;
     padding: 25px;
     border-radius: 20px;
     box-shadow: 10px 10px 10px 10px grey; 
     align: center;
     margin-top:100px;
     margin-left:10px;
     }
 </style>
  
</head>
<body>

    <div id="empRevenue" class="revenue">
        <form method="post" action="/hrms/employee/profit">
        <div class="form-group">
            <div class="input-group">
                <input type="date" class="form-control" name="startDate" id="startDate"
                    placeholder="Start Date" required="required">
            </div>
            <div class="input-group">
                <input type="date" class="form-control" name="endDate" id="endDate"
                    placeholder="End Date" required="required">
            </div>
            <div class="input-group">
                <button type="submit">Show</button>
            </div>
         </div>
         <input type="hidden" id="empId" name="empId">
        </form>
    </div>
    
</body>
</html>    