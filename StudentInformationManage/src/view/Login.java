package view;

import java.awt.Container;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import model.StudentModel;

public class Login extends JFrame  implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	JLabel jLabel1, jLabel2;
	JTextField jTextField;
	JPasswordField jPasswordField;
	ButtonGroup buttonGroup;
	JRadioButton jRadioButton1, jRadioButton2;
	JButton jButton1, jButton2;
	Font font = new Font("����", Font.PLAIN, 16);
	
	public static void main(String[] args) {
		
		new Login();
	}

	public Login() {
		
		Container container = this.getContentPane();
		this.setLayout(null);
		jLabel1 = new JLabel("�û�����");
		jLabel1.setFont(font);
		jLabel1.setBounds(100, 30, 80, 25);
		container.add(jLabel1);
		
		jTextField = new JTextField();
		jTextField.setBounds(180, 30, 120, 25);
		container.add(jTextField);
		
		jLabel2 = new JLabel("���� ��");
		jLabel2.setFont(font);
		jLabel2.setBounds(110, 60, 80, 25);
		container.add(jLabel2);
		
		jPasswordField = new JPasswordField();
		jPasswordField.setBounds(180, 60, 120, 25);
		container.add(jPasswordField);
		
		buttonGroup = new ButtonGroup();
		
		jRadioButton1 = new JRadioButton("��ʦ", true);
		jRadioButton1.setBounds(120, 90, 80, 25);
		container.add(jRadioButton1);
		
		jRadioButton2 = new JRadioButton("ѧ��", false);
		jRadioButton2.setBounds(200, 90, 80, 25);
		container.add(jRadioButton2);
		buttonGroup.add(jRadioButton1);
		buttonGroup.add(jRadioButton2);
		
		jButton1 = new JButton("��¼");
		jButton1.addActionListener(this);
		jButton1.setBounds(110, 180, 70, 30);
		container.add(jButton1);
		jButton2 = new JButton("ȡ��");
		jButton2.addActionListener(this);
		jButton2.setBounds(210, 180, 70, 30);
		container.add(jButton2);
		
		this.setTitle("��¼ϵͳ");
		this.setSize(400, 300);
		int width = Toolkit.getDefaultToolkit().getScreenSize().width;
		int height = Toolkit.getDefaultToolkit().getScreenSize().height;
		this.setLocation(width/2-200, height/2-150);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {

		if(arg0.getSource() == jButton1){
			
			StudentModel studentModel = new StudentModel();
			if (jRadioButton1.isSelected()) {
				
				String []params = {jTextField.getText(), "1" };
				
				@SuppressWarnings({"deprecation" })
				String password = jPasswordField.getText();
				String sql = "select password from login where user=? and level=?";
				if (studentModel.checkUser(sql, params, password)) {
					
					new Teacher(jTextField.getText());
					this.dispose();
				}else {
					
					JOptionPane.showMessageDialog(this, "��¼��Ϣ����");
				}
				
			}else if (jRadioButton2.isSelected()) {
				
				String []params = {jTextField.getText(), "2" };
				
				@SuppressWarnings({"deprecation" })
				String password = jPasswordField.getText();
				String sql = "select password from login where user=? and level=?";
				if (studentModel.checkUser(sql, params, password)) {
					
					
					new Student(jTextField.getText());
					this.dispose();
				}else {
					
					JOptionPane.showMessageDialog(this, "��¼��Ϣ����");
				}
			}
			
		}else if (arg0.getSource() == jButton2) {
			
			this.dispose();
		}
		
	}

}
