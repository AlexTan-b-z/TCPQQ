package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ChooseUI extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField usernameField;
	
	/**
	 * Create the frame.
	 */
	public ChooseUI(String[] onlineUser,String username) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		String user[][] = new String[onlineUser.length][2];
		for(int i=0; i<onlineUser.length; i++){
			user[i][0]=i+1+"";
			user[i][1]=onlineUser[i];
			System.out.println((i+1)+":"+onlineUser[i]);
		}
		table = new JTable();
		table.setModel(new DefaultTableModel(
			user,
			new String[] {
				"±àºÅ", "ÓÃ»§Ãû"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		table.setBounds(121, 147, 173, -92);
		JScrollPane scroll = new JScrollPane(table);
		scroll.setLocation(0, 51);
		scroll.setSize(428,118);
		contentPane.add(scroll);
		
		//contentPane.add(table);
		
		JLabel label = new JLabel("\u5F53\u524D\u5728\u7EBF\u7528\u6237\u4E3A\uFF1A");
		label.setBounds(126, 26, 158, 21);
		contentPane.add(label);
		
		JButton button = new JButton("\u9000\u51FA\u7CFB\u7EDF");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				LoginUI login = new LoginUI();
				login.setVisible(true);
				ChooseUI.this.dispose();
			}
		});
		button.setBounds(15, 215, 123, 29);
		contentPane.add(button);
		
		usernameField = new JTextField();
		usernameField.setBounds(317, 173, 96, 27);
		contentPane.add(usernameField);
		usernameField.setColumns(10);
		
		JLabel label_1 = new JLabel("\u8BF7\u8F93\u5165\u5BF9\u65B9\u7528\u6237\u540D\uFF1A");
		label_1.setBounds(160, 176, 177, 21);
		contentPane.add(label_1);
		
		JButton button_1 = new JButton("\u63D0\u4EA4");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String toUser = usernameField.getText();
				ChatUI chatui = new ChatUI(toUser,username);
				chatui.setVisible(true);
				ChooseUI.this.dispose();
			}
		});
		button_1.setBounds(278, 215, 123, 29);
		contentPane.add(button_1);
		
		JLabel label_2 = new JLabel("\u6B22\u8FCE\u60A8\uFF1A"+username);
		label_2.setBounds(15, 0, 152, 21);
		contentPane.add(label_2);
	}
}
