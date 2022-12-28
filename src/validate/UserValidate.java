/**
 * Copyright(C) 2020  Luvina Software
 *LogInValidate.java, Feb 25, 2020 DuongTV
 */
package validate;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import entities.UserInforEntities;
import logic.MstGroupLogic;
import logic.MstJapanLogic;
import logic.TblUserLogic;
import logic.impl.MstGroupLogicImpl;
import logic.impl.MstJapanLogicImpl;
import logic.impl.TblUserLogicImpl;
import utils.CommonUtils;
import utils.ConstantUtils;
import utils.MessageErrorPropertiesUtils;
import utils.StringUtils;

/**
 * xác thực dữ liệu khi xử lý đăng nhập
 * @author DuongTV
 */
public class UserValidate {
	/**
	 * xác thực loginId và password
	 * @param loginId acount người dùng nhập từ màn hình
	 * @param password mật khẩu người dùng nhập từ màn hình
	 * @return list các lỗi 
	 * @throws Exception 
	 *
	 */
	public List<String> validateLogin(String loginId, String password) throws Exception {
		// Khai báo đối tượng TblUserLogicImpl để sử dụng phương thức 
		TblUserLogic tblUserLogicImpl = new TblUserLogicImpl();
		//khởi tạo đối tượng Map để lấy các lỗi 
		new MessageErrorPropertiesUtils();
		//Khởi tạo List lưu các lỗi
		List<String> listErr = new ArrayList<String>();
		//kiểm tra lỗi khi nhập từ màn hình
		if ("".equals(loginId) && "".equals(password)) {
			//lỗi không nhập tài khoản
			listErr.add(ConstantUtils.ER001_LOGINNAME);
			//lỗi không nhập mật khẩu
			listErr.add(ConstantUtils.ER001_PASS);
			//kiểm tra riêng mật khẩu
		} else if ("".equals(password)) {
			//lỗi không nhập mật khẩu
			listErr.add(ConstantUtils.ER001_PASS);
			//Kiểm tra riêng tài khoản
		} else if ("".equals(loginId)) {
			//Lỗi không nhập mật khẩu
			listErr.add(ConstantUtils.ER001_LOGINNAME);
			//kiểm tra tính đúng đắn của tài khoản và mật khẩu
		} else if (!tblUserLogicImpl.checkExistLoginId(loginId, password)) {
			//tài khoản hoặc mật khẩu nhập sai
			listErr.add(MessageErrorPropertiesUtils.getValueByKey("ER016"));
			// xử lý khi không có lỗi xảy ra
		} else {
			// listErr == null
			listErr = null;
			//Kết thúc if
		}
		//trả về danh sách lỗi
		return listErr;
		//kết thúc hàm
	}
	/**
	 * Thực hiện validate thông tin user nhập từ màn hình ADM003
	 * @param userInforEntities đối tượng nhập từ ADM003
	 * @return lstError các lỗi khi validate không thành công
	 * @throws Exception
	 */
	public static List<String> validateUserInfor(UserInforEntities userInforEntities) throws Exception {
		// Khởi tạo lstError
		List<String> lstError = new ArrayList<String>();
		// Khai báo sử dụng tầng DAO lấy giá trị từ bảng tbl_user
		TblUserLogic tblUserLogicImpl = new TblUserLogicImpl();
		// Khai bao MstGroupLogic
		MstGroupLogic mstGroupLogicImpl = new MstGroupLogicImpl();
		MstJapanLogic mstJapanLogicImpl = new MstJapanLogicImpl();
		// user_id
		int userId = userInforEntities.getUserId();
		// lấy giá trị account
		String accountName = userInforEntities.getAccountName();
		// Lấy giá trị của group
		int groupId = userInforEntities.getGroupId();
		// Lấy giá trị fullName
		String fullName = userInforEntities.getFullName();
		// Lấy giá trị katakana
		String kanaName = userInforEntities.getKanaName();
		// Lấy email
		String email = userInforEntities.getEmail();
		// Lấy số điện thoại
		String tel = userInforEntities.getTel();
		// Lấy password
		String pass = userInforEntities.getPassword();
		// Lấy password xác nhận
		String passConfirm = userInforEntities.getPasswordConfirm();
		// Lấy trình độ tiếng nhật
		String codeLevelDefault = ConstantUtils.DEFAULT_CODE_LEVEL;
		String codeLevel = userInforEntities.getCodeLevel();
		String totalPoint = userInforEntities.getTotalPoint();
		Date startDate = userInforEntities.getStartDate();
		Date endDate = userInforEntities.getEndDate();
		int total = userInforEntities.getTotal();
		List<Integer> lsBirtday = userInforEntities.getLsBirthday();
		List<Integer> lsStartDate = userInforEntities.getLsStartDate();
		List<Integer> lsEndDate = userInforEntities.getLsEndDate();
		// Validate accountName
		if(userId <= 0) {
			// Nếu không nhập
			if (StringUtils.checkNullAndNotType(accountName)) {
				lstError.add(ConstantUtils.ER001_ACCOUNT);
				// ngược lại sẽ kiểm tra format và số kí tự
				// nếu bắt đầu bằng số
			} else {
				if (StringUtils.isDigit(accountName.charAt(0))
						|| !StringUtils.checkMatcherPattern(accountName, ConstantUtils.FORM_ACCOUNT)) {
					// Lỗi format
					lstError.add(ConstantUtils.ER019_ACCOUNT);
				} else if (!StringUtils.checkLengthString(4, 15, accountName)) {
					// không nằm trong khoảng kí tự cho phép
					lstError.add(ConstantUtils.ER007_ACCOUNT);
					// hết else
				} else {
					if(userId <= 0) {
						// kiểm tra tồn tại
						if (tblUserLogicImpl.checkExistedLoginName(userId,accountName)) {
							// đã tồn tại
							lstError.add(ConstantUtils.ER003_ACCOUNT);
						}
						
					}
				}
			}
		}
		// Kiểm tra group
		// Nếu không chọn
		if (groupId == 0) {
			// lỗi không chọn
			lstError.add(ConstantUtils.ER002_GROUP);
			// groupName không tồn tại
		} else if (!mstGroupLogicImpl.checkExistGroupId(groupId)) {
			// lỗi không tồn tại
			lstError.add(ConstantUtils.ER004_GROUP);
			// Hết kiểm tra tồn tại
		}
		// Kiểm tra không nhập tên
		if (StringUtils.checkNullAndNotType(fullName)) {
			// lỗi không nhập
			lstError.add(ConstantUtils.ER001_NAME);
			// ngược lại sẽ kiểm tra số kí tự, format
			// số kì tự > 255
		} else if (!StringUtils.checkLengthString(0, 255, fullName)) {
			// lỗi vượt quá 255 kí tự
			lstError.add(ConstantUtils.ER006_NAME);
			// hết if
		}
		// Kiểm tra khi nhập kanaName
		if (!StringUtils.checkNullAndNotType(kanaName)) {
			// khi không phải là kí tự katakana
			if (!StringUtils.checkKanakata(kanaName)) {
				// lỗi chứa kí tự không phải là katakana
				lstError.add(ConstantUtils.ER009_KANA);
				// Kiểm tra số kí tự
			} else if (!StringUtils.checkLengthString(0, 255, kanaName)) {
				// lỗi vượt quá 255 kí tự
				lstError.add(ConstantUtils.ER006_KANA);
				// hết if
			}
			// hết else
		}
		// validate birthday
		if (!CommonUtils.checkDate(lsBirtday)) {
			// ngày không hợp lệ
			lstError.add(ConstantUtils.ER011_BIRTHDAY);
		}
		
			// không nhập email
			if (StringUtils.checkNullAndNotType(email)) {
				// lỗi không nhập
				lstError.add(ConstantUtils.ER001_MAIL);
				// ngược lại sẽ kiểm tra số kí tự, format, tồn tại
				// số kí tự lớn hơn 100
			} else if (!StringUtils.checkLengthString(0, 100, email)) {
				// lỗi vượt quá 100 kí tự
				lstError.add(ConstantUtils.ER006_MAIL);
			} else if (!StringUtils.checkMatcherPattern(email, ConstantUtils.MAIL_FORMAT)) {
				// sai format
				lstError.add(ConstantUtils.ER005_MAIL);
			} else {
				if (tblUserLogicImpl.checkExistedEmail(userId, email)) {
					// đã tồn tại
					lstError.add(ConstantUtils.ER003_MAIL);
				}
			}
		

		// validate số điện thoại
		if (StringUtils.checkNullAndNotType(tel)) {
			// Không nhập
			lstError.add(ConstantUtils.ER001_TEL);
		} else if (!StringUtils.checkLengthString(0, 14, tel)) {
			// vượt quá kí tự cho phép
			lstError.add(ConstantUtils.ER006_TEL);
		} else if (!StringUtils.checkMatcherPattern(tel, ConstantUtils.TEL_FORMAT)) {
			// Sai format
			lstError.add(ConstantUtils.ER005_TEL);
		}
		if(userId <= 0) {
			// validate password
			if (StringUtils.checkNullAndNotType(pass)) {
				// lỗi không nhập
				lstError.add(ConstantUtils.ER001_PASS);
			} else if (!StringUtils.is1Byte(pass)) {
				// lỗi không phải kí tự 1 byte
				lstError.add(ConstantUtils.ER008_PASS);
			} else if (!StringUtils.checkLengthString(5, 15, pass)) {
				// lỗi độ dài
				lstError.add(ConstantUtils.ER007_PASS);
			}
			// validate pass xác nhận
			if (!pass.equals(passConfirm) || StringUtils.checkNullAndNotType(passConfirm)) {
				lstError.add(ConstantUtils.ER017_PASS_CONFIRM);
			}
		}
		// validate trình độ tiếng nhật
		// nếu không chọn trình độ sẽ không validate
		if (!codeLevelDefault.equals(codeLevel)) {
			// check khoong ton tai trinh do tieng nhat
			if (!mstJapanLogicImpl.checkExistCodeLevel(codeLevel)) {
				lstError.add(ConstantUtils.ER004_JAPAN);
			} else {
				if (!CommonUtils.checkDate(lsStartDate)) {
					// ngày startDate không hợp lệ
					lstError.add(ConstantUtils.ER011_STARTDATE);
				}
				// enđate không hợp lệ
				if (!CommonUtils.checkDate(lsEndDate)) {
					lstError.add(ConstantUtils.ER011_ENDDATE);
				} else {
					// lỗi ngày hết hạn < ngày bắt đầu
					if (!endDate.after(startDate)) {
						lstError.add(ConstantUtils.ER012);
					}
				}
				// validate total
				if (StringUtils.checkNullAndNotType(totalPoint)) {
					// Lỗi không nhập
					lstError.add(ConstantUtils.ER002_TOTAL);
				} else if (!StringUtils.is1Byte(totalPoint)) {
					// Lỗi phải là haftsize
					if (!StringUtils.checkIsStringNumber(totalPoint)) {
						lstError.add(ConstantUtils.ER018_TOTAL);
					}
				} else if (total < 80 || total > 180) {
					// Lỗi maxlength
					lstError.add(ConstantUtils.ER021_TOTAL);
				}
			}

		}
		return lstError;

	}
	//kết thúc class
}
