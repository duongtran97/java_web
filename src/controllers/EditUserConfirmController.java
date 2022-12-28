/**
 * Copyright(C) 2020  LUvina Software
 * EditUserConfirmController.java, 09 Apr 2020 DuongTV
 */
package controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entities.UserInforEntities;
import logic.TblUserLogic;
import logic.impl.TblDetailUserJapanLogicImpl;
import logic.impl.TblUserLogicImpl;
import utils.ConstantUtils;

/**
 * xử lý màn hình ADM004 chức năng edit
 * @author DuongTV
 */
@WebServlet(name = "EditConfirm", urlPatterns = { ConstantUtils.URL_EDIT_CONF, ConstantUtils.URL_EDIT_OK })
public class EditUserConfirmController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	/**
	 * xử lý khi click Ok trên ADM003
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			HttpSession session = req.getSession();
			String key = req.getParameter(ConstantUtils.JSP_KEY);
			// lấy giá trị userInfor theo key
			UserInforEntities userInforEntities = (UserInforEntities) session
					.getAttribute(ConstantUtils.JSP_USER_INF + key);
			// set lên req để hiển thị ADM004
			req.setAttribute(ConstantUtils.JSP_USER_INF, userInforEntities);
			RequestDispatcher disp = getServletContext().getRequestDispatcher(ConstantUtils.URL_ADM004);
			disp.forward(req, resp);
		} catch (Exception e) {
			System.out.println("EditUserConfirmController + doGet() + " + e.getMessage());
			resp.sendRedirect(
					this.getServletContext().getContextPath() + ConstantUtils.URL_SYSTEM_ERROR + "?type=ER015");
		}
	}

	@Override
	/**
	 * ghi DB, click Ok màn hình ADM004
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			HttpSession session = req.getSession();
			// khai báo, khởi tạo tblUserLogicImpl
			TblUserLogic tblUserLogicImpl = new TblUserLogicImpl();
			// Lấy dữ liệu userInfor từ session
			String key = req.getParameter("key");
			UserInforEntities userInforEntities = (UserInforEntities) session
					.getAttribute(ConstantUtils.JSP_USER_INF + key);
			// check tồn tại của loginName và email
			int checkUser = tblUserLogicImpl.checkUserAdmin(userInforEntities.getUserId());
			boolean checkLoginName = tblUserLogicImpl.checkExistedLoginName(userInforEntities.getUserId(),
					userInforEntities.getAccountName());
			boolean checkEmail = tblUserLogicImpl.checkExistedEmail(userInforEntities.getUserId(),
					userInforEntities.getEmail());
			// remove userInfor + key
			session.removeAttribute(ConstantUtils.JSP_USER_INF + key);
			// Kiểm tra tồn tại user theo id
			if (checkUser != -1) {
				// kiểm tra login name
				if (!checkLoginName || checkEmail) {
					resp.sendRedirect(
							this.getServletContext().getContextPath() + ConstantUtils.URL_SYSTEM_ERROR + "?type=ER015");
				} else {
					// Kiểm tra tồn tại của vùng trình độ tiếng nhật trong DB
					boolean checkCodeLevel = new TblDetailUserJapanLogicImpl()
							.checkExistDetailJapan(userInforEntities.getUserId());
					// Thực hiện update User
					if (tblUserLogicImpl.updateTblUser(userInforEntities, checkCodeLevel)) {
						// thông báo thành công
						resp.sendRedirect(
								this.getServletContext().getContextPath() + ConstantUtils.URL_SUCCESS + "?type=MSG002");
					} else {
						// thông báo lỗi
						resp.sendRedirect(this.getServletContext().getContextPath() + ConstantUtils.URL_SYSTEM_ERROR
								+ "?type=ER015");
					}
				}

			} else {
				// chuyển tới màn hình lỗi
				resp.sendRedirect(
						this.getServletContext().getContextPath() + ConstantUtils.URL_SYSTEM_ERROR + "?type=ER013");
			}

		} catch (Exception e) {
			System.out.println("EditUserConfirmController + doPost() + " + e.getMessage());
			resp.sendRedirect(
					this.getServletContext().getContextPath() + ConstantUtils.URL_SYSTEM_ERROR + "?type=ER015");
		}
	}

}
