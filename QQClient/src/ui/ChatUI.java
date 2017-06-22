package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controller.Operator;
import model.ChatMsg;
import model.ReturnResult;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JScrollBar;

public class ChatUI extends JFrame {

	private JPanel contentPane;
	public static String returnStr = "";
	public static String returnTime = "";
	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public ChatUI(String toUser,String username) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTextArea sendMsgtextArea = new JTextArea();
		sendMsgtextArea.setBounds(46, 144, 335, 61);
		contentPane.add(sendMsgtextArea);
		
		JLabel sendLabel = new JLabel("\u4F60\u8FD8\u672A\u53D1\u9001\u6D88\u606F");
		sendLabel.setBounds(46, 47, 335, 21);
		contentPane.add(sendLabel);
		
		JLabel receiveLabel = new JLabel("\u4F60\u8FD8\u672A\u63A5\u6536\u5230\u6D88\u606F");
		receiveLabel.setBounds(46, 83, 335, 21);
		contentPane.add(receiveLabel);
		
		JButton button = new JButton("\u53D1\u9001");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Operator operate = new Operator();
				String message = sendMsgtextArea.getText();
				System.out.println(toUser+message);
				operate.sendMsg(message, toUser);		
				ChatMsg chat = new ChatMsg(message,toUser);
				sendLabel.setText("你向"+toUser+"发送了:"+message);
			}
		});
		button.setBounds(278, 215, 123, 29);
		contentPane.add(button);
		
		JButton button_1 = new JButton("\u9000\u51FA\u7CFB\u7EDF");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ChatUI.this.dispose();
			}
		});
		button_1.setBounds(15, 215, 123, 29);
		contentPane.add(button_1);
		
		JLabel lblSdasd = new JLabel("欢迎你："+username);
		lblSdasd.setBounds(46, 0, 152, 21);
		contentPane.add(lblSdasd);
		
		changereturnStr();
		
		Thread t2 = new Thread(new Runnable(){  
            public void run(){  
            	while(true){
            		receiveLabel.setText(returnStr);     
            	}     		
            }
		});
		t2.start();
	}
	
	public void changereturnStr(){
		Thread t = new Thread(new Runnable(){  
            public void run(){  
            	while(true){
            		try{
	            		Operator operate = new Operator();
	            		ReturnResult receive = operate.readMsg();
	            		returnStr = receive.message;
	            		returnTime = receive.time;
	            		System.out.println("接收到信息:"+returnStr);
            		}catch(Exception e){
        				continue;
        			}
            	}
            }});  
        t.start();
	}
	
}
