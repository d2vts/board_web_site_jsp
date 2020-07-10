package member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

import jdbc.JdbcUtil;
import member.model.Member;

public class MemberDao {

	public Member selectById(Connection conn, String id) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = conn.prepareStatement("SELECT * FROM members WHERE memberid = ?");
			pstmt.setString(1, id); // JoinService에서 Member.selectById 사용해서 읽어온값 넣어주기위해 메소드의 파라미터값으로 받은 id값을 setString 인자값으로 
			rs = pstmt.executeQuery(); // Query 실행
			Member member = null; // Member클래스 객체 선언
			if (rs.next()) { //Query의 실행결과가 담겨있는 rs에 next메소드로 하나씩 값을 읽으면서 member에 생성자에 값을 넣어주면서 초기화
				member = new Member(rs.getString("memberid"), rs.getString("password"), rs.getString("name"),
						toDate(rs.getTimestamp("regdate")));
			}
			return member;
		} finally {
			JdbcUtil.close(rs); // ResultSet닫아준다
			JdbcUtil.close(pstmt); // PreparedStatement 닫아준다
		}
	}

	private Date toDate(Timestamp date) {
		return date == null ? null : new Date(date.getTime());
	} // toDate 메소드의 파라미터값인 date가 null 값이라면 null을 주고 null값이 아니라면 Date

	public void insert(Connection conn, Member member) throws SQLException { // SQL INSERT 문을 진행하는 메소드
		//다른 클래스에서 인스턴스 생성후 이 메소드를 이용하여 삽입쿼리를 진행 할 수있게 함
		try (PreparedStatement pstmt = conn.prepareStatement("INSERT INTO members VALUES(?,?,?,?)")) {
			pstmt.setString(1, member.getId());
			pstmt.setString(2, member.getName());
			pstmt.setString(3, member.getPassword());
			pstmt.setTimestamp(4, new Timestamp(member.getDate().getTime()));
			pstmt.executeUpdate();
		}
	}
	
	public void updatePW(Connection conn, Member member)throws SQLException{
		try(PreparedStatement pstmt = conn.prepareStatement("UPDATE members SET password=? WHERE memberid = ?")){
			pstmt.setString(1, member.getPassword()); // 바꾸는 비밀번호
			pstmt.setString(2, member.getId()); // WHERE문에 걸어서 조건을 맞출 값
			pstmt.executeUpdate();
		}
	}
	
	public String MemberNameSelectById(Connection conn, String memberid)throws SQLException{
		String memberName = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			pstmt = conn.prepareStatement("SELECT * FROM members WHERE memberid=?");
			pstmt.setString(1, memberid);
			
			rs = pstmt.executeQuery();
			if(rs.next()) { memberName = rs.getString("name");}
			return memberName;
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	public int delete(Connection conn, String memberid) throws SQLException {
		try (PreparedStatement pstmt = conn.prepareStatement("DELETE FROM members WHERE memberid=?")) {
			pstmt.setString(1, memberid);

			return pstmt.executeUpdate();
		}
	}
	
	
}
