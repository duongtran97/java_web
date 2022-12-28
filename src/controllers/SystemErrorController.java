/**
 * Copyright(C) 2020  LUvina Software
 * SystemErrorController.java, 12 Mar 2020 DuongTV
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
 * Xử lý các thông báo lỗi hiển thị màn hình System_Error
 * @author DuongTV
 */
@WebServlet(name = "SystemError",urlPatterns = ConstantUtils.URL_SYSTEM_ERROR)
public class SystemErrorController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			String noteER013 = ConstantUtils.ER013;
			String noteER014 = ConstantUtils.ER014;
			String noteER015 = ConstantUtils.ER015;
			String noteER020 = ConstantUtils.ER020;
			String type = req.getParameter(ConstantUtils.JSP_TYPE);
			if("ER013".equals(type)) {
				//Biên tập user không tồn tại
				req.setAttribute(ConstantUtils.JSP_MSG_ERROR, noteER013);
			}else if("ER014".equals(type)) {
				//xóa user không tồn tại
				req.setAttribute(ConstantUtils.JSP_MSG_ERROR, noteER014);
			} else if("ER020".equals(type)) {
				req.setAttribute(ConstantUtils.JSP_MSG_ERROR, noteER020);
			} else  {
				//Hệ thống đang có lỗi 
				req.setAttribute(ConstantUtils.JSP_MSG_ERROR, noteER015);
			}
			// hiển thị màn hình SystemError với thông báo lỗi
			RequestDispatcher disp = getServletContext().getRequestDispatcher(ConstantUtils.URL_JSP_SYSTEM_ERROR);
			disp.forward(req, resp);
		} catch (Exception e) {
			System.out.println("SystemErrorController + doGet() + " + e.getMessage());
		}
		
	}
	
}
