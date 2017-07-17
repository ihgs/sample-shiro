package sample.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;

public class BaseDao {

	protected QueryRunner qr = new QueryRunner();

	protected Connection connection() throws SQLException{
		return DriverManager.getConnection("jdbc:derby:./db/SampleDB;");
	}
}
