package dsa1.servlet;

/*
 * Copyright 2004 The Apache Software Foundation
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
/* $Id: HelloWorldExample.java,v 1.3 2006/10/12 14:31:28 abadea Exp $
 *
 */
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Just another Servlet example
 *
 * @author Bernd Owsnicki
 */
public class ParamServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String title = "Reading Request Parameters";
        out.println("<html><head><title>" + title + "<</title></head>" + 
                "<body>" +
                "<h1>" + title + "</h1>" +
                "<ul>" +
                "  <li>param1: " + request.getParameter("param1") + "</li>" +
                "  <li>param2: " + request.getParameter("param2") + "</li>" +
                "  <li>param3: " + request.getParameter("param3") + "</li>" +
                "</ul>");
        
        
        out.println("</body></html>");
    }

    @Override
    public void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}



