package jdbc;

import java.io.IOException;
import java.io.StringReader;
import java.sql.DriverManager;
import java.util.Properties;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.dbcp2.ConnectionFactory;
import org.apache.commons.dbcp2.DriverManagerConnectionFactory;
import org.apache.commons.dbcp2.PoolableConnection;
import org.apache.commons.dbcp2.PoolableConnectionFactory;
import org.apache.commons.dbcp2.PoolingDriver;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;


//ServletContextListener 인터페이스를 구현한 클래스 실행하려면 web.xml에 <listener> 태그와 <listener-class>에 클래스 풀네임 적어주면 됨
public class DBCPInitListener implements ServletContextListener{

	/*
	 * 
	  ServletContextListener - 웹 어플리케이션이 시작되거나 종료될 때 호출할 메서드를 정의한 인터페이스.
	  
	  - public void contextInitialized(ServletContextEvent sce) : 웹어플리케이션을 초기화할 때 호출
	  
	  - public void contextDestroyed(ServletContextEvent sce) : 웹 어플리케이션을 종료할 때 호출.
	  
	 */
	
	@Override
	public void contextInitialized(ServletContextEvent sce) { //웹어플리케이션을 초기화할 때 호출
		String poolConfig = sce.getServletContext().getInitParameter("poolConfig");
		//getServletContext()은 web.xml에 설정된 컨텍스트 초기화 파라미터를 구할 수 있다.
		//getInitParameter(String name)은 지정한 이름을 갖는 컨텍스트 초기화 파라미터 값을 리턴. name 파라미터에는 <param-name> 값 
		//
		Properties prop = new Properties();//String, String 의 형태로 저장하는 컬렉션클래스 Properties 객체생성
		try {
			prop.load(new StringReader(poolConfig));// "키=값" 형식으로 구성된 문자열로부터 프로퍼티 로딩
		} catch( IOException e) {
			throw new RuntimeException("config load fail", e); // poolConfig 로딩 실패시의 익셉션 처리 
		}
		loadJDBCDriver(prop); // prop를 파라미터값으로 JDBC 드라이버 로딩 메소드 호출
		initConnectionPool(prop);// prop를 파라미터값으로 커넥션풀 접속 초기화 메소드 호출
	}
	
	private void loadJDBCDriver(Properties prop) {
		String driverClass = prop.getProperty("jdbcdriver"); // jdbcdriver 의 값을 driverClass에 저장
		try {
			Class.forName(driverClass);// jdbc 드라이버 로딩(mysql)
		}catch(ClassNotFoundException ex) {
			throw new RuntimeException("JDBC드라이버를 불러오는데 실패했습니다."); // mysql과 연동 실패시 익셉션 처리
		}
	}
	
	private void initConnectionPool(Properties prop) {
		try {
			String jdbcUrl = prop.getProperty("jdbcUrl"); // jdbcUrl의 값을 jdbcUrl에 저장
			String username = prop.getProperty("dbUser"); // username의 값을 dbUser에 저장
			String pw = prop.getProperty("dbPass"); // pw의 값을 dbPass에 저장
			jdbcUrl += "&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
			//mysql 버전이 5.1.23 보다 높은 버전을 사용하면 MySQL 타임존의 시간표현 포맷이 달라져서 connector 에서 인식을 하지 못한다고 함
			
			/*
			 * 해결방법이 아래와 같이 나와있는데  utf8 쓰고있으니 useUnicode 지우고 jdbcUrl 뒤에 &useJDBCCompliantTimezonShift부터 쭉 적어주니 오류 해결 
			private static String dburl = "jdbc:mysql://localhost:3306/DB이름?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
			 									**********timezone이 안맞으니 드라이버를 com.mysql.cj.jdbc.Driver로 바꿔버림 주의 해야할듯 **********
			*/
			
			
			ConnectionFactory connFactory = new DriverManagerConnectionFactory(jdbcUrl, username, pw);
			// prop.getProperty로 받아온 값들로 새로운 커넥션을 생성할 때 사용할 커넥션 팩토리를 생성. MySQL에 연결할때 사용할 위의 세정보를 생성자로 지정. 
			
			PoolableConnectionFactory poolableConnFactory = new PoolableConnectionFactory(connFactory, null);
			//PoolableConnection을 생성하는 팩토리를 생성.  커넥션 풀에 커넥션을 보관할 때 PoolableConnection을 사용한다.
			//내부적으로 실제 커넥션을 담고 있으며, 커넥션 풀을 관리하는 데 필요한 기능을 추가로 제공 ex) close()호출시 커넥션 종료않고 풀에 커넥션 반환
			String validationQuery = prop.getProperty("validationQuery");
			// web.xml에서 validationQuery값 validationQuery에 저장
			if(validationQuery != null&& !validationQuery.isEmpty()) {
				poolableConnFactory.setValidationQuery(validationQuery);
			}//validationQuery가 null값이아니고 비어있는값이 아니라면 poolableConnectionFactory.setValidationQuery로 커넥션 유효성 검사
			GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();//커넥션풀 설정 정보 객체 생성
			poolConfig.setTimeBetweenEvictionRunsMillis(1000L*60L*5L);//유휴 커넥션 검사 주기
			poolConfig.setTestWhileIdle(true);// 풀에 보관 중인 커넥션이 유효한지 검사할지 여부
			int minIdle = getIntProperty(prop, "minIdle",5); //getIntProperty(파라미터들)의 값을 minIdle에 저장
			poolConfig.setMinIdle(minIdle);// 커넥션 최소 개수 지정 
			int maxTotal = getIntProperty(prop, "maxIdle", 50);//getIntProperty(파라미터들)의 값을 maxTotal에 저장
			poolConfig.setMaxTotal(maxTotal);// 커넥션 최대 개수 지정 
			
			GenericObjectPool<PoolableConnection> connectionPool = new GenericObjectPool<>(poolableConnFactory, poolConfig);
			//커넥션 풀을 생성. 생성자로  poolableConnectionFactory와 poolConfig를 파라미터로 전달받음
			poolableConnFactory.setPool(connectionPool); //생성한 커넥션 풀을 연결
			
			Class.forName("org.apache.commons.dbcp2.PoolingDriver");// 커넥션 풀을 제공하는 JDBC 드라이버를 등록
			PoolingDriver driver = (PoolingDriver)DriverManager.getDriver("jdbc:apache:commons:dbcp:");
			//PoolingDriver의 객체생성. driver이라는 변수에 지정한 JDBC의 URL(jdbc:apache:commons:dbcp)을 이해할 수 있는 드라이버를 구하여 driver에 저장
			String poolName = prop.getProperty("poolName");// 풀의 이름으로 사용할 스트링 변수를 생성하여 poolName값을 prop.getProperty로 가져와서 저장
			driver.registerPool(poolName, connectionPool);//풀의 이름으로 prop.getProperty가 가져오는 값을 지정해줌
			} catch(Exception e) {
				throw new RuntimeException("여기서 문제가 생김",e); // 상위 메소드로 메소드 던짐
			}
	}
	
	private int getIntProperty(Properties prop, String propName, int defaultValue) {
		String value = prop.getProperty(propName);
		
		if(value == null) return defaultValue; // value 값이 null 값이라면 defaultValue 값을 리턴
		
		return Integer.parseInt(value); // value값을 정수형태로 형변환후 리턴
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent sce) {}
}
