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

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

/**
 * Just another Servlet example
 *
 * @author Bernd Owsnicki
 */
public class MyFirstServlet extends HttpServlet {

    private int accessCount = 0;

    @Override
    public void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws IOException, ServletException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<html>");
        out.println("<head>");

        out.println("<title>My First Servlet</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Servlet activation " + accessCount++ + "</h1>");
        out.println("Ya see? Servlets are persistent!");
        out.println("</body>");
        out.println("</html>");
    }
}
