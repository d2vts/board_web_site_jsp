package member.service;

import java.util.Map;

public class WithdrawalRequest {

	private String memberid;
	private String password;
	
	public WithdrawalRequest(String memberid, String password) {
		this.memberid = memberid;
		this.password = password;
	}
	public String getMemberid() {
		return memberid;
	}
	public String getPassword() {
		return password;
	}
	public void validate(Map<String, Boolean> errors) {
		if(password==null || password.trim().isEmpty()) 
			errors.put("password_empty", Boolean.TRUE);
		}
	
}
