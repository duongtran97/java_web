/**
 * Copyright(C) 2020  Luvina Software
 *BaseDaoImpl.java, Feb 27, 2020 DuongTV
 */
package dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


import dao.BaseDAO;
import utils.ConstantUtils;

/**
 * đóng mở kết nối tới cơ sở dữ liệu
 * @author DuongTV
 */
public class BaseDaoImpl implements BaseDAO {
	protected Connection conn;
	/**
	 * @return the conn
	 */
	public Connection getConn() {
		return conn;
	}

	/**
	 * @param conn the conn to set
	 */
	public void setConn(Connection conn) {
		this.conn = conn;
	}

	/**
	 * Ghi đè phương thức openConnection của BaseDAO
	 */
	@Override
	public void openConnection() throws Exception{
		// lấy địa chỉ url của cơ sở dữ liệu
		String url = ConstantUtils.URL;
		// lấy user để đăng nhập
		String user = ConstantUtils.USER;
		// Lấy password để đăng nhập
		String password = ConstantUtils.PASS;
		// bắt lỗi không tìm thấy Class
		// và lỗi truy cập
		try {
			// nạp driver
			Class.forName("com.mysql.jdbc.Driver");
			// tạo kết nối
			conn = DriverManager.getConnection(url, user, password);
//				System.out.println("success");
		} catch ( SQLException | ClassNotFoundException e) {
			System.out.println("BaseDaoImpl + openConnection(): " + e);
			throw e;
		}
	}
	/**
	 * Ghi đè phương thức closeConnection của BaseDAO
	 */
	@Override
	public void closeConnection() throws Exception {
		// try catch bắt lỗi SQLException
		try {
			
			// kiểm tra kết nối
			// khi không còn sử dụng kết nối này nữa và kết nối này
			// chưa đóng
			if (conn != null && !conn.isClosed()) {
				// đóng kết nối
				conn.close();
				// hết khối if
			}
			// hết khối try
		} catch (SQLException e) {
			// hiện lỗi ra console
			System.out.println("BaseDaoImpl + closeConnection(): " + e);
			throw e;
			// hết khối catch
		}
		// Trả về giá trị khi đóng kết nối
	}

	@Override
	public void commitTrans() throws SQLException {
		conn.commit();
	}

	@Override
	public void rollbackTrans() throws SQLException {
		conn.rollback();
		
	}

	@Override
	public void disableAutoCommit() throws SQLException {
		conn.setAutoCommit(false);
	}
	
	// Hết Class
}
