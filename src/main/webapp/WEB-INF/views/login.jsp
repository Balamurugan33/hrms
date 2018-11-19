<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<body>
<head>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
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
                    <label for="user" class="label">Email Address</label>
                    <input type="text" title="please enter emailId" name="userName" class="input" required>
                </div>
                <div class="group">
                    <label for="pass" class="label">Password</label>
                    <input type="password" title="please enter password" name="password" class="input" required >
                    <button type="button" class="eye-button" onclick="showPassword(this.form)"><i id ="eyeslash" class="fa fa-eye-slash"></i></button>
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
                    <label for="user" class="label">Email Address</label>
                    <input type="text" title="please enter already register emailId" name="userName" class="input" required>
                </div>
                <div class="group">
                    <label for="pass" class="label">Password</label>
                    <input type="password" title="please enter password" id="psw" name="password" class="input" required>
                    <button type="button" class="eye-button" onclick="showPassword(this.form)"><i id ="eye" class="fa fa-eye-slash"></i></button>
                </div>
                <div class="group">
                    <label for="pass" class="label">Repeat Password</label>
                    <input type="password" name="confirmpassword" title="please repeat password" id="con-psw" class="input" onkeyup="checkPassword()" required><p id="error"></p>
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
<script>
function checkPassword() {
    if (document.getElementById("psw").value != document.getElementById("con-psw").value) {
    	document.getElementById("error").style.color ="#ff0000b5";
    	document.getElementById("error").innerHTML ="Passsword Missmatched";
    } else {
    	document.getElementById("error").innerHTML ="";
    }
}

function showPassword(form) {
    var password = form.password;
    if (password.type === "password") {
        password.type = "text";
        form.confirmpassword.type = "text";
        document.getElementById('eye').className='fa fa-eye';
        document.getElementById('eyeslash').className='fa fa-eye';
    } else {
        password.type = "password";
        form.confirmpassword.type = "password";
        document.getElementById('eye').className='fa fa-eye-slash';
        document.getElementById("eyeslash").className='fa fa-eye-slash';
    }
}

</script>
</html>
