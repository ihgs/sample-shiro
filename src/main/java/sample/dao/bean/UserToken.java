package sample.dao.bean;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Date;

import au.com.bytecode.opencsv.CSVWriter;
import lombok.Data;

@Data
public class UserToken {
	
	private int id;
	private String token;
	private int user_id;
	private String name;
	private String roles;
	private Date created_at;
	private boolean revoked;
	private Date revoked_at;
	
	public static String rolesStr(String[] roles)  {
		try (StringWriter sw = new StringWriter(); CSVWriter w = new CSVWriter(sw)) {
			w.writeNext(roles);
			return sw.toString();
		}catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}
}
