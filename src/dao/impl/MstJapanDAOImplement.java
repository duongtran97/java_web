/**
 * Copyright(C) 2020  Luvina Software
 *MstJapanDAOImplement.java, Feb 25, 2020 DuongTV
 */
package dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.MstJapanDAO;
import entities.MstJapanEntities;

/**
 * Implement MstGroupDao để thao tác với DB bảng mst_group
 * @author DuongTV
 */
public class MstJapanDAOImplement extends BaseDaoImpl implements MstJapanDAO {

	@Override
	/**
	 * Lấy tất cả các trình độ Tiếng Nhật có trong DB
	 */
	public List<MstJapanEntities> getAllJapan() throws Exception {
		// Khởi tạo list để lưu dữ liệu của bảng
		List<MstJapanEntities> allJapan = new ArrayList<MstJapanEntities>();
		// để tránh lỗi sql injection sử dụng PreparedStatement
		PreparedStatement ps;
		// thực thi PreparedStatement có ngoại lệ
		try {
			// Mở một kết nối tới database
			openConnection();
			// khai báo câu query sử dụng để truy vấn
			String sql = "select code_level,name_level from mst_japan order by name_level asc";
			// gắn giá trị cho ps
			ps = conn.prepareStatement(sql);
			// lấy kết quả của bảng sử dụng ResultSet
			ResultSet rs = ps.executeQuery();
			// lấy các bản ghi của bảng
			while (rs.next()) {
				// khởi tạo một đối tượng MstGroupEntities
				MstJapanEntities mstJapanEntities = new MstJapanEntities();
				// setCodeLevel
				mstJapanEntities.setCodeLevel(rs.getString(1));
				// setNameLevel
				mstJapanEntities.setNameLevel(rs.getString(2));
				// add vào list allGroup
				allJapan.add(mstJapanEntities);
				// kết thúc vòng lặp while
			}
			// xử lý ngoại lệ
		} catch (SQLException e) {
			// ghi log
			System.out.println("MstJapanDAOImplement + getAllJapan() + " + e.getMessage());
			throw e;
			// kết thúc khối catch
		} finally {
			// đóng kết nối
			closeConnection();
			// kết thúc khối finally
		}
		// trả về giá trị của list
		return allJapan;
		// kết thúc hàm
	}

	@Override
	/**
	 * lấy thông tin của bảng mst_japan
	 */
	public MstJapanEntities getNameLevelByCodeLevel(String codeLevel) throws Exception {
		MstJapanEntities mstJapanEntities = new MstJapanEntities();
		PreparedStatement ps;
		try {
			// mở kết nối
			openConnection();
			// Tạo câu truy vấn
			String sql = "select code_level,name_level from mst_japan where code_level = ?";
			ps = conn.prepareStatement(sql);
			int index = 1;
			ps.setString(index++, codeLevel);
			// thực thi truy vấn
			ResultSet rs = ps.executeQuery();
			// Lấy giá trị sau khi thực thi
			if (rs.next()) {
				mstJapanEntities.setCodeLevel(rs.getString("code_level"));
				mstJapanEntities.setNameLevel(rs.getString("name_level"));
			}
		} catch (SQLException e) {
			System.out.println("MstJapanDAOImplement + getNameLevelByCodeLevel() + " + e.getMessage());
			throw e;
		}
		return mstJapanEntities;
	}

}
