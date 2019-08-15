
package Demo;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

// Extend HttpServlet class
@WebServlet("/accessfile")
public class AccessFile extends HttpServlet {
 
   
   public void init() throws ServletException {
      
   }

   public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
          
      // Set response content type
  //    String rootPath = "C:\\Users\\Administrator\\Desktop\\javatrav\\new\\";
      String rootPath = "C:\\Program Files\\Apache Software Foundation\\Tomcat 8.5\\webapps\\dm\\users\\";
      String filename = request.getParameter("file_name");
      String userName = request.getParameter("username");
      String absolutePath = request.getParameter("absolute_path");
      String parentName = request.getParameter("parent_name");
      if(absolutePath.equals("none")){
        absolutePath = rootPath + userName + "\\\\" + filename;
      }
      else{
      absolutePath = escapePath(absolutePath);
      }
      if(parentName.equals("received-files")){
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        for(int i=0; i<UserManipulations.userList.size(); i++){
         NewUser user = UserManipulations.userList.get(i);
         if(user.username.equals(userName)){
        //    String senderOfFile = user.sharedFiles.get(filename);
            ArrayList<String> details = user.sharedFiles.get(filename);
            out.println("<h1>"+details+"</h1>");
            String senderOfFile = details.get(0);
            String sentFilePath = details.get(1);
            String accessMode = details.get(2);
       //     out.println("<h1>sndr = "+senderOfFile+"<h1>");
         //   String logContents = "Shared File Opened: " + filename + "\n";
            
      //      String logContents = "Shared File Opened:\nUser name: "+userName+"\nFile name: "+filename+"\n\n";
      //      String pathToLog = rootPath+senderOfFile+"\\\\logs\\\\logfile.log";
      //      Drmng.appendDataJava(pathToLog, logContents);
        //    CommonLog.logContents(senderOfFile, filename, absolutePath, "shared-file-opened Receivername:"+userName, false);
            CommonLog.logContents(senderOfFile, filename, sentFilePath, "shared-file-opened Receivername:"+userName+" Accessmode:"+accessMode, false);   //update at sender
            CommonLog.logContents(userName, filename, absolutePath, "file-opened Sendername:"+senderOfFile+" Accessmode:"+accessMode, false);  //update at receiver  
            Example.setFileNameAlone(filename);
            Example.setFileName(sentFilePath);
            String contents = Drmng.getFileJava(sentFilePath);
            Example.setFileContents(contents);
            Example.setIsReceivedFile("true");

         }
      }
      }
 //     if(!filename.equals("logfile.log")){
 //     String logContents = "File Opened: " + filename + "\npath: "+absolutePath.substring(50, absolutePath.length())+"\n"; 
  //    int index = absolutePath.indexOf(userName);
  //    String pathDisplay = absolutePath.substring(index, absolutePath.length());
  //    String logContents = "File Opened: " + filename + "\nlocation: "+pathDisplay+"\n\n";
  //    String pathToLog = rootPath+userName+"\\\\logs\\\\logfile.log";
  //    Drmng.appendDataJava(pathToLog, logContents);
      if(! parentName.equals("received-files")){
        CommonLog.logContents(userName, filename, absolutePath, "file-opened", true);
        Example.setFileNameAlone(filename);
        Example.setFileName(absolutePath);
        String contents = Drmng.getFileJava(absolutePath);
        Example.setFileContents(contents);

      }
 //     }
      
 
 
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