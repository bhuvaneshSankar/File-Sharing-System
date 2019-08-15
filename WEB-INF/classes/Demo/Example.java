package Demo;
import java.util.ArrayList;
import java.io.File;
public class Example{
	public static ArrayList<String> directory = new ArrayList<String>();
	
	static String fileContents="";
    static String fileName = "";
    static String fileNameAlone = "";
    static String formDirecName = "none";
    static String isReceivedFile = "false";

    public static void setIsReceivedFile(String str){
        isReceivedFile = str;
    }
    public static String getIsReceivedFile(){
        String cache = isReceivedFile;
        isReceivedFile = "false";
        return cache;
    }
    public static void setFormDirecName(String str){
        formDirecName = str;
    }
    public static String getFormDirecName(){
        String cache = formDirecName;
    //    formDirecName = "none";
        return cache;
    }
    
    public static void setFileNameAlone(String filename){
        fileNameAlone = filename;
    }
    public static String getFileNameAlone(){
        String cache = fileNameAlone;
        fileNameAlone = "";
        return cache;
    } 
    public static String escapePath(String path)
    {
    return path.replace("\\", "\\\\");
    }
    public static void setFileName(String str){
        fileName = str;
    }
    public static String getFileName(){
        String cache = fileName;
        fileName = "";
        return cache;
        
    }

    public static void setFileContents(String str){
        fileContents = str;
    }
    public static String getFileContents(){
        String cache = fileContents;
        fileContents = "";
        return cache;
        
    }
	
	public static void traverseDir(File[] files, ArrayList<File> direc) {
        for (File file : files) {
            if (file.isDirectory()) {
                System.out.println("Directory: " + file.getName());
                direc.add(file);
                traverseDir(file.listFiles(), direc); // Calls same method again.
            } else {
                direc.add(file);
                System.out.println("File: " + file.getName());
            }
        }
    }
    public static ArrayList getDir(String username) {
        ArrayList<File> direc = new ArrayList<>();
    //    String pathToWorkSpace = "C:\\Users\\Administrator\\Desktop\\javatrav\\new";
        String pathToWorkSpace = "C:\\Program Files\\Apache Software Foundation\\Tomcat 8.5\\webapps\\dm\\users";
        pathToWorkSpace += "\\" + username;
        File[] files = new File(pathToWorkSpace).listFiles();
        traverseDir(files, direc);
        return direc;
    }
	
}