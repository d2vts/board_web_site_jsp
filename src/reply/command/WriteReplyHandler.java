package reply.command;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import login.User;
import mvc.command.CommandHandler;
import post.service.WriteRequest;
import reply.service.WriteReplyRequest;
import reply.service.WriteReplyService;

public class WriteReplyHandler implements CommandHandler {
	
	//private String READ_POST_VIEW = "/view/read.do?pid=" + String.valueOf(req)
	
	
	private WriteReplyService replyService = new WriteReplyService();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) {
		if(req.getMethod().equalsIgnoreCase("POST")) {
			return processSubmit(req,res);
		}else {
			res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}
	
	private String processSubmit(HttpServletRequest req, HttpServletResponse res) {
		String pid = req.getParameter("pid");
		Map<String,Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors);
		
		User user = (User)req.getSession(false).getAttribute("loginUser");
		WriteReplyRequest w_reply_req = createReplyWriteRequest(user, req, pid);
		
		System.out.println("/read.do?pid="+pid+"");
		
		w_reply_req.validate(errors);
		if(!errors.isEmpty()) return "/read.do?pid="+pid+"";
		
		req.setAttribute("reply", w_reply_req);
		replyService.write(w_reply_req);
		
		return "/read.do?pid="+pid+"";
	}
	
	private WriteReplyRequest createReplyWriteRequest(User user, HttpServletRequest req, String pid) {
		

		System.out.println("Integer.parseInt(pid) : " + Integer.parseInt(pid));
		System.out.println("user.getId() : " + user.getId());
		System.out.println("req.getParameter(\"content\") : " + req.getParameter("content"));
		
		return new WriteReplyRequest(Integer.parseInt(pid), user.getId(), req.getParameter("content"));
	}
	
}
