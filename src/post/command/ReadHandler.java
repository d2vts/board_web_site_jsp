package post.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import post.model.PostInfo;
import post.service.ReadPostService;
import post.service.postNotFoundException;

public class ReadHandler implements CommandHandler {

	private ReadPostService readService = new ReadPostService();

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String req_pid = req.getParameter("pid");
		try {
			PostInfo postinfo = readService.getPost(Integer.parseInt(req_pid), true);
			req.setAttribute("postinfo", postinfo);
			return "/view/readPost.jsp";
		} catch (postNotFoundException e) {
			req.getServletContext().log("there is no post", e);
			res.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}
	}
}
