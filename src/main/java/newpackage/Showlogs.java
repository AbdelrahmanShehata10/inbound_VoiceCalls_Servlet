/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package newpackage;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author heba
 */
public class Showlogs extends HttpServlet {

DataBase db=new DataBase();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       PrintWriter pen=response.getWriter();
        
       
       ResultSet res=db.getcalls();

       
           try {
                  pen.println("<html>\n" +
"    <head>   <link rel=\"stylesheet\" href=\"newcss.css\"></head>\n" +
"    <body>\n" +
"        <h1>Call Logs</h1>\n" +
"        <table class=\"list\">\n" +
"            <thead class=\"list\">\n" +
"                <tr  class=\"list\">\n" +
"                    <td class=\"list top\">ID</td>\n" +
"                    <td class=\"list top\">To</td>\n" +
"                    <td class=\"list top\">From</td>\n" +
 "                    <td class=\"list top\">Status</td>\n" +

"                </tr>\n" +
"            </thead>\n" +
"            <tbody class=\"list\">");

while (res.next()) {
    pen.println("<tr class=\"list\">\n" +
                 "    <td class=\"list id\">" + res.getString("callsid") + "</td>\n" +
                 "    <td class=\"list\">" + res.getString("recieverr") + "</td>\n" +
                "    <td class=\"list\">" + res.getString("senderr") + "</td>\n" +
 "    <td class=\"list\">" + res.getString("CallDuration") + "</td>\n" +
 "</tr>\n");
}

pen.println("        <script src=\"newjavascript.js\"></script>\n" +
"</tbody></table></body></html>");

           
           } catch (SQLException ex) {
ex.printStackTrace();           }
     
               }
}
