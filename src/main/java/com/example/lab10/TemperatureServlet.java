package com.example.lab10;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.*;
import java.util.stream.Collectors;

@WebServlet("/analyze")
public class TemperatureServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        InputStream in = getServletContext().getResourceAsStream("/data/temps.txt");
        List<Integer> temps = new ArrayList<>();
        try (Scanner sc = new Scanner(in)) {
            while (sc.hasNextInt()) {
                temps.add(sc.nextInt());
            }
        }
        double avg = temps.stream().mapToInt(Integer::intValue).average().orElse(0);
        long above = temps.stream().filter(t -> t > avg).count();
        long belowZero = temps.stream().filter(t -> t < 0).count();
        List<Map.Entry<Integer,Integer>> top3 = IntStream.range(0, temps.size())
            .mapToObj(i -> new AbstractMap.SimpleEntry<>(i+1, temps.get(i)))
            .sorted(Map.Entry.<Integer,Integer>comparingByValue().reversed())
            .limit(3)
            .collect(Collectors.toList());

        resp.setContentType("text/html; charset=UTF-8");
        try (PrintWriter out = resp.getWriter()) {
            out.println("<!DOCTYPE html><html><head><title>Analysis Results</title></head><body>");
            out.printf("<p>Average monthly temperature: %.2f</p>", avg);
            out.printf("<p>Days above average: %d</p>", above);
            out.printf("<p>Days below 0°C: %d</p>", belowZero);
            out.println("<p>Three warmest days:</p><ul>");
            for (Map.Entry<Integer,Integer> e : top3) {
                out.printf("<li>Day %d: %d°C</li>", e.getKey(), e.getValue());
            }
            out.println("</ul><p><a href=\"temps.jsp\">Back</a></p>");
            out.println("</body></html>");
        }
    }
}