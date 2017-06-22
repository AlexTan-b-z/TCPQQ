package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.Operator;
import model.ReturnResult;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoginUI extends JFrame {

	private JPanel contentPane;
	private JTextField loginName;
	private JLabel lblqq;
	private JButton button;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginUI frame = new LoginUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoginUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("\u8BF7\u8F93\u5165\u60A8\u7684\u7528\u6237\u540D\uFF1A");
		label.setBounds(15, 87, 177, 21);
		contentPane.add(label);
		
		loginName = new JTextField();
		loginName.setBounds(174, 84, 158, 27);
		contentPane.add(loginName);
		loginName.setColumns(10);
		
		JButton submitButton = new JButton("\u63D0\u4EA4");
		submitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String username = loginName.getText();
				Operator operate = new Operator();
				ReturnResult res = operate.login(username);
				ChooseUI chooseui = new ChooseUI(res.onlineUser,username);
				chooseui.setVisible(true);
				LoginUI.this.dispose();
			}
		});
		submitButton.setBounds(276, 168, 123, 29);
		contentPane.add(submitButton);
		
		lblqq = new JLabel("\u6B22\u8FCE\u4F7F\u7528QQ");
		lblqq.setBounds(143, 15, 110, 21);
		contentPane.add(lblqq);
		
		button = new JButton("\u9000\u51FA");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				LoginUI.this.dispose();
			}
		});
		button.setBounds(28, 168, 123, 29);
		contentPane.add(button);
	}
}
