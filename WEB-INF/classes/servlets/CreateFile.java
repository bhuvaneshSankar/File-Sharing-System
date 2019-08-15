
package servlets;

import Demo.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.io.PrintWriter;


// Extend HttpServlet class
@WebServlet("/createFile")
public class CreateFile extends HttpServlet {
 

   public void init() throws ServletException {
      
   }

   public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
      
      // Set response content type
      String fname = request.getParameter("file_name");
      Drmng.createFileJava(fname);
      response.setContentType("text/html");
      PrintWriter out = response.getWriter();
      out.println("<html>");
      out.println("<body>");
      out.println(fname);
      out.println("</body>");
      out.println("</html>");

      
   }

   public void destroy() {
      // do nothing.
   }
}