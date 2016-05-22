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
		jButton1 = new JButton("��ѯ");
		jButton1.addActionListener(this);
		jButton5 = new JButton("��ѯ����");
		jButton5.addActionListener(this);
		jLabel = new JLabel("�����˶�Ա����");
		
		jPanel1.add(jLabel);
		jPanel1.add(jTextField);
		jPanel1.add(jButton1);
		jPanel1.add(jButton5);
		
		jButton2 = new JButton("��Ӽ�¼");
		jButton2.addActionListener(this);
		jButton3 = new JButton("�޸ļ�¼");
		jButton3.addActionListener(this);
		jButton4 = new JButton("ɾ����¼");
		jButton4.addActionListener(this);
		jButton6 = new JButton("�ɼ�ͳ��");
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
		
		this.setTitle("�˶���ɼ�����");
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
			
			//��ѯĳһλͬѧ�ĳɼ���¼
			String name = this.jTextField.getText().trim();
			String sql = "select * from sports where sName='"+name+"'";
			sportsModel = new SportsModel();
			sportsModel.querySports(sql);
			jTable.setModel(sportsModel);
			
		}else if (arg0.getSource() == jButton5) {
			
			//��ѯ����
			sportsModel = new SportsModel();
			sportsModel.querySports("select * from sports");
			jTable.setModel(sportsModel);
			
		}else if (arg0.getSource() == jButton2) {
			
			//��Ӽ�¼
			new AddData(this, "��ӳɼ���¼", true);
			sportsModel = new SportsModel();
			sportsModel.querySports("select * from sports");
			jTable.setModel(sportsModel);
			
		}else if (arg0.getSource() == jButton3) {
			
			//�޸ļ�¼
			int rowNum = this.jTable.getSelectedRow();
			if(rowNum == -1){
				//��ʾ��Ϣ
				JOptionPane.showMessageDialog(this, "��ѡ��һ�У����޸�");
				return;
			}
			
			//��ʾ�޸ļ�¼�ĶԻ���
			new Update(this, "�޸ĳɼ���¼", true, sportsModel, rowNum);
			sportsModel = new SportsModel();
			sportsModel.querySports("select * from sports");
			jTable.setModel(sportsModel);
			
		}else if (arg0.getSource() == jButton4) {
			
			//ɾ����¼
			int rowNum = this.jTable.getSelectedRow();
			if(rowNum == -1){
				//��ʾ��Ϣ
				JOptionPane.showMessageDialog(this, "��ѡ��һ�У���ɾ��");
				return;
			}
			
			if (0==JOptionPane.showConfirmDialog(this, "ȷ��ɾ����")) {
				
				String sportsId = (String)sportsModel.getValueAt(rowNum, 0);
				String sql = "delete from sports where sId=?";
				sportsModel = new SportsModel();
				String []params = {sportsId};
				sportsModel.update(sql, params);
				sportsModel.querySports("select * from sports");
				jTable.setModel(sportsModel);
			}
			
		}else if (arg0.getSource() == jButton6) {
			
			//�ɼ�ͳ��
			new GradeRanking(this, "�ɼ�ͳ��", true);
		}
	}
	
	
}
