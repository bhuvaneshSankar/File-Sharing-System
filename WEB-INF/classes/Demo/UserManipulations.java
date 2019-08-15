package Demo;
import java.util.ArrayList;
import Demo.*;
public class UserManipulations{
	static ArrayList<NewUser> userList = new ArrayList<>();
	static ArrayList<String> userNamesList = new ArrayList<>();
	public static boolean signup(String username, String password){
		if(!userExists(username)){
			NewUser user = new NewUser(username, password);
			userList.add(user);
			userNamesList.add(username);
			return true;
		}
		return false;
	}
	public static ArrayList getUserNamesList(){
		return userNamesList;
	}
	public static ArrayList getUserList(){
		return userList;
	}
	public static void printAl(){
		for(int i=0; i<userList.size(); i++){
			NewUser obj = userList.get(i);
			System.out.println(obj.username);
		}
	}
	public static boolean loginCheckup(String username, String password){
		if(userExists(username)){
			for(int i=0; i<userList.size(); i++){
				NewUser user = userList.get(i);
				String uname = user.username;
				if(uname.equals(username)){
					String pass = user.password;
					if(pass.equals(password)){
						return true;
					}
				}
			}
		}
		return false;
	}
	public static boolean userExists(String username){
		for(int i=0; i<userList.size(); i++){
			NewUser user = userList.get(i);
			String uname = user.username;
			if(uname.equals(username))
				return true;
		}
		return false;
	}
}