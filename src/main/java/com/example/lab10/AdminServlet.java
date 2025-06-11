package com.example.lab10;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin")
public class AdminServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        HttpSession s = req.getSession(false);
        if (s == null || !"admin".equals(s.getAttribute("role"))) {
            resp.sendRedirect("login.jsp");
            return;
        }
        resp.setContentType("text/html; charset=UTF-8");
        try (var out = resp.getWriter()) {
            out.println("<html><head><title>All Bookings</title></head><body>");
            out.println("<h1>All Bookings</h1>");
            List<String> b = (List<String>) getServletContext().getAttribute("bookings");
            if (b != null) {
                out.println("<ul>");
                for (String e : b) out.println("<li>" + e + "</li>");
                out.println("</ul>");
            } else out.println("<p>No bookings.</p>");
            out.println("<p><a href=\"logout\">Logout</a></p></body></html>");
        }
    }
}