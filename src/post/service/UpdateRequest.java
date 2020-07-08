package post.service;

import java.util.Map;

public class UpdateRequest {
	private String title;
	private String content;
	private int postId;
	private String writerId;
	
	public UpdateRequest(String writerId, int postId, String title, String content) {
		this.writerId = writerId;
		this.postId = postId;
		this.title = title;
		this.content = content;
	}
	
	public String getWriterId() {
		return writerId;
	}
	public int getPostId() {
		return postId;
	}
	public String getTitle() {
		return title;
	}
	public String getContent() {
		return content;
	}
	
	public void validate(Map<String, Boolean> errors) {
		if(title==null || title.trim().isEmpty()) 
			errors.put("title_empty", Boolean.TRUE);
		if(content==null || content.trim().isEmpty())
			errors.put("content_empty", Boolean.TRUE);
	}
}
