package member.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import member.dao.MemberDao;
import member.model.Member;

public class JoinService {

	
	private MemberDao memberDao = new MemberDao();
	
	public void join(JoinRequest joinReq) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);// 자동으로 커밋하는것을 방지함
			
			Member member = memberDao.selectById(conn, joinReq.getId()); // MemberDao의 selectById()를 이용해서 joinReq.getId()에 해당하는 회원 데이터 구함
			if(member!=null) {
				JdbcUtil.rollback(conn);
				throw new DuplicatedIdException();
			}// 가입하려는 id와 중복되는 데이터가 이미 DB의 테이블안에 존재할 경우 트랜잭션 롤백시키고 DuplicatedException을 발생시킴
		
			memberDao.insert(conn, new Member(joinReq.getId(), joinReq.getPassword(), joinReq.getName(), new Date()));
			// joinReq이용해서 Member 객체 생성, MemberDao의 insert()실행해서 JSP 페이지 폼의 데이터를 members 테이블에 삽입
			conn.commit(); // 트랜잭션 커밋
		}catch(SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		}finally {JdbcUtil.close(conn);}
	}
	
}
