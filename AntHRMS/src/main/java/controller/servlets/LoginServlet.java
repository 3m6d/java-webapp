package controller.servlets;

import java.io.IOException;



import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.database.DBController;
import model.LoginModel;
import util.StringUtils;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet(urlPatterns = StringUtils.SERVLET_URL_LOGIN, asyncSupported = true)
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final DBController dbController;	
	
	public LoginServlet() {
		this.dbController = new DBController();
	}
	
	/**
	 * Handles HTTP POST requests for user login.
	 *
	 * @param request  The HttpServletRequest object containing login form data.
	 * @param response The HttpServletResponse object for sending responses.
	 * @throws ServletException if a servlet-specific error occurs.
	 * @throws IOException      if an I/O error occurs.
	 */
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
		System.out.println("Servlet hit: LoginServlet");
	    String email = request.getParameter("email");
	    if (email == null || email.isEmpty()) {
	        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
	        response.getWriter().write("Email parameter is missing!");
	        return; // early exit
	    }
	    String password = request.getParameter("password");
	    if (password == null || password.isEmpty()) {
	        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
	        response.getWriter().write("Password parameter is missing!");
	        return; // early exit
	    }
	    System.out.println("Login attempt by: " + email + " with password: " + password);

	    // Create a LoginModel object to hold user credentials
	    LoginModel loginModel = new LoginModel(email, password);
	    int loginResult = dbController.getStudentLoginInfo(loginModel);

	 // Handle login results with appropriate messages and redirects
        if (loginResult == 1) {
            // Login successful
        	HttpSession userSession = request.getSession();
			userSession.setAttribute(StringUtils.USER_EMAIL, email);
			userSession.setMaxInactiveInterval(30*60);
			
			Cookie userCookie= new Cookie(StringUtils.USER, email);
			userCookie.setMaxAge(30*60);
			response.addCookie(userCookie);
			
            request.setAttribute(StringUtils.MESSAGE_SUCCESS, StringUtils.MESSAGE_SUCCESS_LOGIN);
			response.sendRedirect(request.getContextPath() + StringUtils.PAGE_URL_WELCOME);
        } else if (loginResult == 0) {
            // Username or password mismatch
            request.setAttribute(StringUtils.MESSAGE_ERROR, StringUtils.MESSAGE_ERROR_LOGIN);
			request.setAttribute(StringUtils.USER_EMAIL, email);
            request.getRequestDispatcher(StringUtils.PAGE_URL_LOGIN).forward(request, response);
        } else if (loginResult == -1) {
            // Username not found
            request.setAttribute(StringUtils.MESSAGE_ERROR, StringUtils.MESSAGE_ERROR_CREATE_ACCOUNT);
			request.setAttribute(StringUtils.USER_EMAIL, email);
            request.getRequestDispatcher(StringUtils.PAGE_URL_LOGIN).forward(request, response);
        } else {
            // Internal server error
            request.setAttribute(StringUtils.MESSAGE_ERROR, StringUtils.MESSAGE_ERROR_SERVER);
			request.setAttribute(StringUtils.USER_EMAIL, email);
            request.getRequestDispatcher(StringUtils.PAGE_URL_LOGIN).forward(request, response);
        }
	}
}