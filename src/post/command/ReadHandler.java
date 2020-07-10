package post.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import post.model.PostInfo;
import post.service.ReadPostService;
import post.service.postNotFoundException;
import reply.model.Reply;

public class ReadHandler implements CommandHandler {

	private ReadPostService readService = new ReadPostService();

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String req_pid = req.getParameter("pid");
		try {
			PostInfo postinfo = readService.getPost(Integer.parseInt(req_pid), true);
			
			List<Reply> reply = readService.getReply(Integer.parseInt(req_pid));
			

			//System.out.println();
			
			req.setAttribute("postinfo", postinfo);
			if(reply!=null)
			req.setAttribute("reply", reply);
			

			
			return "/view/readPost.jsp";
		} catch (postNotFoundException e) {
			req.getServletContext().log("there is no post", e);
			res.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}
	}
}
