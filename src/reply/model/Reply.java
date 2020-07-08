package reply.model;

import java.util.Date;

public class Reply {

	private int reply_num;
	private int post_id;
	private String writerId;
	private String content;
	private Date regDate;
	private Date modDate;

	public Reply(Reply reply) {
		this.post_id = reply.post_id;
		this.writerId = reply.writerId;
		this.content = reply.content;
		this.regDate = reply.regDate;
		this.modDate = reply.modDate;
	}

	public Reply(int post_id, String writer_id, String content) {
		this.post_id = post_id;
		this.writerId = writer_id;
		this.content = content;
	}

	public Reply(int post_id, String writer_id, String content, Date reg_date, Date mod_date) {
		this.post_id = post_id;
		this.writerId = writer_id;
		this.content = content;
		this.regDate = reg_date;
		this.modDate = mod_date;
	}
	
	public Reply(int reply_num, int post_id, String writer_id, String content, Date reg_date, Date mod_date) {
		this.reply_num = reply_num;
		this.post_id = post_id;
		this.writerId = writer_id;
		this.content = content;
		this.regDate = reg_date;
		this.modDate = mod_date;
	}

	public int getReplyNum() {
		return reply_num;
	}

	public int getPostId() {
		return post_id;
	}

	public String getWriterId() {
		return writerId;
	}

	public String getContent() {
		return content;
	}

	public Date getRegDate() {
		return regDate;
	}

	public Date getModDate() {
		return modDate;
	}

}
