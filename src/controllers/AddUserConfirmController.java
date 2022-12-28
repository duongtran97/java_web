/**
 * Copyright(C) 2020  LUvina Software
 * AddUserConfirmController.java, 12 Mar 2020 DuongTV
 *
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
import logic.impl.TblUserLogicImpl;
import utils.ConstantUtils;

/**
 * Xử lý các logic của màn hình ADM004
 * 
 * @author DuongTV
 */
@WebServlet(name = "AddUserConf", urlPatterns = { ConstantUtils.URL_ADDUSERCONF, ConstantUtils.URL_ADDUSEROK })
public class AddUserConfirmController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	/**
	 * xử lý khi click vào button Xác nhận của ADM003
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			HttpSession session = req.getSession();
			//lấy key trên session
			String key = req.getParameter("key");
			//lấy theo key giá trị của userInfor
			UserInforEntities userInforEntities = (UserInforEntities) session
					.getAttribute(ConstantUtils.JSP_USER_INF + key);
			//set lại giá trị cho userInfor để view
			req.setAttribute(ConstantUtils.JSP_USER_INF, userInforEntities);
			//hiển thị ADM004
			RequestDispatcher disp = getServletContext().getRequestDispatcher(ConstantUtils.URL_ADM004);
			disp.forward(req, resp);
		} catch (Exception e) {
			System.out.println("AddUserConfirm + doGet() + " + e.getMessage());
			//có lỗi sẽ thông báo mã lỗi ER015
			resp.sendRedirect(
					this.getServletContext().getContextPath() + ConstantUtils.URL_SYSTEM_ERROR + "?type=ER015");
		}
	}

	@Override
	/**
	 * xử lý khi click vào button OK của ADM004
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		try {
			// khai báo, khởi tạo tblUserLogicImpl
			TblUserLogic tblUserLogicImpl = new TblUserLogicImpl();
			// Lấy dữ liệu userInfor từ session
			String key = req.getParameter("key");
			UserInforEntities userInforEntities = (UserInforEntities) session
					.getAttribute(ConstantUtils.JSP_USER_INF + key);
			//xóa userInfor+key trên session
			session.removeAttribute(ConstantUtils.JSP_USER_INF + key);
			// check tồn tại của loginName và email
			boolean checkLoginName = tblUserLogicImpl.checkExistedLoginName(userInforEntities.getUserId(), userInforEntities.getAccountName());
			boolean checkEmail = tblUserLogicImpl.checkExistedEmail(userInforEntities.getUserId(),
					userInforEntities.getEmail());
			// nếu tồn tại
			if (checkEmail || checkLoginName) {
				// chuyển tới màn hình lỗi
				resp.sendRedirect(
						this.getServletContext().getContextPath() + ConstantUtils.URL_SYSTEM_ERROR + "?type=ER015");
				// không tồn tại sẽ thực hiện ghi db
			} else {
				// gọi phương thức createUser
				boolean checkCreateUser = tblUserLogicImpl.createUser(userInforEntities);
				// Kiểm tra giá trị của biến checkCreateUser
				if (checkCreateUser) {
					// đúng sẽ gọi controller SuccessController để hiển thị màn hình ADM006 với
					// thông báo thành công
					resp.sendRedirect(
							this.getServletContext().getContextPath() + ConstantUtils.URL_SUCCESS + "?type=MSG001");
					// ngược lại
				} else {
					// Gọi controller SuccessController để hiển thị màn hình SystemError
					resp.sendRedirect(
							this.getServletContext().getContextPath() + ConstantUtils.URL_SYSTEM_ERROR + "?type=ER015");
					// hết else
				}
			}
			// Khi có lỗi xảy ra sẽ gọi màn hình lỗi
		} catch (Exception e) {
			System.out.println("AddUserConfirmController + doPost() + " + e.getMessage());
			resp.sendRedirect(
					this.getServletContext().getContextPath() + ConstantUtils.URL_SYSTEM_ERROR + "?type=ER015");
		}
		// Hết class
	}
}
