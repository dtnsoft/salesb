package com.offact.framework.db;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbcp.BasicDataSource;

public class SPDataSource extends BasicDataSource {

	public Connection getConnection() throws SQLException {
		Connection con = super.getConnection();
		return this.getLogConnection(con);
	}

	/**
	 */
	public Connection getConnection(String username, String password)
			throws SQLException {
		Connection con = super.getConnection(username, password);
		return con == null ? null : this.getLogConnection(con);
	}

	protected Connection getLogConnection(Connection con) {
		
		Connection rv;

		try {
			rv = new LogConnection(con);

		} catch (Throwable t) {
			try {
				con.close();
			} catch (SQLException ignore) {
			}
			if (t instanceof Error) {
				throw (Error) t;
			} else if (t instanceof RuntimeException) {
				throw (RuntimeException) t;
			} else {
				throw new RuntimeException(
						"loggging connection wrapping fail.", t);
			}
		}
		return rv;
	}
}
