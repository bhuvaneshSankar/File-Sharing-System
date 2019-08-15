package Demo;
import Demo.*;
import java.time.LocalDateTime; 
import java.time.format.DateTimeFormatter;
public class CommonLog{
	static String commonLogPath = "C:\\Program Files\\Apache Software Foundation\\Tomcat 8.5\\webapps\\dm\\logs\\commonlog.log";
	public static void logContents(String username, String filename, String abspath, String contents, boolean toEscapews){
		abspath = escapeDoublePath(abspath);
		username = escapeSpaces(username);
		filename = escapeSpaces(filename);
		abspath = escapeSpaces(abspath);
		if(toEscapews==true){
			contents = escapeSpaces(contents);
		}
		String currentTime = getTime();
		String commonContents = "Username:"+username+" Filename:"+filename+" location:"+abspath+" "+contents+" "+currentTime+"\n";
        Drmng.appendDataJava(commonLogPath, commonContents);

	}
	public static void logUsers(String username, String contents){
		username = escapeSpaces(username);
		contents = escapeSpaces(contents);
		String currentTime = getTime();
		String commonContents = "Username:"+username+" "+contents+" "+currentTime+"\n";
  		Drmng.appendDataJava(commonLogPath, commonContents);
	}
	public static String escapeDoublePath(String path)
    {
    	int index = path.indexOf("\\\\");
    	if(index!=-1)
        	path.replace("\\\\", "\\");
       	return path;
    }	
    public static String escapeSpaces(String str){
    	return str.replaceAll("\\s", "");
    }
    public static String getTime(){
      LocalDateTime myDateObj = LocalDateTime.now();  
      DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"); 
      String formattedDate = myDateObj.format(myFormatObj); 
      return formattedDate; 
    }
}