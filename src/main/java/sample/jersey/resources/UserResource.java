package sample.jersey.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import com.google.gson.Gson;

import sample.user.UserManager;

@Path("/user")
public class UserResource {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String userList(){
		Subject currentUser = SecurityUtils.getSubject();
		System.out.println(currentUser);
		Gson g = new Gson();
		
		return  g.toJson(UserManager.list());
	}
}
