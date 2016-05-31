package view;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import model.StudentModel;

public class StuInfManage extends JFrame  implements ActionListener{

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

	
	public StuInfManage() {
		
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
		
		jButton2 = new JButton("���ѧ��");
		jButton2.addActionListener(this);
		jButton4 = new JButton("ɾ��ѧ��");
		jButton4.addActionListener(this);
		jButton3 = new JButton("�˳���ҳ");
		jButton3.addActionListener(this);
		
		jPanel2 = new JPanel();
		jPanel2.add(jButton2);
		jPanel2.add(jButton4);
		
		studentModel = new StudentModel();
		studentModel.queryStudent("select * from student");
		jTable = new JTable(studentModel);
		
		jScrollPane = new JScrollPane(jTable);
		
		this.add(jPanel1,"North");
		this.add(jScrollPane);
		this.add(jPanel2,"South");
		
		this.setTitle("ѧ����Ϣ����");
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
			String sql = "select * from student where sName='"+name+"'";
			studentModel = new StudentModel();
			studentModel.queryStudent(sql);
			jTable.setModel(studentModel);
			
		}else if (arg0.getSource() == jButton5) {
			
			//��ѯ����
			studentModel = new StudentModel();
			studentModel.queryStudent("select * from student");
			jTable.setModel(studentModel);
			
		}else if (arg0.getSource() == jButton2) {
			
			//��Ӽ�¼
			new AddStudent(this, "���ѧ����¼", true);
			studentModel = new StudentModel();
			studentModel.queryStudent("select * from student");
			jTable.setModel(studentModel);
			
		}else if (arg0.getSource() == jButton4) {
			
			//ɾ����¼
			int rowNum = this.jTable.getSelectedRow();
			if(rowNum == -1){
				//��ʾ��Ϣ
				JOptionPane.showMessageDialog(this, "��ѡ��һ�У���ɾ��");
				return;
			}
			
			if (0==JOptionPane.showConfirmDialog(this, "ȷ��ɾ����")) {
				
				String sId = (String)studentModel.getValueAt(rowNum, 0);
				String sql = "delete from student where sId=?";
				String sql2 = "delete from login where user=?";
				studentModel = new StudentModel();
				String []params = {sId};
				studentModel.update(sql, params);
				studentModel.update(sql2, params);
				studentModel.queryStudent("select * from student");
				jTable.setModel(studentModel);
			}
			
		}else if (arg0.getSource() == jButton3) {
			
			this.dispose();	
		}
		
	}

	
	
}
