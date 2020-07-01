package member.model;

import java.util.Date;


public class Member {

	private String id;
	private String password;
	private String name;
	private Date regDate;
	
	public Member(String id, String name, String password, Date regDate) {
		this.id = id;
		this.password = password;
		this.name = name;
		this.regDate = regDate;
	} //Member 생성자에서 인스턴스변수들 초기화 
	
	public String getId() {
		return id;
	}
	public String getPassword() {
		return password;
	}
	public String getName() {
		return name;
	}
	public Date getDate() {
		return regDate;
	}
	public boolean matchPassword(String pwd) {
		return pwd.equals(password);
	}
	public void changePW(String NewPW) {
		this.password = NewPW;
	} // 비밀번호 변경 메소드
	
}
