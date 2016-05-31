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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.StudentModel;

public class AddStudent extends JDialog implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	JLabel jLabel1, jLabel2, jLabel3, jLabel4;
	JTextField jTextField1, jTextField2, jTextField3, jTextField4;
	JButton jButton1, jButton2;
	JPanel jPanel1, jPanel2, jPanel3;
	
	public AddStudent(Frame owner, String title, boolean modal) {
		
		super(owner, title, modal);
		
		jLabel1 = new JLabel("学号");
		jLabel2 = new JLabel("姓名");
		jLabel3 = new JLabel("联系方式");
		jLabel4 = new JLabel("家庭住址");
		
		jTextField1 = new JTextField();
		jTextField2 = new JTextField();
		jTextField3 = new JTextField();
		jTextField4 = new JTextField();
		
		jButton1 = new JButton("确认添加");
		jButton1.addActionListener(this);
		jButton2 = new JButton("取消添加");
		jButton2.addActionListener(this);
		
		jPanel1 = new JPanel();
		jPanel2 = new JPanel();
		jPanel3 = new JPanel();
		//布局
		jPanel1.setLayout(new GridLayout(4, 1));
		jPanel2.setLayout(new GridLayout(4, 1));
		
		jPanel1.add(jLabel1);
		jPanel1.add(jLabel2);
		jPanel1.add(jLabel3);
		jPanel1.add(jLabel4);
		
		jPanel2.add(jTextField1);
		jPanel2.add(jTextField2);
		jPanel2.add(jTextField3);
		jPanel2.add(jTextField4);
		
		jPanel3.add(jButton1);
		jPanel3.add(jButton2);
		
		this.add(jPanel1, BorderLayout.WEST);
		this.add(jPanel2, BorderLayout.CENTER);
		this.add(jPanel3, BorderLayout.SOUTH);
		
		this.setSize(300, 200);
		int width = Toolkit.getDefaultToolkit().getScreenSize().width;
		int height = Toolkit.getDefaultToolkit().getScreenSize().height;
		this.setLocation(width/2+100, height/2-150);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == jButton1) {
			
			String sql = "insert into student value (?,?,?,?)";
			String sql2 = "insert into login value (?,?,?)";
			String []params = new String[4];
			params[0] = jTextField1.getText();
			params[1] = jTextField2.getText();
			params[2] = jTextField3.getText();
			params[3] = jTextField4.getText();
			
			String []params2 = new String[3];
			params2[0] = "2";
			params2[1] = jTextField1.getText();
			params2[2] = jTextField1.getText();
			
			StudentModel studentModel = new StudentModel();
			if (!studentModel.update(sql, params)) {
				JOptionPane.showMessageDialog(this, "添加失败");
			}
			if (!studentModel.update(sql2, params2)) {
				JOptionPane.showMessageDialog(this, "初始化学生登录失败");
			}
			this.dispose();
		}else if (e.getSource() == jButton2) {
			this.dispose();
		}
		
	}	
}
