package member.service;


import java.sql.Connection;
import java.sql.SQLException;

import jdbc.connection.ConnectionProvider;
import member.dao.MemberDao;
import member.model.Member;
import post.service.NotMatchPasswordException;

public class WithdrawalService {

	private MemberDao memberdao = new MemberDao();
	
	public void withdrawal(WithdrawalRequest wdReq) {
		Connection conn = null;
		boolean WithdrawalPossibility;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			Member member = memberdao.selectById(conn, wdReq.getMemberid());
			WithdrawalPossibility = member.getPassword().equals(wdReq.getPassword());
			
			if(!WithdrawalPossibility) throw new NotMatchPasswordException();
			
			memberdao.delete(conn, wdReq.getMemberid());
			conn.commit();
			
		}catch(SQLException e) {}
	}
}
