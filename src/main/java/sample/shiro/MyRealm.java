package sample.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;

import sample.shiro.UserManager.User;

public class MyRealm extends AuthorizingRealm{

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		User u = (User)principals.getPrimaryPrincipal();
		AuthorizationInfo info = new SimpleAuthorizationInfo(u.getRoles() );
		return info;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		UsernamePasswordToken utoken = (UsernamePasswordToken)token;
		
		char[] apitoken = utoken.getPassword();
		User user = UserManager.getUser(apitoken);
		if(user == null){
			throw new AuthenticationException(String.valueOf(apitoken));
		}
		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, utoken.getPassword(), this.getClass().getName());
		PrincipalCollection principals = new SimplePrincipalCollection(user, this.getClass().getName());
				
		info.setPrincipals(principals );
		return info;
	}

}
