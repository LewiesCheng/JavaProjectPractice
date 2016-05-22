package sports;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Index extends JFrame implements ActionListener{

	
	
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
	SportsModel sportsModel = null;
	
	public static void main(String[] args) {
		
		new Index();
	}

	public Index() {
		
		jPanel1 = new JPanel();
		jTextField = new JTextField(10);
		jButton1 = new JButton("查询");
		jButton1.addActionListener(this);
		jButton5 = new JButton("查询所有");
		jButton5.addActionListener(this);
		jLabel = new JLabel("输入运动员姓名");
		
		jPanel1.add(jLabel);
		jPanel1.add(jTextField);
		jPanel1.add(jButton1);
		jPanel1.add(jButton5);
		
		jButton2 = new JButton("添加记录");
		jButton2.addActionListener(this);
		jButton3 = new JButton("修改记录");
		jButton3.addActionListener(this);
		jButton4 = new JButton("删除记录");
		jButton4.addActionListener(this);
		jButton6 = new JButton("成绩统计");
		jButton6.addActionListener(this);
		
		jPanel2 = new JPanel();
		jPanel2.add(jButton2);
		jPanel2.add(jButton3);
		jPanel2.add(jButton4);
		jPanel2.add(jButton6);
		
		
		
		sportsModel = new SportsModel();
		sportsModel.querySports("select * from sports");
		jTable = new JTable(sportsModel);
		
		jScrollPane = new JScrollPane(jTable);
		
		this.add(jPanel1,"North");
		this.add(jScrollPane);
		this.add(jPanel2,"South");
		
		this.setTitle("运动会成绩管理");
		this.setSize(500, 400);
		int width = Toolkit.getDefaultToolkit().getScreenSize().width;
		int height = Toolkit.getDefaultToolkit().getScreenSize().height;
		this.setLocation(width/2-500, height/2-200);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource() == jButton1){
			
			//查询某一位同学的成绩记录
			String name = this.jTextField.getText().trim();
			String sql = "select * from sports where sName='"+name+"'";
			sportsModel = new SportsModel();
			sportsModel.querySports(sql);
			jTable.setModel(sportsModel);
			
		}else if (arg0.getSource() == jButton5) {
			
			//查询所有
			sportsModel = new SportsModel();
			sportsModel.querySports("select * from sports");
			jTable.setModel(sportsModel);
			
		}else if (arg0.getSource() == jButton2) {
			
			//添加记录
			new AddData(this, "添加成绩记录", true);
			sportsModel = new SportsModel();
			sportsModel.querySports("select * from sports");
			jTable.setModel(sportsModel);
			
		}else if (arg0.getSource() == jButton3) {
			
			//修改记录
			int rowNum = this.jTable.getSelectedRow();
			if(rowNum == -1){
				//提示信息
				JOptionPane.showMessageDialog(this, "请选择一行，再修改");
				return;
			}
			
			//显示修改记录的对话框
			new Update(this, "修改成绩记录", true, sportsModel, rowNum);
			sportsModel = new SportsModel();
			sportsModel.querySports("select * from sports");
			jTable.setModel(sportsModel);
			
		}else if (arg0.getSource() == jButton4) {
			
			//删除记录
			int rowNum = this.jTable.getSelectedRow();
			if(rowNum == -1){
				//提示信息
				JOptionPane.showMessageDialog(this, "请选择一行，再删除");
				return;
			}
			
			if (0==JOptionPane.showConfirmDialog(this, "确定删除吗？")) {
				
				String sportsId = (String)sportsModel.getValueAt(rowNum, 0);
				String sql = "delete from sports where sId=?";
				sportsModel = new SportsModel();
				String []params = {sportsId};
				sportsModel.update(sql, params);
				sportsModel.querySports("select * from sports");
				jTable.setModel(sportsModel);
			}
			
		}else if (arg0.getSource() == jButton6) {
			
			//成绩统计
			new GradeRanking(this, "成绩统计", true);
		}
	}
	
	
}
