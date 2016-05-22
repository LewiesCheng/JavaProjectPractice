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

public class AddData extends JDialog implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	JLabel id, name, longJump, highJump, run100, run400, swim;
	JTextField jTextFieldId, jTextFieldName, jTextFieldLongJump, jTextFieldHighJump, jTextFieldRun100,jTextFieldRun400, jTextFieldSwim;
	JButton confirmAdd, cancelAdd;
	JPanel jPanel1, jPanel2, jPanel3;
	public AddData(Frame owner, String title, boolean modal) {
			
		super(owner, title, modal);
		
		id = new JLabel("编号");
		name = new JLabel("姓名");
		longJump = new JLabel("跳远");
		highJump = new JLabel("跳高");
		run100 = new JLabel("100米");
		run400 = new JLabel("400米");
		swim = new JLabel("游泳");
		
		jTextFieldId = new JTextField();
		jTextFieldName = new JTextField();
		jTextFieldLongJump = new JTextField();
		jTextFieldHighJump = new JTextField();
		jTextFieldRun100 = new JTextField();
		jTextFieldRun400 = new JTextField();
		jTextFieldSwim = new JTextField();
		
		confirmAdd = new JButton("确认添加");
		confirmAdd.addActionListener(this);
		cancelAdd = new JButton("取消添加");
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
			
			String sql = "insert into sports value (?,?,?,?,?,?,?)";
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
				JOptionPane.showMessageDialog(this, "添加失败");
			}
			this.dispose();
		}else if (arg0.getSource() == cancelAdd) {
			this.dispose();
		}
	}

	
}
