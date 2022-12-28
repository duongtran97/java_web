/**
 * Copyright(C) 2020  LUvina Software
 * SuccessController.java, 12 Mar 2020 DuongTV
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


import utils.ConstantUtils;

/**
 * Xử lý các logic thông báo thành công hiển thị màn hình ADM006
 * @author DuongTV
 */
@WebServlet(name = "Success", urlPatterns = ConstantUtils.URL_SUCCESS)
public class SuccessController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	/**
	 * Xử lý các logic thông báo thành công và lỗi hệ thống
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//đăng ký thành công
		String noteMSG001 = ConstantUtils.MSG001;
		//update thành công
		String noteMSG002 = ConstantUtils.MSG002;
		//delete thành công
		String noteMSG003 = ConstantUtils.MSG003;
//		String MSG004 = ConstantUtils.MSG004;
//		String MSG005 = ConstantUtils.MSG005;
		try {
			
			// Lấy giá trị type từ request
			String type = req.getParameter(ConstantUtils.JSP_TYPE);
			if("MSG001".equals(type)) {
				req.setAttribute(ConstantUtils.JSP_MSG_ERROR, noteMSG001);
			}else if("MSG002".equals(type)) {
				req.setAttribute(ConstantUtils.JSP_MSG_ERROR, noteMSG002);
			}else if("MSG003".equals(type)) {
				req.setAttribute(ConstantUtils.JSP_MSG_ERROR, noteMSG003);
			}
			// hiển thị màn hình ADM006 với thông báo thành công
			RequestDispatcher disp = getServletContext().getRequestDispatcher(ConstantUtils.URL_ADM006);
			disp.forward(req, resp);
		} catch (Exception e) {
			System.out.println("SuccessController + doGet() + " + e.getMessage());
		}
	}
}
