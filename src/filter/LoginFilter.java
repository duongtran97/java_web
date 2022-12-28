/**
 * Copyright(C) 2020 Luvina Software
 * LoginFilter.java, 08/04/2020 DuongTV
 */
package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import utils.CommonUtils;
import utils.ConstantUtils;

@WebFilter(servletNames = { "ListUser", "AddUser", "AddUserConf", "ViewDetailUser", "Success",
		"EditUser", "EditConfirm", "DeleteUser" }, initParams = { @WebInitParam(name = "Login", value = "/Login.do") })
public class LoginFilter implements Filter {
	private String urlLogin;

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();
		HttpServletResponse resp = (HttpServletResponse) response;
		try {
			if (CommonUtils.checkLogin(session)) {
				chain.doFilter(request, response);
			} else {
				resp.sendRedirect(req.getContextPath() + this.urlLogin);
			}
		} catch (Exception e) {
			System.out.println("LoginFilter + doFilter() + " + e.getMessage());
			resp.sendRedirect(req.getContextPath() + ConstantUtils.URL_SYSTEM_ERROR + "?type=ER015");
		}
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		urlLogin = filterConfig.getInitParameter("Login");

	}

}
