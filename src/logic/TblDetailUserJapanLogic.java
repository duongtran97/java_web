/**
 * Copyright(C) 2020  Luvina Software
 *TblDetailUserJapanLogic.java, Feb 25, 2020 DuongTV
 */
package logic;

import java.util.List;

import entities.TblDetailUserJapanEntities;

/**
 * Thao tác với database và file hibernate
 * @author DuongTV
 */
public interface TblDetailUserJapanLogic {
	/**
	 * Kiểm tra tồn tại codeLevel
	 * @param userId id tham chiếu của bảng tbl_detail_user_japan
	 * @return true - có tồn tại, false - không tồn tại
	 * @throws Exception 
	 */
	public boolean checkExistDetailJapan(int userId) throws Exception;
	/**
	 * Lấy ra list Id
	 * @return list detail
	 * @throws Exception 
	 */
	public List<Integer> getListDetailId() throws Exception;
	/**
	 * Insert thông tin chi tiết của user vào bảng tbl_detail_user_japan
	 * @param tblDetailUserJapanEntities
	 * @return pk
	 * @throws Exception 
	 */
	public int insertDetailUserJapan(TblDetailUserJapanEntities tblDetailUserJapanEntities) throws Exception;
}
