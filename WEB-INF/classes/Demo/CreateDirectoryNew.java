
package Demo;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.io.PrintWriter;


// Extend HttpServlet class
@WebServlet("/createDirectoryNew")
public class CreateDirectoryNew extends HttpServlet {
 

   public void init() throws ServletException {
      
   }

   public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {


      Example obj = new Example();
  //    String rootPath = "C:\\Users\\Administrator\\Desktop\\javatrav\\new\\";
      String rootPath = "C:\\Program Files\\Apache Software Foundation\\Tomcat 8.5\\webapps\\dm\\users\\";
      String username = request.getParameter("username");
      String previousdirec = request.getParameter("prev_directory_name");
 //     String pd = previousdirec;
      String direcname = request.getParameter("directory_name");
      String absolutePath = request.getParameter("absolute_path");
      if(absolutePath.equals("none")){
        absolutePath = rootPath + username + "\\\\" + direcname;
      }
      else{
      absolutePath = escapePath(absolutePath);
      absolutePath += "\\\\" + direcname;
       }
  //    String contents = "Directory created: " + direcname + "\npath: "+absolutePath.substring(50, absolutePath.length())+"\n"; 
      
      String pathDisplay = "";
      if(absolutePath.equals("none")){
        pathDisplay = username+"\\"+direcname;
      }
      else{
        int index = absolutePath.indexOf(username);
        pathDisplay = absolutePath.substring(index, absolutePath.length());
 //       pathDisplay += "\\"+direcname;
      }

  //    String contents = "Directory created: " + direcname + "\nlocation: "+pathDisplay+"\n\n";
  //    String pathToLog = rootPath+username+"\\\\logs\\\\logfile.log";
  //    Drmng.appendDataJava(pathToLog, contents);


      CommonLog.logContents(username, direcname, absolutePath, "Directory-Created", true);



      Drmng.createDirectoryJava(absolutePath);
     
      response.sendRedirect("/workspace.jsp");  

      
   }

   public void destroy() {
      // do nothing.
   }

   public static String escapePath(String path)
    {
    return path.replace("\\", "\\\\");
    }
   
}