package ui;

import java.util.Scanner;

import controller.Operator;
import model.ReturnResult;

public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		System.out.println("欢迎来到xx聊天系统!");
		System.out.println("请输入您的用户名：");
		String username = scan.nextLine();
		Operator operate = new Operator();
		ReturnResult res = operate.login(username);
		System.out.println("当前在线的用户有：");
		for(int i=0; i<res.onlineUser.length; i++){
			System.out.println((i+1)+":"+res.onlineUser[i]);
		}
		System.out.println("当前时间为：");
		System.out.println(res.time);
		while(true){
			System.out.println("1.发送信息,2.接收信息,3.退出系统");
			String choose = scan.nextLine();
			if(choose.equals("1")){
				System.out.println("请输入您想要给谁发送信息：");
				String toUser = scan.nextLine();
				System.out.println("请输入您想要发送的信息：");
				String message = scan.nextLine();
				operate.sendMsg(message, toUser);
			}else if(choose.equals("2")){
				System.out.println("等待接收信息中:");
				ReturnResult receive = operate.readMsg();
				System.out.println(receive.message);
				System.out.println(receive.time);
			}else{
				operate.exit();
				System.out.println("退出成功,欢迎再次光临;");
				break;
			}
		}
	}

}
