package reply.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import reply.dao.ReplyDao;
import reply.model.Reply;

public class WriteReplyService {

	private ReplyDao replydao = new ReplyDao();
	
	public void write(WriteReplyRequest r_req) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			Reply reply = toReplying(r_req);
			int savedreply = replydao.insert(conn, reply);
			
			System.out.println("writeReplyService에서 savedreply 확인 : " + savedreply);
			
			
			if(savedreply == 0) throw new RuntimeException("FAIL TO INSERT REPLY INTO REPLY(DB)");
			
			conn.commit();
		}catch(SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		}catch(RuntimeException e) {
			JdbcUtil.rollback(conn);
			throw e;
		}finally {
			JdbcUtil.close(conn);
		}
	}
	
	private Reply toReplying(WriteReplyRequest req) {
		Date now = new Date();
		return new Reply(req.getPostId(), req.getWriterId(), req.getContent(), now, now);
	}
}
