
package Demo;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

// Extend HttpServlet class
@WebServlet("/savefile")
public class SaveFile extends HttpServlet {
 

   public void init() throws ServletException {
      
   }

   public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
      
      // Set response content type
      String rootPath = "C:\\Users\\Administrator\\Desktop\\javatrav\\new\\";
      String filename = request.getParameter("file_name");
      String userName = request.getParameter("username");
      String contents = request.getParameter("text");
      String filenamealone = request.getParameter("file_name_alone");
      if(!filenamealone.equals("logfile.log")){
      Drmng.saveFileJava(filename, contents);
   //  response.setContentType("text/html");
   //   PrintWriter out = response.getWriter();
   //   out.println("<h1>filename<h1>");
  //    out.println("<h1>contents = "+contents+"<h1>");
   //   String logContents = "File Saved: " + filenamealone + "\npath: "+filename.substring(50, filename.length())+"\n"; 
      int index = filename.indexOf(userName);
      String pathDisplay = filename.substring(index, filename.length());
      String logContents = "File Saved: " + filenamealone + "\nlocation: "+pathDisplay+"\n\n";
      String pathToLog = rootPath+userName+"\\\\logs\\\\logfile.log";
      Drmng.appendDataJava(pathToLog, logContents);
      CommonLog.logContents(userName, filenamealone, filename, "file-saved", true);
  //    Drmng.saveFileJava(filename, contents);

      int check = filename.indexOf("received-files");
      if(check!=-1){
  //      response.setContentType("text/html");
  //    PrintWriter out = response.getWriter();
  //    out.println("<h1>received-files<h1>");
  //    out.println("<h1>size = "+UserManipulations.userList.size()+"<h1>");
  //    response.sendRedirect("/");
        for(int i=0; i<UserManipulations.userList.size(); i++){
         NewUser user = UserManipulations.userList.get(i);
  //       out.println("<h1>uname = "+user.username+"<h1>");
  //       out.println("<h1>sf = "+user.sharedFiles+"<h1>");
         if(user.username.equals(userName)){
     //       String senderOfFile = user.sharedFiles.get(filenamealone);
            ArrayList<String> details = user.sharedFiles.get(filenamealone);
            String senderOfFile = details.get(0);
            String sentFilePath = details.get(1);
  //          out.println("<h1>sndr = "+senderOfFile+"<h1>");
         //   String logContents = "Shared File Opened: " + filename + "\n";
            String logContentss = "Shared File Saved:\nUser name: "+userName+"\nFile name: "+filenamealone+"\n\n";
            String pathToLogg = rootPath+senderOfFile+"\\\\logs\\\\logfile.log";
            Drmng.appendDataJava(pathToLogg, logContentss);
            CommonLog.logContents(senderOfFile, filenamealone, sentFilePath, "shared-file-saved Receivername:"+userName, false);
         }
      }
      }
    }
  //    response.sendRedirect("/workspace.jsp");   
   }

   public void destroy() {
      // do nothing.
   }

   
}