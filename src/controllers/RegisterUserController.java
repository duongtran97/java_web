package controllers;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.impl.TblUserDAOImplement;
import entities.MstGroupEntities;
import entities.TblUserEntities;
import logic.impl.MstGroupLogicImpl;
import utils.CommonUtils;
import utils.ConstantUtils;

/**
 * Servlet implementation class RegisterUserController
 */
@WebServlet(urlPatterns = ConstantUtils.URL_REGISTRY_USER)
public class RegisterUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterUserController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			RequestDispatcher disp = getServletContext().getRequestDispatcher(ConstantUtils.URL_REGISTRY);
			//get mstGroup
			MstGroupLogicImpl mstGroup = new MstGroupLogicImpl();
			List<MstGroupEntities> lstMstGroup = mstGroup.getAllGroup();
			request.setAttribute("lstMstGroup", lstMstGroup);
			disp.forward(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			String passwordConfirm = request.getParameter("password_confirm") ;
			String fullname = request.getParameter("fullname");
			String fullnameKana = request.getParameter("fullname_kana");
			String phone = request.getParameter("phone"); 
			String birthday = request.getParameter("birthday");
			String hash = CommonUtils.createSalt();
			String email = request.getParameter("email");
			String valueOfGroupName = request.getParameter("groupname");
			TblUserEntities tblUser = new TblUserEntities();
			tblUser.setUserId(0);
			tblUser.setTel(phone);
			tblUser.setSalt(hash);
			tblUser.setRule(1);
			tblUser.setPassword(password);
			tblUser.setLoginName(username);
			tblUser.setGroupId(CommonUtils.convertStringToInt(valueOfGroupName, 1));
			tblUser.setFullNameKana(fullnameKana);
			tblUser.setFullName(fullname);
			tblUser.setEmail(email);
			tblUser.setBirthday(CommonUtils.convertStringToDate(birthday));
			TblUserDAOImplement tblUserDao = new TblUserDAOImplement();
			tblUserDao.insertUser(tblUser);
			response.sendRedirect(this.getServletContext().getContextPath() + ConstantUtils.URL_LOGIN);
		} catch (Exception e) {
			System.out.println("LoginController + doPost()" +e.getMessage());
			response.sendRedirect(this.getServletContext().getContextPath()+ConstantUtils.URL_SYSTEM_ERROR);
		}
	}

}
