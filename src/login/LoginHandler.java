package login;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;

//만약에 에러나면 trim() 부분 먼저 볼것

public class LoginHandler implements CommandHandler{ //커맨드 핸들러 인터페이스 사용해서 로그인 핸들러구현

	private static final String LOGIN_FORM_VIEW = "/view/loginForm.jsp"; // 로그인 페이지경로를 상수로 선언 및 초기화
	private LoginService loginService = new LoginService(); // 로그인 서비스 클래스 객체 생성
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception{ // 커맨드핸들러 process 오버라이딩
		if(req.getMethod().equalsIgnoreCase("get")) return loginForm(req,res); // get방식 요청엔 loginForm메소드로
		else if(req.getMethod().equalsIgnoreCase("post")) return loginProcess(req,res); // post방식 요청엔 loginProcess로
		else {
			res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null; // 둘다아니라면 405 응답코드 
		}
	}
	private String loginForm(HttpServletRequest req, HttpServletResponse res) { // 로그인페이지 경로 반환
		return LOGIN_FORM_VIEW;
	}
	
	private String loginProcess(HttpServletRequest req, HttpServletResponse res) throws Exception{ // 로그인처리메소드
		String id = req.getParameter("id").trim(); 
		String password = req.getParameter("password").trim();
		//id와 password 받아온 값에서 공백제거후 각 변수에 저장
		
		//로그인 양식 검사
		Map<String,Boolean> errors = new HashMap<>(); // HashMap 타입 errors 변수 생성
		req.setAttribute("errors", errors); // errors라는 이름의 속성와 errors값을  request객체에 실어서 보냄
		if(id==null||id.isEmpty()) errors.put("idEmpty",Boolean.TRUE); // id칸이 빈칸이라면 errors에 idEmpty,TRUE 값 저장
		if(password==null||password.isEmpty()) errors.put("pwEmpty", Boolean.TRUE); // password칸이 빈칸이라면 errors에 pwEmpty, TRUE 값 저장
		if(!errors.isEmpty()) return LOGIN_FORM_VIEW; // errors가 비어있지 않다면 로그인페이지 경로 리턴
		
		try {
			User user = loginService.login(id,  password);
			req.getSession().setAttribute("loginUser", user);
			res.sendRedirect(req.getContextPath() + "/view/index.jsp");
			return null; // 로그인하게되면 세션에 loginUser라는 속성과 user의 정보를 값으로 지정하고 프로젝트의 path + /index.jsp 로 리다이렉트
		}catch(LoginFailException lfe) {
			errors.put("discord", Boolean.TRUE); // 로그인 실패시 errors에 discord, TRUE 넣고 로그인 페이지 경로 리턴
			return LOGIN_FORM_VIEW;
		}
	}
}
