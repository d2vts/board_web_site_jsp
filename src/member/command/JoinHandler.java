package member.command;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.service.DuplicatedIdException;
import member.service.JoinRequest;
import member.service.JoinService;
import mvc.command.CommandHandler;

public class JoinHandler implements CommandHandler { // CommandHandler 구현
	private static final String FORM_VIEW = "/view/joinForm.jsp";
	private JoinService joinService = new JoinService();

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) {
		if (req.getMethod().equalsIgnoreCase("GET"))
			return processForm(req, res);
		else if (req.getMethod().equalsIgnoreCase("POST"))
			return processSubmit(req, res);
		else {
			res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);// 405 코드
			return null;
		}
	}//GET방식 요청이면 processForm , POST방식 요청이면 processSubmit실행, 둘 다 아니면 405 응답코드

	private String processForm(HttpServletRequest req, HttpServletResponse res) { // 폼 보여주는 뷰 경로 리턴
		return FORM_VIEW;
	}

	private String processSubmit(HttpServletRequest req, HttpServletResponse res) { // 폼 전송 처리
		
		JoinRequest joinReq = new JoinRequest();
		joinReq.setId(req.getParameter("id"));
		joinReq.setName(req.getParameter("name"));
		joinReq.setPassword(req.getParameter("password"));
		joinReq.setConfirmPassword(req.getParameter("confirmPassword"));
		// JoinRequest 객체 생성해서 setXX메소드로 폼에서 받아온 req.getParameter로 받은 값 저장
		
		Map<String, Boolean> errors = new HashMap<>(); // 에러 정보를 담을 맵 객체를 생성하고
		req.setAttribute("errors", errors);// "errors" 속성에 저장

		joinReq.validate(errors); // JoinRequest 객체의 값이 올바른지 검사한다. 값이 올바르지 않을 경우 errors 맵 객체에 키를 추가. 

		if (!errors.isEmpty()) return FORM_VIEW;
		//errors 맵 객체에 데이터가 존재하면, 폼 뷰 경로를 리턴한다. errors에 데이터가 존재한다는 것은 joinReq 객체의 데이터가 올바르지 않아서 joinReq.validate(errors)에서 
		//에러와 관련되 키 추가했다는 것임. joinReq는 폼의 입력데이터를 저장하고 있기 때문에 폼 입력값에 에러가 있을 경우 다시 폼 보여줌
		
		try {
			joinService.join(joinReq); //JoinService의 join()실행. 가입 처리에 성공하면 FORM_VIEW가 아닌 가입성공을 보여주는 페이지경로 리턴
			return "/view/joinSuccess.jsp";
		} catch (DuplicatedIdException e) {
			errors.put("duplicatedId", Boolean.TRUE);// 동일한 아이디로 가입한 회원 존재하면 중복익셉션 발생해서 errors에 duplicatedID키 추가하고 폼보여주는 뷰 리턴
			return FORM_VIEW;
		}
	}
}