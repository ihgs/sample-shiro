package sample.user;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class User {
	private String username;
	private String token;
	public String getToken() {
		return token;
	}

	private Set<String> roleSet = new HashSet<>();

	
	public User(String username, String token){
		this.username = username;
		this.token = token;
	}
	
	public User(String username) {
		this.username = username;
		this.token = UUID.randomUUID().toString();
	}

	public String getUsername() {
		return this.username;
	}

	public void addRole(String... roles) {
		for (String role : roles) {
			this.roleSet.add(role);
		}
	}

	public Set<String> getRoles() {
		return this.roleSet;
	}

	@Override
	public String toString() {
		return this.username;
	}
	
}
