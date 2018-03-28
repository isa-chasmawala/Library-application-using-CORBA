import java.util.*;
import Library.*;
import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextPackage.*;
import org.omg.CORBA.*;
public class LibClient{
	public static void main(String args[]){
		try{	
			ORB orb = ORB.init(args,null);
			org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
			NamingContext ncRef = NamingContextHelper.narrow(objRef);
			NameComponent nc = new NameComponent("Lib","");
			NameComponent path[]={nc};
			Lib Ref = LibHelper.narrow(ncRef.resolve(path));
			System.out.println("Enter your choice:\n1.To issue\n2.To return\n");
			Scanner sc=new Scanner(System.in);
			int choice = sc.nextInt();
			switch(choice){
				case 1: System.out.print("Enter book name:");
						String b_name=sc.next();
						boolean res = Ref.issue_book(b_name);
						if(res == true)
							System.out.println("Book issued.");
						else
							System.out.println("Book OOS.");
						break;
				case 2: System.out.print("Enter book name:");
						String b_name1 = sc.next();
						boolean res1 = Ref.return_book(b_name1);
						if(res1 == true)
							System.out.println("Book recieved.");
						else
							System.out.println("Invalid.");
						break;
			}
		}
		catch(Exception e){}
	}
}
