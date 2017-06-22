package util;

public class Protocol {
	//operate:xxx,xxx
	public String login(String username){
		//login:username,password
		String returnMsg = "login:"+username+"\n";
		return returnMsg;
	}
	
	public String sendMsg(String message,String toUser){
		//send:haha&username
		String returnMsg = "send:"+message+"&"+toUser+"\n";
		return returnMsg;
	}
	
	public String exit(){
		String returnMsg = "exit:\n";
		return returnMsg;
	}

}
