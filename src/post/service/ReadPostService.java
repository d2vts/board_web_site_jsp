package post.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import jdbc.connection.ConnectionProvider;
import post.dao.PostDao;
import post.model.PostInfo;
import reply.dao.ReplyDao;
import reply.model.Reply;

public class ReadPostService {

	private PostDao postdao = new PostDao();
	private ReplyDao replydao = new ReplyDao();

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
	
	public List<Reply> getReply(int postId) {
		try(Connection conn = ConnectionProvider.getConnection()){
			List<Reply> reply = replydao.SelectById(conn, postId);
			
			// test
			System.out.println("ReadPostService에서 List<Reply>의  reply 값 = " + reply);
			System.out.println("reply 사이즈 : " + reply.size());


			
			
			if(reply.isEmpty()) return null;
			else return reply;
			
			
			
			
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	/*
	 * public String getReplyWriterName(String memberid) { try(Connection conn =
	 * ConnectionProvider.getConnection()){ String memberName =
	 * memberdao.MemberNameSelectById(conn, memberid);
	 * 
	 * return memberName;
	 * 
	 * }catch(SQLException e) { throw new RuntimeException(e); } }
	 */
	

}
