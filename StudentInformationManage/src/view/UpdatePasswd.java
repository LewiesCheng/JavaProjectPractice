package view;

import java.awt.Container;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

import model.StudentModel;

public class UpdatePasswd extends JDialog implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	String sId = null;
	String level = null;
	JLabel jLabel1, jLabel2, jLabel3;
	JPasswordField jPasswordField1, jPasswordField2, jPasswordField3;
	JButton jButton1, jButton2;
	
	public UpdatePasswd(Dialog owner, String title, boolean modal, String id, String i) {
		
		super(owner, title, modal);
		sId = id;
		level = i;
		Container container = this.getContentPane();
		this.setLayout(null);
		jLabel1 = new JLabel("������ԭ���룺");
		jLabel1.setBounds(10, 35, 100, 25);
		container.add(jLabel1);
		jLabel2 = new JLabel("�����������룺");
		jLabel2.setBounds(10, 70, 100, 25);
		container.add(jLabel2);
		jLabel3 = new JLabel("ȷ�������룺");
		jLabel3.setBounds(10, 105, 100, 25);
		container.add(jLabel3);
		
		jPasswordField1 = new JPasswordField(20);
		jPasswordField1.setBounds(110, 35, 120, 25);
		container.add(jPasswordField1);
		jPasswordField2 = new JPasswordField(20);
		jPasswordField2.setBounds(110, 70, 120, 25);
		container.add(jPasswordField2);
		jPasswordField3 = new JPasswordField(20);
		jPasswordField3.setBounds(110, 105, 120, 25);
		container.add(jPasswordField3);
		
		jButton1 = new JButton("ȷ���޸�");
		jButton1.addActionListener(this);
		jButton1.setBounds(30, 160, 100, 30);
		container.add(jButton1);
		jButton2 = new JButton("ȡ���޸�");
		jButton2.addActionListener(this);
		jButton2.setBounds(150, 160, 100, 30);
		container.add(jButton2);
		
		this.setSize(280, 300);
		int width = Toolkit.getDefaultToolkit().getScreenSize().width;
		int height = Toolkit.getDefaultToolkit().getScreenSize().height;
		this.setLocation(width/2+150, height/2-150);
		this.setVisible(true);
	}

	public UpdatePasswd(Frame owner, String title, boolean modal, String id, String i){
		super(owner, title, modal);
		sId = id;
		level = i;
		Container container = this.getContentPane();
		this.setLayout(null);
		jLabel1 = new JLabel("������ԭ���룺");
		jLabel1.setBounds(10, 35, 100, 25);
		container.add(jLabel1);
		jLabel2 = new JLabel("�����������룺");
		jLabel2.setBounds(10, 70, 100, 25);
		container.add(jLabel2);
		jLabel3 = new JLabel("ȷ�������룺");
		jLabel3.setBounds(10, 105, 100, 25);
		container.add(jLabel3);
		
		jPasswordField1 = new JPasswordField(20);
		jPasswordField1.setBounds(110, 35, 120, 25);
		container.add(jPasswordField1);
		jPasswordField2 = new JPasswordField(20);
		jPasswordField2.setBounds(110, 70, 120, 25);
		container.add(jPasswordField2);
		jPasswordField3 = new JPasswordField(20);
		jPasswordField3.setBounds(110, 105, 120, 25);
		container.add(jPasswordField3);
		
		jButton1 = new JButton("ȷ���޸�");
		jButton1.addActionListener(this);
		jButton1.setBounds(30, 160, 100, 30);
		container.add(jButton1);
		jButton2 = new JButton("ȡ���޸�");
		jButton2.addActionListener(this);
		jButton2.setBounds(150, 160, 100, 30);
		container.add(jButton2);
		
		this.setSize(280, 300);
		int width = Toolkit.getDefaultToolkit().getScreenSize().width;
		int height = Toolkit.getDefaultToolkit().getScreenSize().height;
		this.setLocation(width/2+150, height/2-150);
		this.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == jButton1) {
			StudentModel studentModel = new StudentModel();
			String []params = {sId,level};
			String sql = "select password from login where user=? and level=?";
			if (studentModel.checkUser(sql, params, new String(jPasswordField1.getPassword()))) {
				
				if (new String(jPasswordField2.getPassword()).equals(new String(jPasswordField3.getPassword()))) {
					
					String sql2 = "update login set password=? where user=? and level=?";
					String []p = {new String(jPasswordField2.getPassword()), sId, level};
					if (studentModel.update(sql2, p)) {
						JOptionPane.showMessageDialog(this, "�޸ĳɹ�");
					} else {
						JOptionPane.showMessageDialog(this, "�޸�ʧ��");
					}
					this.dispose();
				}else{
					JOptionPane.showMessageDialog(this, "���������벻ͬ");
				}
			} else{
				JOptionPane.showMessageDialog(this, "ԭ�������");
			}
		}else if (e.getSource() == jButton2) {
			
			this.dispose();
		}
		
	}

}
