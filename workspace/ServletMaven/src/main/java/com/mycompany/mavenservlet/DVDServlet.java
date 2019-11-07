package com.mycompany.mavenservlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/service")
public class DVDServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/xml");
        PrintWriter out = response.getWriter();
        out.println("<library>\n"
                + "    <dvd id=\"1\">\n"
                + "        <title>Breakfast at Tiffany's</title>\n"
                + "        <format>Movie</format>\n"
                + "        <genre>Classic</genre>\n"
                + "    </dvd>\n"
                + "    <dvd id=\"2\">\n"
                + "        <title>Contact</title>\n"
                + "        <format>Movie</format>\n"
                + "        <genre>Science fiction</genre>\n"
                + "    </dvd>\n"
                + "    <dvd id=\"3\">\n"
                + "        <title>Little Britain</title>\n"
                + "        <format>TV Series</format> \n"
                + "        <genre>Comedy</genre>\n"
                + "    </dvd>\n" +
        "</library>");
    }

    @Override
    public void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
