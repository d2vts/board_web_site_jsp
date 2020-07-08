package post.command;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import login.User;
import mvc.command.CommandHandler;
import post.service.DeletePostService;
import post.service.DeleteRequest;
import post.service.NotMatchPasswordException;
import post.service.PermissionDeniedException;
import post.service.postNotFoundException;

public class DeleteHandler implements CommandHandler {

	private static String DELETE_FORM = "/view/deleteConfirm.jsp";

	private DeletePostService deleteService = new DeletePostService();

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		if (req.getMethod().equalsIgnoreCase("GET"))
			return processForm(req, res);
		else if (req.getMethod().equalsIgnoreCase("POST"))
			return processSubmit(req, res);
		else {
			res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}

	private String processForm(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String req_pid = req.getParameter("pid");
		req.setAttribute("pid", req_pid);
		return "/view/deleteConfirm.jsp";
	}

	private String processSubmit(HttpServletRequest req, HttpServletResponse res) throws Exception {

		String str_pid = req.getParameter("pid");

		int req_pid = Integer.parseInt(str_pid);
		String form_password = req.getParameter("password");
		User loginUser = (User) req.getSession().getAttribute("loginUser");
		DeleteRequest deleteReq = new DeleteRequest(loginUser.getId(), form_password, req_pid);

		req.setAttribute("deleteReq", deleteReq);
		Map<String, Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors);
		deleteReq.validate(errors);

		try {
			deleteService.deletePost(deleteReq);
			return "/list.do";
		} catch (NotMatchPasswordException e) {
			errors.put("NotMatchPW", Boolean.TRUE);
			req.setAttribute("pid", req_pid);
			return DELETE_FORM;
		} catch (postNotFoundException e) {
			res.sendError(HttpServletResponse.SC_NOT_FOUND);
			req.setAttribute("pid", req_pid);
			return null;
		} catch (PermissionDeniedException e) {
			res.sendError(HttpServletResponse.SC_FORBIDDEN);
			req.setAttribute("pid", req_pid);
			return "/login.do";
		} catch (NumberFormatException e) {
			errors.put("NFExcep", Boolean.TRUE);
			req.setAttribute("pid", req_pid);
			return DELETE_FORM;
		}
	}
}
