package post.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import post.service.ListService;
import post.service.postPagination;

public class ListHandler implements CommandHandler {

	private ListService listService = new ListService();

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String s_reqPageNum = req.getParameter("pageNo");
		int i_reqPageNum = 1;

		if (s_reqPageNum != null)
			i_reqPageNum = Integer.parseInt(s_reqPageNum);

		postPagination postPaging = listService.getPostPage(i_reqPageNum);

		//test
		System.out.println("postPaging 값 ");
		System.out.println("currentPage : " + postPaging.getCurrentPage());
		System.out.println("Content : " + postPaging.getContent());
		System.out.println("totalPage : " + postPaging.getTotalPage());
		System.out.println("startPage : " + postPaging.getStartPage());
		System.out.println("endPage : " + postPaging.getEndPage());
		System.out.println("pagecount : " + postPaging.pagecount);
		
		req.setAttribute("postPaging", postPaging);
		return "/view/boardlist.jsp";
	}
}
