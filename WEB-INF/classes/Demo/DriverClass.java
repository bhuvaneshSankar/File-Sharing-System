package Demo;
import java.util.ArrayList;
import java.util.HashMap;
public class DriverClass{
	public static void main(String[] args){
	UserManipulations.printAl();
	System.out.println("inside fn");
	ArrayList<NewUser> al = UserManipulations.getUserList();
	System.out.println(al);
	for(int i=0; i<UserManipulations.userList.size(); i++){
		 System.out.println(i);
         NewUser user = UserManipulations.userList.get(i);
         System.out.println(user.username);
      }
  }
}