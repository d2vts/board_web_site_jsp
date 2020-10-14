package post.service;

import java.util.Map;

public class WriteRequest {
	private String writerName;
	private String title;
	private String content;
	private String writerId;
	
	public WriteRequest(String writerName, String writerId, String title, String content) {
		
		this.writerName = writerName;
		this.writerId = writerId;
		this.title = title;
		this.content = content;
	}
	
	public String getWriterName() {	
		return writerName;
	}
	
	public String getWriterId() {
		return writerId;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getContent() {
		return content;
	}
	
	public void validate(Map<String,Boolean> errors) {
		if(title==null||title.trim().isEmpty()) errors.put("titleEmpty",Boolean.TRUE); // 제목은 필수값으로 적어줘야 하기때문에 제목 칸이 빈값이면 에러 맵에 titleEmpty 에러 삽입
	}
}
