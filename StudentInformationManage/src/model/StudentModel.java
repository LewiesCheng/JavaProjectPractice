package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;


public class StudentModel extends AbstractTableModel{

	
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
	 * 学生登录
	 */
	public boolean checkUser(String sql, String []params, String password){
		boolean b = false;
		
		try {
			connection = new ConnectDB().getConnect();
			preparedStatement = connection.prepareStatement(sql);
			for(int i = 0; i < params.length; i++){
				preparedStatement.setString(i+1, params[i]);
			}
			
			resultSet = preparedStatement.executeQuery();
			
			if (resultSet.next()) {
				
				if (resultSet.getString(1).equals(password)) {
					
					b = true;
				}
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			
			//关闭资源 
			this.close();
		}
		
		return b;
	}
	
	/**
	 * 查询单人成绩记录
	 * @param sql  sql查询语句
	 */
	public void queryGrade(String sql) {
		
		columnNames = new Vector<>();
		columnNames.add("学号");
		columnNames.add("学科");
		columnNames.add("成绩");
		
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
				rowData.add(hang);
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
		} finally {
			this.close();
		}
		
	}

	/**
	 * 查询个人信息
	 */
	public void queryStudent(String sql) {
		
		columnNames = new Vector<>();
		columnNames.add("学号");
		columnNames.add("姓名");
		columnNames.add("联系方式");
		columnNames.add("家庭地址");
		
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
				rowData.add(hang);
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		} finally {
			this.close();
		}
		
	}
	

	/**
	 * 查询成绩信息
	 */
	public void queryAllGrade(String sql) {
		
		columnNames = new Vector<>();
		columnNames.add("学号");
		columnNames.add("姓名");
		columnNames.add("高等数学");
		columnNames.add("英语");
		columnNames.add("数据库");
		columnNames.add("软件工程");
		
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
				hang.add(resultSet.getString(3));
				hang.add(resultSet.getString(4));
				rowData.add(hang);
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		} finally {
			this.close();
		}
		
	}
	
	/**
	 * 修改记录
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
