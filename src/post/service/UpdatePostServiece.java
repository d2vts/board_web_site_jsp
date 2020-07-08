package post.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import post.dao.PostDao;
import post.model.PostInfo;

public class UpdatePostServiece {

	private PostDao postdao = new PostDao();
	
	public void update(UpdateRequest updateReq) {
		Connection conn = null;
		boolean UpdatePossibility;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			PostInfo postinfo = postdao.ReadByPostId(conn, updateReq.getPostId());
			UpdatePossibility = postinfo.getWriterId().equals(updateReq.getWriterId());
			if(postinfo == null)
				throw new postNotFoundException();
			
			if(UpdatePossibility==false)
				throw new PermissionDeniedException();
			
			postdao.update(conn, updateReq.getPostId(), updateReq.getTitle(),updateReq.getContent());
			conn.commit();
			
		}catch(SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		}catch(PermissionDeniedException e) {
			JdbcUtil.rollback(conn);
			throw e;
		}finally {
			JdbcUtil.close(conn);
		}
	}
}
