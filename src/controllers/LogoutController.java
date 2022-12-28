/**
 * Copyright(C) 2020  Luvina Software
 *LogoutController.java, Feb 26, 2020 DuongTV
 */
package controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import utils.ConstantUtils;

/**
 * xử lý Logout của link logout các màn hình
 * @author DuongTV
 */
@WebServlet(urlPatterns = ConstantUtils.URL_LOGOUT)
public class LogoutController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			// khởi tạo session
			HttpSession session = req.getSession();
			//Kiểm tra session khác null
			if (session != null) {
				//nếu khác null sẽ xóa session này
				session.invalidate();
			}
			//gọi controller login.do
			resp.sendRedirect(this.getServletContext().getContextPath() +ConstantUtils.URL_LOGIN);
			
		} catch (Exception e) {
			System.out.println("LogoutController + doget() + "+e.getMessage());
		}
	}

}
