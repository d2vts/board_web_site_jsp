package util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class CharacterEncodingFilter implements Filter { // filter인터페이스 상속
	
	private String encoding;
	
	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException{
		// 필터기능을 수행하는 메소드. chain을 이용해서 체인의 다음 필터로 처리를 전달 할 수 있다.
		req.setCharacterEncoding(encoding); //request 파라미터를 이용하여 요청의 필터 작업 수행. 사용할 encoding은 init메소드의 encoding에서 설정
		chain.doFilter(req,res);// chain의 다음 필터 처리
		//만약 res.~ 가있으면 response를 이용하여 응답의 필터링 작업 수행
	}
	//doFilter( ) 메소드는 필터링 설정한 서블릿을 실행할 때마다 호출되는 메소드로서 실제 필터링 기능을 구현하는 메소드
	

	@Override
	public void init(FilterConfig config) throws ServletException{
		//필터를 초기화 할때 호출하는 메소드
		encoding = config.getInitParameter("encoding"); //doFilter에서 적용할 인코딩의 설정을 web.xml의 filterSetting 부분의 init-param의 param-value로 설정
		if(encoding == null) {
			encoding = "UTF-8"; // 해당 encoding방식이 정해져있지않아 null값이 나올 경우 UTF-8방식으로 인코딩 설정
		}
	}
	
	@Override
	public void destroy() {// 필터가 웹 컨테이너에서 삭제될 때 호출 즉, 주로 필터가 사용한 자원을 반납할때 사용
		
	}
}
