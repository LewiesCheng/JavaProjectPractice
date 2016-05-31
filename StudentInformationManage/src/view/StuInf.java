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

public class StuInf  extends JDialog implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	JLabel jLabel1, jLabel2, jLabel3, jLabel4, jLabel5, jLabel6;
	JTextField jTextField1, jTextField2, jTextField3, jTextField4;
	JButton jButton1, jButton2, jButton3;
	JPanel jPanel1, jPanel2, jPanel3, jPanel4;
	StudentModel studentModel = null;
	String sId = null;
	
	
	public StuInf(Frame owner, String title, boolean modal, String id) {
		
		super(owner, title, modal);
		sId = id;
		jLabel1 = new JLabel("个人信息");
		jLabel2 = new JLabel("学号");
		jLabel3 = new JLabel("姓名");
		jLabel4 = new JLabel("联系方式");
		jLabel5 = new JLabel("家庭住址");
		jLabel6 = new JLabel("登录密码");
		
		studentModel = new StudentModel();
		studentModel.queryStudent("select * from student where sId = '"+sId+"'");
		jTextField1 = new JTextField();
		jTextField1.setEditable(false);
		jTextField1.setText(studentModel.getValueAt(0, 0));
		jTextField2 = new JTextField();
		jTextField2.setEditable(false);
		jTextField2.setText(studentModel.getValueAt(0, 1));
		jTextField3 = new JTextField();
		jTextField3.setText(studentModel.getValueAt(0, 2));
		jTextField4 = new JTextField();
		jTextField4.setText(studentModel.getValueAt(0, 3));
		
		jButton1 = new JButton("修改");
		jButton1.addActionListener(this);
		jButton2 = new JButton("确认修改");
		jButton2.addActionListener(this);
		jButton3 = new JButton("取消修改");
		jButton3.addActionListener(this);
		
		jPanel1 = new JPanel();
		jPanel2 = new JPanel();
		jPanel3 = new JPanel();
		jPanel4 = new JPanel();
		
		jPanel1.add(jLabel1);
		jPanel2.setLayout(new GridLayout(5, 1));
		jPanel3.setLayout(new GridLayout(5, 1));
		
		jPanel2.add(jLabel2);
		jPanel2.add(jLabel3);
		jPanel2.add(jLabel4);
		jPanel2.add(jLabel5);
		jPanel2.add(jLabel6);
		
		jPanel3.add(jTextField1);
		jPanel3.add(jTextField2);
		jPanel3.add(jTextField3);
		jPanel3.add(jTextField4);
		jPanel3.add(jButton1);
		
		jPanel4.add(jButton2);
		jPanel4.add(jButton3);
		
		this.add(jPanel1, BorderLayout.NORTH);
		this.add(jPanel2, BorderLayout.WEST);
		this.add(jPanel3, BorderLayout.CENTER);
		this.add(jPanel4, BorderLayout.SOUTH);
		
		this.setSize(280, 300);
		int width = Toolkit.getDefaultToolkit().getScreenSize().width;
		int height = Toolkit.getDefaultToolkit().getScreenSize().height;
		this.setLocation(width/2+80, height/2-150);
		this.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {

		if(arg0.getSource() == jButton1){
			
			new UpdatePasswd(this, "密码修改", true, sId, "2");
		}else if (arg0.getSource() == jButton2) {
			
			String sql = "update student set contactway=?, address=? where sId=?";
			String []params = {jTextField3.getText(), jTextField4.getText(), sId};
			if (studentModel.update(sql, params)) {
				JOptionPane.showMessageDialog(this, "修改成功");
			}else {
				JOptionPane.showMessageDialog(this, "修改失败");
			}
			this.dispose();
		}else if (arg0.getSource() == jButton3) {
			
			this.dispose();
		}
		
	}

	
}
