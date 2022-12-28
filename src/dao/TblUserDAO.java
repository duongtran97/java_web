/**
 * Copyright(C) 2020  Luvina Software
 *TblUserDAO.java.java, Feb 25, 2020 DuongTV
 */
package dao;


import java.sql.SQLException;
import java.util.List;

import entities.TblUserEntities;
import entities.UserInforEntities;

/**
 * Thao tác với DB của các chức năng login + list/search user
 * @author DuongTV
 * 
 */
public interface TblUserDAO extends BaseDAO{
	/**
	 * lấy ra TblUser theo id để kiểm tra user admin
	 * @param useId id cần lấy
	 * @return TblUser
	 * @throws Exception
	 */
	public TblUserEntities getTblUserById(int useId) throws Exception;
	/**
	 * Kiểm tra tồn tại id
	 * @param userId id cần kiểm tra
	 * @return userId giá trị lấy được
	 * @throws Exception
	 */
	public TblUserEntities getLoginNameById(int userId) throws Exception;
	/**
	 * Lấy thông tin user theo id
	 * @param userId tham số cần lấy thông tin
	 * @return UserInforEntities
	 * @throws Exception
	 */
	public UserInforEntities getUserInforByUserId(int userId) throws Exception;
	/**
	 * 
	 * Lấy giá trị trong database theo login name theo loginId 
	 * nhập vào từ người dùng
	 * @param loginId giá trị login name nhập vào từ màn hình
	 * @return list của đối tượng TblUserEntities
	 * @throws Exception 
	 */
	public TblUserEntities getUserByLoginId(String loginId) throws Exception;
	/**
	 * lấy danh sách thông tin của User
	 * @param offset vị trí data cần lấy
	 * @param limit số lượng lấy 
	 * @param groupId mã nhóm tìm kiếm 
	 * @param fullName Tên tìm kiếm 
	 * @param sortType nhận biết xem cột nào được sắp xếp ưu tiên
	 * @param sortByFullName Giá trị sắp xếp của cột Tên
	 * @param sortByCodeLevel Giá trị sắp xếp của cột trình độ TN
	 * @param sortByEndDate Giá trị sắp xếp của cột ngày hết hạn 
	 * @return List<UserInforEntities> danh sách thông tin user
	 * @throws Exception 
	 */
	public  List<UserInforEntities> getListUser(int offset, int limit, int groupId, String fullName, String sortType,
			String sortByFullName, String sortByCodeLevel, String sortByEndDate) throws Exception;
	/**
	 * Lấy tổng số user
	 * @param groupId mã nhóm tìm kiếm 
	 * @param fullName tên tìm kiếm 
	 * @return tổng số record
	 * @throws Exception 
	 */
	public int getTotalRecords(int groupId,String fullName) throws Exception;
	/**
	 * Thực hiện thêm mới 1 user vào DB
	 * @param tblUserEntities
	 * @return userId id của bản ghi vừa được thêm vào
	 * @throws Exception 
	 */
	public int insertUser(TblUserEntities tblUserEntities) throws Exception;
	/**
	 * update TblUser
	 * @param tblUserEntities
	 * @return true - update thành công, false - update không thành công 
	 * @throws Exception
	 */
	public void updateTblUser(TblUserEntities tblUserEntities) throws Exception;
	/**
	 * xóa bản ghi có userId truyền vào
	 * @param userId
	 * @return true - thành công, false - không thành công
	 * @throws SQLException 
	 */
	public void deleteTblUser(int userId) throws SQLException;
	/**
	 * Lấy thông tin của user theo Email
	 * @param userId Update: có giá trị truyền vào, Add thì truyền vào null
	 * @param email
	 * @return tblUserEntities 
	 * @throws Exception 
	 */
	public TblUserEntities getUserByEmail(int userId, String email) throws Exception;
	/**
	 * Lấy thông tin của user theo Tel
	 * @param userId Update: có giá trị truyền vào, Add thì truyền vào null
	 * @param tel số điện thoại người dùng nhập
	 * @return tblUserEntities
	 * @throws Exception
	 */
	public TblUserEntities getUSerByTel(int userId, String tel) throws Exception;
	/**
	 * Kiểm tra loginName đã tồn tại trong bảng tbl_user chưa
	 * @param userId update: có giá trị truyền vào, add: truyền vào null
	 * @param loginName tên đăng nhập cần kiểm tra 
	 * @return TblUserEntities 
	 * @throws Exception 
	 *
	 */
	public TblUserEntities getTblUserByLoginName(int userId,String loginName) throws Exception;
}
