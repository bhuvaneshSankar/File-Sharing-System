
package Demo;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.io.PrintWriter;


// Extend HttpServlet class
@WebServlet("/deletedirectory")
public class DeleteDirectory extends HttpServlet {
 

   public void init() throws ServletException {
      
   }

   public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
      
      // Set response content type
      String directoryName = request.getParameter("directory_name");
      String username = request.getParameter("username");
      String absolutePath = request.getParameter("absolute_path");
 //     String rootPath = "C:\\Users\\Administrator\\Desktop\\javatrav\\new\\";
      String rootPath = "C:\\Program Files\\Apache Software Foundation\\Tomcat 8.5\\webapps\\dm\\users\\";
   //   String contents = "Directory deleted: " + directoryName + "\npath: "+absolutePath.substring(50, absolutePath.length())+"\n"; 
      
 //     int index = absolutePath.indexOf(username);
 //     String pathDisplay = absolutePath.substring(index, absolutePath.length());
 //     String contents = "Directory deleted: " + directoryName + "\nlocation: "+pathDisplay+"\n\n";
 //     String pathToLog = rootPath+username+"\\\\logs\\\\logfile.log";
 //     Drmng.appendDataJava(pathToLog, contents);
      
      CommonLog.logContents(username, directoryName, absolutePath, "Directory-Deleted", true);
 //     PrintWriter out = response.getWriter();
 //     out.println("<h1>"+absolutePath+"<h1>");
 //     out.println("<h1>" +directoryName + "</h1>");
      Drmng.deleteDirectoryJava(absolutePath);
      
      response.sendRedirect("/workspace.jsp");
      
   }

   public void destroy() {
      // do nothing.
   }

   
}