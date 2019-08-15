package Demo;
import java.util.HashMap;
import java.util.ArrayList;
public class NewUser{
	String username = "";
	String password = "";
//	ArrayList<String> sharedFiles = new ArraisyLt<>();
//	HashMap<String, String> sharedFiles = new HashMap<>();
	HashMap<String, ArrayList<String>> sharedFiles= new HashMap<>();
	HashMap<String, ArrayList<String>> sentFiles = new HashMap<>();
	NewUser(String username, String password){
			this.username = username;
			this.password = password;
	}
}