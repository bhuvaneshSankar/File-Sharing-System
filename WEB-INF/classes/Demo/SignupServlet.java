
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
@WebServlet("/signup")
public class SignupServlet extends HttpServlet {
 

   public void init() throws ServletException {
      
   }

   public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
     
      String uname = request.getParameter("uname");
      String pass = request.getParameter("password");

      
      if(UserManipulations.signup(uname, pass)==true){
         request.setAttribute("username", uname);
         System.out.println("calling export class");
         Export.exportSignup(uname, pass);
         RequestDispatcher rd = request.getRequestDispatcher("/createuseriddir");

         rd.forward(request, response);   
      }
      else{
         response.sendRedirect("/");
      }
      
   }

   public void destroy() {
      // do nothing.
   }
   
}