package sample.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;

public class BaseDao {

	protected QueryRunner qr = new QueryRunner();

	protected Connection connection() throws SQLException{
		DataSource ds;
		try {
			ds = datasource();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return ds.getConnection();
//		return DriverManager.getConnection("jdbc:derby:./db/SampleDB;");
	}
	
	private DataSource datasource() throws NamingException{
		InitialContext context = new InitialContext();
		return (DataSource)context.lookup("java:comp/env/jdbc/jnditest");
	}
}
