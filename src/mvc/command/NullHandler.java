package mvc.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class NullHandler implements CommandHandler { // CommandHandler 인터페이스 상속

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) 
	throws Exception { 
		res.sendError(HttpServletResponse.SC_NOT_FOUND);
		return null; // process메소드를 오버라이드 해서 NullHandler 일시 SC_NOT_FOUND 를 띄운다(SC_NOT_FOUND는 404코드)
	}

}
