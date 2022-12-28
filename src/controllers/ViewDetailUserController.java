/**
 * Copyright(C) 2020  LUvina Software
 * ViewDetailUser.java, 7 Apr 2020 DuongTV
 */
package controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.UserInforEntities;
import logic.TblUserLogic;
import logic.impl.TblUserLogicImpl;
import utils.CommonUtils;
import utils.ConstantUtils;

/**
 * xử lý view màn hình ADM005
 * @author DuongTV
 */
@WebServlet(name = "ViewDetailUser", urlPatterns = ConstantUtils.URL_DETAIL_USER)
public class ViewDetailUserController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	/**
	 * xử lý khi click id trên màn hình ADM002
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			UserInforEntities userInforEntities = new UserInforEntities();
			// Khai báo khởi tạo đối tượng TblLogic
			TblUserLogic tblUserLogicImpl = new TblUserLogicImpl();
			// Lấy id của user từ request
			String valueUserId = req.getParameter(ConstantUtils.DB_USER_ID);
			// chuyển giá trị string về int
			int userId = CommonUtils.convertStringToInt(valueUserId, ConstantUtils.DEFAULT_USER_ID);
			// kiểm tra tồn tại id
			int checkUser = tblUserLogicImpl.checkUserAdmin(userId);
			if (checkUser != -1) {
				// tồn tại sẽ thực hiện query để lấy ra thông tin của UserInfor
				userInforEntities = tblUserLogicImpl.getUserInforByUserId(userId);
				//set userIf lên req
				req.setAttribute(ConstantUtils.JSP_USER_INF, userInforEntities);
				//view ra màn hình ADM005
				RequestDispatcher disp = getServletContext().getRequestDispatcher(ConstantUtils.URL_ADM005);
				disp.forward(req, resp);
			} else {
				resp.sendRedirect(
						this.getServletContext().getContextPath() + ConstantUtils.URL_SYSTEM_ERROR + "?type=ER013");
			}

		} catch (Exception e) {
			System.out.println("ViewDetailUser + doGet() + " + e.getMessage());
			resp.sendRedirect(
					this.getServletContext().getContextPath() + ConstantUtils.URL_SYSTEM_ERROR + "?type=ER015");
		}
	}
}
