/**
 * Copyright(C) 2020  Luvina Software
 *TblDetailUserJapanDAOImplement.java, Feb 25, 2020 DuongTV
 */
package dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.TblDetailUserJapanDAO;
import entities.TblDetailUserJapanEntities;
import utils.ConstantUtils;

/**
 * thao tác với bảng tbl_detail_user_japan
 * @author DuongTV
 */
public class TblDetailUserJapanDAOImplement extends BaseDaoImpl implements TblDetailUserJapanDAO {

	@Override
	/**
	 * Thực hiện thêm data vào bảng tbl_detail_user_japan
	 */
	public void insertDetailUserJapan(TblDetailUserJapanEntities tblDetailUserJapanEntities) throws Exception {
		PreparedStatement ps;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("insert into tbl_detail_user_japan ");
			sql.append("( `user_id`,`code_level`,`start_date`,`end_date`,`total`) ");
			sql.append("values ( ?, ?, ?, ?, ?)");
			ps = conn.prepareStatement(sql.toString());
			int index = 1;
			// set các thông số cho sql
//			ps.setInt(index++, tblDetailUserJapanEntities.getDetailUserJapan());
			ps.setInt(index++, tblDetailUserJapanEntities.getUserId());
			ps.setString(index++, tblDetailUserJapanEntities.getCodeLevel());
			ps.setDate(index++, tblDetailUserJapanEntities.getStartDate());
			ps.setDate(index++, tblDetailUserJapanEntities.getEndDate());
			ps.setInt(index++, tblDetailUserJapanEntities.getTotal());
			ps.execute();
		} catch (SQLException e) {
			System.out.println("TblDetailUserJapanDAOImplement + insertDetailUserJapan() + " + e.getMessage());
			throw e;
		}
	}

	@Override
	/**
	 * Lấy ra tất cả id có trong DB
	 */
	public List<Integer> getListIdDetailUser() throws Exception {
		List<Integer> lsID = new ArrayList<Integer>();
		PreparedStatement ps;
		try {
			// mở kết nối
			openConnection();
			// khai báo query
			String sql = "select detail_user_japan_id from tbl_detail_user_japan";
			// thực thi query
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			// lấy giá trị từ resultSet
			while (rs.next()) {
				lsID.add(rs.getInt(1));
			}
		} catch (SQLException e) {
			System.out.println("TblDetailUserJapan + getListIdDetailUser() + " + e.getMessage());
			throw e;
		} finally {
			closeConnection();
		}
		return lsID;
	}

	@Override
	/**
	 * Lấy ra bản ghi tương ứng với userId
	 */
	public TblDetailUserJapanEntities getTblDetailJapanByUserId(int userId) throws Exception {
		TblDetailUserJapanEntities tblDetailUserJapanEntities = new TblDetailUserJapanEntities();
		try {
			// mở kết nối
			openConnection();
			// khai báo query
			StringBuilder sql = new StringBuilder("select * from tbl_detail_user_japan where user_id = ?");
			// thực thi kêt nối
			PreparedStatement ps = conn.prepareStatement(sql.toString());
			int index = 1;
			ps.setInt(index++, userId);
			ResultSet rs = ps.executeQuery();
			// lấy kết quả
			while (rs.next()) {
				tblDetailUserJapanEntities.setCodeLevel(rs.getString(ConstantUtils.DB_CODE_LEVEL));
				tblDetailUserJapanEntities.setDetailUserJapan(rs.getInt(ConstantUtils.DB_DETAIL_ID));
				tblDetailUserJapanEntities.setEndDate(rs.getDate(ConstantUtils.DB_END_DATE));
				tblDetailUserJapanEntities.setStartDate(rs.getDate(ConstantUtils.DB_START_DATE));
				tblDetailUserJapanEntities.setTotal(rs.getInt(ConstantUtils.DB_TOTAL));
				tblDetailUserJapanEntities.setUserId(rs.getInt(ConstantUtils.DB_USER_ID));
			}
		} catch (SQLException e) {
			System.out.println("TblDetailUserJapan + getDetailJapanByUserId() + " + e.getMessage());
			throw e;
		} finally {
			// đóng kết nối
			closeConnection();
		}
		return tblDetailUserJapanEntities;
	}

	@Override
	/**
	 * xóa bản ghi có user_id là userId
	 */
	public void deleteDetailJapanByUserId(int userId) throws Exception {
		try {
			String sql = "delete from tbl_detail_user_japan where user_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			int index = 1;
			ps.setInt(index++, userId);
			ps.execute();
		} catch (SQLException e) {
			System.out.println("TblDetailUserJapan + deleteDetailJapanByUserId() + " + e.getMessage());
			throw e;
		}
	}

	@Override
	/**
	 * cập nhật thông tin trình độ tiếng nhật
	 */
	public void updateDetailUserJapan(TblDetailUserJapanEntities tblDetailUserJapanEntities) throws Exception {
		try {
			StringBuilder sql = new StringBuilder("update tbl_detail_user_japan set ");
			sql.append("code_level = ?");
			sql.append(",start_date = ?");
			sql.append(",end_date = ?");
			sql.append(",total = ?");
			sql.append(" where user_id = ?");
			PreparedStatement ps = conn.prepareStatement(sql.toString());
			int index = 1;
			ps.setString(index++, tblDetailUserJapanEntities.getCodeLevel());
			ps.setDate(index++, tblDetailUserJapanEntities.getStartDate());
			ps.setDate(index++, tblDetailUserJapanEntities.getEndDate());
			ps.setInt(index++, tblDetailUserJapanEntities.getTotal());
			ps.setInt(index++, tblDetailUserJapanEntities.getUserId());
			ps.execute();
		} catch (SQLException e) {
			System.out.println("TblDetailUserJapan + updateDetailJapan() + " + e.getMessage());
			throw e;
		}
	}

}
