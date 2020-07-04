package post.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import jdbc.connection.ConnectionProvider;
import post.dao.PostDao;
import post.model.PostInfo;

public class ListService {

	private PostDao postDao = new PostDao();
	private int size = 10;
	
	public postPagination getPostPage(int pageNum) {
		try(Connection conn = ConnectionProvider.getConnection()){
			int total = postDao.getTotalCount(conn); // 전체 게시글 수 구함
		List<PostInfo> content = postDao.select(conn, (pageNum-1)*size, size);
		// (pageNum-1)*size  ==>> 시작 행 번호 즉, 조회할 레코드의 시작 행
		return new postPagination(total, pageNum, size, content);
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
