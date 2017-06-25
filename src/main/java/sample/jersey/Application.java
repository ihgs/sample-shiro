package sample.jersey;

import java.util.HashSet;
import java.util.Set;

import org.apache.shiro.web.jaxrs.ShiroFeature;

import sample.jersey.resources.AdminResource;
import sample.jersey.resources.HelloResource;
import sample.jersey.resources.UserResource;

public class Application extends javax.ws.rs.core.Application {

	
	@Override
	public Set<Class<?>> getClasses(){
		Set<Class<?>> classes = new HashSet<>();
        classes.add(ShiroFeature.class);
        classes.add(HelloResource.class);
        classes.add(AdminResource.class);
        classes.add(UserResource.class);
        
        return classes;
	}
	
	
}
