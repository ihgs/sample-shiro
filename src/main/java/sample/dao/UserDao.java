package sample.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import sample.dao.bean.User;
import sample.dao.bean.UserToken;

public class UserDao extends BaseDao {

	public void create(String username) throws SQLException {
		try (Connection conn = connection()) {
			String sql = "INSERT into MyUser (name) values (?)";
			this.qr.update(conn, sql, username);
		}
	}

	public List<User> list() throws SQLException {
		try(Connection conn = connection()){
			String sql = "SELECT * from MyUser";
			ResultSetHandler<List<User>> resultSet = new BeanListHandler<>(User.class);
			return (List<User>) this.qr.query(conn, sql, resultSet);
		}
	}
	
	public static void main(String[]args) throws Exception{
//		UserDao ud = new UserDao();
//		ud.create("taro");
//		List<User> ulist = ud.list();
//		System.out.println(ulist.size());
//		for(User u : ulist){
//			System.out.println(u);
//			
//		};
		
		UserTokenDao utd  = new UserTokenDao();
//		utd.create(2, "api", new String[]{"a","b"});
		utd.revokeByUser(2);
		List<UserToken> utlist = utd.list(2, true);
		for(UserToken t : utlist){
			System.out.println(t);
		}
	}

}
