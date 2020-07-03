package post.service;

import java.util.List;

import post.model.PostInfo;

public class postPagination {

	private int total; // 총 게시룸 수
	private int currentPage; // 현재 페이지
	private List<PostInfo> content;
	private int totalPage; // 총 페이지수
	private int startPage; // 밑에 보여질 페이지들 중 시작 페이지
	private int endPage; // 밑에 보여질 페이지들 중 끝 페이지
	private int pagecount = 5; // 밑에 보여질 페이지의 수

	public postPagination(int total, int currentPage, int size, List<PostInfo> content) {

		this.total = total;
		this.currentPage = currentPage;
		this.content = content;
		if (total == 0) {
			totalPage = 0;
			startPage = 0;
			endPage = 0;
		} else {
			totalPage = total / size;
			if (total % size > 0)
				totalPage++;
		}
		startPage = currentPage / pagecount * pagecount + 1;
		if (currentPage % 5 == 0)startPage -= pagecount;
	}
	
	public int getTotal() { return total;}
	
	public boolean hasNoPosts() {
		return total==0;
	}
	
	public boolean hasPosts() {
		return total>0;
	}
	
	public int getCurrentPage() {
		return currentPage;
	}
	
	public int getTotalPages() {
		return totalPage;
	}
	
	public List<PostInfo> getContent(){
		return content;
	}
	
	public int getStartPage() {
		return startPage;
	}
	
	public int getEndPage() {
		return endPage;
	}
}
