package reply.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jdbc.JdbcUtil;
import reply.model.Reply;

public class ReplyDao {

	public int insert(Connection conn, Reply reply) throws SQLException {
		String insertQuery = "INSERT INTO reply(post_id, writer_id,content,reg_date,mod_date) VALUES(?,?,?,?,?)";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		new Timestamp(reply.getModDate().getTime());
		try {
			pstmt = conn.prepareStatement(insertQuery);
			pstmt.setInt(1, reply.getPostId());
			pstmt.setString(2, reply.getWriterId());
			pstmt.setString(3, reply.getContent());
			pstmt.setTimestamp(4, new Timestamp(reply.getRegDate().getTime()));
			pstmt.setTimestamp(5, new Timestamp(reply.getModDate().getTime()));

			int insertedCount = pstmt.executeUpdate();

			return insertedCount;

		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}

	private Reply rsToReply(ResultSet rs) throws SQLException {
		return new Reply(rs.getInt("reply_num"), rs.getInt("post_id"), rs.getString("writer_id"),
				rs.getString("content"), toDate(rs.getTimestamp("reg_date")), toDate(rs.getTimestamp("mod_date")));
	}

	private Date toDate(Timestamp timestamp) {
		return new Date(timestamp.getTime());
	}

	public List<Reply> SelectById(Connection conn, int postId) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement("SELECT * FROM reply WHERE post_id=?");
			pstmt.setInt(1, postId);
			rs = pstmt.executeQuery();
			List<Reply> reply = new ArrayList<>();
			while (rs.next())
				reply.add(rsToReply(rs));

			// test
			System.out.println("replydao에서 List<Reply>의 reply 값 :  " + reply);

			return reply;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}

}
