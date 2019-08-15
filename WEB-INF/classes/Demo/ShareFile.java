
package Demo;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpSession;
import javax.servlet.RequestDispatcher;
import java.util.ArrayList;

// Extend HttpServlet class
@WebServlet("/sharefile")
public class ShareFile extends HttpServlet {
 

   public void init() throws ServletException {
      
   }

   public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
      
      
  
      String senderName = request.getParameter("user_name");
      String receiverName = request.getParameter("receiver_name");
      String fileName = request.getParameter("file_name");
      String accessMode = request.getParameter("access_mode");
      String filePath = request.getParameter("file_path");
   //   String rootPath = "C:\\Users\\Administrator\\Desktop\\javatrav\\new\\";
      String rootPath = "C:\\Program Files\\Apache Software Foundation\\Tomcat 8.5\\webapps\\dm\\users\\";
      filePath = escapePath(filePath);
      String contents = Drmng.getFileJava(filePath);
      String targetFileName = rootPath + receiverName + "\\\\" + "received-files\\\\" + fileName;
      Drmng.createFileJava(targetFileName);
      Drmng.saveFileJava(targetFileName, contents);

   //   String logContents = "File Shared: " + fileName + "\nReceiver Name: "+receiverName+"\nAccess mode: "+accessMode+"\n\n";
   //   String pathToLog = rootPath+senderName+"\\\\logs\\\\logfile.log";
   //   Drmng.appendDataJava(pathToLog, logContents);
      String commonContents = "file-sent Receivername:"+receiverName+" Accessmode:"+accessMode;
      CommonLog.logContents(senderName, fileName, filePath, commonContents, false);
      commonContents = "file-received Sendername:"+senderName+" Accessmode:"+accessMode;
      CommonLog.logContents(receiverName, fileName, targetFileName, commonContents, false);
   //   logContents = "File Received: " + fileName + "\nSender Name: "+senderName+"\nAccess mode: "+accessMode+"\n\n";
   //   pathToLog = rootPath+receiverName+"\\\\logs\\\\logfile.log";
   //   Drmng.appendDataJava(pathToLog, logContents);

      File file = new File(targetFileName);
      if(accessMode.equals("readonly")){
        file.setWritable(false);
      }
      else{
        file.setWritable(true);
      }
      response.setContentType("text/html");
      PrintWriter out = response.getWriter();
      
      for(int i=0; i<UserManipulations.userList.size(); i++){  // updating in recievers end about the received file
         NewUser user = UserManipulations.userList.get(i);
       //   out.println("<h1>usr = "+user.username+"<h1>");
         

         if(user.username.equals(receiverName)){
            ArrayList<String> sfdetails = new ArrayList<>();
            sfdetails.add(senderName);
            sfdetails.add(filePath);
            sfdetails.add(accessMode);
            out.println("<h1>"+sfdetails+"<h1>");
        //    user.sharedFiles.put(fileName, senderName);
            user.sharedFiles.put(fileName, sfdetails);
            out.println("<h1>"+user.username+"</h1>");
            out.println("<h1>"+user.sharedFiles.get(fileName)+"<h1>");
            break;
         }
      }

      

      response.sendRedirect("workspace.jsp");
      
   }

   public void destroy() {
      // do nothing.
   }
   public static String escapePath(String path){
      return path.replace("\\", "\\\\");
   }
   
}