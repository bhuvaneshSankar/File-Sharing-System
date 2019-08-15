
package Demo;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDateTime; 
import java.time.format.DateTimeFormatter;

// Extend HttpServlet class
@WebServlet("/logofafile")
public class LogOfAFile extends HttpServlet {
 

   public void init() throws ServletException {
      
   }

   public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
        String username = request.getParameter("username");
        String filename = request.getParameter("filename");
        String location = request.getParameter("location");
        String parentname = request.getParameter("parent_name");
        username = escapeSpaces(username);
        filename = escapeSpaces(filename);
        location = escapePath(location);
        location = escapeSpaces(location);
        String logContents = "";
        BufferedReader reader;
        reader = new BufferedReader(new FileReader("C:\\Program Files\\Apache Software Foundation\\Tomcat 8.5\\webapps\\dm\\logs\\commonlog.log"));
        String line = reader.readLine();
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
  //      out.println("<h3>"+username+" "+filename+" "+location+"\n"+"</h3>");
        while(line!=null){
          String[] splitLine = line.split(" ");
          int index;
          if(splitLine.length>3){
            String loguname = splitLine[0];
            index = loguname.indexOf(":");
            loguname = loguname.substring(index+1, loguname.length());

            String logfname = splitLine[1];
            index = logfname.indexOf(":");
            logfname = logfname.substring(index+1, logfname.length());

            String logfpath = splitLine[2];
            index = logfpath.indexOf(":");
            logfpath = logfpath.substring(index+1, logfpath.length());
            logfpath = escapePath(logfpath);
            
            
      //      out.println("<h3>"+loguname+" "+logfname+" "+logfpath+"\n"+"</h3>");

            if(username.equals(loguname) && filename.equals(logfname) && location.equals(logfpath)){
               for(int i=3; i<splitLine.length; i++){
                 logContents += splitLine[i]+" ";
               }
               logContents+="\n";

            }
            

          }
          line = reader.readLine();
        }
        reader.close(); 
        Example.setFileNameAlone("");
        Example.setFileName("");
        Example.setFileContents(logContents);    
      response.sendRedirect("/workspace.jsp");  

      
   }

   public void destroy() {
      // do nothing.
   }

   public static String escapePath(String path)
    {
    return path.replace("\\", "");
    }
    public static String escapeSpaces(String str){
      return str.replaceAll("\\s", "");
    }
     
    
   
}