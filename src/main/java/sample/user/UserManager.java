package sample.user;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class UserManager {
	
	public static class User{
		private String username;
		private Set<String> roleSet = new HashSet<>();
		
		private User(String username){
			this.username = username;
		}
		
		public String getUsername(){
			return this.username;
		}
		
		public void addRole(String...roles){
			for (String role :roles){
				this.roleSet.add(role);
			}
		}
		
		public Set<String> getRoles(){
			return this.roleSet;
		}
		
		@Override
		public String toString() {
			return this.username;
		}
		
	}
		
	
	public static User getUser(String apitoken){
		return getUser(apitoken.toCharArray());
	}
	
	public static User getUser(char[] apitoken) {
		Map<String, User> store = new HashMap<>();
		{
			User u1 = new User("user1");
			u1.addRole("admin");
			u1.addRole("user");
			store.put(u1.username, u1);
		}	
		{
			User u1 = new User("user2");
			u1.addRole("user");
			store.put(u1.username, u1);
		}	
		{
			User u1 = new User("user3");
			u1.addRole("user");
			store.put(u1.username, u1);
		}
		
		String str = String.valueOf(apitoken);
		
		return store.get(str);
	}

}
