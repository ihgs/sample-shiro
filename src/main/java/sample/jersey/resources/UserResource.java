package sample.jersey.resources;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;

import com.google.gson.Gson;

import sample.dao.UserDao;
import sample.user.User;
import sample.user.UserManager;

@Path("/users")
public class UserResource {

	@GET
	@RequiresRoles(value={"user","admin"}, logical=Logical.OR)
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String userList(){
		Gson g = new Gson();

		UserDao dao = new UserDao();
		try {
			List<sample.dao.bean.User> ulist = dao.list(null);
			return  g.toJson(ulist);

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
//		Collection<User> users = UserManager.list();
//		
//		Subject currentUser = SecurityUtils.getSubject();
//		if (!currentUser.hasRole("admin")){
//			Collection<User> notokenUser = new ArrayList<>();
//			for (User u : users){
//				User us = new User(u.getUsername(), null);
//				us.addRole(u.getRoles().toArray(new String[0]));
//				notokenUser.add(us);
//			}
//			return  g.toJson(notokenUser);
//		}else{
//			return  g.toJson(users);
//		}	
	}
	
	@POST
	@RequiresPermissions("user:create")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String createUser(@QueryParam("username") String username, @QueryParam("role") final List<String> roles){
		User u = new User(username);
		System.out.println(roles);
		for (String role: roles){
			u.addRole(role);
		}
		
		UserManager.addUser(u);
		return new Gson().toJson(u);
	}
}
