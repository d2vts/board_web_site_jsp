package post.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.connection.ConnectionProvider;
import member.dao.MemberDao;
import member.model.Member;
import post.dao.PostDao;
import post.model.PostInfo;

public class DeletePostService {

	private PostDao postdao = new PostDao();
	private MemberDao memberdao = new MemberDao();

	public void deletePost(DeleteRequest deleteReq) {
		Connection conn = null;
		boolean DeletePossibility;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);

			PostInfo postinfo = postdao.ReadByPostId(conn, deleteReq.getPostId()); // 게시물 객체 생성해서 게시물 아이디로 게시물 정보를 deleteReq의 postId를 넣어서 가져옴
			DeletePossibility = postinfo.getWriterId().equals(deleteReq.getWriterId()); // deleteReq의 글쓴이와 postinfo의 글쓴이 동일 여부확인
			Member member = memberdao.selectById(conn, deleteReq.getWriterId()); // member객체에 postinfo의 getWriterId()로 구한 아이디와 동일한 아이디의 멤버 정보를 가져와 저장
			
			//비밀번호 확인 파트넣어야함
			
			
			
			
			if(!deleteReq.getPassword().equals(member.getPassword()))
				throw new NotMatchPasswordException(); // 비밀번호가 일치하지 않을경우
			
			if (postinfo == null)
				throw new postNotFoundException();

			if (DeletePossibility == false)
				throw new PermissionDeniedException();

			postdao.delete(conn, postinfo.getPostId());
			conn.commit();
			
		} catch (SQLException e) {

		}
	}

}
