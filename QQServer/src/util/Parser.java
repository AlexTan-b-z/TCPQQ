package util;

import controller.Operator;

public class Parser {
	public String returnStr;
	
	public String factory(String receiveStr){
		String choose = receiveStr.split(":")[0];
		String endMsg = receiveStr.split(":")[1];
		if(choose.equals("login")){
			String username = endMsg.split(",")[0];
			return "login:"+username;
			
//			String password = endMsg.split(",")[1];
//			Operator operator = new Operator();
//			boolean isSuccess = operator.login(username, password);
//			if(isSuccess){
//				returnStr = "success";
//			}else{
//				returnStr = "error";
//			}
		}
		return "¹þ¹þ";
	}

}
