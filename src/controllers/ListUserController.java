/**
 * Copyright(C) 2020  Luvina Software
 *ListUserServlet.java, Feb 25, 2020 DuongTV
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

import entities.MstGroupEntities;
import entities.UserInforEntities;
import logic.MstGroupLogic;
import logic.TblUserLogic;
import logic.impl.MstGroupLogicImpl;
import logic.impl.TblUserLogicImpl;
import utils.CommonUtils;
import utils.ConstantUtils;
import utils.MessagePropertiesUtils;
import utils.StringUtils;

/**
 * class xử lý cho màn hình ADM002
 * @author DuongTV
 */
@WebServlet(name = "ListUser" , urlPatterns = ConstantUtils.URL_LISTUSER)
public class ListUserController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Khởi tạo session để lấy về session đang tồn tại trên server
		HttpSession session = req.getSession();
		try {
			// Kiểm tra phiên làm việc khi chưa đăng nhập, và khi đã logout
//			if (CommonUtils.checkLogin(session)) {
				// dòng thông báo khi không tìm thấy user
				String note = MessagePropertiesUtils.getValueByKey("MSG005");
				// chuyen doi UTF-8
				req.setCharacterEncoding("UTF-8");
				// gọi phương thức lấy dữ liệu trong DB
				MstGroupLogic mstGroupLogicImplement = new MstGroupLogicImpl();
				// Khởi tạo list group để lấy ra các group trong db
				List<MstGroupEntities> mstGrp = mstGroupLogicImplement.getAllGroup();
				// Khởi tạo TblUserDAOImplement để thực thi các phương thức trong nó
				TblUserLogic tblUserLogicImpl = new TblUserLogicImpl();
				// Khởi tạo list user có trong db
				List<UserInforEntities> listUser = new ArrayList<UserInforEntities>();
				// Khởi tạo class TblUserLogicImpl
				TblUserLogic totalUser = new TblUserLogicImpl();
				// Khai báo listPage
				List<Integer> listPage = null;
				// Sắp xếp ưu tiên
				String sortType = ConstantUtils.DEFAULT_SORT;
				// Giá trị full_name asc
				String sortByFullName = ConstantUtils.DEFAULT_SORTFULLNAME;
				// Giá trị name_level asc
				String sortByCodeLevel = ConstantUtils.DEFAULT_SORTCODELEVEL;
				// Giá trị end_date desc
				String sortByEndDate = ConstantUtils.DEFAULT_SORTENDDATE;
				// số bản ghi trên 1 trang
				int limit = ConstantUtils.RECORDS_OF_PAGE;
				// trang hiện tại
				int currentPage = ConstantUtils.DEFAULT_CURRENT_PAGE;
				// vị trí lấy trong DB
				int offset = ConstantUtils.DEFFAULT_OFFSET;
				// khai báo biến kiểu int để lấy groupId
				int groupId = 0;
				// khai báo biến lưu trữ fullName
				String fullName = "";
				// Trang tiếp theo khi click >>
				int currentNext = 0;
				// giá trị kiểm tra xuất hiện link >>
				int checkCurrentNext = 1;
				// giá trị kiểm tra xuất hiện link <<
				int checkCurrentBack = 1;
				// khai báo giá trị của currentPage khi click <<
				int currentPageBack = 0;
				// Khai báo biến lưu trữ tổng số user
				int totalRecords;
				int totalPage;
				// Các biến cache để kiểm tra
				String checkSortType;
				String checkSortByFullName;
				String checkSortByCodeLevel;
				String checkSortByEnDate;
				String checkGroupId;
				String checkCurrentPage;
				// lấy giá trị của action
				String action = req.getParameter(ConstantUtils.JSP_TYPE);
				// TH search lấy điều kiện hiển thị từ req
				if ("search".equals(action)) {
					// lấy giá trị can thiet tuw req
					fullName = req.getParameter(ConstantUtils.JSP_FULLNAME);
					checkGroupId = req.getParameter(ConstantUtils.JSP_GROUPID);
					checkCurrentPage = req.getParameter(ConstantUtils.JSP_CURRENTPAGE);
					checkSortType = req.getParameter(ConstantUtils.JSP_SORT_TYPE);
					checkSortByFullName = req.getParameter(ConstantUtils.JSP_SORTBYFULLNAME);
					checkSortByCodeLevel = req.getParameter(ConstantUtils.JSP_SORTBYCODELEVEL);
					checkSortByEnDate = req.getParameter(ConstantUtils.JSP_SORTBYENDDATE);

					// xử lý group_id khi URL bị thay đổi
					if (StringUtils.checkIsStringNumber(checkGroupId)) {
						int group = Integer.parseInt(checkGroupId);
						if (mstGroupLogicImplement.checkExistGroupId(group)) {
							groupId = group;
						}
					}
					// xử lý currentPage khi URL bị thay đổi
					if (StringUtils.checkIsStringNumber(checkCurrentPage)) {
						currentPage = Integer.parseInt(checkCurrentPage);
					}
					// lấy sortType từ req
					if (checkSortType != null) {
						sortType = checkSortType;
					}
					if (checkSortByFullName != null) {
						sortByFullName = checkSortByFullName;
					}
					if (checkSortByCodeLevel != null) {
						sortByCodeLevel = checkSortByCodeLevel;
					}
					if (checkSortByEnDate != null) {
						sortByEndDate = checkSortByEnDate;
					}
				}
				// trường hợp sort và paging sẽ lấy điều kiện hiển thị từ req
				if ("sort".equals(action)) {
					// lấy giá trị can thiet tuw req
					fullName = req.getParameter(ConstantUtils.JSP_FULLNAME);
					checkGroupId = req.getParameter(ConstantUtils.JSP_GROUPID);
					checkCurrentPage = req.getParameter(ConstantUtils.JSP_CURRENTPAGE);
					checkSortType = req.getParameter(ConstantUtils.JSP_SORT_TYPE);
					checkSortByFullName = req.getParameter(ConstantUtils.JSP_SORTBYFULLNAME);
					checkSortByCodeLevel = req.getParameter(ConstantUtils.JSP_SORTBYCODELEVEL);
					checkSortByEnDate = req.getParameter(ConstantUtils.JSP_SORTBYENDDATE);

					// xử lý group_id khi URL bị thay đổi
					if (StringUtils.checkIsStringNumber(checkGroupId)) {
						int group = Integer.parseInt(checkGroupId);
						if (mstGroupLogicImplement.checkExistGroupId(group)) {
							groupId = group;
						}
					}
					// xử lý currentPage khi URL bị thay đổi
					if (StringUtils.checkIsStringNumber(checkCurrentPage)) {
						currentPage = Integer.parseInt(checkCurrentPage);
					}
					if (checkSortType != null) {
						sortType = checkSortType;
					}
					if (checkSortByFullName != null) {
						sortByFullName = checkSortByFullName;
					}
					if (checkSortByCodeLevel != null) {
						sortByCodeLevel = checkSortByCodeLevel;
					}
					if (checkSortByEnDate != null) {
						sortByEndDate = checkSortByEnDate;
					}
				}
				// trường hợp paging lấy các điều kiện từ request
				if ("paging".equals(action)) {
					// lấy giá trị can thiet tuw req
					fullName = req.getParameter(ConstantUtils.JSP_FULLNAME);
					checkGroupId = req.getParameter(ConstantUtils.JSP_GROUPID);
					checkCurrentPage = req.getParameter(ConstantUtils.JSP_CURRENTPAGE);
					checkSortType = req.getParameter(ConstantUtils.JSP_SORT_TYPE);
					checkSortByFullName = req.getParameter(ConstantUtils.JSP_SORTBYFULLNAME);
					checkSortByCodeLevel = req.getParameter(ConstantUtils.JSP_SORTBYCODELEVEL);
					checkSortByEnDate = req.getParameter(ConstantUtils.JSP_SORTBYENDDATE);
					// xử lý group_id khi URL bị thay đổi
					if (StringUtils.checkIsStringNumber(checkGroupId)) {
						int group = Integer.parseInt(checkGroupId);
						if (mstGroupLogicImplement.checkExistGroupId(group)) {
							groupId = group;
						}
					}
					// xử lý currentPage khi URL bị thay đổi
					if (StringUtils.checkIsStringNumber(checkCurrentPage)) {
						currentPage = Integer.parseInt(checkCurrentPage);
					}
					if (checkSortType != null) {
						sortType = checkSortType;
					}
					if (checkSortByFullName != null) {
						sortByFullName = checkSortByFullName;
					}
					if (checkSortByCodeLevel != null) {
						sortByCodeLevel = checkSortByCodeLevel;
					}
					if (checkSortByEnDate != null) {
						sortByEndDate = checkSortByEnDate;
					}
				}
				// Quay về từ màn hình ADM003 sẽ lấy các giá trị từ session
				if ("back".equals(action)) {
					// Lấy các giá trị để lấy list user từ session
					fullName = (String) session.getAttribute(ConstantUtils.JSP_FULLNAME);
					groupId = (int) session.getAttribute(ConstantUtils.JSP_GROUPID);
					currentPage = (int) session.getAttribute(ConstantUtils.JSP_CURRENTPAGE);
					String sessionSortType = (String) session.getAttribute(ConstantUtils.JSP_SORT_TYPE);
					String sessionSortByFullName = (String) session.getAttribute(ConstantUtils.JSP_SORTBYFULLNAME);
					String sesionSortByCodeLevel = (String) session.getAttribute(ConstantUtils.JSP_SORTBYCODELEVEL);
					String sessionSortByEndDate = (String) session.getAttribute(ConstantUtils.JSP_SORTBYENDDATE);
					if (sessionSortType != null) {
						sortType = sessionSortType;
					}
					if (sessionSortByFullName != null) {
						sortByFullName = sessionSortByFullName;
					}
					if (sesionSortByCodeLevel != null) {
						sortByCodeLevel = sesionSortByCodeLevel;
					}
					if (sessionSortByEndDate != null) {
						sortByEndDate = sessionSortByEndDate;
					}
				}
				// Lấy ra tổng số bản ghi
				totalRecords = totalUser.getTotalRecords(groupId, fullName);
				if (totalRecords != 0) {
					// lấy ra tổng số trang
					totalPage = CommonUtils.getTotalPage(totalRecords, limit);
					if (currentPage > totalPage) {
						// Ở lại page hiện tại
						currentPage = (int) session.getAttribute(ConstantUtils.JSP_CURRENTPAGE);
					}
					// lấy ra vị trí cần lấy
					offset = CommonUtils.getOffset(currentPage, limit);
					// lấy ra danh sách user cho từng trang
					listUser = tblUserLogicImpl.getListUser(offset, limit, groupId, fullName, sortType, sortByFullName,
							sortByCodeLevel, sortByEndDate);
					// lấy ra list paging ứng với current page
					listPage = CommonUtils.getListPaging(totalRecords, ConstantUtils.RECORDS_OF_PAGE, currentPage);
					// Trang tiếp theo khi click >>
					currentNext = CommonUtils.currentPageNext(currentPage);
					// Kiểm tra điều kiện link >>
					checkCurrentNext = CommonUtils.checkCurrentNext(listPage, totalPage);
					// trang khi click <<
					checkCurrentBack = CommonUtils.checkCurrentPageBack(listPage);
					// lấy ra currentPage khi click <<
					currentPageBack = CommonUtils.currentPageBack(currentPage);
					req.setAttribute(ConstantUtils.JSP_TOTALPAGE, totalPage);
					// set paging
					req.setAttribute(ConstantUtils.JSP_CURRENTNEXT, currentNext);
					req.setAttribute(ConstantUtils.JSP_CURRENTBACK, currentPageBack);
					req.setAttribute(ConstantUtils.JSP_CHECKBACK, checkCurrentBack);
					req.setAttribute(ConstantUtils.JSP_CHECKNEXT, checkCurrentNext);
					// gọi phương thức lấy ra list
					req.setAttribute(ConstantUtils.JSP_LISTPAGE, listPage);
					// set currentPage
					req.setAttribute(ConstantUtils.JSP_CURRENTPAGE, currentPage);
					session.setAttribute(ConstantUtils.JSP_CURRENTPAGE, currentPage);
					// set dữ liệu cho request vùng hiển thị kết quả tìm kiếm
					req.setAttribute(ConstantUtils.JSP_LISTUS, listUser);
					// giá trị sort ưu tiên
					req.setAttribute(ConstantUtils.JSP_SORT_TYPE, sortType);
					session.setAttribute(ConstantUtils.JSP_SORT_TYPE, sortType);
					// sort theo fullname
					req.setAttribute(ConstantUtils.JSP_SORTBYFULLNAME, sortByFullName);
					session.setAttribute(ConstantUtils.JSP_SORTBYFULLNAME, sortByFullName);
					// sort theo code_level
					req.setAttribute(ConstantUtils.JSP_SORTBYCODELEVEL, sortByCodeLevel);
					session.setAttribute(ConstantUtils.JSP_SORTBYCODELEVEL, sortByCodeLevel);
					// sort theo end_date
					req.setAttribute(ConstantUtils.JSP_SORTBYENDDATE, sortByEndDate);
					session.setAttribute(ConstantUtils.JSP_SORTBYENDDATE, sortByEndDate);
				} else {
					// Không có sẽ thông báo ra màn hình
					req.setAttribute("note", note);
					// kết thúc if
				}
				// set dữ liệu cho request vùng hiển thị điều kiện tìm kiếm
				req.setAttribute(ConstantUtils.JSP_MSTGROUP, mstGrp);
				// set dữ liệu tìm kiếm fullName lên session
				session.setAttribute(ConstantUtils.JSP_FULLNAME, fullName);
				req.setAttribute(ConstantUtils.JSP_FULLNAME, fullName);
				// set dữ liệu tìm kiếm groupId lên session
				session.setAttribute(ConstantUtils.JSP_GROUPID, groupId);
				req.setAttribute(ConstantUtils.JSP_GROUPID, groupId);
				// chuyển thông tin tới ADM002
				RequestDispatcher disp = getServletContext().getRequestDispatcher(ConstantUtils.URL_ADM002);
				disp.forward(req, resp);
		} catch (Exception e) {
			System.out.println("ListUserController + doGet()" + e.getMessage());
			resp.sendRedirect(this.getServletContext().getContextPath() + ConstantUtils.URL_SYSTEM_ERROR);
		}
		// hết method
	}
//hết class
}
