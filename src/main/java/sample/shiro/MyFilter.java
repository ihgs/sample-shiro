package sample.shiro;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyFilter implements Filter{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("IN:doFilter");
		
		HttpServletRequest req = (HttpServletRequest)request;
		
		String apitoken  = req.getParameter("token");

		Subject currentUser = SecurityUtils.getSubject();
		if (currentUser.isAuthenticated()){
			System.out.println("Already "); //confirm deleting the session.
			chain.doFilter(req, response);
			return;
		}
		
		AuthenticationToken token = new MyAuthenticationToken(apitoken);
		
		try{
			currentUser.login(token);
		}catch(AuthenticationException e){
			throw e;
		}
		try{
			chain.doFilter(req, response);
		}finally{
			finish(currentUser);
		}
	}

	/**
	 * 一度の呼び出しでセッション情報を破棄する
	 * @param currentUser
	 */
	private void finish(Subject currentUser){
		currentUser.getSession().stop();
	}
	
	@Override
	public void destroy() {
		
	}

}
