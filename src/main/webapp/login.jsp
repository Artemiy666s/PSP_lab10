<%@ page contentType="text/html; charset=UTF-8" %>
<html><head><title>Login</title></head><body>
<h1>Login</h1>
<% if(request.getParameter("error")!=null){ %>
  <p style="color:red;">Login failed</p>
<% } %>
<form action="login" method="post">
  Username: <input name="username"/><br/>
  Password: <input type="password" name="password"/><br/>
  <input type="submit" value="Login"/>
</form>
</body></html>