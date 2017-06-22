package util;

import model.ReturnResult;

public class Parser {
	public String judge = null;
	public String message = null;
	public String time = null;
	public String fromUser = null;
	public String[] onlineUser = null;
	
	//judge:message,time,fromUser&Online:username1,username2...
	public ReturnResult login(String returnStr){
		judge = returnStr.split(":")[0];
		if(judge.equals("success")){
			//ֻ��time,��onlineUser����ֵ
			time = returnStr.split("&")[0].split(",")[1];
			onlineUser = returnStr.split("&")[1].split(",");
			ReturnResult rst = new ReturnResult(judge,message,time,fromUser,onlineUser);
			return rst;
		}else{//messageΪ��½ʧ�ܣ��û������������
			message = returnStr.split(":")[1].split(",")[0];
			ReturnResult rst = new ReturnResult(judge,message,time,fromUser,onlineUser);
			return rst;
		}
		
	}
	
	public ReturnResult sendMsg(String returnStr){
		judge = returnStr.split(":")[0];
		if(judge.equals("success")){
			//success:
			message = "���ͳɹ�";
			ReturnResult rst = new ReturnResult(judge,message,time,fromUser,onlineUser);
			return rst;
		}else{
			//error:
			message = "����ʧ��,��ǰ�û�������";
			ReturnResult rst = new ReturnResult(judge,message,time,fromUser,onlineUser);
			return rst;
		}
	}
	
	public ReturnResult read(String returnStr){
		message = returnStr.split(",")[0];
		time = returnStr.split(",")[1];
		ReturnResult rst = new ReturnResult(judge,message,time,fromUser,onlineUser);
		return rst;
	}
}
