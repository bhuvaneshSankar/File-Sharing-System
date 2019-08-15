
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
import java.util.ArrayList;

// Extend HttpServlet class
@WebServlet("/deletefile")
public class DeleteFile extends HttpServlet {
 
  private  static Logger logger;
  FileHandler fh;
   public void init() throws ServletException {
      
   }

   public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
      
      // Set response content type

      String username = request.getParameter("username");
      String filename = request.getParameter("file_name");
      String absolutePath = request.getParameter("absolute_path");
      String parentName = request.getParameter("parent_name");
      absolutePath = escapePath(absolutePath);
      
//      String rootPath = "C:\\Users\\Administrator\\Desktop\\javatrav\\new\\";
      String rootPath = "C:\\Program Files\\Apache Software Foundation\\Tomcat 8.5\\webapps\\dm\\users\\";
//      String contents = "file deleted: " + filename + "\npath: "+absolutePath.substring(50, absolutePath.length())+"\n"; 
      String pathDisplay = "";
      
        int index = absolutePath.indexOf(username);
        pathDisplay = absolutePath.substring(index, absolutePath.length());
    //    pathDisplay += "\\"+filename;
      

   //   String contents = "file deleted: " + filename + "\nlocation: "+pathDisplay+"\n\n";
   //   String pathToLog = rootPath+username+"\\\\logs\\\\logfile.log";
   //   Drmng.appendDataJava(pathToLog, contents);

      CommonLog.logContents(username, filename, absolutePath, "File-Deleted", true);
  
      if(parentName.equals("received-files")){
         for(int i=0; i<UserManipulations.userList.size(); i++){
           NewUser user = UserManipulations.userList.get(i);
           if(user.username.equals(username)){
             ArrayList<String> details = user.sharedFiles.get(filename);
             String senderOfFile = details.get(0);
             String sentFilePath = details.get(1);
             String accessMode = details.get(2);
       //      String logContents = "Shared File Deleted:\nUser name: "+username+"\nFile name: "+filename+"\n\n";
       //      pathToLog = rootPath+senderOfFile+"\\\\logs\\\\logfile.log";
       //      Drmng.appendDataJava(pathToLog, logContents);
             CommonLog.logContents(senderOfFile, filename, sentFilePath, "shared-file-deleted Receivername:"+username+" Accessmode:"+accessMode, false);   //update at sender
             
           }
         }
      }

      Drmng.deleteFileJava(absolutePath);
      
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