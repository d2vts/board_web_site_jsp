package post.command;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import login.User;
import mvc.command.CommandHandler;
import post.service.WritePostService;
import post.service.WriteRequest;

public class WritePostHandler implements CommandHandler{

	private static final String WRITE_POST_FORM_VIEW = "/view/writePostForm.jsp";
	private WritePostService postService = new WritePostService();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) {
		if(req.getMethod().equalsIgnoreCase("GET")) {
			return processForm(req, res);
		}else if(req.getMethod().equalsIgnoreCase("POST")) {
			return processSubmit(req,res);
		}else {
			res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
		
		
	}
	private String processForm(HttpServletRequest req, HttpServletResponse res) {
		return WRITE_POST_FORM_VIEW;
	}
	
	private String processSubmit(HttpServletRequest req, HttpServletResponse res) {
		Map<String,Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors);
		
		User user = (User)req.getSession(false).getAttribute("loginUser"); // getSession() 괄호 안에 true 면없을시 새로운 세션 생성하는것임 그래서 없을시 null을 리턴하는 false를 파라미터값으로 준것
		WriteRequest writeReq = createWriteRequest(user, req);
		writeReq.validate(errors);
		if(!errors.isEmpty()) return WRITE_POST_FORM_VIEW;
		
		postService.write(writeReq);
		
		return "/list.do";
		
	}
	
	private WriteRequest createWriteRequest(User user, HttpServletRequest req) {
		return new WriteRequest( user.getName(), user.getId(), req.getParameter("title"), req.getParameter("content"));
	}
}
