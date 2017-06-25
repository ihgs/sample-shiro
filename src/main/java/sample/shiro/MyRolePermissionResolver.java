package sample.shiro;

import java.util.Arrays;
import java.util.Collection;

import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.permission.RolePermissionResolver;
import org.apache.shiro.authz.permission.WildcardPermission;

public class MyRolePermissionResolver implements RolePermissionResolver{

	@Override
	public Collection<Permission> resolvePermissionsInRole(String roleString) {
		System.out.println("IN:resolvePermission:"+roleString);
		Permission permsion = new WildcardPermission("top:read");
		return Arrays.asList(permsion);
	}

}
