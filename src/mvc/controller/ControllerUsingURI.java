package mvc.controller;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import mvc.command.NullHandler;

public class ControllerUsingURI extends HttpServlet { // HttpServlet 을 상속받는 ControllerUsingURI 클래스를 생성

	private Map<String, CommandHandler> commandHandlerMap = new HashMap<>(); // <커맨드, 핸들러인스턴스> 매핑 정보 저장

	public void init() throws ServletException { // init()을 호출하는곳으로 ServletException 전가
		
		String configFile = getInitParameter("configFile"); // configFile 파라미터값을 읽어옴
		Properties prop = new Properties();// Properties 객체 생성
		String configFilePath = getServletContext().getRealPath(configFile);// configFile 의 설정 파일 경로를 불러옴
		
		//-----------------------------------------------------------------------------------------------------
		//System.out.println("ControllerUsingURI파일에서 configFilePath : " + configFilePath);log찍음 궁금해서
		//-----------------------------------------------------------------------------------------------------
		/*
		 * ControllerUsingURI파일에서 configFilePath
		 * C:\Users\d2vts\Desktop\DEVELOPER\PORTFOLIO\JSP\_board\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\board\WEB-INF\commandHandlerURI.properties
		 */
		//System.out.println("fis 파일의 값은 : "+fis);
		//fis 파일의 값은 : java.io.FileReader@3a690f90
		try (FileReader fis = new FileReader(configFilePath)) {
			prop.load(fis);
			// 설정 파일로부터 매핑 정보를 읽어와서 Properties 객체에 저장함. 
			//Properties는 (이름, 값) 목록을 갖는 클래스임. 그래서 프로퍼티 이름을 커맨드 이름으로 사용하고 값을 클래스 이름으로 사용
		} catch (IOException e) {
			throw new ServletException(e);
		}
		Iterator keyIter = prop.keySet().iterator();//Iterator 인터페이스의 객체로 keyIter 객체 생성후 Properties에 저장되어있는 값을 keyIter에 저장
		
		// System.out.println("keyIter의 값은 : " + keyIter); keyIter의 값은 : java.util.Hashtable$Enumerator@6d1112eb
		
		//.keySet() : key값만 가져옴
		while (keyIter.hasNext()) { //해당 이터레이션(iteration)이 다음 요소를 가지고 있으면 true를 반환하고, 더 이상 다음 요소를 가지고 있지 않으면 false를 반환. 
			
			String command = (String) keyIter.next(); //프로퍼티 이름을 커맨드 이름으로 사용한다.
			
			
			System.out.println(" ||| 등록되어 있는 command 확인합니다 ||| " + command);
			
			/*
			 ||| 등록되어 있는 command 확인합니다 ||| /changePW.do
 		     ||| 등록되어 있는 command 확인합니다 ||| /login.do
 			 ||| 등록되어 있는 command 확인합니다 ||| /join.do
 			 ||| 등록되어 있는 command 확인합니다 ||| /logout.do
			*/
			
			String handlerClassName = prop.getProperty(command); // 커맨드 이름에 해당하는 핸들러 클래스 이름을 Properties에서 구한다.
			try {
				Class<?> handlerClass = Class.forName(handlerClassName); //핸들러 클래스 이름을 이용해서 Class 객체를 구한다.
				CommandHandler handlerInstance = (CommandHandler) handlerClass.newInstance();// 핸들러 객체를 생성. Properties에서 구한 핸들러 클래스이름에 해당하는 클래스의 객체를 생성
				commandHandlerMap.put(command, handlerInstance); // (커맨드, 핸들러 객체) 매핑 정보를 저장
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
				throw new ServletException(e);
			}
		}
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		process(request, response);
	}

	private void process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String command = request.getRequestURI(); // URI를 명령어로 사용하기 위해 String 타입의 command에 저장
		if (command.indexOf(request.getContextPath()) == 0) { // request.getContextPath()로 읽어온 경로를 command에 indexOf()를 사용해서 0값이 될시. 즉, 처음부터 인덱스에 일치하는 값을 가진다면
			command = command.substring(request.getContextPath().length());//command에 앞의 웹경로를 제거해서 /뒤의 커맨드만 읽히도록 command에 명령어 형태로 저장한다.
		}
		CommandHandler handler = commandHandlerMap.get(command); //commandHandlerMap에서 요청을 처리할 핸들러 객체를 구하고 파라미터인 command의 값을 키로 사용
		if (handler == null) {
			handler = new NullHandler(); // 명령어에 해당하는 핸들러 객체가 존재하지 않을 경우 NullHandler를 사용
		}
		String viewPage = null;
		try {
			viewPage = handler.process(request, response); // 구한 핸들러 객체의 process() 호출해서 요청 처리 후 결과로 보여줄 뷰 페이지 경로를 리턴 값으로 전달받음.
			// 핸들러 객체인 handler의 process()는 클라이언트의 요청을 알맞게 처리한 후, 뷰 페이지에 보여줄 결과값을 request나 session의 속성에 저장해야 함
		} catch (Throwable e) {
			throw new ServletException(e);
		}
		if (viewPage != null) {
			RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
			dispatcher.forward(request, response); // viewPage가 null값이 아닐 경우, 핸들러 객체가 리턴한 뷰 페이지로 이동
		}
	}
}
