<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "form"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>

<head>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css" href="<c:url value = '/resources/dvdStore.css' />">
</head>

<body class="background" style="margin:200px">

<form:form method="post" align="center"> 
<div id="login">
 <table align="center">
        <tr>
            <td>
                <form:select path="role" style="width:193px;">
                        <form:option value="admin">Admin</form:option>
                        <form:option value="customer" selected="selected">Customer</form:option>
                </form:select>
            </td>
        </tr>
        <tr>
            <td><form:input type="text" path="userName" class="input" placeholder="User Name"/></td>
        </tr>
        <tr>
            <td><form:input type="password" path="password" class="input" placeholder="Password" />
            <button type="button" onclick="showPassword(this.form)"><i id ="eye" class="fa fa-eye-slash"></i></button></td>
        </tr>
        <tr>
            <td><button type="submit" formaction="/dvdstore/user/login" onclick="setRequired(this.form)" class="login" >Login</button></td>
        </tr>
        <tr/><tr/>
        <tr>
            <td><button type="button" style="width:193px;border-radius: 20px;margin-top:120px;height:25px" onclick="setVisible()">Register</botton></td>
        </tr>
 </table>
 </form:form>
</div>

<div id="register" style="display:none">
 <form:form action="user" method="post" align="center" > 
 <table align="center">
        <tr>
            <td><form:input type="text" path="userName" class="input" placeholder="User Name"/></td>
        </tr>
        <tr>
            <td><form:input type="password" path="password" class="input" placeholder="Password" />
            <button type="button" onclick="showPassword(this.form)"><i id ="eyeslash" class="fa fa-eye-slash"></i></button></td>
        </tr>
        <tr>
            <td><input type="password" name="confirmpassword" class="input" placeholder="Confirm Password"/></td>
        </tr>
        <tr>
            <td><button type="submit" style="width:193px;border-radius: 20px;margin: 8px 0;height:25px" formaction="/dvdstore/user/register" onclick="return(checkPassword(this.form) && setRequired(this.form))">Register</button></td>
        </tr>
        <input type="hidden" name="role" value="customer">
 </table>
 </form:form>
 </div>
 </body>
 
<c:if test="${not empty message}">
<script>
    alert ("${message}");
</script>
<c:remove var="message" scope="session" />
</c:if>
 
 <script>
function showPassword(form) {
    var password = form.password;
    if (password.type === "password") {
        password.type = "text";
        document.getElementById('eye').className='fa fa-eye';
        document.getElementById('eyeslash').className='fa fa-eye';
    } else {
        password.type = "password";
        document.getElementById("eye").className='fa fa-eye-slash';
        document.getElementById("eyeslash").className='fa fa-eye-slash';
    }
}

function checkPassword(form) {
    if (form.password.value === form.confirmpassword.value) {
        return true;
    } else {
        alert ("the password is not matched");
        return false;
    }
}

function setVisible() {
    document.getElementById("register").style.display="block";
    document.getElementById("login").style.display="none";
}

function setRequired(form) {
    form.username.required = true;
    form.password.required = true;
    form.confirmpassword.required = true;
}
 </script>
 </html>
