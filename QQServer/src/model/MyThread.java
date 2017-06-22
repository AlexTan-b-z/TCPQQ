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
			System.out.println("���ظ��ͻ������ݣ�" + returnStr);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("�յ��˿ͻ������ӣ�����" + tcpConn.getInetAddress() + ":" + tcpConn.getPort());
		try {
			Scanner netIn = new Scanner(tcpConn.getInputStream());
			OutputStream out = tcpConn.getOutputStream();
			String returnStr;
			while(true)
			{
				String receiveStr = netIn.nextLine();
				if(receiveStr.equals("exit"))
				{
					returnStr = "��ӭ�ٴ�ʹ�ã�\n";
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
						//��ȡ��ǰʱ��:
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
						returnStr = "success:��ӭ�û�"+username+"ʹ������ϵͳ"+","+time+"&"+onlineUser+"\n";
						write(returnStr);
					}else if(choose.equals("send")){
						String endMsg = receiveStr.split(":")[1];
						String message = endMsg.split("&")[0];
						String toUser = endMsg.split("&")[1];
						//��ȡ��ǰʱ��:
						Date date=new Date();
						DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						String time=format.format(date);
						
						MyThread aim = Server.threadMap.get(toUser);
						returnStr = "����" + user +" ���͵���Ϣ:" + message + "," + time + "\n";
						aim.write(returnStr);
						write("success\n");
						
					}else if(choose.equals("exit")){
						Server.threadMap.remove(user);
						System.out.println(user+" ���˳���½!");
						break;
					}
				}
			}
			System.out.println("�ͻ���:"+tcpConn.getInetAddress()+ ":" + tcpConn.getPort() +" �Ͽ��ˣ�");
			out.close();
			tcpConn.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
