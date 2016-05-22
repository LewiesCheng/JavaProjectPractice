package sports;

import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class GradeRanking extends JDialog implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	JPanel jPanel1,jPanel2;
	JButton jButton1, jButton2, jButton3, jButton4, jButton5, jButton6;
	JTable jTable;
	JScrollPane jScrollPane = null;
	SportsModel sportsModel = null;
	
	public GradeRanking(Frame owner, String title, boolean modal) {
		
		super(owner, title, modal);
		jButton1 = new JButton("跳远");
		jButton1.addActionListener(this);
		jButton2 = new JButton("跳高");
		jButton2.addActionListener(this);
		jButton3 = new JButton("100米");
		jButton3.addActionListener(this);
		jButton4 = new JButton("400米");
		jButton4.addActionListener(this);
		jButton5 = new JButton("游泳");
		jButton5.addActionListener(this);
		jButton6 = new JButton("关闭");
		jButton6.addActionListener(this);
		
		jPanel1 = new JPanel();
		jPanel1.add(jButton1);
		jPanel1.add(jButton2);
		jPanel1.add(jButton3);
		jPanel1.add(jButton4);
		jPanel1.add(jButton5);
		
		jTable = new JTable();
		jScrollPane = new JScrollPane(jTable);
		
		jPanel2 = new JPanel();
		jPanel2.add(jButton6);
		
		this.add(jPanel1,"North");
		this.add(jScrollPane);
		this.add(jPanel2,"South");
		
		this.setSize(400, 400);
		int width = Toolkit.getDefaultToolkit().getScreenSize().width;
		int height = Toolkit.getDefaultToolkit().getScreenSize().height;
		this.setLocation(width/2+80, height/2-200);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

		if(arg0.getSource() == jButton1){
			
			//跳远
			String sports = "longJump";
			String sql = "select sId,sName,longJump from sports where longJump is not null  order by longJump desc;";
			sportsModel = new SportsModel();
			sportsModel.gradeRanking(sql, sports);
			jTable.setModel(sportsModel);
			
		}else if (arg0.getSource() == jButton2) {
			
			//跳高
			String sports = "highJump";
			String sql = "select sId,sName,highJump from sports where highJump is not null  order by highJump desc;";
			sportsModel = new SportsModel();
			sportsModel.gradeRanking(sql, sports);
			jTable.setModel(sportsModel);
			
			
		}else if (arg0.getSource() == jButton3) {
			
			//100米
			String sports = "run100";
			String sql = "select sId,sName,run100 from sports where run100 is not null  order by run100 desc;";
			sportsModel = new SportsModel();
			sportsModel.gradeRanking(sql, sports);
			jTable.setModel(sportsModel);
			
			
		}else if (arg0.getSource() == jButton4) {
			
			//400米
			String sports = "run400";
			String sql = "select sId,sName,run400 from sports where run400 is not null  order by run400 desc;";
			sportsModel = new SportsModel();
			sportsModel.gradeRanking(sql, sports);
			jTable.setModel(sportsModel);
			
			
		}else if (arg0.getSource() == jButton5) {
			
			//游泳
			String sports = "swim";
			String sql = "select sId,sName,swim from sports where swim is not null  order by swim desc;";
			sportsModel = new SportsModel();
			sportsModel.gradeRanking(sql, sports);
			jTable.setModel(sportsModel);
			
		}else if (arg0.getSource() == jButton6) {
			
			//关闭
			this.dispose();
		}
		
	}
}
