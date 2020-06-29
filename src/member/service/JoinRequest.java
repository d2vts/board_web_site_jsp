package member.service;

import java.util.Map;

public class JoinRequest {
	private String id;
	private String password;
	private String name;
	private String confirmPassword;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	
	public boolean isPwOk() {
		return password != null && password.equals(confirmPassword);
	}
	
	public void validate(Map<String, Boolean> errors) {
		checkEmpty(errors, id, "id");
		checkEmpty(errors, name, "name");
		checkEmpty(errors, password, "password");
		checkEmpty(errors, confirmPassword, "confirmPassword");
		if(!errors.containsKey("confirmPassword")) {
			if(!isPwOk())errors.put("notMatch", Boolean.TRUE);
		}
	}// 각 필드의 데이터가 유효한지 검사한다. 파라미터로 전달받는 errors 맵 객체는 에러 정보를 담기 위해 사용한다. 예를 들어, id필드 값이 올바르지 않는 경우 errors 맵 객체에 "id"키의 값으로 TRUE값을 추가
	//이 errors파라미터는 뒤에서 살펴볼 회원가입핸들러에서 생성해서 전달한다. isPwOk()이용해서 암호 불일치시 "notMatch"라는 에러 키 추가
	
	
	
	

	private void checkEmpty(Map<String, Boolean> errors, String value, String fieldName) {
		if(value==null||value.isEmpty())errors.put(fieldName, Boolean.TRUE);
	}
}
