package sample.user;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Properties;

import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.permission.WildcardPermission;

public class RoleManager {

	private static RoleManager manager;
	
	private Properties prop;
	
	private RoleManager() {
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		prop = new Properties();
		try(InputStream input = loader.getResourceAsStream("role.properties")){
			prop.load(input);			
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public  Collection<Permission> permissions(String role){
		String ps = (String) prop.get(role);
		if(ps!=null){
			Collection<Permission> pslist = new ArrayList<>();
			 for(String s :ps.split(",")){
				 pslist.add(new WildcardPermission(s));
			 }
			 return pslist;
		}else{
			return Collections.emptyList();
		}
	}
	
	public static Collection<Permission> getPermissions(String role){
		if (manager==null){
			manager = new RoleManager();
		}
		return manager.permissions(role);
	}

}
