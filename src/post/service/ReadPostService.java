package post.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.connection.ConnectionProvider;
import post.dao.PostDao;
import post.model.PostInfo;

public class ReadPostService {

	private PostDao postdao = new PostDao();

	public PostInfo getPost(int postId, boolean increaseView) {
		try (Connection conn = ConnectionProvider.getConnection()) {
			PostInfo postinfo = postdao.ReadByPostId(conn, postId);
			if (postinfo == null)
				throw new postNotFoundException();
			if (increaseView)
				postdao.increaseView(conn, postId);
			return new PostInfo(postinfo);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
