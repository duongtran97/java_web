/**
 * Copyright(C) 2020  Luvina Software
 * ConstantUtils.java, Feb 26, 2020 DuongTV
 */
package utils;


/**
 * Chứa các constant của dự án
 * @author DuongTV
 */
public class ConstantUtils {
	// đường dẫn đến file properties
	public static final String PROPERTIES_MESSAGE_ERR_PATH = "\\message_error_ja.properties";
	public static final String PROPERTIES_MESSAGE_PATH = "\\message_ja.properties";
	public static final String PROPERTIES_DATABASE_PATH = "\\databaseProperties.properties";
	public static final String PROPERTIES_CONFIG_PATH = "\\config.properties";
	//Rule admin user
	public static final int ADMIN = 0;
	public static final int USER_RULE = 1;
	public static final String NAME_DB = DatabasePropertiesUtils.getValueByKey("nameDB");
	// Các trường trong db
	public static final String DB_ERROR = MessageErrorPropertiesUtils.getValueByKey("ER015");
	public static final String DB_LOGIN_NAME = "login_name";
	public static final String DB_PASSWORD = "password";
	public static final String DB_GROUP_ID = "group_id";
	public static final String DB_SALT = "salt";
	public static final String DB_EMAIL = "email";
	public static final String DB_USER_ID = "user_id";
	//Hiển thj trên JSP ADM001
	public static final String JSP_LOGINNAME = "loginName";
	public static final String JSP_PASS = "password";
	public static final String JSP_ERRORLS = "errorList";
	// Hiển thị trên JSP ADM002
	public static final String JSP_FULLNAME = "fullName";
	public static final String JSP_SORT_TYPE = "sortType";
	public static final String JSP_SORTBYFULLNAME = "sortByFullName";
	public static final String JSP_SORTBYCODELEVEL = "sortByCodeLevel";
	public static final String JSP_SORTBYENDDATE = "sortByEndDate";
	public static final String JSP_CURRENTPAGE = "currentPage";
	public static final String JSP_GROUPID = "groupId";
	public static final String JSP_MSG_ERROR = "message";
	public static final String JSP_TYPE = "type";
	public static final String JSP_SUBMIT = "submit";
	public static final String JSP_MSTGROUP = "mstGroup";
	public static final String JSP_LISTPAGE = "listPage";
	public static final String JSP_LISTUS = "listUs";
	public static final String JSP_TOTALPAGE = "totalPage";
	public static final String JSP_CURRENTNEXT = "currentPageNext";
	public static final String JSP_CURRENTBACK = "currentPageBack";
	public static final String JSP_CHECKNEXT = "checkCurrentNext";
	public static final String JSP_CHECKBACK = "checkCurrentBack";
	public static final String JSP_NOTE = "note";
	public static final String JSP_MSG = "mesage";
	// Trường hợp default của màn hình ADM002
	public static final String DEFAULT_SORT = "";
	public static final String DEFAULT_SORTFULLNAME = "asc";
	public static final String DEFAULT_SORTCODELEVEL = "asc";
	public static final String DEFAULT_SORTENDDATE = "desc";
	// vị trí hiện tại default
	public static final int DEFAULT_CURRENT_PAGE = 1;
	// số bản ghi trên một trang
	public static final int RECORDS_OF_PAGE = 5;
	// số bản ghi được hiển thị
	public static final int DEFAULT_LIMIT_PAGES = 3;
	// vị trí offset ban đầu
	public static final int DEFFAULT_OFFSET = ConfigProperties.getValueByKey("offset");
	//giá trị default codeLevel
	public static final String DEFAULT_CODE_LEVEL = "N0";
	// các trường hợp sắp xếp
	public static final String ASCENDING = "asc";
	public static final String DESCENDING = "desc";
	// Truong hop default cua JSP ADM003
	public static final String JSP_ACCOUNTNAME = "accountName";
	public static final String JSP_USER_INF = "userInfor";
	public static final String JSP_MSTJAPAN = "mstJapan";
	public static final String JSP_YEAR_BIRTH = "yearBirthday";
	public static final String JSP_MONTH_BIRTH = "monthBirthday";
	public static final String JSP_DAY_BIRTH = "dayBirthday";
	public static final String JSP_YEAR_START = "yearStartDate";
	public static final String JSP_MONTH_START ="monthStartDate";
	public static final String JSP_DAY_START ="dayStartDate";
	public static final String JSP_YEAR_END = "yearEndDate";
	public static final String JSP_MONTH_END ="monthEndDate";
	public static final String JSP_DAY_END ="dayEndDate";
	//trường hợp mặc định 
	public static final String JSP_YEAR = "year";
	public static final String JSP_MONTH = "month";
	public static final String JSP_DAY = "day";
	public static final String JSP_KANANAME = "kanaName";
	public static final String JSP_PASS_CONF = "passwordConfirm";
	public static final String JSP_LEVELJP = "kyu_id";
	public static final String JSP_TOTAL = "total";
	public static final String JSP_TEL = "tel";
	public static final String JSP_EMAIL = "email";
	public static final String DEFAULT_US_ENT_ACC = "" ;
	public static final String DEFAULT_US_ENT_FULLNAME="";
	public static final String DEFAULT_US_ENT_KANANAME = "";
	public static final String DEFAULT_US_ENT_TEL =  "";
	public static final String DEFAULT_US_ENT_PASS = "";
	public static final String DEFAULT_US_ENT_PASS_CONF = "";
	public static final String DEFAULT_US_ENT_EMAIL = "";
	public static final int DEFAULT_US_ENT_GROUPID = 0;
	public static final int DEFAULT_US_ENT_TOTAL = 0;
	// đường dẫn URL mapping
	public static final String URL_ADM001 = "/VIEW/jsp/ADM001.jsp";
	public static final String URL_ADM002 = "/VIEW/jsp/ADM002.jsp";
	public static final String URL_ADM003 = "/VIEW/jsp/ADM003.jsp";
	public static final String URL_ADM004 = "/VIEW/jsp/ADM004.jsp";
	public static final String URL_ADM005 = "/VIEW/jsp/ADM005.jsp";
	public static final String URL_ADM006 = "/VIEW/jsp/ADM006.jsp";
	public static final String URL_JSP_SYSTEM_ERROR = "/VIEW/jsp/System_Error.jsp";
	public static final String URL_REGISTRY = "/VIEW/jsp/registry.jsp";
	
	public static final String URL_LOGIN = "/Login.do";
	public static final String URL_LISTUSER = "/ListUser.do";
	public static final String URL_LOGOUT = "/Logout.do";
	public static final String URL_ADDUSERINPUT = "/AddUserInput.do";
	public static final String URL_ADDUSERCONF = "/AddUserConf.do";
	public static final String URL_SUCCESS = "/Success.do";
	public static final String URL_ADD_VALIDATE ="/AddUserValidate.do";
	public static final String URL_ADDUSEROK = "/AddUserOK.do";
	public static final String URL_DEL_DETAIL = "/DeleteUser.do";
	public static final String URL_DETAIL_USER = "/ViewDetailUser.do";
	public static final String URL_SYSTEM_ERROR = "/SystemError.do";
	public static final String URL_EDIT_USER = "/EditUserInput.do";
	public static final String URL_EDIT_VALIDATE = "/EditUserValidate.do";
	public static final String URL_EDIT_CONF = "/EditUserConfirm.do";
	public static final String URL_EDIT_OK = "/EditUserOK.do";
	public static final String URL_REGISTRY_USER = "/Registry.do";
	// kết nối db
	public static final String URL = DatabasePropertiesUtils.getValueByKey("url");
	public static final String USER = DatabasePropertiesUtils.getValueByKey("user");
	public static final String PASS = DatabasePropertiesUtils.getValueByKey("password");
	// Lỗi Log in
	public static final String ER001_LOGINNAME = MessageErrorPropertiesUtils.getValueByKey("ER001_LOGIN");
	public static final String ER001_PASS = MessageErrorPropertiesUtils.getValueByKey("ER001_PASS");

	public static final String ER016 = MessageErrorPropertiesUtils.getValueByKey("ER016");
	// lỗi validate màn hình ADM003 khi thêm mới user

	// các message thông báo
	// Đăng ký User thành công
	public static final String MSG001 = MessagePropertiesUtils.getValueByKey("MSG001");
	// Update User thành công
	public static final String MSG002 = MessagePropertiesUtils.getValueByKey("MSG002");
	// Delete User thành công
	public static final String MSG003 = MessagePropertiesUtils.getValueByKey("MSG003");
	// Xác nhận trước khi xóa
	public static final String MSG004 = MessagePropertiesUtils.getValueByKey("MSG004");
	// Không tìm thấy User
	public static final String MSG005 = MessagePropertiesUtils.getValueByKey("MSG005");
	// Lỗi không nhập tên đăng nhập
	public static final String ER001_ACCOUNT = MessageErrorPropertiesUtils.getValueByKey("ER001_ACCOUNT");
	// lỗi số kí tự
	public static final String ER007_ACCOUNT = MessageErrorPropertiesUtils.getValueByKey("ER007_ACCOUNT");
	// Lỗi nhập sai format
	public static final String ER019_ACCOUNT = MessageErrorPropertiesUtils.getValueByKey("ER019_ACCOUNT");
	public static final String FORM_ACCOUNT = "\\w+";
	//Lỗi đã tồn tại account name
	public static final String ER003_ACCOUNT = MessageErrorPropertiesUtils.getValueByKey("ER003_ACCOUNT");
	// Lỗi không chọn groupName
	public static final String ER002_GROUP = MessageErrorPropertiesUtils.getValueByKey("ER002_GROUP");
	// Lỗi groupName không tồn tại
	public static final String ER004_GROUP = MessageErrorPropertiesUtils.getValueByKey("ER004_GROUP");
	// Lỗi không nhập fullName
	public static final String ER001_NAME = MessageErrorPropertiesUtils.getValueByKey("ER001_NAME");
	// Lỗi vượt quá 255 kí tự
	public static final String ER006_NAME = MessageErrorPropertiesUtils.getValueByKey("ER006_NAME");
	// Lỗi nhập kí tự katakana vượt quá 255
	public static final String ER006_KANA = MessageErrorPropertiesUtils.getValueByKey("ER006_KANA");
	// Lỗi nhập sai format: chỉ chứa kí tự katakana
	public static final String ER009_KANA = MessageErrorPropertiesUtils.getValueByKey("ER009_KANA");
	// Lỗi không nhập email
	public static final String ER001_MAIL = MessageErrorPropertiesUtils.getValueByKey("ER001_MAIL");
	// Lỗi vượt quá 100 kí tự
	public static final String ER006_MAIL = MessageErrorPropertiesUtils.getValueByKey("ER006_MAIL");
	// Lỗi nhập sai format
	public static final String ER005_MAIL = MessageErrorPropertiesUtils.getValueByKey("ER005_MAIL");
	// format mail
	public static final String MAIL_FORMAT = "\\w+@\\w+\\.\\w+";
	// Lỗi đã tồn tại
	public static final String ER003_MAIL = MessageErrorPropertiesUtils.getValueByKey("ER003_MAIL");
	// Lỗi không nhập tel
	public static final String ER001_TEL = MessageErrorPropertiesUtils.getValueByKey("ER001_TEL");
	// Lỗi số kí tự
	public static final String ER006_TEL = MessageErrorPropertiesUtils.getValueByKey("ER006_TEL");
	// Lỗi format
	public static final String ER005_TEL = MessageErrorPropertiesUtils.getValueByKey("ER005_TEL");
	// format tel
	public static final String TEL_FORMAT = "[0-9]{4}-[0-9]{4}-[0-9]+";
	//looix ton tai	
	public static final String ER003_TEL = MessageErrorPropertiesUtils.getValueByKey("ER003_TEL");
	// Lỗi kí tự 1 byte
	public static final String ER008_PASS = MessageErrorPropertiesUtils.getValueByKey("ER008_PASS");
	// Lỗi số kí tự
	public static final String ER007_PASS = MessageErrorPropertiesUtils.getValueByKey("ER007_PASS");
	// Nhập không đúng mật khẩu xác nhận
	public static final String ER017_PASS_CONFIRM = MessageErrorPropertiesUtils.getValueByKey("ER017_PASS_CONFIRM");
	// Không nhập password xác nhận
	public static final String ER001_PASSCONF = MessageErrorPropertiesUtils.getValueByKey("ER001_PASSCONF");
	// Chọn trình độ tiếng nhật không tồn tại
	public static final String ER004_JAPAN = MessageErrorPropertiesUtils.getValueByKey("ER004_JAPAN");
	public static final String DB_BIRTHDAY = "birthday" ;
	public static final String DB_CODE_LEVEL = "code_level";
	public static final String DB_END_DATE = "end_date";
	public static final String DB_FULLNAME = "full_name";
	public static final String DB_GROUP_NAME = "group_name";
	public static final String DB_KANA_NAME = "full_name_kana";
	public static final String DB_NAME_LEVEL = "name_level";
	public static final String DB_START_DATE = "start_date";
	public static final String DB_TEL = "tel";
	public static final String DB_TOTAL = "total";
	
	public static final String ER002_TOTAL = MessageErrorPropertiesUtils.getValueByKey("ER002_TOTAL");
	public static final String ER012 = MessageErrorPropertiesUtils.getValueByKey("ER012");
	public static final String ER011_BIRTHDAY = MessageErrorPropertiesUtils.getValueByKey("ER011_BIRTHDAY");
	public static final String ER011_ENDDATE = MessageErrorPropertiesUtils.getValueByKey("ER011_ENDDATE");
	public static final String ER011_STARTDATE = MessageErrorPropertiesUtils.getValueByKey("ER011_STARTDATE");
	public static final String ER006_TOTAL = MessageErrorPropertiesUtils.getValueByKey("ER006_TOTAL");
	public static final String ER018_TOTAL = MessageErrorPropertiesUtils.getValueByKey("ER018_TOTAL");
	public static final String ER021_TOTAL = MessageErrorPropertiesUtils.getValueByKey("ER021_TOTAL");
	public static final int DEFAULT_USER_ID = 0;
	//lỗi biên tập user không tồn tại
	public static final String ER013 = MessageErrorPropertiesUtils.getValueByKey("ER013");
	//hệ thống đang có lỗi
	public static final String ER015 = MessageErrorPropertiesUtils.getValueByKey("ER015");
	//xóa user không tồn tại
	public static final String ER014 = MessageErrorPropertiesUtils.getValueByKey("ER014");
	//không được xóa user admin
	public static final String ER020 = MessageErrorPropertiesUtils.getValueByKey("ER020");
	public static final String DB_DETAIL_ID = "detail_user_japan_id";
	public static final String DB_RULE = "rule";
	public static final String JSP_KEY = "key";
	

	

}
