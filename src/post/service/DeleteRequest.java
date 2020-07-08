package post.service;

import java.util.Map;

public class DeleteRequest {

	private int postId;
	private String writerId;
	private String password;
	
	public DeleteRequest(String writerId, int postId) {
		this.writerId = writerId;
		this.postId = postId;
	}
	
	public DeleteRequest(String password) {
		this.password = password;
	}
	
	public DeleteRequest(String writerId, String password, int postId) {
		this.writerId = writerId;
		this.password = password;
		this.postId = postId;
		
	}
	
	public int getPostId() {
		return postId;
	}
	
	public String getWriterId() {
		return writerId;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void validate(Map<String, Boolean> errors) {
		if(password==null || password.trim().isEmpty()) 
			errors.put("password_empty", Boolean.TRUE);
		}
	
	
}
