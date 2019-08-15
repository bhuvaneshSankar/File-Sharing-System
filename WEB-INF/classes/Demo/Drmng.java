package Demo;
import java.*;
import java.util.Scanner;
import java.util.ArrayList;
public class Drmng{
	public native boolean createDirectory(String direcName);
	public native void deleteDirectory(String direcName);
	public native void listDirectoryContents(String direcName);
	public native boolean createFile(String fileName);
	public native boolean deleteFile(String fileName);
	public native void appendDataToFile(String fileName, String data);
	public native ArrayList getDirectoryStructure();
	public native String getFile(String fileName);
	public native void saveFile(String fileName, String contents);
    static{
    	System.setProperty( "java.library.path", "C:\\Program Files\\Apache Software Foundation\\Tomcat 8.5\\lib\\dr1cpp.dll" );
    	System.loadLibrary("dr1cpp");
//    	try{
//    	System.setProperty( "java.library.path", "C:\\Program Files\\Apache Software Foundation\\Tomcat 8.5\\lib\\dr1cpp.dll" );
//	    System.load("C:\\Program Files\\Apache Software Foundation\\Tomcat 8.5\\webapps\\dm\\WEB-INF\\classes\\Demo\\drcpp");
//    	System.load("C://Program Files//Apache Software Foundation//Tomcat 8.5//webapps//dm//WEB-INF//classes//Demo//dr1cpp.dll");
	}
	static Drmng object = new Drmng();
	public static void createDirectoryJava(String direcName){
		System.out.println(direcName);
		object.createDirectory(direcName);
	//	return direcName;
	}
	public static void deleteDirectoryJava(String direcName){
		object.deleteDirectory(direcName);
	}
	public static void createFileJava(String fileName){
	/*	String rootPath = "C:\\Users\\Administrator\\Desktop\\javatrav\\new\\";
		String logFile = rootPath + username + "\\\\" + "logs\\\\" + "logfile.log";
      LogClass.setLogger(logFile);
      LogClass.logr.info("file created  " + name);  */
		object.createFile(fileName);
	}
	public static void deleteFileJava(String fileName){
		object.deleteFile(fileName);
	}
	public static String getFileJava(String fileName){
		String s = object.getFile(fileName);
		System.out.println(fileName);
		return s;
	}
	public static void saveFileJava(String fileName, String contents){
		object.saveFile(fileName, contents);
	}
	public static void appendDataJava(String fileName, String contents){
		object.appendDataToFile(fileName, contents);
	}
	
}
