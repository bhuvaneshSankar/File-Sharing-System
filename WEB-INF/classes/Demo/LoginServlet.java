
package Demo;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpSession;
import javax.servlet.RequestDispatcher;

// Extend HttpServlet class
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
 

   public void init() throws ServletException {
      
   }

   public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    
      String uname = request.getParameter("uname");
      String pass = request.getParameter("password");

 
      if(UserManipulations.loginCheckup(uname, pass)==true){
        HttpSession session = request.getSession();
        session.setAttribute("username", uname);
        CommonLog.logUsers(uname, "logged-in");
        request.getRequestDispatcher("workspace.jsp").forward(request, response);

      }
      else{
      //  response.sendRedirect("loginjsp.jsp");
        response.sendRedirect("/");
      }
      
   }

   public void destroy() {
      // do nothing.
   }
   
}