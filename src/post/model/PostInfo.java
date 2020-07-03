package post.model;

import java.util.Date;

public class PostInfo {

	private int postId;
	private String writerId;
	private String writerName;
	private String postTitle;
	private String postContent;
	private Date regDate;
	private Date modDate;
	private int postView;

	// 파라미터 2개 생성자 = String writerId, String postContent
	// 파라미터 3개 생성자 = String writerId, String postTitle, String postContent
	// 파라미터 4개 생성자 = String writerId, String writerName, String postTitle, String postContent
	// 파라미터 7개 생성자 = String writerId, String writerName, String postTitle, String postContent, Date regDate, Date modDate, int postView
	
	
	public PostInfo(String writerId, String postContent) {
		this.writerId = writerId;
		this.postContent = postContent;
	}

	public PostInfo(String writerId, String postTitle, String postContent) {
		this.writerId = writerId;
		this.postContent = postContent;
		this.postTitle = postTitle;
	}

	public PostInfo(String writerId, String writerName, String postTitle, String postContent) {
		this.writerId = writerId;
		this.writerName = writerName;
		this.postContent = postContent;
		this.postTitle = postTitle;
	}

	public PostInfo(String writerId, String writerName, String postTitle, String postContent, Date regDate,
			Date modDate, int postView) {
		this.writerId = writerId;
		this.writerName = writerName;
		this.postContent = postContent;
		this.postTitle = postTitle;
		this.regDate = regDate;
		this.modDate = modDate;
		this.postView = postView;
	}
	
	public PostInfo(int postId, String writerId, String writerName, String postTitle, String postContent, Date regDate,
			Date modDate, int postView) {
		this.postId = postId;
		this.writerId = writerId;
		this.writerName = writerName;
		this.postContent = postContent;
		this.postTitle = postTitle;
		this.regDate = regDate;
		this.modDate = modDate;
		this.postView = postView;
	}

	public int getPostId() {
		return postId;
	}
	
	public String getWriterId() {
		return writerId;
	}

	public String getWriterName() {
		return writerName;
	}

	public String getPostTitle() {
		return postTitle;
	}

	public String getPostContent() {
		return postContent;
	}

	public Date getRegDate() {
		return regDate;
	}
	
	public Date getModDate() {
		return modDate;
	}
	
	public int getPostView() {
		return postView;
	}
}
