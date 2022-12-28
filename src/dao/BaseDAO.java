/**
 * Copyright(C) 2020  Luvina Software
 *BaseDAO.java.java, Feb 25, 2020 DuongTV
 */
package dao;

import java.sql.Connection;
import java.sql.SQLException;


/**
 * thực hiện transaction với DB
 * @author DuongTV
 * 
 */
public interface BaseDAO {
	/**
	 * setAutoCommit(false)
	 * @throws SQLException 
	 */
	public void disableAutoCommit() throws SQLException;
	/**
	 * set connection
	 * @param conn
	 */
	public void setConn(Connection conn);
	/**
	 * Lấy ra connection
	 * @return Connection
	 */
	public Connection getConn();
	/*
	 * Thực hiện commit giao dịch khi tất cả các câu lệnh thành công
	 * @param cnt
	 * @throws SQLException 
	 */
	public void commitTrans() throws SQLException ;
	/**
	 * thực hiện rollback data khi insert bị lỗi
	 * @param cnt
	 * @throws SQLException 
	 */
	public void rollbackTrans() throws SQLException;
	/**
	 * tạo kết nối tới Database
	 * @throws Exception 
	 * @exception SQLException
	 * @exception ClassNotFoundException
	 */
	public void openConnection() throws Exception;
	/**
	 * closeConnection dùng để đóng kết nối Database
	 * @param connection: Kết nối cần đóng
	 * @throws Exception 
	 * @exception: SQLException
	 */
	public void closeConnection() throws Exception;

}
