package reply.service;

import java.util.Map;

public class WriteReplyRequest {

	private int post_id;
	private String content;
	private String writer_id;

	public WriteReplyRequest(int post_id, String writer_id, String content) {
		this.post_id = post_id;
		this.writer_id = writer_id;
		this.content = content;
	}

	public int getPostId() {
		return post_id;
	}

	public String getWriterId() {
		return writer_id;
	}

	public String getContent() {
		return content;
	}

	public void validate(Map<String,Boolean> errors) {
		if(content==null||content.trim().isEmpty()) errors.put("contentEmpty",Boolean.TRUE);// 내용공백 에러
	}
}
