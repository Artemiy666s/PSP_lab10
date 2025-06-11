package com.example.lab10;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private Map<String, String> users = new HashMap<>();
    private Map<String, String> roles = new HashMap<>();

    @Override
    public void init() {
        users.put("user", "userpass");
        roles.put("user", "user");
        users.put("admin", "adminpass");
        roles.put("admin", "admin");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.sendRedirect("login.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        String u = req.getParameter("username"), p = req.getParameter("password");
        if (users.containsKey(u) && users.get(u).equals(p)) {
            HttpSession s = req.getSession();
            s.setAttribute("user", u);
            s.setAttribute("role", roles.get(u));
            if ("admin".equals(roles.get(u))) resp.sendRedirect("admin");
            else resp.sendRedirect("rooms");
        } else resp.sendRedirect("login.jsp?error");
    }
}