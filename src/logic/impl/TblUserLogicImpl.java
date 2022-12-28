/**
 * Copyright(C) 2020  Luvina Software
 * TblUserLogicImpl.java, Feb 25, 2020 DuongTV
 */
package logic.impl;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.TblDetailUserJapanDAO;
import dao.TblUserDAO;
import dao.impl.TblDetailUserJapanDAOImplement;
import dao.impl.TblUserDAOImplement;
import entities.TblDetailUserJapanEntities;
import entities.TblUserEntities;
import entities.UserInforEntities;
import logic.TblUserLogic;
import utils.CommonUtils;
import utils.ConstantUtils;
import utils.StringUtils;

/**
 * xử lý logic với dữ liệu lấy từ DB
 * @author DuongTV
 */
public class TblUserLogicImpl implements TblUserLogic {
	/**
	 * kiểm tra có tồn tại hay không login_name được nhập từ màn hình true khi tồn
	 * tại login_name này và giống password trong db, false khi tồn tại login_name
	 * nhưng không giống password trong db
	 * @throws Exception
	 */
	@Override
	public boolean checkExistLoginId(String loginId, String password) throws Exception {
		// khai báo biến kiểm tra
		boolean check = false;
		// lấy về một đối tượng TblUser để kiểm tra password
		TblUserEntities tblUser = new TblUserDAOImplement().getUserByLoginId(loginId);
		// bắt lỗi null
		try {
			// kiểm tra mật khẩu người dùng nhập và mật khẩu đã được mã hóa trong DB
			check = CommonUtils.compareString(CommonUtils.encryptPassword(tblUser.getSalt(), password),
					tblUser.getPassword());
			// xử lý khi có lỗi
		} catch (NullPointerException | NoSuchAlgorithmException e) {
			// trả về kết quả
			return false;
			// kết thúc vòng catch
		}
		// trả về giá trị của biến check
		return check;
		// kết thúc hàm
	}

	@Override
	/**
	 * Lấy danh sách user
	 */
	public List<UserInforEntities> getListUser(int offset, int limit, int groupId, String fullName, String sortType,
			String sortByFullName, String sortByCodeLevel, String sortByEndDate) throws Exception {
		TblUserDAO tblUserDAOImpl = new TblUserDAOImplement();
		List<UserInforEntities> listUserInforEntities = new ArrayList<UserInforEntities>();
		// xử lý toán tử wildcard
		fullName = CommonUtils.wildcard(fullName);
		// gọi hàm getListUSer
		listUserInforEntities = tblUserDAOImpl.getListUser(offset, limit, groupId, fullName, sortType, sortByFullName,
				sortByCodeLevel, sortByEndDate);
		return listUserInforEntities;
	}

	/**
	 * Lấy ra tổng số bản ghi
	 * @param groupId
	 * @param fullName
	 * @return totalRecords
	 * @throws Exception
	 */
	public int getTotalRecords(int groupId, String fullName) throws Exception {
		TblUserDAO tblUserDAOImpl = new TblUserDAOImplement();
		fullName = CommonUtils.wildcard(fullName);
		// trả về tổng số bản ghi
		return tblUserDAOImpl.getTotalRecords(groupId, fullName);
	}

	@Override
	public boolean createUser(UserInforEntities userInforEntities) throws Exception {
		// Khởi tạo đối tượng tblUserDAOImplement để thêm data
		TblUserDAO tblUserDAOImplement = new TblUserDAOImplement();
		try {
			// Khai báo đối tượng tbl_user entities
			TblUserEntities tblUserEntities = CommonUtils.getTblUser(userInforEntities);
			// quản lý transaction
			// Mở kết nối
			tblUserDAOImplement.openConnection();
			// gán kết nối
			tblUserDAOImplement.getConn();
			// setAutoCommit
			tblUserDAOImplement.disableAutoCommit();
			// Gọi phương thức insert của tầng DAO với bảng tbl_user
			int userId = tblUserDAOImplement.insertUser(tblUserEntities);
			String codeLevel = userInforEntities.getCodeLevel();
			// insert thông tin của bảng tbl_detail_user_japan
			if (!ConstantUtils.DEFAULT_CODE_LEVEL.equals(codeLevel)) {
				// Exception
				// Khởi tạo đối tượng tblDetailUserJapanDAOImplement để thêm data
				TblDetailUserJapanDAO tblDetailUserJapanDAOImplement = new TblDetailUserJapanDAOImplement();
				// set lại kết nối dùng chung
				tblDetailUserJapanDAOImplement.setConn(tblUserDAOImplement.getConn());
				// lấy giá trị của tbl_detail_user_japan
				userInforEntities.setUserId(userId);
				TblDetailUserJapanEntities tblDetailUserJapanEntities = CommonUtils.getDetailUser(userInforEntities);
				// gọi dao để insert dữ liệu
				tblDetailUserJapanDAOImplement.insertDetailUserJapan(tblDetailUserJapanEntities);
			}
			// commit giao dịch khi thực hiện thành công
			tblUserDAOImplement.commitTrans();
		} catch (SQLException e) {
			// thực hiện rollback data
			tblUserDAOImplement.rollbackTrans();
			System.out.println("TblUserLogicImpl + createUser() + " + e.getMessage());
			// trả về false khi không thành công
			return false;
		} finally {
			// Đóng kết nối đã mở
			tblUserDAOImplement.closeConnection();
		}
		// trả về true khi thực hiện thành công
		return true;
	}

	@Override
	/**
	 * Kiểm tra email đã tồn tại trong bảng tbl_user chưa
	 */
	public boolean checkExistedEmail(int userId, String email) throws Exception {
		// Khai báo gọi tầng DAO
		TblUserDAO tblUserDAOImplement = new TblUserDAOImplement();
		// Lấy ra TblUser để kiểm tra
		TblUserEntities tblUserEntities = tblUserDAOImplement.getUserByEmail(userId, email);
		String mail = tblUserEntities.getEmail();
		// kiểm tra cả id cho trường hợp edit
		if (userId == 0) {
			// Kiểm tra kết quả trả về của hàm getUserByEmail
			if (email.equals(mail)) {
				// khác null trả về true
				return true;
			}
			// trường hợp tồn tại một email khác khác id truyền vào
		} else if (userId != tblUserEntities.getUserId() && email.equals(mail)) {
			return true;
		}
		// ngược lại, trả về false
		return false;
	}

	@Override
	/**
	 * Kiểm tra loginName đã tồn tại trong bảng tbl_user chưa
	 */
	public boolean checkExistedLoginName(int userId, String loginName) throws Exception {
		// Khai báo gọi tầng DAO
		TblUserDAO tblUserDAOImplement = new TblUserDAOImplement();
		// Lấy ra login Name
		TblUserEntities tblUserEntities = tblUserDAOImplement.getTblUserByLoginName(userId, loginName);
		String accountName = tblUserEntities.getLoginName();
		if (userId == 0) {
			// Kiểm tra kết quả trả về của hàm getUserByLoginName
			if (!StringUtils.checkNullAndNotType(accountName)) {
				// khác null, hay tồn tại 1 user có login_name này, trả về true
				return true;
			}
		} else {
			// trường hợp edit sẽ kiểm tra với một accout duy nhất sẽ ứng với một id duy
			// nhất
			if (userId == tblUserEntities.getUserId() && loginName.equals(accountName)) {
				return true;
			}
		}
		// trả về false khi không thỏa mãn
		return false;
	}

	@Override
	public TblUserEntities getUserByLoginId(String loginName) throws Exception {
		// Khai báo khởi tạo DAO
		TblUserDAO tblUserDAO = new TblUserDAOImplement();
		// khai báo khởi tạo TblUser
		TblUserEntities tblUserEntities = new TblUserEntities();
		// lấy thông tin TblUser theo loginId
		tblUserEntities = tblUserDAO.getUserByLoginId(loginName);
		// trả về TblUser
		return tblUserEntities;
	}


	@Override
	/**
	 * Lấy thông tin chi tiết của môt user
	 */
	public UserInforEntities getUserInforByUserId(int userId) throws Exception {
		TblUserDAO tblUserDAOImpl = new TblUserDAOImplement();
		// Lấy UserInfor từ DAO
		UserInforEntities userInforEntities = tblUserDAOImpl.getUserInforByUserId(userId);
		return userInforEntities;
	}

	@Override
	public boolean updateTblUser(UserInforEntities userInforEntities, boolean valueOfCheckDetail) throws Exception {
		TblUserDAO tblUserDAOImpl = new TblUserDAOImplement();
		try {
			// lấy thông tin chứa tbl_user
			TblUserEntities tblUserEntities = CommonUtils.getTblUser(userInforEntities);
			// lấy thông tin chứa tbl_detail_user_japan
			TblDetailUserJapanEntities tblDetailUserJapanEntities = CommonUtils.getDetailUser(userInforEntities);
			// mở kết nối
			tblUserDAOImpl.openConnection();
			// setAutoCommit(false)
			tblUserDAOImpl.getConn().setAutoCommit(false);
			// gọi phương thức update
			tblUserDAOImpl.updateTblUser(tblUserEntities);
			// các trường hợp vùng trình độ tiếng nhật, kiểm tra trình độ tiếng nhật từ DB
			TblDetailUserJapanDAO tblDetailUserJapanDAOImpl = new TblDetailUserJapanDAOImplement();
			// set lại kêt nối cho đối tượng TblDetaiUserJapanDao
			tblDetailUserJapanDAOImpl.setConn(tblUserDAOImpl.getConn());
			// khai báo biến để kiểm tra trường hợp query trong bảng tbl_detail_user_japan
			String codeLevelUserInfor = userInforEntities.getCodeLevel();
			String codeLevelDefault = ConstantUtils.DEFAULT_CODE_LEVEL;
			if (valueOfCheckDetail) {
				// có dữ liệu trong DB và có thông tin userInfor
				if (!codeLevelDefault.equals(codeLevelUserInfor)) {
					// thực hiện update
					tblDetailUserJapanDAOImpl.updateDetailUserJapan(tblDetailUserJapanEntities);
					// có thông tin trong DB và không có thông tin userInfor
				} else {
					// lấy id từ userInfor
					int userId = userInforEntities.getUserId();
					// Thực hiện delete
					tblDetailUserJapanDAOImpl.deleteDetailJapanByUserId(userId);
				}
				// có thông tin của userInfor và không có thông tin của DB
			} else {
				if (!codeLevelDefault.equals(codeLevelUserInfor)) {
					// thực hiện insert
					tblDetailUserJapanDAOImpl.insertDetailUserJapan(tblDetailUserJapanEntities);
				}
			}
			// commit
			tblUserDAOImpl.commitTrans();
		} catch (SQLException e) {
			// rollback khi có lỗi
			tblUserDAOImpl.rollbackTrans();
			return false;
		} finally {
			// đóng kết nối
			tblUserDAOImpl.closeConnection();
		}
		return true;
	}

	@Override
	public boolean deleteTblUSer(int userId, boolean valueCheckDetailJapan) throws Exception {
		TblUserDAO tblUserDAOImpl = new TblUserDAOImplement();
		try {
			tblUserDAOImpl.openConnection();
			tblUserDAOImpl.getConn().setAutoCommit(false);
			// tồn tại detail user sẽ thực hiện xóa thông tin của bảng tbl_detail_user_japan
			// trước
			if (valueCheckDetailJapan) {
				TblDetailUserJapanDAO tblDetailUserJapanDAOImpl = new TblDetailUserJapanDAOImplement();
				// set connect tạo kết nối dùng chung
				tblDetailUserJapanDAOImpl.setConn(tblUserDAOImpl.getConn());
				// xóa thông tin bảng tbl_detail
				tblDetailUserJapanDAOImpl.deleteDetailJapanByUserId(userId);
			}
			// xóa thông tin bảng tbl_user
			tblUserDAOImpl.deleteTblUser(userId);
			// commit
			tblUserDAOImpl.commitTrans();
			// lỗi sẽ rollback data
		} catch (SQLException e) {
			System.out.println("TblUserLogicImpl + deleteTblUser() + " + e.getMessage());
			tblUserDAOImpl.rollbackTrans();
			// trả về false
			return false;
		} finally {
			tblUserDAOImpl.closeConnection();
		}
		return true;
	}

	@Override
	public int checkUserAdmin(int userId) throws Exception {
		// khai báo khởi tạo DAO
		TblUserDAO tblUserDAOimpl = new TblUserDAOImplement();
		// lấy thông tin tblUser từ DB
		TblUserEntities tblUserEntities = tblUserDAOimpl.getTblUserById(userId);
		int rule = tblUserEntities.getRule();
		int valueId = tblUserEntities.getUserId();
		if(rule == 0 && valueId != 0 ) {
			//trả về 0 nếu là tồn tại và là admin
			return 0;
		} else if(rule == 1 && valueId != 0){
			//trả về 1 khi tồn tại và là user
			return 1;
		}else {
			//trả về -1 nếu không tồn tại 
			return -1;
		}
	}
}
