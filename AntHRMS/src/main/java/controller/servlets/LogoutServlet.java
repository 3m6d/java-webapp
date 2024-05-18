package controller.servlets;

import java.io.IOException;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import util.StringUtils;

public class LogoutServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("LogoutServlet doPost called");

        try {
            // Clear existing cookies
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);
                }
                System.out.println("Cookies cleared.");
            }

            // Invalidate user session
            HttpSession session = request.getSession(false);
            if (session != null) {
                System.out.println("Session ID: " + session.getId());
                System.out.println("Session Creation Time: " + new Date(session.getCreationTime()));
                System.out.println("Session Last Accessed Time: " + new Date(session.getLastAccessedTime()));
                
                session.invalidate();
                System.out.println("Session invalidated successfully.");
            } else {
                System.out.println("No session found.");
            }

            // Redirect to login page
            String loginPage = StringUtils.PAGE_URL_LOGIN;
            System.out.println("Redirecting to: " + loginPage);
            response.sendRedirect(request.getContextPath() + loginPage);
        } catch (Exception e) {
            // Handle potential exceptions
            System.out.println("Error in LogoutServlet: " + e.getMessage());
        }
    }
}
