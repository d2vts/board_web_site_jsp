package jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcUtil { // JdbcUtil.close()시에 각 Connection resulutSett Statement 들을 닫아주는 역할을 하게하기 위해 만든 클래스

	public static void close(ResultSet rs) {
		if(rs != null) {
			try {
				rs.close();
			}catch(SQLException ex) {}
		}
	}
	public static void close(Statement stmt) {
		if(stmt != null) {
			try {
				stmt.close();
			}catch(SQLException ex) {}
		}
	}
	public static void close(Connection conn) {
		if(conn != null) {
			try {
				conn.close();
			}catch(SQLException ex) {}
		}
	}
	public static void rollback(Connection conn) {
		if(conn != null) {
			try {
				conn.rollback();
			}catch(SQLException ex) {}
		}
	}
	
	
}
