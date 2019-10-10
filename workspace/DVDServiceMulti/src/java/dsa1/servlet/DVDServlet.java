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
public class DVDServlet extends HttpServlet {

    private static enum DocType {
        XML,
        JSON,
        HTML,
        TEXT,
        OTHER
    };

    @Override
    public void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        DocType choice;
        PrintWriter out = response.getWriter();

        switch (request.getHeader("Accept").toLowerCase()) {
            case "application/xml":
                choice = DocType.XML;
                break;
            case "application/json":
                choice = DocType.JSON;
                break;
            case "text/html":
                choice = DocType.HTML;
                break;
            default:
                choice = DocType.OTHER;
        }

        switch (choice) {
            case XML:
                response.setContentType("application/xml");
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
                        + "    </dvd>\n"
                        + "    <dvd id=\"4\">\n"
                        + "        <title>" + request.getHeader("Accept") + "</title>\n"
                        + "        <format>TV Series</format> \n"
                        + "        <genre>Comedy</genre>\n"
                        + "    </dvd>\n"
                        + "</library>");
                out.close();
                break;
            case JSON:
                response.setContentType("application/json");
                out.println("{\n"
                        + "    \"dvds\": [\n"
                        + "        {\n"
                        + "            \"id\": 1,\n"
                        + "            \"title\": \"Breakfast at Tiffany's\",\n"
                        + "            \"format\": \"Movie\",\n"
                        + "            \"genre\": \"Classic\"\n"
                        + "        },\n"
                        + "        {\n"
                        + "            \"id\": 2,\n"
                        + "            \"title\": \"Contact\",\n"
                        + "            \"format\": \"Movie\",\n"
                        + "            \"genre\": \"Science Fiction\"\n"
                        + "        },\n"
                        + "        {\n"
                        + "            \"id\": 3,\n"
                        + "            \"title\": \"Little Britain\",\n"
                        + "            \"format\": \"TV Series\",\n"
                        + "            \"genre\": \"Comedy\"\n"
                        + "        }\n"
                        + "    ]\n"
                        + "}\n");
                out.close();
                break;
            case HTML:
                response.setContentType("text/html");
                out.print("<html>\n"
                        + "<head>\n"
                        + "<META http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n"
                        + "<title>DVD library</title>\n"
                        + "<link rel=\"stylesheet\" href=\"libhtml.css\" type=\"text/css\" media=\"screen\">\n"
                        + "</head>\n"
                        + "<body>\n"
                        + "<div id=\"main\">\n"
                        + "<h1>Our DVD offers</h1>\n"
                        + "<table border=\"0\" cellpadding=\"6\" cellspacing=\"3\" width=\"100%\">\n"
                        + "<thead>\n"
                        + "<tr>\n"
                        + "<td>Title</td><td>Format</td><td>Genre</td>\n"
                        + "</tr>\n"
                        + "</thead>\n"
                        + "<tr>\n"
                        + "<td class=\"title\">Breakfast at Tiffany's</td><td>Movie</td><td>Classic</td>\n"
                        + "</tr>\n"
                        + "<tr>\n"
                        + "<td class=\"title\">Contact</td><td>Movie</td><td>Science fiction</td>\n"
                        + "</tr>\n"
                        + "<tr>\n"
                        + "<td class=\"title\">Little Britain</td><td>TV Series</td><td>Comedy</td>\n"
                        + "</tr>\n"
                        + "</table>\n"
                        + "</div>\n"
                        + "</body>\n"
                        + "</html>");
                out.close();
                break;
            default:
                response.sendError(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE);
        }
    }

    @Override
    public void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
