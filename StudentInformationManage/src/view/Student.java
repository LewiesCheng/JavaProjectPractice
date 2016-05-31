package view;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import model.StudentModel;


public class Student extends JFrame  implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	JPanel jPanel1, jPanel2;
	JTextField jTextField;
	JLabel jLabel;
	JButton jButton1, jButton2, jButton3, jButton4, jButton5, jButton6;
	JTable jTable = null;
	JScrollPane jScrollPane = null;
	String sId = null;
	StudentModel studentModel = null;
	
	public Student(String id) {

		sId = id;
		
		jPanel1 = new JPanel();
		jTextField = new JTextField(10);
		jButton1 = new JButton("��ѯ");
		jButton1.addActionListener(this);
		jButton5 = new JButton("��ѯ����");
		jButton5.addActionListener(this);
		jLabel = new JLabel("����γ���");
		
		jPanel1.add(jLabel);
		jPanel1.add(jTextField);
		jPanel1.add(jButton1);
		jPanel1.add(jButton5);
		
		jButton2 = new JButton("������Ϣ");
		jButton2.addActionListener(this);
		jButton3 = new JButton("�˳�ϵͳ");
		jButton3.addActionListener(this);
		
		jPanel2 = new JPanel();
		jPanel2.add(jButton2);
		jPanel2.add(jButton3);
		
		studentModel = new StudentModel();
		studentModel.queryGrade("select sId, cName, grade from course, grade where course.cId=grade.cId and sId='"+sId+"';");
		jTable = new JTable(studentModel);
		
		jScrollPane = new JScrollPane(jTable);
		
		this.add(jPanel1,"North");
		this.add(jScrollPane);
		this.add(jPanel2,"South");
		
		this.setTitle("ѧ��������Ϣ����");
		this.setSize(400, 400);
		int width = Toolkit.getDefaultToolkit().getScreenSize().width;
		int height = Toolkit.getDefaultToolkit().getScreenSize().height;
		this.setLocation(width/2-200, height/2-200);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		

		if(e.getSource() == jButton1){
			
			//��ѯĳһ�γ̳ɼ�
			String sql = "select sId, cName, grade from course, grade where course.cId=grade.cId and sId='"+sId+"' and cName='"+jTextField.getText()+"';";
			studentModel = new StudentModel();
			studentModel.queryGrade(sql);
			jTable.setModel(studentModel);
			
		}else if (e.getSource() == jButton5) {
			
			//��ѯ���гɼ�
			studentModel = new StudentModel();
			studentModel.queryGrade("select sId, cName, grade from course, grade where course.cId=grade.cId and sId='"+sId+"';");
			jTable.setModel(studentModel);
			
		}else if (e.getSource() == jButton2) {
			
			new StuInf(this, "������Ϣ", false, sId);
		}else if (e.getSource() == jButton3) {
			
			this.dispose();
		}
	}

}
