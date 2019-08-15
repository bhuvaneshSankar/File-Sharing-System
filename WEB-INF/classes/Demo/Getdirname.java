
package Demo;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;

// Extend HttpServlet class
@WebServlet("/getdirname")
public class Getdirname extends HttpServlet {
 

   public void init() throws ServletException {
      
   }

   public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
       
      String s = request.getParameter("dir_name");
      Example object = new Example();
      Example.setFormDirecName(s);
      request.setAttribute("previousDirectoryname", s);
  //    RequestDispatcher rd = request.getRequestDispatcher("/createDirectoryNew");
  //    rd.forward(request, response);
  //    response.setContentType("text/html");
  //    PrintWriter out = response.getWriter();
  //    out.println("<h1>"+object.getFormDirecName()+"<h1>");
    //  response.sendRedirect("/");
      
   }

   public void destroy() {
      // do nothing.
   }


   
}