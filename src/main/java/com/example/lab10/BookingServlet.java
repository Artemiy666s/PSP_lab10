package com.example.lab10;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.*;

@WebServlet("/book")
public class BookingServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        HttpSession s = req.getSession(false);
        if (s == null || s.getAttribute("user") == null) {
            resp.sendRedirect("login.jsp");
            return;
        }
        String room = req.getParameter("room"), user = (String) s.getAttribute("user");
        List<String> list = (List<String>) getServletContext().getAttribute("bookings");
        if (list == null) list = new ArrayList<>();
        list.add(user + " booked room " + room);
        getServletContext().setAttribute("bookings", list);
        resp.sendRedirect("rooms");
    }
}