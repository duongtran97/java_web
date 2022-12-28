/**
 * Copyright(C) 2020  Luvina Software
 *Tbl_detail_user_japanDAO.java, Feb 25, 2020 DuongTV
 */
package dao;

import java.util.List;

import entities.TblDetailUserJapanEntities;

/**
 * Thao tác với database bảng tblUser
 * @author DuongTV
 * 
 */
public interface TblDetailUserJapanDAO extends BaseDAO{
	/**
	 * xóa một bản ghi có id tương ứng
	 * @param userId
	 * @return true - thành công, false không thành công
	 * @throws Exception 
	 */
	public void deleteDetailJapanByUserId(int userId) throws Exception;
	/**
	 * update bangr tbl_detail_user_japan
	 * @param tblDetailUserJapanEntities
	 * @return true - update thành công , false - không thành công
	 * @throws Exception 
	 */
	public void updateDetailUserJapan(TblDetailUserJapanEntities tblDetailUserJapanEntities) throws Exception;
	/**
	 * Kiểm tra tồn tại trình độ tiếng nhật
	 * @param userId id của user cần kiểm tra
	 * @return tblDetailUserJapan
	 * @throws Exception 
	 */
	public TblDetailUserJapanEntities getTblDetailJapanByUserId(int userId) throws Exception;
	/**
	 * Thực hiện thêm mới 1 user vào DB
	 * @param tblDetailUserJapanEntities đối tượng chưa thông tin 
	 * @return true - thành công, false - không thành công
	 * @throws Exception 
	 */
	public void insertDetailUserJapan(TblDetailUserJapanEntities tblDetailUserJapanEntities) throws Exception;
	/**
	 * Lấy list id tương ứng trong db
	 * @return List<Integer>
	 * @throws Exception 
	 */
	public List<Integer> getListIdDetailUser() throws Exception;
}
