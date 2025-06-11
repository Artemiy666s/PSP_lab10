<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.List" %>
<html><head><title>Rooms</title></head><body>
<% String user=(String)session.getAttribute("user");
   if(user==null){response.sendRedirect("login.jsp");return;} %>
<h1>Welcome, <%=user%></h1>
<form action="book" method="post">
  Room:
  <select name="room">
    <option>101</option><option>102</option>
    <option>201</option><option>202</option>
  </select>
  <input type="submit" value="Book"/>
</form>
<p><a href="logout">Logout</a></p>
</body></html>