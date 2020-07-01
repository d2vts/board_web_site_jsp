package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginCheckFilter implements Filter{

	
	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException{
		HttpServletRequest request = (HttpServletRequest)req;
		HttpSession session = request.getSession(false);
		if(session == null || session.getAttribute("LoginUser")==null) {
			HttpServletResponse response = (HttpServletResponse)res;
			response.sendRedirect(request.getContextPath()+"/login.do");
			// 세션이 존재하지 않거나 세션에 LoginUser속성이 없으면 Login.do로 리다이렉트 한다.
		}else {
			chain.doFilter(req, res);// 세션이 존재하고 LoginUser 속성이 존재하면 로그인한것으로 판단하고 기능 실행
		}
	}
	
	@Override
	public void init(FilterConfig config)throws ServletException{}
	@Override
	public void destroy() {}
}
