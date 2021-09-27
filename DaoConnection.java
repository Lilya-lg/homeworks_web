package ua.itea;

import java.sql.Connection;

public interface DaoConnection {
	 public static DbConnector getInstance() {
		 return null;
	 }
	 public Connection getConnection();
}
