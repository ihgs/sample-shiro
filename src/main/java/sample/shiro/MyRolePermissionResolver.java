package sample.shiro;

import java.util.Collection;

import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.permission.RolePermissionResolver;

import sample.user.RoleManager;

public class MyRolePermissionResolver implements RolePermissionResolver{

	
	@Override
	public Collection<Permission> resolvePermissionsInRole(String roleString) {
		System.out.println("IN:resolvePermission:"+roleString);
		return RoleManager.getPermissions(roleString);
	}

}
