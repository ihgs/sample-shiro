package sample.user;

import java.util.HashSet;
import java.util.Set;

public class User {
	private String username;
	private Set<String> roleSet = new HashSet<>();

	public User(String username) {
		this.username = username;
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
