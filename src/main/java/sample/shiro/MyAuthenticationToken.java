package sample.shiro;

import org.apache.shiro.authc.AuthenticationToken;

import sample.user.User;
import sample.user.UserManager;

public class MyAuthenticationToken implements AuthenticationToken{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4928436088733021624L;
	private String token;
	
	public MyAuthenticationToken(String token) {
		this.token = token;
	}

	@Override
	public Object getPrincipal() {
		User user = UserManager.getUser(token);
		return user;
	}

	@Override
	public Object getCredentials() {
		return token;
	}

}
