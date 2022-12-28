/**
 * Copyright(C) 2020  Luvina Software
 *TblUserDAOImplement.java, Feb 25, 2020 DuongTV
 */
package dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.TblUserDAO;
import entities.TblUserEntities;
import entities.UserInforEntities;
import utils.CommonUtils;
import utils.ConstantUtils;
import utils.StringUtils;

/**
 * thao tác với DB bảng tbl_user
 * @author DuongTV
 */
public class TblUserDAOImplement extends BaseDaoImpl implements TblUserDAO {
	/**
	 * Ghi đè phương thức lấy record trong DB theo login name từ loginId nhập vào từ
	 * người dùng
	 */
	@Override
	public TblUserEntities getUserByLoginId(String loginName) throws Exception {
		// khởi tạo đối tượng bảng tbl_user
		TblUserEntities tblUser = new TblUserEntities();
		// Khởi tạo giá trị của admin
		int rule = ConstantUtils.ADMIN;
		// khai báo khởi tạo giá trị set khi execute query
		int index = 1;
		// Giá trị của cột tương ứng
		// Khai báo sử dụng PreparedStatment
		PreparedStatement ps;
		try {
			// Tạo kết nối
			openConnection();
			// Khai báo câu sql trong db
			String sql = "select * from tbl_user where rule = ? and login_name = ?";
			// gán ps để kết nối db
			ps = conn.prepareStatement(sql);
			// set giá trị int của rule
			ps.setInt(index++, rule);
			// set giá trị login name mệnh đề where
			ps.setString(index++, loginName);
			// khai báo RusultSet để lấy đối tượng tìm được
			ResultSet rs = ps.executeQuery();
			// lấy đối tượng tìm được bằng cách duyệt từng hàng dùng vòng while
			while (rs.next()) {
				// Lấy ra loginName
				tblUser.setLoginName(rs.getString(ConstantUtils.DB_LOGIN_NAME));
				// Lấy ra password
				tblUser.setPassword(rs.getString(ConstantUtils.DB_PASSWORD));
				// Lấy ra admin
				tblUser.setRule(rs.getInt(ConstantUtils.DB_RULE));
				// lấy ra salt để encrypt khi người dùng nhập mật khẩu
				tblUser.setSalt(rs.getString(ConstantUtils.DB_SALT));
			}
		} catch (SQLException e) {
			System.out.println("TblUserDAOImplement + getUserByLoginId()" + e.getMessage());
			throw e;
		} finally {
			// đóng kết nối
			closeConnection();
		}
		return tblUser;
	}

	@Override
	/**
	 * Ghi đè phương thức để thực hiện truy vấn DB Lấy ra list user tìm kiếm
	 */
	public List<UserInforEntities> getListUser(int offset, int limit, int groupId, String fullName, String sortType,
			String sortByFullName, String sortByCodeLevel, String sortByEndDate) throws Exception {
		// khởi tạo list lưu data để lưu resultset
		List<UserInforEntities> listU = new ArrayList<UserInforEntities>();
		int index = 1;
		int user = ConstantUtils.USER_RULE;
		try {
			// mở kết nối
			openConnection();
			// khai báo câu sql
			StringBuilder sql = new StringBuilder();
			sql.append("select u.user_id,full_name,birthday,group_name,email,tel,name_level,end_date,total \r\n");
			sql.append("from tbl_user u\r\n" + "inner join mst_group on u.group_id = mst_group.group_id\r\n");
			sql.append("left join tbl_detail_user_japan on u.user_id = tbl_detail_user_japan.user_id\r\n");
			sql.append("left join mst_japan on tbl_detail_user_japan.code_level = mst_japan.code_level\r\n");
			sql.append("where rule = ? and full_name like binary ? ");
			StringBuilder orderBy = new StringBuilder(" order by ");
			// nếu groupId != 0
			if (groupId != 0) {
				// sẽ append giá trị của groupId
				sql.append("and u.group_id =");
				sql.append(groupId);
			}
			// gán giá trị rỗng cho fullName khi trường này có giá trị null
			if (StringUtils.checkNullAndNotType(fullName)) {
				fullName = "";
			}
			// khai báo biến kiểu PreparedStatement để thực thi truy vấn
			PreparedStatement ps;
			// thực thi truy vấn dữ liệu, bắt ngoại lệ khi thực thi
			switch (sortType) {
			// ưu tiên theo full_name
			case "full_name":
				// sắp xếp theo full_name
				orderBy.append(" full_name " + sortByFullName);
				// sắp xếp theo name_level
				orderBy.append(",name_level " + sortByCodeLevel);
				// sắp xếp theo end_date
				orderBy.append(",end_date " + sortByEndDate);
				// thoát khỏi vòng lặp
				break;
			// ưu tiên theo name_level
			case "name_level":
				// sắp xếp theo name_level
				orderBy.append("name_level " + sortByCodeLevel);
				// sắp xếp theo full_name
				orderBy.append(",full_name " + sortByFullName);
				// sắp xếp theo end_date
				orderBy.append(",end_date " + sortByEndDate);
				// thoát khỏi vòng lặp
				break;
			// ưu tiên theo end_date
			case "end_date":
				// sắp xếp theo end_date
				orderBy.append("end_date " + sortByEndDate);
				// sắp xếp theo full_name
				orderBy.append(",full_name " + sortByFullName);
				// sắp xếp theo name_level
				orderBy.append(",name_level " + sortByCodeLevel);
				// thoát khỏi vòng lặp
				break;
			// trường hợp mặc định
			default:
				// sắp xếp theo full_name
				orderBy.append("full_name " + sortByFullName);
				// sắp xếp theo name_level
				orderBy.append(",name_level " + sortByCodeLevel);
				// sắp xếp theo end_date
				orderBy.append(",end_date " + sortByEndDate);
				// thoát khỏi vòng lặp
				break;
			// hết mệnh đề order by
			}
			sql.append(orderBy.toString());

			// mệnh đề limit lấy số bản ghi trên một trang
//			System.out.println(CommonUtils.whiteList("end_date", conn));
			sql.append(" limit ?,?");
			// thực thi truy vấn
			ps = conn.prepareStatement(sql.toString());
			// rule user
			ps.setInt(index++, user);
			// set tên
			ps.setString(index++, "%" + fullName + "%");
//			ps.setString(index++ , "%" + "\\%" + "%");
			// set vị trí bắt đầu lấy ra bản ghi
			ps.setInt(index++, offset);
			// set số lượng bản ghi cần lấy
			ps.setInt(index++, limit);
			// lấy dữ liệu sau khi thực thi bằng ResultSet
			ResultSet rs = ps.executeQuery();
			// thêm kết quả vào list đối tượng
			while (rs.next()) {
				// khai báo đối tượng uList
				UserInforEntities uList = new UserInforEntities();
				// set id
				uList.setUserId(rs.getInt(1));
				// set full_name
				uList.setFullName(rs.getString(2));
				// set birthday
				uList.setBirthday(rs.getDate(3));
				// set group_ame
				uList.setGroupName(rs.getString(4));
				// set Email
				uList.setEmail(rs.getString(5));
//				set tel
				uList.setTel(rs.getString(6));
				// set name_level
				uList.setNameLevel(rs.getString(7));
				// set start_date
				uList.setStartDate(rs.getDate(8));
				// set total
				if (rs.getInt(9) != 0) {
					uList.setTotal(rs.getInt(9));
				}
				// add vào list đã khởi tạo
				listU.add(uList);
			}
		} catch (SQLException e) {
			System.out.println("TblUserDAOImplement + getListUser() + " + e.getMessage());
			throw e;
		} finally {
			//đóng kêt nối 
			closeConnection();
		}
		return listU;
	}

	@Override
	/**
	 * tính tổng số record có trong DB
	 */
	public int getTotalRecords(int groupId, String fullName) throws Exception {
		// khai báo rule user = 1
		int user = ConstantUtils.USER_RULE;
		// khởi tạo giá trị của các bản ghi
		int totalRecords = 0;
		// gán giá trị rỗng cho fullName khi trường này có giá trị null
		if (StringUtils.checkNullAndNotType(fullName)) {
			fullName = "";
		}
		// Sử dụng PreparedStatment
		PreparedStatement ps;
		int index = 1;
		// bắt lỗi khi thực thi truy vấn
		try {
			// mở kết nối
			openConnection();
			// sử dụng StringBuilder để nối chuỗi
			StringBuilder sql = new StringBuilder("select count(*) from tbl_user u inner join mst_group on u.group_id = mst_group.group_id where rule = ? and full_name like binary ?");
			// tìm kiếm tất cả các group khi group id = 0
			if (groupId != 0) {
				sql.append(" and u.group_id = ");
				sql.append(groupId);
			}
			// thực thi truy vấn
			ps = conn.prepareStatement(sql.toString());
			// gán giá trị rule
			ps.setInt(index++, user);
			// gán giá trị fullName
			ps.setString(index++, "%" + fullName + "%");
			// Sử dụng ResultSet để lấy giá trị
			ResultSet rs = ps.executeQuery();
			// đếm giá trị của bản ghi
			while (rs.next()) {
				totalRecords = rs.getInt(1);
			}
		} catch (SQLException e) {
			System.out.println("TblUserDAOImplement + getTotalUsers() + " + e.getMessage());
			throw e;
		} finally {
			// đóng kết nối
			closeConnection();
		}
		// trả về giá trị của tất cả các bản ghi
		return totalRecords;
	}

	@Override
	/**
	 * thực hiện thêm mới 1 user vào DB
	 */
	public int insertUser(TblUserEntities tblUserEntities) throws Exception {
		PreparedStatement ps;
		int userId = 0;
		try {
			StringBuilder sql =new StringBuilder();
			sql.append("insert into tbl_user (`group_id`, `login_name`, `password`,\r\n");
			sql.append(" `full_name`, `full_name_kana`, `email`, `tel`, `birthday`, `rule`, `salt`) \r\n");
			sql.append(" values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			ps = conn.prepareStatement(sql.toString());
			//set giá trị cho câu query
			int index = 1;
			ps.setInt(index++, tblUserEntities.getGroupId());
			ps.setString(index++, tblUserEntities.getLoginName());
			ps.setString(index++, tblUserEntities.getPassword());
			ps.setString(index++, tblUserEntities.getFullName());
			ps.setString(index++, tblUserEntities.getFullNameKana());
			ps.setString(index++, tblUserEntities.getEmail());
			ps.setString(index++, tblUserEntities.getTel());
			ps.setDate(index++, tblUserEntities.getBirthday());
			ps.setInt(index++, tblUserEntities.getRule());
			ps.setString(index++, tblUserEntities.getSalt());
			ps.execute();
			//sau khi insert lấy ra userId
			ps = conn.prepareStatement("select user_id from tbl_user where user_id = last_insert_id()");
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				userId = rs.getInt(ConstantUtils.DB_USER_ID);
			}
		} catch (SQLException e) {
			System.out.println("TblUserDAOImplement + insertUser() + " + e.getMessage());
			throw e;
		}
		return userId;
	}

	@Override
	/**
	 * Lấy thông tin của user theo email
	 */
	public TblUserEntities getUserByEmail(int userId, String email) throws Exception {
		TblUserEntities tblUser = new TblUserEntities();
		PreparedStatement ps;
		try {
			openConnection();
			StringBuilder sql = new StringBuilder("select * from tbl_user where email =?");
			if (userId != 0) {
				// Lấy ra user khác userId truyền vào
				sql.append(" and user_id != ");
				sql.append(userId);
			}
			ps = conn.prepareStatement(sql.toString());
			int index = 1;
			ps.setString(index++, email);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				tblUser.setUserId(rs.getInt(1));
				tblUser.setGroupId(rs.getInt(2));
				tblUser.setLoginName(rs.getString(3));
				tblUser.setPassword(rs.getString(4));
				tblUser.setFullName(rs.getString(5));
				tblUser.setFullNameKana(rs.getString(6));
				tblUser.setEmail(rs.getString(7));
				tblUser.setTel(rs.getString(8));
				tblUser.setBirthday(rs.getDate(9));
				tblUser.setRule(rs.getInt(10));
				tblUser.setSalt(rs.getString(11));
			}

		} catch (SQLException e) {
			System.out.println("TblUserDAOImplement + getUserByEmail() + " + e.getMessage());
			throw e;
		} finally {
			closeConnection();
		}
		return tblUser;
	}

	@Override
	/**
	 * Kiểm tra loginName đã tồn tại hay chưa
	 */
	public TblUserEntities getTblUserByLoginName(int userId, String loginName) throws Exception {
		TblUserEntities tblUser = new TblUserEntities();
		PreparedStatement ps;
		try {
			openConnection();
			StringBuilder sql = new StringBuilder("select login_name,user_id from tbl_user where login_name =? ");
			if (userId != 0) {
				sql.append("and user_id = ");
				sql.append(userId);
			}
			ps = conn.prepareStatement(sql.toString());
			int index = 1;
			ps.setString(index++, loginName);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				tblUser.setUserId(rs.getInt(ConstantUtils.DB_USER_ID));
//				tblUser.setGroupId(rs.getInt(2));
				tblUser.setLoginName(rs.getString(ConstantUtils.DB_LOGIN_NAME));
//				tblUser.setPassword(rs.getString("password"));
//				tblUser.setFullName(rs.getString(5));
//				tblUser.setFullNameKana(rs.getString(6));
//				tblUser.setEmail(rs.getString(7));
//				tblUser.setTel(rs.getString(8));
//				tblUser.setBirthday(rs.getDate(9));
//				tblUser.setRule(rs.getInt(10));
//				tblUser.setSalt(rs.getString(11));
			}
		} catch (SQLException e) {
			System.out.println("TblUserDAOImpl + getTblUserByLoginName() + " + e.getMessage());
			throw e;
		} finally {
			closeConnection();
		}
		return tblUser;
	}

	@Override
	/**
	 * Lấy giá trị TblUser theo số điện thoại 
	 */
	public TblUserEntities getUSerByTel(int userId, String tel) throws Exception {
		TblUserEntities tblUserEntities = new TblUserEntities();
		PreparedStatement ps;
		int index = 1;
		try {
			openConnection();
			String sql = "select tel from tbl_user where tel = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(index++, tel);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				tblUserEntities.setTel(rs.getString("tel"));

			}
		} catch (SQLException e) {
			System.out.println("TblUserDAOImplement + getUserByTel() + " + e.getMessage());
			throw e;
		}
		return tblUserEntities;
	}

	@Override
	/**
	 * Lấy UserInfor theo id
	 */
	public UserInforEntities getUserInforByUserId(int userId) throws Exception {
		UserInforEntities userInforEntities = new UserInforEntities();
		PreparedStatement ps;
		try {
			// mở kết nối
			openConnection();
			// khai báo câu sql
			StringBuilder sql = new StringBuilder();
			sql.append("select tdu.detail_user_japan_id,login_name,group_name,u.group_id,full_name,full_name_kana,birthday,email,tel,tdu.code_level,name_level,start_date,end_date,total,password \r\n");
			sql.append("from tbl_user u\r\n" + "inner join mst_group on u.group_id = mst_group.group_id\r\n");
			sql.append("left join tbl_detail_user_japan tdu on u.user_id = tdu.user_id\r\n");
			sql.append("left join mst_japan on tdu.code_level = mst_japan.code_level where u.user_id = ?");
			ps = conn.prepareStatement(sql.toString());
			int index = 1;
			ps.setInt(index++, userId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				userInforEntities.setAccountName(rs.getString(ConstantUtils.DB_LOGIN_NAME));
				userInforEntities.setBirthday(rs.getDate(ConstantUtils.DB_BIRTHDAY));
				userInforEntities.setLsBirthday(CommonUtils.convertToInt(userInforEntities.getBirthday()));
				userInforEntities.setCodeLevel(rs.getString(ConstantUtils.DB_CODE_LEVEL));
				userInforEntities.setEmail(rs.getString(ConstantUtils.DB_EMAIL));
				userInforEntities.setEndDate(rs.getDate(ConstantUtils.DB_END_DATE));
//				userInforEntities.setLsEndDate(CommonUtils.convertToInt(userInforEntities.getEndDate()));
				userInforEntities.setFullName(rs.getString(ConstantUtils.DB_FULLNAME));
				userInforEntities.setGroupId(rs.getInt(ConstantUtils.DB_GROUP_ID));
				userInforEntities.setGroupName(rs.getString(ConstantUtils.DB_GROUP_NAME));
				userInforEntities.setKanaName(rs.getString(ConstantUtils.DB_KANA_NAME));
				userInforEntities.setNameLevel(rs.getString(ConstantUtils.DB_NAME_LEVEL));
				userInforEntities.setStartDate(rs.getDate(ConstantUtils.DB_START_DATE));

				userInforEntities.setTel(rs.getString(ConstantUtils.DB_TEL));
				userInforEntities.setTotal(rs.getInt(ConstantUtils.DB_TOTAL));
				userInforEntities.setUserId(userId);
				userInforEntities.setDetailUserId(rs.getInt(ConstantUtils.DB_DETAIL_ID));
				userInforEntities.setPassword(rs.getString(ConstantUtils.DB_PASSWORD));
				userInforEntities.setPasswordConfirm(userInforEntities.getPassword());
			}

		} catch (SQLException e) {
			System.out.println("TblUserDAOImplement + getUserByUserId() + " + e.getMessage());
			throw e;
		} finally {
			closeConnection();
		}
		return userInforEntities;
	}

	@Override
	/**
	 * Lấy ra loginName từ TblUser theo Id 
	 */
	public TblUserEntities getLoginNameById(int userId) throws Exception {
		TblUserEntities tblUserEntities = new TblUserEntities();
		PreparedStatement ps;
		try {
			openConnection();
			String sql = "select user_id,login_name from tbl_user where user_id = ?";
			ps = conn.prepareStatement(sql);
			int index = 1;
			ps.setInt(index++, userId);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				tblUserEntities.setLoginName(rs.getString(ConstantUtils.DB_LOGIN_NAME));
				tblUserEntities.setUserId(rs.getInt(ConstantUtils.DB_USER_ID));
			}
		} catch (SQLException e) {
			System.out.println("TblUserDAOImpl + checkExistedUserId() + " + e.getMessage());
		} finally {
			closeConnection();
		}
		return tblUserEntities;
	}

	@Override
	public void updateTblUser(TblUserEntities tblUserEntities) throws Exception {
		try {
			StringBuilder sql = new StringBuilder("update tbl_user set ");
			sql.append(ConstantUtils.DB_GROUP_ID + "=?,");
			sql.append(ConstantUtils.DB_FULLNAME + "=?,");
			sql.append(ConstantUtils.DB_KANA_NAME + "=?,");
			sql.append(ConstantUtils.DB_EMAIL + "=?,");
			sql.append(ConstantUtils.DB_TEL + "=?,");
			sql.append(ConstantUtils.DB_BIRTHDAY + "=?");
			sql.append(" where user_id = ?");
			PreparedStatement ps = conn.prepareStatement(sql.toString());
			int index = 1;
			ps.setInt(index++, tblUserEntities.getGroupId());
			ps.setString(index++, tblUserEntities.getFullName());
			ps.setString(index++, tblUserEntities.getFullNameKana());
			ps.setString(index++, tblUserEntities.getEmail());
			ps.setString(index++, tblUserEntities.getTel());
			ps.setDate(index++, tblUserEntities.getBirthday());
			ps.setInt(index++, tblUserEntities.getUserId());
			ps.execute();
		} catch (SQLException e) {
			System.out.println("TblUserDaoImplement + updateTblUser() + " + e.getMessage());
			throw e;
		}
	}

	@Override
	public void deleteTblUser(int userId) throws SQLException {
		try {
			//tạo câu truy vấn 
			String sql = "delete from tbl_user where user_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			int index = 1;
			ps.setInt(index++, userId);
			//thực thi truy vấn
			ps.execute();
		} catch (SQLException e) {
			System.out.println("TblUserDAOImpl + deleteTblUser() + " + e.getMessage());
			//có lỗi sẽ trả về false
			throw e;
		}
	}
	@Override
	/**
	 * Lấy giá trị TblUser theo userId
	 */
	public TblUserEntities getTblUserById(int userId) throws Exception {
		TblUserEntities tblUserEntities = new TblUserEntities();
		try {
			//mở kết nối
			openConnection();
			//tạo câu truy vấn
			String sql = "select user_id,rule from tbl_user where user_id = ? ";
			PreparedStatement ps = conn.prepareStatement(sql);
			int index = 1;
			ps.setInt(index++, userId);
			//thực thi truy vấn
			ResultSet rs = ps.executeQuery();
			//lấy kết quả từ resultset
			while (rs.next()) {
				tblUserEntities.setUserId(rs.getInt(ConstantUtils.DB_USER_ID));
				tblUserEntities.setRule(rs.getInt(ConstantUtils.DB_RULE));
			}
		} catch (SQLException e) {
			System.out.println("TblUserDAOImplement + getTblUserById() + " + e.getMessage());
			throw e;
		} finally {
			closeConnection();
		}
		return tblUserEntities;
	}
}
