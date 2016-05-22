/**
 * 
 */
package sports;

import java.sql.*;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;

public class SportsModel extends AbstractTableModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	Vector<Vector<String>> rowData;
	Vector<String> columnNames;
	PreparedStatement preparedStatement = null;
	Connection connection = null;
	ResultSet resultSet = null;
	
	/**
	 * 关闭用于数据库操作的资源
	 */
	public void close() {

		try {
			
			if(resultSet != null) {
				
				resultSet.close();
				resultSet = null;
			}
			
			if (preparedStatement != null) {
				
				preparedStatement.close();
				preparedStatement = null;
			}
			
			if (connection != null) {
				
				connection.close();
				connection = null;
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	/**
	 * 查询记录
	 * @param sql  sql查询语句
	 */
	public void querySports(String sql) {
		
		columnNames = new Vector<>();
		columnNames.add("编号");
		columnNames.add("姓名");
		columnNames.add("跳远");
		columnNames.add("跳高");
		columnNames.add("100米");
		columnNames.add("400米");
		columnNames.add("游泳");
		
		rowData = new Vector<>();
		
		try {
			
			connection = new ConnectDB().getConnect();
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()){
				
				Vector<String> hang = new Vector<>();
				hang.add(resultSet.getString(1));
				hang.add(resultSet.getString(2));
				hang.add(resultSet.getString(3));
				hang.add(resultSet.getString(4));
				hang.add(resultSet.getString(5));
				hang.add(resultSet.getString(6));
				hang.add(resultSet.getString(7));
				
				rowData.add(hang);
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
		} finally {
			this.close();
		}
		
	}

	/**
	 * 增删改记录
	 * @param sql sql语句
	 * @param params sql语句中的一些变量
	 * @return
	 */
	public boolean update(String sql, String []params) {
		
		boolean b = false;
		try {
			
			connection = new ConnectDB().getConnect();
			preparedStatement = connection.prepareStatement(sql);
			for(int i = 0; i < params.length; i++){
				preparedStatement.setString(i+1, params[i]);
			}
			
			int i = preparedStatement.executeUpdate();
			
			if(i == 1){
				b = true;
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		} finally {
			
			this.close();
		}
		return b;
	}
	
	/**
	 * 查询单项记录
	 * @param sql  sql查询语句
	 * @param String sports 选择的运动项目
	 */
	public void gradeRanking(String sql, String sports) {
		
		columnNames = new Vector<>();
		columnNames.add("排名");
		columnNames.add("编号");
		columnNames.add("姓名");
		if (sports.equals("longJump")) {
			columnNames.add("跳远");
		}else if (sports.equals("highJump")) {
			columnNames.add("跳高");
		}else if (sports.equals("run100")) {
			columnNames.add("100米");
		}else if (sports.equals("run400")) {
			columnNames.add("400米");
		}else if (sports.equals("swim")) {
			columnNames.add("游泳");
		}
		
		rowData = new Vector<>();
		
		try {
			
			connection = new ConnectDB().getConnect();
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			int rank = 1;
			while(resultSet.next()){
				
				Vector<String> hang = new Vector<>();
				hang.add(rank+"");
				hang.add(resultSet.getString(1));
				hang.add(resultSet.getString(2));
				hang.add(resultSet.getString(3));
				
				rowData.add(hang);
				rank++;
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
		} finally {
			this.close();
		}
		
	}
	
	@Override
	public int getColumnCount() {
		
		return this.columnNames.size();
	}

	@Override
	public int getRowCount() {

		return this.rowData.size();
	}

	@Override
	public String getValueAt(int arg0, int arg1) {

		return (this.rowData.get(arg0)).get(arg1);
	}

	@Override
	public String getColumnName(int arg0) {
		
		return (String)this.columnNames.get(arg0);
	}
	
	
}
