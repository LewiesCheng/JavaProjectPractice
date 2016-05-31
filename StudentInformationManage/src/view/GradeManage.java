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

public class GradeManage extends JFrame  implements ActionListener{

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
	StudentModel studentModel = null;
	String sql = "select student.sId, student.sName, mt.grade, eg.grade, db.grade, sp.grade from student_information.student, "
			+ "(select sId, grade from student_information.grade where cId in (select  cId from student_information.course where cName = '�ߵ���ѧ')) mt, "
			+ "(select sId, grade from student_information.grade where cId in (select  cId from student_information.course where cName = 'Ӣ��')) eg,"
			+ "(select sId, grade from student_information.grade where cId in (select  cId from student_information.course where cName = '���ݿ�')) db, "
			+ "(select sId, grade from student_information.grade where cId in (select  cId from student_information.course where cName = '�������')) sp "
			+ "where sp.sId=eg.sId and eg.sId=mt.sId and mt.sId=db.sId and db.sId=student.sId ";
	
	public GradeManage() {
		
		jPanel1 = new JPanel();
		jTextField = new JTextField(10);
		jButton1 = new JButton("��ѯ");
		jButton1.addActionListener(this);
		jButton5 = new JButton("��ѯ����");
		jButton5.addActionListener(this);
		jLabel = new JLabel("����ѧ������");
		
		jPanel1.add(jLabel);
		jPanel1.add(jTextField);
		jPanel1.add(jButton1);
		jPanel1.add(jButton5);
		
		jButton2 = new JButton("��ӳɼ�");
		jButton2.addActionListener(this);
		jButton4 = new JButton("�˳���ҳ");
		jButton4.addActionListener(this);
		
		jPanel2 = new JPanel();
		jPanel2.add(jButton2);
		jPanel2.add(jButton4);
		
		studentModel = new StudentModel();
		studentModel.queryAllGrade(sql);
		jTable = new JTable(studentModel);
		
		jScrollPane = new JScrollPane(jTable);
		
		this.add(jPanel1,"North");
		this.add(jScrollPane);
		this.add(jPanel2,"South");
		
		this.setTitle("ѧ���ɼ�����");
		this.setSize(500, 400);
		int width = Toolkit.getDefaultToolkit().getScreenSize().width;
		int height = Toolkit.getDefaultToolkit().getScreenSize().height;
		this.setLocation(width/2+50, height/2-200);
		
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		if(arg0.getSource() == jButton1){
			
			//��ѯĳһλͬѧ����Ϣ��¼
			String name = this.jTextField.getText().trim();
			String sql2 = sql + " and sName='"+name+"'";
			studentModel = new StudentModel();
			studentModel.queryAllGrade(sql2);
			jTable.setModel(studentModel);
			
		}else if (arg0.getSource() == jButton5) {
			
			//��ѯ����
			studentModel = new StudentModel();
			studentModel.queryAllGrade(sql);
			jTable.setModel(studentModel);
			
		}else if (arg0.getSource() == jButton2) {
			
			//���
			new AddGrade(this, "���ѧ���ɼ�", true);
			studentModel = new StudentModel();
			studentModel.queryAllGrade(sql);
			jTable.setModel(studentModel);
			
		}else if (arg0.getSource() == jButton4) {
			
			this.dispose();		
		}
	}

}
