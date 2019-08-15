package Demo;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpSession;


// Extend HttpServlet class
@WebServlet("/createuseriddir")
public class CreateUserIdDir extends HttpServlet {
 

   public void init() throws ServletException {
      
   }

   public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
      
      Object username = request.getAttribute("username");
   //   String rootPath = "C:\\Users\\Administrator\\Desktop\\javatrav\\new\\";
      String rootPath = "C:\\Program Files\\Apache Software Foundation\\Tomcat 8.5\\webapps\\dm\\users\\";
      String direcname = rootPath+username.toString();
      Drmng.createDirectoryJava(direcname);
   //   Drmng.createDirectoryJava(direcname+"\\\\logs");
   //   Drmng.createFileJava(direcname+"\\\\logs\\\\"+"logfile.log");
      Drmng.createDirectoryJava(direcname+"\\\\"+"received-files");
      RequestDispatcher rd = request.getRequestDispatcher("/");
         rd.forward(request, response);
 //     response.sendRedirect("/index.jsp");
   }

   public void destroy() {
      // do nothing.
   }
   
}