/**
 * Copyright(C) 2020  LUvina Software
 * EditUserInputController.java, 07 Apr 2020 DuongTV
 */
package controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entities.UserInforEntities;
import logic.MstGroupLogic;
import logic.MstJapanLogic;
//import logic.TblDetailUserJapanLogic;
import logic.TblUserLogic;
import logic.impl.MstGroupLogicImpl;
import logic.impl.MstJapanLogicImpl;
//import logic.impl.TblDetailUserJapanLogicImpl;
import logic.impl.TblUserLogicImpl;
import utils.CommonUtils;
import utils.ConstantUtils;
import utils.StringUtils;
import validate.UserValidate;
/**
 * Thực hiện chức năng edit màn hình ADM003
 * @author DuongTV
 */
@WebServlet(name = "EditUser", urlPatterns = { ConstantUtils.URL_EDIT_USER, ConstantUtils.URL_EDIT_VALIDATE })
public class EditUserInputController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	/**
	 * thực hiện khi click button edit trên ADM005
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
			// lấy userId từ req
			String valueUserId = req.getParameter(ConstantUtils.DB_USER_ID);
			int userId = CommonUtils.convertStringToInt(valueUserId, 0);
			// Khai báo TblUserLogic
			TblUserLogic tblUserLogicImpl = new TblUserLogicImpl();
			// Kiểm tra tồn tại id lấy được từ req
			int checkUser = tblUserLogicImpl.checkUserAdmin(userId);
			if (checkUser != -1) {
				// Nếu tồn tại , set giá trị cho các selectbox
				CommonUtils.setDataLogic(req);
				// Lấy giá trị của userInfor
				UserInforEntities userInforEntities = getDefaultValue(req);
				// set lên req
				req.setAttribute(ConstantUtils.JSP_USER_INF, userInforEntities);
				// Chuyển tới view
				RequestDispatcher dispatcher = req.getRequestDispatcher(ConstantUtils.URL_ADM003);
				dispatcher.forward(req, resp);
			} else {
				// Không tồn tại sẽ chuyển tới controller hiển thị mã lỗi ER013
				resp.sendRedirect(
						this.getServletContext().getContextPath() + ConstantUtils.URL_SYSTEM_ERROR + "?type=ER013");
			}
		} catch (Exception e) {
			System.out.println("EditUserInputController + doGet() + " + e.getMessage());
			resp.sendRedirect(
					this.getServletContext().getContextPath() + ConstantUtils.URL_SYSTEM_ERROR + "?type=ER015");
		}
	}

	@Override
	/**
	 * xử lý khi click button xác nhận trên ADM003
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			// Lấy giá trị session hiện thời
			HttpSession session = req.getSession();
			// Lấy giá trị user nhập từ màn hình ADM003
			UserInforEntities userInforEntities = getDefaultValue(req);
			String key = userInforEntities.getSalt();
			//Lấy id từ req
			String valueId = req.getParameter(ConstantUtils.DB_USER_ID);
			int userId = CommonUtils.convertStringToInt(valueId, 0);
			//Kiểm tra tồn tại userId
			int checkUser = new TblUserLogicImpl().checkUserAdmin(userId);
			if(checkUser != -1) {
				// và lấy về list lỗi
				List<String> listError = UserValidate.validateUserInfor(userInforEntities);
				// Kiểm tra list lỗi, nếu không có lỗi
				if (listError.size() == 0) {
					// set thông tin đã nhập của ADM003 lên session
					session.setAttribute(ConstantUtils.JSP_USER_INF + key, userInforEntities);
					// gọi tới controller AddUserConfController
					resp.sendRedirect(
							this.getServletContext().getContextPath() + ConstantUtils.URL_EDIT_CONF + "?key=" + key);
					// ngược lại
				} else {
					// set lại giá trị cho selectbox
					CommonUtils.setDataLogic(req);
					// set giá trị của list lỗi lên req
					req.setAttribute("listError", listError);
					// set giá trị của userInfor lên req
					req.setAttribute(ConstantUtils.JSP_USER_INF, userInforEntities);
					// hiển thị ADM003
					RequestDispatcher disp = getServletContext().getRequestDispatcher(ConstantUtils.URL_ADM003);
					disp.forward(req, resp);
				}
			}else {
				//gọi controller thực hiện hiển thị lỗi ER015
				resp.sendRedirect(
						this.getServletContext().getContextPath() + ConstantUtils.URL_SYSTEM_ERROR + "?type=ER013");
			}
			
		} catch (Exception e) {
			System.out.println("EditUserInputController + doPost() + " + e.getMessage());
			resp.sendRedirect(this.getServletContext().getContextPath() + ConstantUtils.URL_SYSTEM_ERROR + "?type=ER015");
		}
	}

	/**
	 * Lấy giá trị cho UserInfor
	 * @param req sử dụng trong trường hợp back về và validate
	 * @return userInfor
	 * @throws Exception
	 */
	private UserInforEntities getDefaultValue(HttpServletRequest req) throws Exception {
		// chuyen doi UTF-8
		req.setCharacterEncoding("UTF-8");
		UserInforEntities userInforEntities = new UserInforEntities();
		MstGroupLogic mstGroupLogic = new MstGroupLogicImpl();
		MstJapanLogic mstJapanLogic = new MstJapanLogicImpl();
		TblUserLogic tblUserLogicImpl = new TblUserLogicImpl();
//		TblDetailUserJapanLogic tblDetailUserJapanLogicImpl = new TblDetailUserJapanLogicImpl();
		int userId = ConstantUtils.DEFAULT_USER_ID;
		HttpSession session = req.getSession();
		int year = CommonUtils.getYearNow();
		int month = CommonUtils.getMonthNow();
		int day = CommonUtils.getDayNow();
		int total = ConstantUtils.DEFAULT_US_ENT_TOTAL;
		int groupId = ConstantUtils.DEFAULT_US_ENT_GROUPID;
		String groupName = "";
		String codeLevel = "";
		String codeLevelDefault = ConstantUtils.DEFAULT_CODE_LEVEL;
		String acountName = ConstantUtils.DEFAULT_US_ENT_ACC;
		String fullName = ConstantUtils.DEFAULT_US_ENT_FULLNAME;
		String kanaName = ConstantUtils.DEFAULT_US_ENT_KANANAME;
		String email = ConstantUtils.DEFAULT_US_ENT_EMAIL;
		String tel = ConstantUtils.DEFAULT_US_ENT_TEL;
		String password = ConstantUtils.DEFAULT_US_ENT_PASS;
		String passwordConfirm = ConstantUtils.DEFAULT_US_ENT_PASS_CONF;
		String totalPoint = "";
		String type = req.getParameter(ConstantUtils.JSP_TYPE);
		String key = req.getParameter("key");
		//khởi tạo list chứa ngày tháng năm hiện tại 
		List<Integer> lstDateDefault = new ArrayList<Integer>();
		lstDateDefault.add(year);
		lstDateDefault.add(month);
		lstDateDefault.add(day);
		List<Integer> lstBirthday = new ArrayList<Integer>();
		List<Integer> lstStartDate = new ArrayList<Integer>();
		List<Integer> lstEndDate = new ArrayList<Integer>();
		//back sẽ lấy giá trị từ session
		if("back".equals(type)) {
				userInforEntities = (UserInforEntities) session.getAttribute(ConstantUtils.JSP_USER_INF + key);
		}
		// Laays giá trị userInfor từ req
		if ("validate".equals(type)) {
			int yearBirthday = Integer.parseInt(req.getParameter("yearBirthday"));
			int monthBirthday = Integer.parseInt(req.getParameter("monthBirthday"));
			int dayBirthday = Integer.parseInt(req.getParameter("dayBirthday"));
			codeLevel = req.getParameter(ConstantUtils.JSP_LEVELJP);
			acountName = req.getParameter(ConstantUtils.JSP_ACCOUNTNAME);
			fullName = req.getParameter(ConstantUtils.JSP_FULLNAME);
			kanaName = req.getParameter(ConstantUtils.JSP_KANANAME);
			email = req.getParameter(ConstantUtils.JSP_EMAIL);
			tel = req.getParameter(ConstantUtils.JSP_TEL);
			password = req.getParameter(ConstantUtils.JSP_PASS);
			passwordConfirm = req.getParameter(ConstantUtils.JSP_PASS_CONF);
			codeLevel = req.getParameter(ConstantUtils.JSP_LEVELJP);
			groupId = Integer.parseInt(req.getParameter(ConstantUtils.JSP_GROUPID));
			groupName = mstGroupLogic.getGroupName(groupId);
			userId = CommonUtils.convertStringToInt(req.getParameter(ConstantUtils.DB_USER_ID), 0);
			// laays gia tri birthday
			lstBirthday.add(yearBirthday);
			lstBirthday.add(monthBirthday);
			lstBirthday.add(dayBirthday);
			//nếu khác N0 sẽ lấy giá trị của vùng trình độ tiếng nhật
			if (!codeLevelDefault.equals(codeLevel)) {
				int yearStartDate = Integer.parseInt(req.getParameter("yearStartDate"));
				int monthStartDate = Integer.parseInt(req.getParameter("monthStartDate"));
				int dayStartDate = Integer.parseInt(req.getParameter("dayStartDate"));
				int yearEndDate = Integer.parseInt(req.getParameter("yearEndDate"));
				int monthEndDate = Integer.parseInt(req.getParameter("monthEndDate"));
				int dayEndDate = Integer.parseInt(req.getParameter("dayEndDate"));
				totalPoint = req.getParameter(ConstantUtils.JSP_TOTAL);
				totalPoint = totalPoint.trim();
				if (totalPoint != null && !"".equals(totalPoint)) {
					if (StringUtils.checkIsStringNumber(totalPoint)) {
						total = Integer.parseInt(totalPoint);
					}
				}
				// lấy giá trị startDate
				lstStartDate.add(yearStartDate);
				lstStartDate.add(monthStartDate);
				lstStartDate.add(dayStartDate);
				// lấy giá trị endDate
				lstEndDate.add(yearEndDate);
				lstEndDate.add(monthEndDate);
				lstEndDate.add(dayEndDate);
			} else {
				// để giá trị mặc định cho endDate và startDate
				lstStartDate = lstDateDefault;
				lstEndDate = lstDateDefault;
			}
			// Set các giá trị cho các thuộc tính của userInfor
			userInforEntities.setAccountName(acountName);
			userInforEntities.setYear(year);
			userInforEntities.setMonth(month);
			userInforEntities.setDay(day);
			userInforEntities.setGroupId(groupId);
			userInforEntities.setGroupName(groupName);
			userInforEntities.setFullName(fullName);
			userInforEntities.setKanaName(kanaName);
			userInforEntities.setEmail(email);
			userInforEntities.setTel(tel);
			userInforEntities.setPassword(password);
			userInforEntities.setPasswordConfirm(passwordConfirm);
			userInforEntities.setTotal(total);
			userInforEntities.setLsBirthday(lstBirthday);
			userInforEntities.setBirthday(CommonUtils.convertStringToDate(lstBirthday));
			userInforEntities.setCodeLevel(codeLevel);
			userInforEntities.setNameLevel(mstJapanLogic.getNameLevelByCodeLevel(codeLevel));
			userInforEntities.setLsStartDate(lstStartDate);
			userInforEntities.setStartDate(CommonUtils.convertStringToDate(lstStartDate));
			userInforEntities.setLsEndDate(lstEndDate);
			userInforEntities.setEndDate(CommonUtils.convertStringToDate(lstEndDate));
			userInforEntities.setTotal(total);
			userInforEntities.setTotalPoint(totalPoint);
			userInforEntities.setRule(ConstantUtils.USER_RULE);
			userInforEntities.setUserId(userId);
			userInforEntities.setSalt(CommonUtils.createSalt());
		}
		// truong hop default lấy giá trị từ DB
		if ("default".equals(type)) {
			//lấy từ DB
			userId = CommonUtils.convertStringToInt(req.getParameter(ConstantUtils.DB_USER_ID), 0);
			userInforEntities = tblUserLogicImpl.getUserInforByUserId(userId);
			//kiểm tra code level
			codeLevel = userInforEntities.getCodeLevel();
			if (StringUtils.checkNullAndNotType(codeLevel)) {
				//nếu null sẽ gán cho ngày tháng năm hiện tại
				lstEndDate = lstDateDefault;
				lstStartDate = lstDateDefault;
			} else {
				//ngược lại sẽ chuyển kiểu 
				lstEndDate = CommonUtils.convertToInt(userInforEntities.getEndDate());
				lstStartDate = CommonUtils.convertToInt(userInforEntities.getStartDate());

			}
			//set string cho total la totalPoint
			userInforEntities.setTotalPoint(userInforEntities.getTotal()+"");
			//set giá trị cho các list ngày tháng năm
			userInforEntities.setLsEndDate(lstEndDate);
			userInforEntities.setLsStartDate(lstStartDate);
		}
		return userInforEntities;
	}

}
