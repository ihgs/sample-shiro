package sample.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import sample.user.User;

public class MyRealm extends AuthorizingRealm{

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		User u = (User)principals.getPrimaryPrincipal();
		
		AuthorizationInfo info = new SimpleAuthorizationInfo(u.getRoles() );
		return info;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		MyAuthenticationToken utoken = (MyAuthenticationToken)token;
		
		User user = (User)token.getPrincipal();
		
		if(user == null){
			throw new AuthenticationException(String.valueOf(token.getCredentials()));
		}
		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, utoken.getCredentials(), this.getClass().getName());
		return info;
	}
	
	
	@Override
	public boolean supports(AuthenticationToken token) {
		return token instanceof MyAuthenticationToken;
	}
	
	

}
