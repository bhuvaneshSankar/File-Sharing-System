package Demo;
import Edemo.*;
//E:\Apache Software Foundation\Tomcat 8.5_Tomcat8h2\webapps\Edm\WEB-INF\classes
//import E.Apache Software Foundation.Tomcat 8.5_Tomcat8h2.webapps.Edm.WEB-INF.classes.Edemo.*;
public class Export{
	public static void exportSignup(String username, String password){
		System.out.println("C:  "+ username+" "+password);
		Import.importSignup(username, password);
	}
}