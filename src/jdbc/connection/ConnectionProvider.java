package jdbc.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionProvider {

	public static Connection getConnection() throws SQLException{ // 예외 발생시 호출한곳으로 넘김
		return DriverManager.getConnection("jdbc:apache:commons:dbcp:board"); // ConnectionProvider.getConnection()시 DB인 board에 연결을 리턴
	}
}
