package post.command;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import login.User;
import mvc.command.CommandHandler;
import post.model.PostInfo;
import post.service.PermissionDeniedException;
import post.service.ReadPostService;
import post.service.UpdatePostServiece;
import post.service.UpdateRequest;
import post.service.postNotFoundException;

public class UpdateHandler implements CommandHandler {

	private static String UPDATE_VIEW = "/view/updateForm.jsp";

	private ReadPostService readService = new ReadPostService();
	private UpdatePostServiece updateService = new UpdatePostServiece();

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

	private String processForm(HttpServletRequest req, HttpServletResponse res) throws IOException {
		try {
			String req_pid = req.getParameter("pid");
			PostInfo postinfo = readService.getPost(Integer.parseInt(req_pid), false);
			User loginUser = (User) req.getSession().getAttribute("loginUser");
			boolean updatePossibility = postinfo.getWriterId().equals(loginUser.getId());

			if (updatePossibility == false) {
				res.sendError(HttpServletResponse.SC_FORBIDDEN);
				return null;
			}
			UpdateRequest updateReq = new UpdateRequest(loginUser.getId(), Integer.parseInt(req_pid),
					postinfo.getPostTitle(), postinfo.getPostContent());

			req.setAttribute("updateReq", updateReq);
			return UPDATE_VIEW;
		} catch (postNotFoundException e) {
			res.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}
	}

	private String processSubmit(HttpServletRequest req, HttpServletResponse res) throws Exception{

		User loginUser = (User) req.getSession().getAttribute("loginUser");
		String req_pid = req.getParameter("pid");
	
		//-----------------------------------------------
		System.out.println("loginUser : " + loginUser.getId() + "req_pid : " + req_pid);
		//-----------------------------------------------
		
		
		
		UpdateRequest updateReq = new UpdateRequest(loginUser.getId(), Integer.parseInt(req_pid), req.getParameter("title"), req.getParameter("content"));
		req.setAttribute("updateReq", updateReq);
		
		Map<String, Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors);
		updateReq.validate(errors);
		
		if(!errors.isEmpty()) return UPDATE_VIEW;
		
		
		
		try {
			updateService.update(updateReq);
			return "/list.do"; // 추후에 해당 게시물의 게시물 자세히 보기 페이지로 경로 변경할것-------------------------------------------------
		}catch(postNotFoundException e) {
			res.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}catch(PermissionDeniedException e) {
			res.sendError(HttpServletResponse.SC_FORBIDDEN);
			return null;
		}
	}
}
