package member.command;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import login.User;
import member.service.ChangePasswordService;
import member.service.MemberNotFoundException;
import member.service.NotMatchCurrentPasswordException;
import mvc.command.CommandHandler;

public class ChangePasswordHandler implements CommandHandler{
	private static final String FORM_VIEW = "/view/changePWForm.jsp";
	private ChangePasswordService changePWsvc = new ChangePasswordService();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res)throws Exception{
		if(req.getMethod().equalsIgnoreCase("GET")) return processForm(req,res);
		else if(req.getMethod().equalsIgnoreCase("POST")) return processSubmit(req,res);
		else {
			res.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}
	
	private String processForm(HttpServletRequest req, HttpServletResponse res) { return FORM_VIEW;}
	
	private String processSubmit(HttpServletRequest req, HttpServletResponse res)throws Exception{
		User user = (User)req.getSession().getAttribute("loginUser");
		
		Map<String, Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors);
		
		String curPW = req.getParameter("curPW");
		String newPW = req.getParameter("newPW");
		
		if(curPW==null||curPW.isEmpty()) errors.put("curPW",Boolean.TRUE);
		if(newPW==null||newPW.isEmpty()) errors.put("newPW",Boolean.TRUE);
		if(!errors.isEmpty()) return FORM_VIEW;
		try {
			return "/mypage.do";
		}catch(NotMatchCurrentPasswordException nmcpe) {
			errors.put("notMatchCurPW", Boolean.TRUE);
			return FORM_VIEW;
		}catch(MemberNotFoundException mbfe) {
			res.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return FORM_VIEW;
		}
	}
}
