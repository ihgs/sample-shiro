package sample.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.jdbc.SQL;

import sample.dao.bean.User;
import sample.dao.bean.UserToken;

public class UserDao extends BaseDao {

	public void create(String username) throws SQLException {
		try (Connection conn = connection()) {
			String sql = new SQL(){{
				INSERT_INTO("MyUser");
				VALUES("name", "?");
			}}.toString();
			
			System.out.println(sql);
			//String sql = "INSERT into MyUser (name) values (?)";
			this.qr.update(conn, sql, username);
		}
	}

	public static class ParamObject{
		
		private List<Object> parameters = new ArrayList<>();
		
		public ParamObject add(Object o){
			parameters.add(o);
			return this;
		}
		public ParamObject add(Object[] o){
			parameters.addAll(Arrays.asList(o));
			return this;
		}
		
		public Object[] params(){
			return parameters.toArray();
		}
	}
	private String hatena(int size){
		StringBuilder sb = new StringBuilder("?");
		for (int i=0;i<size-1;i++){
			sb.append(",?");
		}
		return sb.toString();
	}
	public List<User> list(String name, Integer... ids) throws SQLException {
		ParamObject po = new ParamObject();
		try(Connection conn = connection()){
			String sql = new SQL(){{
				SELECT("*");
				FROM("MyUser");
				if(name!=null){
					WHERE("name=?");
					po.add(name);
				}
				if(ids.length > 0){
					WHERE("id in ("+hatena(ids.length)+")");
					po.add(ids);
				}
			}}.toString();
			System.out.println(sql);
//			String sql = "SELECT * from MyUser";
			ResultSetHandler<List<User>> resultSet = new BeanListHandler<>(User.class);
			return (List<User>) this.qr.query(conn, sql, resultSet, po.params());
		}
	}
	
	
	public static void main(String[]args) throws Exception{
		UserDao ud = new UserDao();
		ud.create("taaro");
		List<User> ulist = ud.list("taro",201,401,301);
		System.out.println(ulist.size());
		for(User u : ulist){
			System.out.println(u);
			
		};
		
		UserTokenDao utd  = new UserTokenDao();
//		utd.create(2, "api", new String[]{"a","b"});
//		utd.revokeByUser(2);
//		List<UserToken> utlist = utd.list(2, true);
//		for(UserToken t : utlist){
//			System.out.println(t);
//		}
	}

}
