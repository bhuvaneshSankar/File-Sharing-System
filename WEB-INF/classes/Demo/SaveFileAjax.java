
package Demo;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

// Extend HttpServlet class
@WebServlet("/savefileajax")
public class SaveFileAjax extends HttpServlet {
 

   public void init() throws ServletException {
      
   }

   public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
      
      // Set response content type
  //    String rootPath = "C:\\Users\\Administrator\\Desktop\\javatrav\\new\\";
      String rootPath = "C:\\Program Files\\Apache Software Foundation\\Tomcat 8.5\\webapps\\dm\\users\\";
      String filename = request.getParameter("filename");  //whole path
      String userName = request.getParameter("username");
      String contents = request.getParameter("contents");
      String filenamealone = request.getParameter("filenamealone");
      String isReceivedFile = Example.getIsReceivedFile();
      
      
  //    int check = filename.indexOf("received-files");
      if(isReceivedFile.equals("true")){          //shared file
        for(int i=0; i<UserManipulations.userList.size(); i++){
         NewUser user = UserManipulations.userList.get(i);
         if(user.username.equals(userName)){
            ArrayList<String> details = user.sharedFiles.get(filenamealone);
            String senderOfFile = details.get(0);
            String sentFilePath = details.get(1);
            String accessMode = details.get(2);
            String rcvrfp = rootPath+userName+"\\\\received-files\\\\"+filenamealone;
            if(accessMode.equals("write")){
            Drmng.saveFileJava(filename, contents);
            CommonLog.logContents(senderOfFile, filenamealone, sentFilePath, "shared-file-saved Receivername:"+userName+" Accessmode:"+accessMode, false);  //update at sender
            CommonLog.logContents(userName, filenamealone, rcvrfp, "file-saved SenderName:"+senderOfFile+" Accessmode:"+accessMode, false);    //update at receiver
            }
         }
        }
      }
      else{
        Drmng.saveFileJava(filename, contents);
        CommonLog.logContents(userName, filenamealone, filename, "file-saved", true);
      }

      response.sendRedirect("/workspace.jsp");   
   }

   public void destroy() {
      // do nothing.
   }

   
}