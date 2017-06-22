package model;

public class ReturnResult {
	public String judge;
	public String message;
	public String time;
	public String fromUser;
	public String[] onlineUser;
	
	public ReturnResult(String judge, String message, String time, String fromUser, String[] onlineUser){
		this.judge = judge;
		this.message = message;
		this.time = time;
		this.fromUser = fromUser;
		this.onlineUser = onlineUser;
	}
}
