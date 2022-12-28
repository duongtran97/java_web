/**
 * Copyright(C) 2020  Luvina Software
 * CommonUtils.java, Feb 25, 2020 DuongTV
 */
package utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.sql.Connection;
import java.sql.Date;

import entities.MstGroupEntities;
import entities.MstJapanEntities;
import entities.TblDetailUserJapanEntities;
import entities.TblUserEntities;
import entities.UserInforEntities;
import logic.MstGroupLogic;
import logic.MstJapanLogic;
import logic.TblUserLogic;
import logic.impl.MstGroupLogicImpl;
import logic.impl.MstJapanLogicImpl;
import logic.impl.TblUserLogicImpl;

/**
 * class chứa các hàm common của dự án
 * @author DuongTV
 */
public class CommonUtils {
	/**
	 * Thực hiện set giá trị cho các hạng mục selectbox ở màn hình ADM003
	 * @param req set dữ liệu lên req để hiển thị
	 * @throws Exception
	 */
	public static void setDataLogic(HttpServletRequest req) throws Exception {
		MstGroupLogic mstGroupLogicImpl = new MstGroupLogicImpl();
		MstJapanLogic mstJapanLogicImpl = new MstJapanLogicImpl();
		int yearNow = CommonUtils.getYearNow();
		//giá trị năm tháng ngày cho các select box
		List<Integer> listYear = CommonUtils.getListYear(1900, yearNow);
		List<Integer> listMonth = CommonUtils.getListMonth();
		List<Integer> listDay = CommonUtils.getListDay();
		List<MstGroupEntities> listMstGroupEntities = new ArrayList<MstGroupEntities>();
		List<MstJapanEntities> listMstJapanEntities = new ArrayList<MstJapanEntities>();
		List<Integer> listYearEndDate = CommonUtils.getListYear(1900, yearNow + 1);
		listMstGroupEntities = mstGroupLogicImpl.getAllGroup();
		listMstJapanEntities = mstJapanLogicImpl.getAllJapan();
		req.setAttribute(ConstantUtils.JSP_MSTJAPAN, listMstJapanEntities);
		req.setAttribute(ConstantUtils.JSP_MSTGROUP, listMstGroupEntities);
		req.setAttribute(ConstantUtils.JSP_YEAR_END, listYearEndDate);
		// set chung
		req.setAttribute(ConstantUtils.JSP_YEAR, listYear);
		req.setAttribute(ConstantUtils.JSP_MONTH, listMonth);
		req.setAttribute(ConstantUtils.JSP_DAY, listDay);
	}
	/**
	 * Kieemr tra ngày hợp lệ 
	 * @param lsDate danh dách năm tháng ngày
	 * @return true khi hợp lệ, false khi không hợp lệ 
	 */
	public static boolean checkDate(List<Integer> lsDate) {
		int year = lsDate.get(0);
		int month = lsDate.get(1);
		int day = lsDate.get(2);
		switch (month) {
		case 4:
		case 6:
		case 9:
		case 11:	
			//tháng có 30 ngày
			if(day == 31) {
				return false;
			}
			break;
		case 2:
			//kiểm tra năm nhuận và không nhuận
			if((year%4 == 0 && year%100 != 0) || year%400 == 0 ) {
				if(month == 2) {
					if(day == 30 || day == 31) {
						return false;
					}
				}
			}else {
				if(day == 29 || day == 30 || day ==31) {
					return false;
				}
			}
			break;
		}
		return true;
	}
	/**
	 * Chuyển list chứa ngày tháng năm về Date (sql)
	 * @param lstDate
	 * @return date ngày dạng date
	 */
	public static Date convertStringToDate(List<Integer> lstDate) {
		int year = lstDate.get(0) ;
		int month = lstDate.get(1);
		int day = lstDate.get(2) ;
		StringBuilder d = new StringBuilder();
		d.append(year);
		d.append("-");
		d.append(month);
		d.append("-");
		d.append(day);
		Date date = Date.valueOf(d.toString());
		return date;
	}
	public static Date convertStringToDate(String strDate) throws ParseException {
		
		SimpleDateFormat formatter1=new SimpleDateFormat("yyyy/MM/dd");  
		Date date1=(Date) formatter1.parse(strDate);  
		return date1;
	}
	/**
	 * kiểm tra trường xem có trong danh sách các cột trong db hay ko
	 * @param sortField trường cần kiểm tra
	 * @param conn kết nối tới db
	 * @return true có , false - không
	 * @throws SQLException 
	 */
	public static boolean whiteList(String sortField, Connection conn) throws SQLException {
		boolean valueOfCheck = false;
		ArrayList<String> columnSort = new ArrayList<String>();
		PreparedStatement ps;
		String sql = "select * from tbl_user inner join mst_group on tbl_user.group_id = mst_group.group_id where rule = 1 ";
		try {
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			ResultSetMetaData rsColumn = rs.getMetaData();
			for (int i = 1; i <= rsColumn.getColumnCount(); i++) {
				columnSort.add(rsColumn.getColumnName(i));
			}
			if(sortField != null) {
				for (String string : columnSort) {
					if(string.equals("end_date")){
						valueOfCheck = true;
						break;
					}
				}
				
			}
		} catch (SQLException e) {
			System.out.println("CommonUtils + whiteList()" + e);
		}finally {
			conn.close();
		}
		return valueOfCheck;
	}
	/**
	 * kiểm tra đăng nhập khi chuyển qua các màn hình
	 * @param session lấy các attribute được set lên
	 * @return true đã đăng nhập, false chưa đăng nhập
	 * @throws Exception 
	 */
	public static boolean checkLogin(HttpSession session) throws Exception {
		if(session != null) {
			// Lấy giá trị của loginName từ session
			String loginName = (String) session.getAttribute("loginName");
			// Khai báo đối tượng TblUserDAOImplement
			TblUserLogic tblUserLogic = new TblUserLogicImpl();
			// lấy giá trị của TblUserEntities
			TblUserEntities tblUserEntities = tblUserLogic.getUserByLoginId(loginName);
			// nếu loginName = null , chưa đăng nhập
			if (loginName == null) {
				// trả về false
				return false;
				// hết if
			}
			// nếu loginName không phải là admin
			if (tblUserEntities.getRule() != 0) {
				// trả về false
				return false;
				// hết if
			}
		}else {
			return false;
		}
		// trả về true khi thỏa mãn các điều kiện trên
		return true;
		// hết method
	}
	/**
	 * compareString so sánh password người dùng nhập với giá trị password được lưu
	 * trong database
	 * @param password  giá trị người dùng nhập
	 * @param passwordEncrypted giá trị được lưu trong DB
	 * @return true nếu 2 chuỗi bằng nhau, ngược lại là false
	 */
	public static boolean compareString(String password, String passwordEncrypted) {
		// kiểm tra 2 password
		if (passwordEncrypted.equals(password)) {
			// đúng thì trả về true
			return true;
			// kết thúc if
		}
		// ngược lại sẽ trả về false
		return false;
		// kết thúc hàm
	}

	/**
	 * createSalt tạo Salt cho mỗi user khi sử sử dụng LogIn phòng tránh hacker lấy
	 * được thông tin khi hệ thống bị tấn công nếu thông tin không được mã hóa nên
	 * cần mã hóa dữ liệu nhạy cảm
	 * @param salt salt được tạo mới
	 * @return salt trả về giá trị của salt khi salt được tạo
	 *
	 */
	public static String createSalt() {
		StringBuilder salt = new StringBuilder();
		Random rd = new Random();
		for (int i = 0; i < 20; ) {
			char saltChar = (char) rd.nextInt(123);
			if ((saltChar > 47 && saltChar < 58) || (saltChar < 91 && saltChar > 64)
					|| (saltChar > 96 && saltChar < 123)) {
				salt.append(saltChar + "");
				i++ ;
			}
		}
		return salt.toString();

	}
	/**
	 * encryptPassword phòng tránh lỗi khi phá mật khẩu
	 * @param password
	 * @param salt
	 * @return
	 */
	public static String encryptPassword(String salt, String pass) throws NoSuchAlgorithmException {
		String input = salt + pass;
		MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
		byte[] digest = messageDigest.digest(input.getBytes());
		StringBuffer hexDigest = new StringBuffer();
		for (int i = 0; i < digest.length; i++) {
			hexDigest.append(Integer.toString((digest[i] & 0xff) + 0x100, 16).substring(1));
		}
		input = hexDigest.toString();
		return input;
	}
	/**
	 * encodeHTML mã hóa HTML để phòng tránh lỗi XSS giao diện
	 * @param codeHTML đoạn code HTML
	 * @return codeHTML đoạn code đã mã hóa
	 */
	public static String encodeHTML(String codeHTML) {
		String[] charHTML = { "&", "\"", "\'", "/", "<", ">" };
		String[] replaceHTML = { "&amp;", "&quot;", "&#x27;", "&#x2F;", "&lt;", "&gt;" };
		if (codeHTML != null) {
			for (int i = 0; i < replaceHTML.length; i++) {
				codeHTML = codeHTML.replace(charHTML[i], replaceHTML[i]);
			}
		}
		return codeHTML;

	}
	/**
	 * wildcard hàm xử lý chuỗi tìm kiếm có chưa toán tử wildcard đặc biệt trong sql
	 * @param input
	 * @return input giá trị khi chuyển đổi
	 */
	public static String wildcard(String input) {
		if(input!=null && !"".equals(input)) {
			input = input.replace("\\", "\\\\");
			input = input.replace("%", "\\%");
			input = input.replace("_", "\\_");
		}
		return input;
	}

//	public static void main(String[] args) {
//		new CommonUtils().formatTel("0333135576");
//	}
	/**
	 * tạo chuỗi paging
	 * @param totalUser tổng số User
	 * @param limit số lượng cần hiển thị trên một trang
	 * @param currentPage trang hiện tại
	 * @return List<Integer> danh sách các trang cần hiển thị ở chuỗi paging theo
	 *         trang hiện tại
	 */
	public static List<Integer> getListPaging(int totalUser, int limit, int currentPage) {
		// Khởi tạo ArrayList chứa listPaging
				ArrayList<Integer> listPaging = new ArrayList<>();
				// Tổng số Page cần dùng
				int totalPage = getTotalPage(totalUser, limit);
				// Số Page tối đa trong 1 List Paging
				int pageLimit = ConstantUtils.DEFAULT_LIMIT_PAGES;
				// Nếu là Page đầu trong List Paging thì chia cho số Page tối đa luôn dư 1
//				while (firstPage > 1 && firstPage % pageLimit != 1) {
//					// Giảm dần biến firstPage đến khi thỏa mãn điều kiện kết thúc vòng lặp
//					firstPage--;
//				}
//				// Tính toán trang cuối trong listPaging
//				int lastPage = firstPage + pageLimit - 1;
//				// Nếu trang cuối lớn hơn tổng số trang
//				if (lastPage > totalPage) {
//					// Trang cuối bằng tổng số trang
//					lastPage = totalPage;
//				}
//				for (int i = firstPage; i <= lastPage; i++) {
//					// Thêm trang vào listPaging
//					listPaging.add(i);
//				}
				//cach 3
				int start = (currentPage -1)/pageLimit*pageLimit +1;
				int finish = start + pageLimit - 1;
				if (finish > totalPage ) {
					finish = totalPage;
				}
				for (int i = start; i <= finish; i++) {
					listPaging.add(i);
				}
				//cach 2
//				int pageArea = (int) Math.ceil(currentPage / pageLimit);
//				int pageMin = pageArea*pageLimit + pageLimit -1;
//				int pageMax = pageArea*pageLimit;
//				if(pageMax > totalPage) {
//					pageMax = totalPage;
//				}
//				for (int i = pageMin; i <= pageMax; i++) {
//					listPaging.add(i);
//				}
				// Trả về listPaging
				return listPaging;

	}
	/**
	 * lấy giá trị trang hiện tại khi bấm >>
	 * @param totalPage tổng số trang
	 * @return next trả về giá trị page tiếp theo khi ấn >>
	 */
	public static int currentPageNext(int currentPage) {
		int next = 0;
		if (currentPage % 3 == 1) {
			next = currentPage + 3;
		}
		if (currentPage % 3 == 2) {
			next = currentPage + 2;
		}
		if (currentPage % 3 == 0) {
			next = currentPage + 1;
		}
		return next;
	}
	/**
	 * Kiểm tra list paging xem đã chưa trang cuối cùng hay chưa
	 * @param listPaging
	 * @param totalPage
	 * @return 0 khi chưa có số trang cuối cùng, 1 đã đủ số trang
	 */
	public static int checkCurrentNext(List<Integer> listPaging, int totalPage) {
		if(totalPage == 0) {
			return 1;
		}else {
			for (int i = 0; i < listPaging.size(); i++) {
				if (listPaging.get(i) == totalPage) {
					return 1;
				}
			}
		}
		return 0;
	}
	/**
	 * Tìm vị trí currentPage khi bấm link <<
	 * @param currentPage trang hiện tại khi chưa click link <<
	 * @return back vị trí trang hiện tại
	 */
	public static int currentPageBack(int currentPage) {
		int back = 0;
		if (currentPage % 3 == 1) {
			back = currentPage - 3;
		}
		if (currentPage % 3 == 2) {
			back = currentPage - 4;
		}
		if (currentPage % 3 == 0) {
			back = currentPage - 5;
		}
		return back;
	}

	/**
	 * Kiểm tra danh điều kiện khi bấm link <<
	 * @param listPaging listPaging từ currentPage
	 * @return 0 khi không có trang đầu tiên 
	 */
	public static int checkCurrentPageBack(List<Integer> listPaging) {
		if (listPaging.size() == 0) {
			return 1;
		} else {
			for (int i = 0; i < listPaging.size(); i++) {
				if (listPaging.get(i) == 1) {
					return 1;
				}
			}
		}
		return 0;
	}

	/**
	 * lấy vị trí data cần lấy
	 * @param currentPage trang hiện tại
	 * @param limit       số lượng cần hiển thị trên 1 trang
	 * @return offset vị trí cần lấy
	 */
	public static int getOffset(int currentPage, int limit) {
		// vị trí cần lấy sẽ bằng ( trang hiện tại - 1) nhân với số bản ghi trên một
		// trang
		int offset = (currentPage - 1) * limit;
		// trả về giá trị của vị trí cần lấy các bản ghi
		return offset;
	}
	/**
	 * Lấy số lượng hiển thị bản ghi trên một trang
	 * @return limit số lượng bản ghi hiển thị trên một trang
	 */
	public static int getLimit() {
		int limit = ConstantUtils.RECORDS_OF_PAGE;
		return limit;
	}
	/**
	 * lấy ra tổng số trang ứng với số user
	 * @param totalUser tổng số user
	 * @param limit     số bản ghi trên một page
	 * @return totalPage tổng số trang ứng với số user và số bản ghi trên 1 page
	 */
	public static int getTotalPage(int totalUser, int limit) {
		int totalPage = totalUser / limit;
		if (totalUser % limit != 0) {
			totalPage++;
		}
		return totalPage;
	}
	/**
	 * Lấy ra năm hiện tại
	 * @return nowYear
	 */
	public static int getYearNow() {
		Calendar date = Calendar.getInstance();
		int nowYear = date.get(Calendar.YEAR);
		return nowYear;
	}
	/**
	 * Lấy ra tháng hiện tại
	 * @return nowMonth tháng hiện tại
	 */
	public static int getMonthNow() {
		Calendar date = Calendar.getInstance();
		int nowMonth = date.get(Calendar.MONTH) + 1;
		return nowMonth;
	}
	/**
	 * lấy ra ngày hiện tại
	 * @return nowDay ngày hiện tại
	 */
	public static int getDayNow() {
		Calendar date = Calendar.getInstance();
		int nowDay = date.get(Calendar.DATE);
		return nowDay;
	}
	/**
	 * Lấy danh sách các năm từ năm 1900 -> năm hiện tại +1
	 * @param fromYear
	 * @param toYear
	 * @return lstYear danh sách các năm cần lấy
	 */
	public static List<Integer> getListYear(int fromYear, int toYear) {
		List<Integer> lstYear = new ArrayList<Integer>();
		for (int i = fromYear; i <= toYear + 1; i++) {
			lstYear.add(i);
		}
		return lstYear;
	}
	/**
	 * Lấy danh sách các tháng từ 1 -> 12
	 * @return danh sách các tháng
	 */
	public static List<Integer> getListMonth() {
		List<Integer> listMonth = new ArrayList<Integer>();
		for (int i = 1; i < 13; i++) {
			listMonth.add(i);
		}
		return listMonth;
	}
	/**
	 * Lấy danh sách các ngày từ 1 -> 31
	 * @return listDay danh sách ngày
	 */
	public static List<Integer> getListDay() {
		List<Integer> listDay = new ArrayList<Integer>();
		for (int i = 1; i < 32; i++) {
			listDay.add(i);
		}
		return listDay;
	}
	/**
	 * chuyển date về list số nguyên
	 * @param date 
	 * @return danh sách ngày tháng năm đã được chuyển đổi
	 */
	public static List<Integer> convertToInt(Date date) {
		List<Integer> lstYearMonthDay = new ArrayList<Integer>();
		//khởi tạo chuỗi ngày định dạng yyyy-MM-đ
		SimpleDateFormat form = new SimpleDateFormat("yyyy-MM-dd");
		//chuyển kiểu date về String
		String d = form.format(date);
		//thực hiện chuyển kiểu về int
		String[] arrStr = d.split("-");
		for (int i = 0; i < arrStr.length; i++) {
			lstYearMonthDay.add(convertStringToInt((arrStr[i]),0));
		}
		return lstYearMonthDay;
	}
	/**
	 * chuyển giá trị userID về int
	 * @param strNumber
	 * @param defaultValue
	 * @return userId giá trị userID
	 */
	public static int convertStringToInt(String strNumber,int defaultValue) {
		int value = defaultValue;
		if(StringUtils.checkIsStringNumber(strNumber)) {
			value = Integer.parseInt(strNumber);
		}
		return value;
	}
	/**
	 * Lấy thông tin của bảng tbl_user
	 * @param userInforEntities
	 * @return tblUserEntities
	 * @throws NoSuchAlgorithmException 
	 */
	public static TblUserEntities getTblUser(UserInforEntities userInforEntities) throws NoSuchAlgorithmException {
		TblUserEntities tblUserEntities = new TblUserEntities();
		tblUserEntities.setBirthday(userInforEntities.getBirthday());
		tblUserEntities.setEmail(userInforEntities.getEmail());
		tblUserEntities.setFullName(userInforEntities.getFullName());
		tblUserEntities.setFullNameKana(userInforEntities.getKanaName());
		tblUserEntities.setGroupId(userInforEntities.getGroupId());
		tblUserEntities.setLoginName(userInforEntities.getAccountName());
		tblUserEntities.setRule(userInforEntities.getRule());
		tblUserEntities.setSalt(CommonUtils.createSalt());
		tblUserEntities.setPassword(CommonUtils.encryptPassword(tblUserEntities.getSalt(), userInforEntities.getPassword()));
		tblUserEntities.setTel(userInforEntities.getTel());
		tblUserEntities.setUserId(userInforEntities.getUserId());
		return tblUserEntities;
	}
	/**
	 * Lấy giá trị của bảng tbl_detail_user_japan
	 * @param userInforEntities
	 * @return td giá trị của bảng tbl_detail_user_japan
	 * @throws Exception 
	 */
	public static TblDetailUserJapanEntities getDetailUser(UserInforEntities userInforEntities) throws Exception {
		TblDetailUserJapanEntities td = new TblDetailUserJapanEntities();
		td.setCodeLevel(userInforEntities.getCodeLevel());
		td.setDetailUserJapan(userInforEntities.getDetailUserId());
		td.setEndDate(userInforEntities.getEndDate());
		td.setStartDate(userInforEntities.getStartDate());
		td.setTotal(userInforEntities.getTotal());
		td.setUserId(userInforEntities.getUserId());
		return td;
	}
	// kết thúc class
}
