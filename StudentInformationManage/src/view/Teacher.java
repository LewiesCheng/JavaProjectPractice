package view;

import java.awt.Container;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Teacher extends JFrame  implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	JButton jButton1, jButton2, jButton3, jButton4;
	String user = null;
	
	public Teacher(String string) {
		
		user = string;
		Container container = this.getContentPane();
		this.setLayout(null);
		
		jButton1 = new JButton("学生信息");
		jButton1.addActionListener(this);
		jButton1.setBounds(65, 30, 150, 40);
		container.add(jButton1);
		jButton2 = new JButton("学生成绩");
		jButton2.addActionListener(this);
		jButton2.setBounds(65, 110, 150, 40);
		container.add(jButton2);
		jButton3 = new JButton("修改登录密码");
		jButton3.addActionListener(this);
		jButton3.setBounds(65, 190, 150, 40);
		container.add(jButton3);
		jButton4 = new JButton("退出系统");
		jButton4.addActionListener(this);
		jButton4.setBounds(65, 270, 150, 40);
		container.add(jButton4);
		
		this.setTitle("学生管理系统");
		this.setSize(300, 400);
		int width = Toolkit.getDefaultToolkit().getScreenSize().width;
		int height = Toolkit.getDefaultToolkit().getScreenSize().height;
		this.setLocation(width/2-150, height/2-200);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

		if(arg0.getSource() == jButton1){
			
			new StuInfManage();
		}else if (arg0.getSource() == jButton2) {
			
			new GradeManage();
		}else if (arg0.getSource() == jButton3) {
			
			new UpdatePasswd(this, "密码修改", true, user, "1");	
		}else if (arg0.getSource() == jButton4) {
			
			this.dispose();
		}
		
		
	}

}
