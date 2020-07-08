package post.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import post.dao.PostDao;
import post.model.PostInfo;

public class WritePostService {

	private PostDao postDao = new PostDao();

	public void write(WriteRequest req) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);

			PostInfo post = toPosting(req);
			PostInfo savedpost = postDao.insert(conn, post);

			if (savedpost == null)
				throw new RuntimeException("FAIL TO INSERT POST INTO JSPBOARD");

			conn.commit();
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		} catch (RuntimeException e) {
			JdbcUtil.rollback(conn);
			throw e;
		} finally {
			JdbcUtil.close(conn);
		}
	}

	private PostInfo toPosting(WriteRequest req) {
		Date now = new Date();
		return new PostInfo(req.getWriterId(), req.getWriterName(), req.getTitle(), req.getContent(), now, now, 0);
	}
}
