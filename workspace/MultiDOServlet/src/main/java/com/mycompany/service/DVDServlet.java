package com.mycompany.service;

import com.mycompany.model.Model;
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
        Model m = new Model();
        PrintWriter out = response.getWriter();
        switch (request.getHeader("Accept").toLowerCase()) {
            case "application/xml":
                response.setContentType("application/xml");
                out.println(m.toXML());
                break;
            case "application/json":
                response.setContentType("application/json");
                out.println(m.toJSON());
                break;
            default:
                response.sendError(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE); // 415
        }
    }

    @Override
    public void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
