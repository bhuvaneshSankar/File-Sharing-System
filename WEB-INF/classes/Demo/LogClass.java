package Demo;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.io.IOException; 
import java.util.logging.Level;  
import java.util.logging.*; 
  
public class LogClass{
	final static Logger logr = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	public static void setLogger(String filename){
		LogManager.getLogManager().reset();
		logr.setLevel(Level.ALL);
		try{
			FileHandler fh = new FileHandler(filename, true);
			fh.setLevel(Level.FINE);
			SimpleFormatter sf = new SimpleFormatter();
      		fh.setFormatter(sf);
			logr.addHandler(fh);
		}
		catch(IOException e){
			logr.log(Level.SEVERE,"File logger not working", e);
		}
		
	}
	
}