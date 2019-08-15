
package Demo;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

// Extend HttpServlet class
@WebServlet("/createFileNew")
public class CreateFileNew extends HttpServlet {
 
   public void init() throws ServletException {
      
   }

   public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
        
      Example obj = new Example();
      // Set response content type
  //    String commonLogPath = "C:\\Program Files\\Apache Software Foundation\\Tomcat 8.5\\webapps\\dm\\logs\\commonlog.log";
  //    String rootPath = "C:\\Users\\Administrator\\Desktop\\javatrav\\new\\";
      String rootPath = "C:\\Program Files\\Apache Software Foundation\\Tomcat 8.5\\webapps\\dm\\users\\";
      String username = request.getParameter("username");
      String fname = request.getParameter("file_name");
      String path = request.getParameter("directory_name");
      String absolutePath = request.getParameter("absolute_path");
      System.out.println(absolutePath);
 //     String relPath = absolutePath.substring(50, absolutePath.length());
 //     String contents = "file created: " + fname + "\npath: "+absolutePath.substring(50, absolutePath.length())+"\n"; 
      
  //    String relPath = absolutePath.substring(index, absolutePath.length());
  //    response.setContentType("text/html");
  //    PrintWriter out = response.getWriter();
  //    out.println("<h1>"+index+"</h1>");
  //    out.println("<h1>"+username+"</h1>");
  //    out.println("<h1>"+absolutePath+"</h1>");
      String pathDisplay = "";
      if(absolutePath.equals("none")){
        pathDisplay = username+"\\"+fname;
      }
      else{
        int index = absolutePath.indexOf(username);
        pathDisplay = absolutePath.substring(index, absolutePath.length());
        pathDisplay += "\\"+fname;
      }
  //    String contents = "file created: " + fname + "\nlocation: "+pathDisplay+"\n\n";
  //    String pathToLog = rootPath+username+"\\\\logs\\\\logfile.log";
  //    Drmng.appendDataJava(pathToLog, contents);
   

      if(absolutePath.equals("none")){
        absolutePath = rootPath + username+"\\\\"+fname;
      }
      else{
      absolutePath = escapePath(absolutePath);
      absolutePath+= "\\\\"+fname;
      }
  /*    String abspath = absolutePath;
      abspath = escapeDoublePath(abspath);
      String commonContents = "Username:"+username+" Filename:"+fname+" location:"+abspath+" file-created\n";
      Drmng.appendDataJava(commonLogPath, commonContents);   */
      CommonLog.logContents(username, fname, absolutePath, "File-Created", true);
      Drmng.createFileJava(absolutePath);

      RequestDispatcher rd = request.getRequestDispatcher("EcreateFileNew?username=username&directory_name=path&absolute_path=absolutePath&file_name=fname");
      rd.forward(request, response);

  //    response.sendRedirect("/workspace.jsp");      
   }

   public void destroy() {
      // do nothing.
   }
   public static String escapePath(String path)
    {
    return path.replace("\\", "\\\\");
    }
   public static String escapeDoublePath(String path)
    {
    return path.replace("\\\\", "\\");
    }
}