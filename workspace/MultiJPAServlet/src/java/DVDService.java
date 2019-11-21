/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import entities.DVDS;
import entities.Library;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author bowsnickiklewe
 */
// http://localhost:8080/JPAServlet/UserServer
@WebServlet(name = "DVDService", urlPatterns = {"/service"})
public class DVDService extends HttpServlet {

    @SuppressWarnings("empty-statement")
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = null;
        try {
            out = response.getWriter();
            request.setCharacterEncoding("UTF-8");

            // Library lib = new Model().getLibrary();
            Model m = new Model();

            /*
            out.print(new Model().toJSON());
            out.print("\n\n");
            out.print(new Model().toXML());
             */
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
                    response.sendError(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE);
            }
        } catch (Exception e) {
            out.write(e.getMessage());
            throw new ServletException(e.getMessage());
        } finally {
            try {
                out.close();
            } catch (Exception e) {
            }
        }
    }

// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
