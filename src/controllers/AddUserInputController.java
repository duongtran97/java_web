/**
 * Copyright(C) 2020  LUvina Software
 * AddUserInputController.java, 12 Mar 2020 DuongTV
 *
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
import logic.impl.MstGroupLogicImpl;
import logic.impl.MstJapanLogicImpl;
import utils.CommonUtils;
import utils.ConstantUtils;
import utils.StringUtils;
import validate.UserValidate;

/**
 * xử lý hiển thị màn hình ADM003
 * @author DuongTV
 */
@WebServlet(name = "AddUser", urlPatterns = { ConstantUtils.URL_ADDUSERINPUT, ConstantUtils.URL_ADD_VALIDATE})
public class AddUserInputController extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	/**
	 * Xử lý khi click vào button Add của ADM002
	 *  req - object that contains the request the client has made of the servlet resp 
	 *  resp - object thatcontains the response the servlet sends to the client
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Khởi tạo session để lấy về session đang tồn tại trên server
		HttpSession session = req.getSession();
		try {
			UserInforEntities userInforEntities = new UserInforEntities();
			// set giá trị cho các selectbox
			CommonUtils.setDataLogic(req);
			// lấy đối tượng UserInf để hiển thị
			userInforEntities = getDefaultValue(req);
			// set lên req để hiển thi ra view
			req.setAttribute(ConstantUtils.JSP_USER_INF, userInforEntities);
			// Gửi dữ liệu ra màn hình ADM003
			RequestDispatcher disp = getServletContext().getRequestDispatcher(ConstantUtils.URL_ADM003);
			disp.forward(req, resp);
		} catch (Exception e) {
			// in log lỗi theo form
			System.out.println("AddUserInputController + doGet() + " + e.getMessage());
			// gửi lên type để xử lý tại controller SystemErrorController
			session.setAttribute(ConstantUtils.JSP_SUBMIT, "error");
			e.printStackTrace();
			// Gọi controller SystemErrorController
			resp.sendRedirect(this.getServletContext().getContextPath() + ConstantUtils.URL_SYSTEM_ERROR);
		}
	}
	@Override
	/**
	 * xử lý validate 
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Lấy giá trị session hiện thời
		HttpSession session = req.getSession();
		try {
			UserInforEntities userInforEntities;
				// Lấy giá trị user nhập từ màn hình ADM003
				userInforEntities = getDefaultValue(req);
				String key = userInforEntities.getSalt();
				// và lấy về list lỗi
				List<String> listError = UserValidate.validateUserInfor(userInforEntities);
				// Kiểm tra list lỗi, nếu không có lỗi
				if (listError.size() == 0) {
					// set thông tin đã nhập của ADM003 lên session
					session.setAttribute(ConstantUtils.JSP_USER_INF + key, userInforEntities);
					// gọi tới controller AddUserConfController
					resp.sendRedirect(this.getServletContext().getContextPath() + ConstantUtils.URL_ADDUSERCONF + "?key="+ key);
					// ngược lại
				} else {
					// set giá trị của list lỗi lên req
					req.setAttribute("listError", listError);
					// set lại giá trị cho selectbox
					CommonUtils.setDataLogic(req);
					// set giá trị của userInfor lên req
					req.setAttribute(ConstantUtils.JSP_USER_INF, userInforEntities);
					// hiển thị ADM003
					RequestDispatcher disp = getServletContext().getRequestDispatcher(ConstantUtils.URL_ADM003);
					disp.forward(req, resp);
				}
		} catch (Exception e) {
			System.out.println("AddUserInputController + doPost() + " + e.getMessage());
			resp.sendRedirect(this.getServletContext().getContextPath() + ConstantUtils.URL_SYSTEM_ERROR + "?type=ER015");
		}
	}
	/**
	 * Get giá trị default cho màn hình ADM003
	 * @param req yêu cầu từ jsp
	 * @return userInfor các trường hợp hiển thị màn hình ADM003
	 * @throws Exception
	 */
	private UserInforEntities getDefaultValue(HttpServletRequest req) throws Exception {
		// chuyen doi UTF-8
		req.setCharacterEncoding("UTF-8");
		UserInforEntities userInforEntities = new UserInforEntities();
		MstGroupLogic mstGroupLogic = new MstGroupLogicImpl();
		MstJapanLogic mstJapanLogic = new MstJapanLogicImpl();
		HttpSession session = req.getSession();
		try {
			// Lấy điều kiện để trả về các giá trị cho ADM003
			String type = req.getParameter(ConstantUtils.JSP_TYPE);
			String key = req.getParameter("key");
			int year = CommonUtils.getYearNow();
			int month = CommonUtils.getMonthNow();
			int day = CommonUtils.getDayNow();
			int total = ConstantUtils.DEFAULT_US_ENT_TOTAL;
			int groupId = ConstantUtils.DEFAULT_US_ENT_GROUPID;
			String groupName = "";
			String codeLevel = ConstantUtils.DB_CODE_LEVEL;
			String codeLevelDefault = ConstantUtils.DB_CODE_LEVEL;
			String acountName = ConstantUtils.DEFAULT_US_ENT_ACC;
			String fullName = ConstantUtils.DEFAULT_US_ENT_FULLNAME;
			String kanaName = ConstantUtils.DEFAULT_US_ENT_KANANAME;
			String email = ConstantUtils.DEFAULT_US_ENT_EMAIL;
			String tel = ConstantUtils.DEFAULT_US_ENT_TEL;
			String password = ConstantUtils.DEFAULT_US_ENT_PASS;
			String passwordConfirm = ConstantUtils.DEFAULT_US_ENT_PASS_CONF;
			String totalPoint = "";
			List<Integer> lstDateDefault = new ArrayList<Integer>();
			lstDateDefault.add(year);
			lstDateDefault.add(month);
			lstDateDefault.add(day);
			int userId = 0;
			List<Integer> lstBirthday;
			List<Integer> lstStartDate;
			List<Integer> lstEndDate ;
			//trường hợp back về lấy giá trị từ session
			if ("back".equals(type)) {
				userInforEntities = (UserInforEntities) session.getAttribute(ConstantUtils.JSP_USER_INF + key);
			} else {
				//khởi tạo list để chưa các giá trị 
				lstBirthday = new ArrayList<Integer>();
				lstEndDate = new ArrayList<Integer>();
				lstStartDate = new ArrayList<Integer>();
				// trường hợp từ màn hình ADM002 di chuyển sang ADM003
				if (type == null) {
					// các trường để trống, ngày tháng năm hiện tại
					lstStartDate = lstDateDefault;
					lstEndDate = lstDateDefault;
					lstBirthday = lstDateDefault;
				}
				// trường hợp validate lấy thông tin trên req
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
					// laays gia tri birthday
					lstBirthday.add(yearBirthday);
					lstBirthday.add(monthBirthday);
					lstBirthday.add(dayBirthday);
					if (!codeLevelDefault.equals(codeLevel)) {
						int yearStartDate = Integer.parseInt(req.getParameter("yearStartDate"));
						int monthStartDate = Integer.parseInt(req.getParameter("monthStartDate"));
						int dayStartDate = Integer.parseInt(req.getParameter("dayStartDate"));
						int yearEndDate = Integer.parseInt(req.getParameter("yearEndDate"));
						int monthEndDate = Integer.parseInt(req.getParameter("monthEndDate"));
						int dayEndDate = Integer.parseInt(req.getParameter("dayEndDate"));
						totalPoint = req.getParameter(ConstantUtils.JSP_TOTAL);
						totalPoint = totalPoint.trim();
						if (!StringUtils.checkNullAndNotType(totalPoint)) {
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

		} catch (NumberFormatException e) {
			System.out.println("AddUserInputController + getDefaultValue() + " + e.getMessage());
			throw e;
		}

		return userInforEntities;

	}
}
