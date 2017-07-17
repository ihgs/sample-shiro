package sample.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import sample.dao.bean.User;
import sample.dao.bean.UserToken;

public class UserTokenDao extends BaseDao {

	public String create(int userId, String name, String[] roles) throws SQLException {
		try (Connection conn = this.connection()) {
			String sql = "INSERT INTO UserToken (token, user_id, name, roles, created_at)" + " values (?, ?, ?, ?, ?)";
			String uuid = uuid();
			this.qr.update(conn, sql, uuid, userId, name, UserToken.rolesStr(roles), now());
			return uuid;
		}
	}

	public List<UserToken> list(int userId, boolean containRevoked) throws SQLException {
		String revoked = " and revoked=false";
		if (containRevoked){
			revoked = "";
		}
		try (Connection conn = this.connection()) {
			String sql = "SELECT * FROM UserToken where user_Id=?"+ revoked;
			ResultSetHandler<List<UserToken>> resultSet = new BeanListHandler<>(UserToken.class);
			return (List<UserToken>) this.qr.query(conn, sql, resultSet, userId);
		}
	}
	
	public void revoke(int id) throws SQLException{
		try (Connection conn = this.connection()) {
			String sql = "UPDATE UserToken set revoked=true, revoked_at=? where id=?";
			this.qr.update(conn, sql, now(), id);
		}
		
	}
	
	public void revokeByUser(int user_id) throws SQLException{
		try (Connection conn = this.connection()) {
			String sql = "UPDATE UserToken set revoked=true, revoked_at=? where user_id=? and revoked=false";
			int updated = this.qr.update(conn, sql, now(), user_id);
			System.out.println(updated);
		}
	}

	private Date now() {
		return new Date();
	}

	private String uuid() {
		return UUID.randomUUID().toString();
	}

}
