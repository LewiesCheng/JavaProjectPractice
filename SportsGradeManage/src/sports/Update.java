package sports;

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

public class Update extends JDialog implements ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	JLabel id, name, longJump, highJump, run100, run400, swim;
	JTextField jTextFieldId, jTextFieldName, jTextFieldLongJump, jTextFieldHighJump, jTextFieldRun100,jTextFieldRun400, jTextFieldSwim;
	JButton confirmAdd, cancelAdd;
	JPanel jPanel1, jPanel2, jPanel3;
	public Update(Frame owner, String title, boolean modal, SportsModel sportsModel, int rowNum) {
			
		super(owner, title, modal);
		
		id = new JLabel("编号");
		name = new JLabel("姓名");
		longJump = new JLabel("跳远");
		highJump = new JLabel("跳高");
		run100 = new JLabel("100米");
		run400 = new JLabel("400米");
		swim = new JLabel("游泳");
		
		jTextFieldId = new JTextField();
		jTextFieldId.setText((String)sportsModel.getValueAt(rowNum, 0));
		jTextFieldId.setEditable(false);
		jTextFieldName = new JTextField();
		jTextFieldName.setText((String)sportsModel.getValueAt(rowNum, 1));
		jTextFieldLongJump = new JTextField();
		jTextFieldLongJump.setText(sportsModel.getValueAt(rowNum, 2));
		jTextFieldHighJump = new JTextField();
		jTextFieldHighJump.setText(sportsModel.getValueAt(rowNum, 3));
		jTextFieldRun100 = new JTextField();
		jTextFieldRun100.setText(sportsModel.getValueAt(rowNum, 4));
		jTextFieldRun400 = new JTextField();
		jTextFieldRun400.setText(sportsModel.getValueAt(rowNum, 5));
		jTextFieldSwim = new JTextField();
		jTextFieldSwim.setText(sportsModel.getValueAt(rowNum, 6));
		
		confirmAdd = new JButton("确认修改");
		confirmAdd.addActionListener(this);
		cancelAdd = new JButton("取消修改");
		cancelAdd.addActionListener(this);
		
		jPanel1 = new JPanel();
		jPanel2 = new JPanel();
		jPanel3 = new JPanel();
		//布局
		jPanel1.setLayout(new GridLayout(7, 1));
		jPanel2.setLayout(new GridLayout(7, 1));
		
		jPanel1.add(id);
		jPanel1.add(name);
		jPanel1.add(longJump);
		jPanel1.add(highJump);
		jPanel1.add(run100);
		jPanel1.add(run400);
		jPanel1.add(swim);
		
		jPanel2.add(jTextFieldId);
		jPanel2.add(jTextFieldName);
		jPanel2.add(jTextFieldLongJump);
		jPanel2.add(jTextFieldHighJump);
		jPanel2.add(jTextFieldRun100);
		jPanel2.add(jTextFieldRun400);
		jPanel2.add(jTextFieldSwim);
		
		jPanel3.add(confirmAdd);
		jPanel3.add(cancelAdd);
		
		this.add(jPanel1, BorderLayout.WEST);
		this.add(jPanel2, BorderLayout.CENTER);
		this.add(jPanel3, BorderLayout.SOUTH);
		
		this.setSize(300, 300);
		int width = Toolkit.getDefaultToolkit().getScreenSize().width;
		int height = Toolkit.getDefaultToolkit().getScreenSize().height;
		this.setLocation(width/2+80, height/2-150);
		this.setVisible(true);


}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if (arg0.getSource() == confirmAdd) {
			
			String sql = "update sports set sId=?, sName=?, longJump=?, highJump=?, run100=?, run400=?, swim=? where sId='"+jTextFieldId.getText()+"';";
			String []params = new String[7];
			params[0] = jTextFieldId.getText();
			params[1] = jTextFieldName.getText();
			if (jTextFieldLongJump.getText().length() == 0) {params[2] = null;}
			else{params[2] = jTextFieldLongJump.getText();}
			
			if (jTextFieldHighJump.getText().length() == 0) {params[3] = null;}
			else{params[3] = jTextFieldHighJump.getText();}
			
			if (jTextFieldRun100.getText().length() == 0) {params[4] = null;}
			else{params[4] = jTextFieldRun100.getText();}
			
			if (jTextFieldRun400.getText().length() == 0) {params[5] = null;}
			else{params[5] = jTextFieldRun400.getText();}
			
			if (jTextFieldSwim.getText().length() == 0) {params[6] = null;}
			else{params[6] = jTextFieldSwim.getText();}
			
			SportsModel sportsModel = new SportsModel();
			if (!sportsModel.update(sql, params)) {
				JOptionPane.showMessageDialog(this, "修改失败");
			}
			this.dispose();
		}else if (arg0.getSource() == cancelAdd) {
			this.dispose();
		}
	}
}