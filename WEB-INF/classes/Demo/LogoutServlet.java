
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
@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
 

   public void init() throws ServletException {
      
   }

   public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
       
        HttpSession session = request.getSession();
        Object uname = session.getAttribute("username");

        session.removeAttribute("username");
        session.invalidate();
        CommonLog.logUsers(uname.toString(), "logged-out");
        response.sendRedirect("/");
            
   }

   public void destroy() {
      // do nothing.
   }

   
}