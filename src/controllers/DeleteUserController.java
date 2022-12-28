/**
 * Copyright(C) 2020  LUvina Software
 * DeleteUserController.java, 09 Apr 2020 DuongTV
 */
package controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logic.TblUserLogic;
import logic.impl.TblDetailUserJapanLogicImpl;
import logic.impl.TblUserLogicImpl;
import utils.CommonUtils;
import utils.ConstantUtils;

/**
 * xử lý chức năng xóa
 * @author DuongTV
 */
@WebServlet(name = "DeleteUser", urlPatterns = ConstantUtils.URL_DEL_DETAIL)
public class DeleteUserController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	/**
	 * xử lý khi click button xóa trên ADM005
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			TblUserLogic tblUserLogicImpl = new TblUserLogicImpl();
			String getValueUserIdFromReq = req.getParameter(ConstantUtils.DB_USER_ID);
			// Kiểm tra có tồn tại user theo id
			int userId = CommonUtils.convertStringToInt(getValueUserIdFromReq, 0);
			int valueOfCheckExistUser = tblUserLogicImpl.checkUserAdmin(userId);
			if (valueOfCheckExistUser == 1) {
				// kiểm tra tồn tại trình độ tiếng nhật
				boolean valueCheckDetailJapan = new TblDetailUserJapanLogicImpl().checkExistDetailJapan(userId);
				// gọi hàm deleteTblUser()
				if (tblUserLogicImpl.deleteTblUSer(userId, valueCheckDetailJapan)) {
					// thành công hiển thị thông báo xóa thành công
					resp.sendRedirect(
							this.getServletContext().getContextPath() + ConstantUtils.URL_SUCCESS + "?type=MSG003");
				} else {
					// không thành công hiện lỗi ER015
					resp.sendRedirect(
							this.getServletContext().getContextPath() + ConstantUtils.URL_SYSTEM_ERROR + "?type=ER015");
				}
				// là user admin hiển thị lỗi ER020
				// không tồn tại user sẽ hiển thị lỗi ER014
			} else if (valueOfCheckExistUser == 0) {
				resp.sendRedirect(
						this.getServletContext().getContextPath() + ConstantUtils.URL_SYSTEM_ERROR + "?type=ER020");
			} else {
				resp.sendRedirect(
						this.getServletContext().getContextPath() + ConstantUtils.URL_SYSTEM_ERROR + "?type=ER014");
			}
		} catch (Exception e) {
			System.out.println("DeleteUserController + doGet() + " + e.getMessage());
			// thông báo lỗi ER015
			resp.sendRedirect(
					this.getServletContext().getContextPath() + ConstantUtils.URL_SYSTEM_ERROR + "?type=ER015");
		}
	}

}
