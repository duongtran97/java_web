/**
 * Copyright(C) 2020  Luvina Software
 *LogInServlet.java, Feb 25, 2020 DuongTV
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

import utils.ConstantUtils;
import validate.UserValidate;

/**
 * class xử lý cho màn hình ADM001
 * @author DuongTV
 */
@WebServlet(urlPatterns = ConstantUtils.URL_LOGIN)
public class LogInController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//view màn hình ADM001
	RequestDispatcher disp = getServletContext().getRequestDispatcher(ConstantUtils.URL_ADM001);
	disp.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//Đăng nhập thành công tiến hành lưu session
		HttpSession session = req.getSession();
		try {
			//lấy giá trị của acount do người dùng nhập
			String loginName = req.getParameter(ConstantUtils.JSP_LOGINNAME);
			//lấy giá trị của password do người dùng nhập
			String password = req.getParameter(ConstantUtils.JSP_PASS);
			// khai baos đối tượng UserValidate
			UserValidate userValidate = new UserValidate();
			// Khai báo list chứa danh sách lỗi
			List<String> listErr = new ArrayList<String>();
			//gọi phương thức validateLogin để lấy ra list lỗi
			listErr = userValidate.validateLogin(loginName, password);
			//kiểm tra nếu khác 0 thì sẽ thông báo lỗi ra ADM001 vùng hiển thị lỗi
			if(listErr == null) {
				session.setAttribute(ConstantUtils.JSP_LOGINNAME, loginName);
				//Khởi tạo đối tượng dispatcher để điều hướng
				resp.sendRedirect(this.getServletContext().getContextPath() + ConstantUtils.URL_LISTUSER);
				
			}else {
				//set đối tượng hiển thị trên trang jsp
				req.setAttribute(ConstantUtils.JSP_ERRORLS, listErr);
				//lưu lại giá trị nhập textbox cho acount
				req.setAttribute(ConstantUtils.JSP_LOGINNAME, loginName);
				//sử dụng forward để gửi lỗi ra ADM001
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(ConstantUtils.URL_ADM001);
				dispatcher.forward(req, resp);
			}
		} catch (Exception e) {
			System.out.println("LoginController + doPost()" +e.getMessage());
			resp.sendRedirect(this.getServletContext().getContextPath()+ConstantUtils.URL_SYSTEM_ERROR);
		}
		
	}

}
