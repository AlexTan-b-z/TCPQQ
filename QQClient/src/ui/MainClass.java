package ui;

import java.util.Scanner;

import controller.Operator;
import model.ReturnResult;

public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		System.out.println("��ӭ����xx����ϵͳ!");
		System.out.println("�����������û�����");
		String username = scan.nextLine();
		Operator operate = new Operator();
		ReturnResult res = operate.login(username);
		System.out.println("��ǰ���ߵ��û��У�");
		for(int i=0; i<res.onlineUser.length; i++){
			System.out.println((i+1)+":"+res.onlineUser[i]);
		}
		System.out.println("��ǰʱ��Ϊ��");
		System.out.println(res.time);
		while(true){
			System.out.println("1.������Ϣ,2.������Ϣ,3.�˳�ϵͳ");
			String choose = scan.nextLine();
			if(choose.equals("1")){
				System.out.println("����������Ҫ��˭������Ϣ��");
				String toUser = scan.nextLine();
				System.out.println("����������Ҫ���͵���Ϣ��");
				String message = scan.nextLine();
				operate.sendMsg(message, toUser);
			}else if(choose.equals("2")){
				System.out.println("�ȴ�������Ϣ��:");
				ReturnResult receive = operate.readMsg();
				System.out.println(receive.message);
				System.out.println(receive.time);
			}else{
				operate.exit();
				System.out.println("�˳��ɹ�,��ӭ�ٴι���;");
				break;
			}
		}
	}

}
