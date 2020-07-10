package member.command;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import login.User;
import member.service.WithdrawalRequest;
import member.service.WithdrawalService;
import mvc.command.CommandHandler;
import post.service.NotMatchPasswordException;

public class WithDrawalHandler implements CommandHandler {

	private static String INDEX_FORM = "/view/index.jsp";
	private static String WITHDRAWAL_FORM = "/view/withdrawal.jsp";

	private WithdrawalService wdService = new WithdrawalService();

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception{
	
		if(req.getMethod().equalsIgnoreCase("GET")) return processForm(req,res);
		else if(req.getMethod().equalsIgnoreCase("POST")) return processSubmit(req,res);
		else {
			res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}
	
	private String processForm(HttpServletRequest req, HttpServletResponse res) throws Exception{
		return WITHDRAWAL_FORM;
	}
	private String processSubmit(HttpServletRequest req, HttpServletResponse res) throws Exception{
		String form_password = req.getParameter("password");
		User loginUser = (User)req.getSession().getAttribute("loginUser");
		WithdrawalRequest wdReq = new WithdrawalRequest(loginUser.getId(), form_password);
		
		Map<String,Boolean>errors = new HashMap<>();
		req.setAttribute("errors", errors);
		
		wdReq.validate(errors);
		
		
		try {
			wdService.withdrawal(wdReq);
			HttpSession session = req.getSession(false);
			if(session != null) session.invalidate();
			return INDEX_FORM;
		}catch(NotMatchPasswordException e) {
			errors.put("NotMatchPW",Boolean.TRUE);
			return  WITHDRAWAL_FORM;
		}
		
		
		
	}
}
