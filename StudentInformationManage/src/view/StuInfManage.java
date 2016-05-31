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
		jButton1 = new JButton("查询");
		jButton1.addActionListener(this);
		jButton5 = new JButton("查询所有");
		jButton5.addActionListener(this);
		jLabel = new JLabel("输入学生姓名");
		
		jPanel1.add(jLabel);
		jPanel1.add(jTextField);
		jPanel1.add(jButton1);
		jPanel1.add(jButton5);
		
		jButton2 = new JButton("添加学生");
		jButton2.addActionListener(this);
		jButton4 = new JButton("删除学生");
		jButton4.addActionListener(this);
		jButton3 = new JButton("退出本页");
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
		
		this.setTitle("学生信息管理");
		this.setSize(500, 400);
		int width = Toolkit.getDefaultToolkit().getScreenSize().width;
		int height = Toolkit.getDefaultToolkit().getScreenSize().height;
		this.setLocation(width/2+50, height/2-200);
		
		this.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {

		if(arg0.getSource() == jButton1){
			
			//查询某一位同学的信息记录
			String name = this.jTextField.getText().trim();
			String sql = "select * from student where sName='"+name+"'";
			studentModel = new StudentModel();
			studentModel.queryStudent(sql);
			jTable.setModel(studentModel);
			
		}else if (arg0.getSource() == jButton5) {
			
			//查询所有
			studentModel = new StudentModel();
			studentModel.queryStudent("select * from student");
			jTable.setModel(studentModel);
			
		}else if (arg0.getSource() == jButton2) {
			
			//添加记录
			new AddStudent(this, "添加学生记录", true);
			studentModel = new StudentModel();
			studentModel.queryStudent("select * from student");
			jTable.setModel(studentModel);
			
		}else if (arg0.getSource() == jButton4) {
			
			//删除记录
			int rowNum = this.jTable.getSelectedRow();
			if(rowNum == -1){
				//提示信息
				JOptionPane.showMessageDialog(this, "请选择一行，再删除");
				return;
			}
			
			if (0==JOptionPane.showConfirmDialog(this, "确定删除吗？")) {
				
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
