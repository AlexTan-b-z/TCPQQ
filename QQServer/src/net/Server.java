package net;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

import model.MyThread;

public class Server {
	
	private int port = 1234;
	public static HashMap<String,MyThread> threadMap = new HashMap<String,MyThread>();
	
	public Server(){
		try {
			ServerSocket ss = new ServerSocket(port);
			System.out.println("服务器启动了，等待客户端连接...");
			
			while(true){
				Socket tcpConn = ss.accept();
				MyThread thread1 = new MyThread(tcpConn);
				thread1.start();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Server();
	}

}
