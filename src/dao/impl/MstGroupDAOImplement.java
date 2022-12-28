/**
 * Copyright(C) 2020  Luvina Software
 *MstGroupDAOImplement.java, Feb 25, 2020 DuongTV
 */
package dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.MstGroupDAO;
import entities.MstGroupEntities;

/**
 * Class thực thi truy vấn dữ liệu với bảng mst_group
 * @author DuongTV
 */
public class MstGroupDAOImplement extends BaseDaoImpl implements MstGroupDAO {
	/**
	 * Hàm lấy các giá trị của bảng mst_group ghi đè phương thức getAllGroup()
	 * @throws Exception
	 */
	@Override
	public List<MstGroupEntities> getAllGroup() throws Exception {
		// Khởi tạo list để lưu dữ liệu của bảng
		List<MstGroupEntities> allGroup = new ArrayList<MstGroupEntities>();
		// để tránh lỗi sql injection sử dụng PreparedStatement
		// khai báo sử dụng đối tượng
		PreparedStatement ps;
		// thực thi PreparedStatement có ngoại lệ
		try {
			// Mở một kết nối tới database
			openConnection();
			// khai báo câu query sử dụng để truy vấn
			String sql = "select group_id,group_name from mst_group order by group_name asc";
			// gắn giá trị cho ps
			ps = conn.prepareStatement(sql);
			// lấy kết quả của bảng sử dụng ResultSet
			ResultSet rs = ps.executeQuery();
			// lấy các bản ghi của bảng
			while (rs.next()) {
				// khởi tạo một đối tượng MstGroupEntities
				MstGroupEntities mstGroupEntities = new MstGroupEntities();
				// setGroupId
				mstGroupEntities.setGroupId(rs.getInt("group_id"));
				// setGroupName
				mstGroupEntities.setGroupName(rs.getString(2));
				// add vào list allGroup
				allGroup.add(mstGroupEntities);
				// kết thúc vòng lặp while
			}
			// xử lý ngoại lệ
		} catch (SQLException e) {
			// ghi log
			System.out.println("MstGroupDAOImplement + getAllGroup() + " + e.getMessage());
			// kết thúc khối catch
		} finally {
			// đóng kết nối
			closeConnection();
			// kết thúc khối finally
		}
		// trả về giá trị của list
		return allGroup;
		// kết thúc hàm
	}

	@Override
	/**
	 * Lấy ra groupName theo groupId
	 */
	public MstGroupEntities getGroupName(int groupId) throws Exception {
		// để tránh lỗi sql injection sử dụng PreparedStatement
		// khởi tạo một đối tượng MstGroupEntities
		MstGroupEntities mstGroupEntities = new MstGroupEntities();
		// khai báo sử dụng đối tượng
		PreparedStatement ps;
		// thực thi PreparedStatement có ngoại lệ
		try {
			// Mở một kết nối tới database
			openConnection();
			// khai báo câu query sử dụng để truy vấn
			String sql = "select group_id,group_name from mst_group where group_id = ?";
			// gắn giá trị cho ps
			ps = conn.prepareStatement(sql);
			int index = 1;
			ps.setInt(index++, groupId);
			// lấy kết quả của bảng sử dụng ResultSet
			ResultSet rs = ps.executeQuery();
			// lấy các bản ghi của bảng
			while (rs.next()) {
				// setGroupId
				mstGroupEntities.setGroupId(rs.getInt("group_id"));
				// setGroupName
				mstGroupEntities.setGroupName(rs.getString(2));
				// kết thúc vòng lặp while
			}
			// xử lý ngoại lệ
		} catch (SQLException e) {
			// ghi log
			System.out.println("MstGroupDAOImplement + getGroupName() + " + e.getMessage());
			// kết thúc khối catch
		} finally {
			// đóng kết nối
			closeConnection();
			// kết thúc khối finally
		}
		// trả về giá trị của list
		return mstGroupEntities;
	}

//kết thúc class
}
