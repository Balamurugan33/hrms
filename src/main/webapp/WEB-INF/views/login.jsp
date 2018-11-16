<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<body>
<head>
<link rel="stylesheet" type="text/css" href="<c:url value = '/resources/login.css' />">
</head>

<div class="login-wrap">
    <div class="login-html">
        <input id="tab-1" type="radio" name="tab" class="sign-in" checked><label for="tab-1" class="tab">Sign In</label>
        <input id="tab-2" type="radio" name="tab" class="sign-up"><label for="tab-2" class="tab">Sign Up</label>
        <div class="login-form">
            <div class="sign-in-htm">
                
                <form method="post">
                <div class="group">
                    <label for="user" class="label">User Name</label>
                    <input type="text" name="userName" class="input" required>
                </div>
                <div class="group">
                    <label for="pass" class="label">Password</label>
                    <input type="password" name="password" class="input" data-type="password" required>
                </div>
                <div class="group">
                    <input type="submit" class="button" formaction="/hrms/user/login" value="Sign In">
                </div>
                </form>
                
                <div class="hr"></div>
            </div>
            
            <div class="sign-up-htm">
                <form method="post">
                <div class="group">
                    <label for="user" class="label">User Name</label>
                    <input type="text" name="userName" class="input" required>
                </div>
                <div class="group">
                    <label for="user" class="label">Register Email</label>
                    <input type="text" name="emailId" class="input" required>
                </div>
                <div class="group">
                    <label for="pass" class="label">Password</label>
                    <input type="password" name="password" class="input" data-type="password" required>
                </div>
                <div class="group">
                    <label for="pass" class="label">Repeat Password</label>
                    <input type="password" class="input" data-type="password">
                </div>
                <div class="group">
                    <input type="submit" formaction="/hrms/user/register" class="button" value="Sign Up">
                </div>
                <input type="hidden" name="role" value="Employee">
                </form>
                <div class="hr"></div>
                <div class="foot-lnk">
                    <label for="tab-1">Already Member?</a>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<c:if test="${not empty message}">
<script>
    alert ("${message}");
</script>
</c:if>
</html>
