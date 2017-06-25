package sample.user;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class UserManager {
	
	private static class Store{
		private Map<String, User> users = new HashMap<>();
		private Store() {
			{
				User u1 = new User("admin","admintoken");
				u1.addRole("admin");
				u1.addRole("user");
				users.put(u1.getToken(), u1);
			}	
			{
				User u1 = new User("user1","usertoken");
				u1.addRole("user");
				users.put(u1.getToken(), u1);
			}	
			
		}
		
		private User get(String token){
			return users.get(token);
		}
		
		private void add(User user){
			users.put(user.getUsername(), user);
		}
		
		private Collection<User> list(){
			return new ArrayList<>(users.values());
		}
		
		private void delete(String username){
			users.remove(username);
		}
	}
	
	
	
	
	
	
	private static Store store = new Store();
		
	
	public static User getUser(String apitoken){
		return store.get(apitoken);
	}

	public static void addUser(User user){
		store.add(user);
	}
	
	public static Collection<User> list(){
		return store.list();
	}
	
	public static void delete(String username){
		store.delete(username);
	}
}
