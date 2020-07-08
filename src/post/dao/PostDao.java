package post.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jdbc.JdbcUtil;
import post.model.PostInfo;

public class PostDao {

	public PostInfo insert(Connection conn, PostInfo post) throws SQLException {
		PreparedStatement pstmt = null;
		Statement stmt = null;
		ResultSet rs = null;
		Timestamp regstamp, modstamp;
		try {
			pstmt = conn.prepareStatement(
					"INSERT INTO jspboard (writer_id, writer_name, title, content, reg_date, mod_date, views) values(?,?,?,?,?,?,0)");
			pstmt.setString(1, post.getWriterId());
			pstmt.setString(2, post.getWriterName());
			pstmt.setString(3, post.getPostTitle());
			pstmt.setString(4, post.getPostContent());
			pstmt.setTimestamp(5, regstamp = new Timestamp(post.getRegDate().getTime()));
			pstmt.setTimestamp(6, modstamp = new Timestamp(post.getModDate().getTime()));

			int insertedCount = pstmt.executeUpdate();
			if (insertedCount > 0) {
				stmt = conn.createStatement();
				rs = stmt.executeQuery("SELECT last_insert_id() FROM jspboard"); // 가장 마지막에 추가한
				if (rs.next()) {
					return new PostInfo(post.getWriterId(), post.getWriterName(), post.getPostTitle(),
							post.getPostContent(), post.getRegDate(), post.getModDate(), 0);
				}
			}
			return null;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
			JdbcUtil.close(pstmt);
		}
	}

	public int getTotalCount(Connection conn) throws SQLException {
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT count(*) FROM jspboard");
			if (rs.next())
				return rs.getInt(1);
			return 0;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
		}
	}

	public List<PostInfo> select(Connection conn, int startRow, int size) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement("SELECT * FROM jspboard order by post_id desc limit ?,?");
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, size);
			rs = pstmt.executeQuery();
			List<PostInfo> result = new ArrayList<>();
			while (rs.next())
				result.add(rsToPostInfoObject(rs));

			// test
			System.out.println("result = " + result);

			return result;

		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}

	private PostInfo rsToPostInfoObject(ResultSet rs) throws SQLException {
		return new PostInfo(rs.getInt("post_id"), rs.getString("writer_id"), rs.getString("writer_name"),
				rs.getString("title"), rs.getString("content"), toDate(rs.getTimestamp("reg_date")),
				toDate(rs.getTimestamp("mod_date")), rs.getInt("views"));
	}

	private Date toDate(Timestamp timestamp) {
		return new Date(timestamp.getTime());
	}

	public PostInfo ReadByPostId(Connection conn, int postId) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement("SELECT * FROM jspboard WHERE post_id=?");
			pstmt.setInt(1, postId);
			rs = pstmt.executeQuery();
			PostInfo postinfo = null;
			if (rs.next()) {
				postinfo = rsToPostInfoObject(rs);
			}
			return postinfo;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}

	public void increaseView(Connection conn, int postId) throws SQLException {
		try (PreparedStatement pstmt = conn
				.prepareStatement("UPDATE jspboard SET views = views + 1 WHERE post_id = ?")) {
			pstmt.setInt(1, postId);
			pstmt.executeUpdate();
		}
	}

	public int update(Connection conn, int num, String title, String content) throws SQLException {
		try (PreparedStatement pstmt = conn
				.prepareStatement("UPDATE jspboard SET title = ?,content =?, mod_date = now() where post_id=?")) {
			pstmt.setString(1, title);
			pstmt.setString(2, content);
			pstmt.setInt(3, num);

			return pstmt.executeUpdate();
		}
	}

	public int delete(Connection conn, int num) throws SQLException {
		try (PreparedStatement pstmt = conn.prepareStatement("DELETE FROM jspboard WHERE post_id=?")) {
			pstmt.setInt(1, num);

			return pstmt.executeUpdate();
		}
	}

}
