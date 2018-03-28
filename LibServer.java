import Library.*;
import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextPackage.*;
import org.omg.CORBA.*;
import java.util.*;
class LibServer extends _LibImplBase
{
	HashMap< String , Integer > storage = new HashMap<>();
	public LibServer(){
	storage.put("DS",2);
	storage.put("DBMS",2);
	storage.put("AIT",2);
}
	
	public boolean issue_book(String b_name){
		if(storage.get(b_name) != null){
			int b_get = storage.get(b_name);
			if(b_get > 0){
				storage.put(b_name,b_get-1);
				return true;}
			else
				return false;
			}
		return false;
	}

	public boolean return_book(String b_name){
		if(storage.get(b_name) != null){
			int r_book = storage.get(b_name);
			storage.put(b_name,r_book + 1);
			return true;
		}
		else return false;
	}

	public static void main(String args[]){
		try{
			ORB orb = ORB.init(args,null);
			LibServer libRef = new LibServer();
			orb.connect(libRef);
			org.omg.CORBA.Object ObjRef = orb.resolve_initial_references("NameService");
			NamingContext ncRef = NamingContextHelper.narrow(ObjRef);
			NameComponent nc = new NameComponent("Lib","");
			NameComponent path[] = {nc};
			ncRef.rebind(path,libRef);
			System.out.println("Server Started.....");
			Thread.currentThread().join();

		}
		catch(Exception e){
			System.out.print(e);
		}
	}
}
