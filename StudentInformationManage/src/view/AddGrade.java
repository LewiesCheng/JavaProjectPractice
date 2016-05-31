package view;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.StudentModel;

public class AddGrade extends JDialog implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	JLabel jLabel1, jLabel2, jLabel3, jLabel4, jLabel5;
	JTextField jTextField1, jTextField2, jTextField3, jTextField4, jTextField5;
	JButton jButton1, jButton2;
	JPanel jPanel1, jPanel2, jPanel3;
	
	public AddGrade(Frame owner, String title, boolean modal) {
		
		super(owner, title, modal);
		
		jLabel1 = new JLabel("学号");
		jLabel2 = new JLabel("高等数学");
		jLabel3 = new JLabel("英语");
		jLabel4 = new JLabel("数据库");
		jLabel5 = new JLabel("软件工程");
		
		jTextField1 = new JTextField();
		jTextField2 = new JTextField();
		jTextField3 = new JTextField();
		jTextField4 = new JTextField();
		jTextField5 = new JTextField();
		
		jButton1 = new JButton("确认添加");
		jButton1.addActionListener(this);
		jButton2 = new JButton("取消添加");
		jButton2.addActionListener(this);
		
		jPanel1 = new JPanel();
		jPanel2 = new JPanel();
		jPanel3 = new JPanel();
		//布局
		jPanel1.setLayout(new GridLayout(5, 1));
		jPanel2.setLayout(new GridLayout(5, 1));
		
		jPanel1.add(jLabel1);
		jPanel1.add(jLabel2);
		jPanel1.add(jLabel3);
		jPanel1.add(jLabel4);
		jPanel1.add(jLabel5);
		
		jPanel2.add(jTextField1);
		jPanel2.add(jTextField2);
		jPanel2.add(jTextField3);
		jPanel2.add(jTextField4);
		jPanel2.add(jTextField5);
		
		jPanel3.add(jButton1);
		jPanel3.add(jButton2);
		
		this.add(jPanel1, BorderLayout.WEST);
		this.add(jPanel2, BorderLayout.CENTER);
		this.add(jPanel3, BorderLayout.SOUTH);
		
		this.setSize(300, 250);
		int width = Toolkit.getDefaultToolkit().getScreenSize().width;
		int height = Toolkit.getDefaultToolkit().getScreenSize().height;
		this.setLocation(width/2+100, height/2-125);
		this.setVisible(true);
	}
	

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		if (arg0.getSource() == jButton1) {
			
			String sql = "insert into grade value (?,?,?)";
			String []params = new String[3];
			params[0] = jTextField1.getText();
			params[1] = "001";
			params[2] = jTextField2.getText();
			StudentModel studentModel = new StudentModel();
			studentModel.update(sql, params);
			
			params[1] = "002";
			params[2] = jTextField3.getText();
			studentModel.update(sql, params);
			
			params[1] = "003";
			params[2] = jTextField4.getText();
			studentModel.update(sql, params);
			
			params[1] = "004";
			params[2] = jTextField5.getText();
			studentModel.update(sql, params);
			
			this.dispose();
		}else if (arg0.getSource() == jButton2) {
			this.dispose();
		}
		
	}

}
