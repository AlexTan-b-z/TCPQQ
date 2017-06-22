package model;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

import net.Server;
import util.Protocol;


public class MyThread extends Thread {
	
	private Socket tcpConn;
	private String user;
	
	public MyThread(Socket tcpConn){
		this.tcpConn = tcpConn;
	}
	
	public void write(String returnStr){
		try {
			OutputStream out = tcpConn.getOutputStream();
			out.write(returnStr.getBytes());
			System.out.println("返回给客户端数据：" + returnStr);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("收到了客户端连接，来自" + tcpConn.getInetAddress() + ":" + tcpConn.getPort());
		try {
			Scanner netIn = new Scanner(tcpConn.getInputStream());
			OutputStream out = tcpConn.getOutputStream();
			String returnStr;
			while(true)
			{
				String receiveStr = netIn.nextLine();
				if(receiveStr.equals("exit"))
				{
					returnStr = "欢迎再次使用！\n";
					write(returnStr);
					break;
				}
				else
				{
					//Protocol protocol = new Protocol();
					//String res = protocol.backMsg(receiveStr);
					String choose = receiveStr.split(":")[0];
					if(choose.equals("login")){
						String endMsg = receiveStr.split(":")[1];
						String username = receiveStr.split(":")[1];
						String onlineUser = "";
						//获取当前时间:
						Date date=new Date();
						DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						String time=format.format(date);
						
						Server.threadMap.put(username, this);
						user = username;
						Iterator iter = Server.threadMap.entrySet().iterator();
						while (iter.hasNext()) {
							Map.Entry entry = (Map.Entry) iter.next();
							String key = entry.getKey().toString();
							onlineUser += key + ",";
						}
						returnStr = "success:欢迎用户"+username+"使用聊天系统"+","+time+"&"+onlineUser+"\n";
						write(returnStr);
					}else if(choose.equals("send")){
						String endMsg = receiveStr.split(":")[1];
						String message = endMsg.split("&")[0];
						String toUser = endMsg.split("&")[1];
						//获取当前时间:
						Date date=new Date();
						DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						String time=format.format(date);
						
						MyThread aim = Server.threadMap.get(toUser);
						returnStr = "来自" + user +" 发送的信息:" + message + "," + time + "\n";
						aim.write(returnStr);
						write("success\n");
						
					}else if(choose.equals("exit")){
						Server.threadMap.remove(user);
						System.out.println(user+" 已退出登陆!");
						break;
					}
				}
			}
			System.out.println("客户端:"+tcpConn.getInetAddress()+ ":" + tcpConn.getPort() +" 断开了！");
			out.close();
			tcpConn.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
