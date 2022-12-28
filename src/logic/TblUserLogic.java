/**
 * Copyright(C) 2020  Luvina Software
 *TblUserLogic.java, Feb 25, 2020 DuongTV
 */
package logic;

import java.util.List;

import entities.TblUserEntities;
import entities.UserInforEntities;

/**
 * xử lý logic cho các chức năng login + list/search user
 * @author DuongTV
 * 
 */
public interface TblUserLogic {
	/**
	 * Kiểm tra user có phải admin hay không
	 * @param userId id cần kiểm tra
	 * @return 0 - admin, 1 - user, -1 - không tồn tại user
	 * @throws Exception 
	 */
	public int checkUserAdmin(int userId) throws Exception;
	/**
	 * Lấy thông tin chi tiết của một User bằng user_id
	 * @param userId id của user
	 * @return userInforEntities
	 * @throws Exception
	 */
	public UserInforEntities getUserInforByUserId(int userId) throws Exception;
	/**
	 * kiểm tra sự tồn tại của loginId trong DB ,để đăng nhập vào hệ thống
	 * @param loginId giá trị login_name người dùng nhập từ màn hình
	 * @param password giá trị password người dùng nhập từ màn hình
	 * @return true khi tồn tại giá trị của loginId trong DB, false khi không tồn tại
	 * @throws Exception 
	 */
	public boolean checkExistLoginId(String loginId, String password) throws Exception;
	/**
	 * Insert data user vào bảng tbl_user và tbl_detail_user_japan
	 * @param userInforEntities đối tượng entities
	 * @return true : insert thành công
	 * @throws Exception 
	 */
	public boolean createUser(UserInforEntities userInforEntities) throws Exception;
	/**
	 * Thực hiện update các trường được admin thay đổi 
	 * @param userInforEntities
	 * @return true - update thành công, false - không thành công 
	 * @throws Exception 
	 */
	public boolean updateTblUser(UserInforEntities userInforEntities, boolean valueOfCheckDetail) throws Exception;
	/**
	 * xóa user theo id
	 * @param userId id cần xóa 
	 * @param valueCheckDetailJapan giá trị kiểm tra tồn tại bảng detail, true - tồn tại, false - không tồn tại
	 * @return
	 * @throws Exception 
	 */
	public boolean deleteTblUSer(int userId,boolean valueCheckDetailJapan) throws Exception;
	/**
	 * Kiểm tra email đã tồn tại trong bảng tbl_user chưa
	 * @param userId Update: có giá trị truyền vào, Add thì truyền vào null
	 * @param email
	 * @return true email đã tồn tại
	 * @throws Exception 
	 */
	public boolean checkExistedEmail(int userId,String email) throws Exception;
	/**
	 * Kiểm tra loginName đã tồn tại trong bảng tbl_user chưa
	 * @param loginName
	 * @return true loginName đã tồn tại
	 * @throws Exception 
	 */
	public boolean checkExistedLoginName(int userId,String loginName) throws Exception;
	/**
	 * @throws Exception 
	 * lấy ra tổng số bản ghi 
	 * @param name giải thích
	 * @return int số bản ghi 
	 */
	public int getTotalRecords(int groupId, String fullName) throws Exception;
	/**
	 * @throws Exception 
	 * Lấy danh sách user
	 * @param offset vị trí trả về danh sách 
	 * @param limit số lương bản ghi trả về
	 * @param groupId groupId tìm kiếm
	 * @param li
	 * @return danh sách các bản ghi có trong DB 
	 * @exception 
	 */
	public List<UserInforEntities> getListUser(int offset, int limit, int groupId, String fullName, String sortType,
			String sortByFullName, String sortByCodeLevel, String sortByEndDate) throws Exception;
	/**
	 * @throws Exception 
	 * @param loginName tên người dùng nhập từ màn hình
	 * @return bản ghi chứa loginName 
	 */
	public TblUserEntities getUserByLoginId(String loginName) throws Exception;
}
