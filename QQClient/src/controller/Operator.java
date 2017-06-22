package controller;

import model.ReturnResult;
import net.Client;
import util.Parser;
import util.Protocol;

public class Operator {
	
	public ReturnResult login(String username){
		System.out.println("您的用户名为："+username);
		Protocol protocol = new Protocol();
		String msg = protocol.login(username);
		Client.write(msg);
		String returnStr = Client.read();
		Parser parser = new Parser();
		ReturnResult returnResult = parser.login(returnStr);
		return returnResult;
	}
	
	public ReturnResult sendMsg(String message, String toUser){
		Protocol protocol = new Protocol();
		String msg = protocol.sendMsg(message, toUser);
		Client.write(msg);
		String returnStr = Client.read();
		Parser parser = new Parser();
		ReturnResult returnResult = parser.sendMsg(returnStr);
		return returnResult;
	}
	
	public ReturnResult readMsg(){
		String returnStr = Client.read();
		Parser parser = new Parser();
		ReturnResult returnResult = parser.read(returnStr);
		return returnResult;
	}
	
	public void exit(){
		Protocol protocol = new Protocol();
		String msg = protocol.exit();
		Client.write(msg);
	}

}
